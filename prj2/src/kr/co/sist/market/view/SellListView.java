package kr.co.sist.market.view;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.market.evt.SellListViewEvt;

/**
 * 탭을 가지고 사용자에게 보여주는 Form
 * 
 * @author user
 *
 */
@SuppressWarnings("serial")
public class SellListView extends JFrame {
	private JTable jtComplet, jtWait, jtLst;
	private DefaultTableModel dtmComplet, dtmWait, dtmLst;
	private JTabbedPane jtpTab;
	private JButton jbClose, jbWClose, jbLClose;

	public SellListView(int tabNum) {
		super("판매목록");
		////////////////////////////////////// 판매 완료 목록
		////////////////////////////////////// /////////////////////////////////////////
		String[] columnNames = { "번호", "구매자", "제품코드", "제품명", "판매완료일시" };
		String[][] data = {};

		jbClose = new JButton("닫기");

		dtmComplet = new DefaultTableModel(data, columnNames) {
			// 편집불가
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable

		};

		jtComplet = new JTable(dtmComplet) {
			// 컬럼에 이미지를 넣기위한 method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}// getColumnClass

		};
		// 컬럼을 선택하여 움직이지 못하도록 설정
		jtComplet.getTableHeader().setReorderingAllowed(false);
		// 컬럼의 높이 설정
		jtComplet.setRowHeight(40);
		// 컬럼의 넓이 설정
		jtComplet.getColumnModel().getColumn(0).setPreferredWidth(10);
		jtComplet.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtComplet.getColumnModel().getColumn(2).setPreferredWidth(150);
		jtComplet.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtComplet.getColumnModel().getColumn(4).setPreferredWidth(60);

		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcm = jtComplet.getColumnModel();

		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		JScrollPane jspComplet = new JScrollPane(jtComplet);

		jspComplet.setBounds(0, 0, 800, 500);
		jbClose.setBounds(700, 505, 60, 25);

		JPanel jpComplete = new JPanel();
		jpComplete.add(jspComplet);
		jpComplete.add(jbClose);
		// 수동배치
		jpComplete.setLayout(null);

		////////////////////////////////////// 판매 대기 목록
		////////////////////////////////////// /////////////////////////////////////////
		String[] columnWNames = { "번호", "구매신청자", "제품코드", "제품명", "신청일" };
		String[][] wdata = {};

		jbWClose = new JButton("닫기");

		dtmWait = new DefaultTableModel(wdata, columnWNames) {
			// 편집불가
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable

		};

		jtWait = new JTable(dtmWait) {
			// 컬럼에 이미지를 넣기위한 method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}// getColumnClass

		};

		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		dtcr1.setHorizontalAlignment(SwingConstants.CENTER);

		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcm1 = jtWait.getColumnModel();

		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcm1.getColumnCount(); i++) {
			tcm1.getColumn(i).setCellRenderer(dtcr1);
		}
		// 컬럼을 선택하여 움직이지 못하도록 설정
		jtWait.getTableHeader().setReorderingAllowed(false);
		// 컬럼의 높이 설정
		jtWait.setRowHeight(40);
		// 컬럼의 넓이 설정
		jtWait.getColumnModel().getColumn(0).setPreferredWidth(10);
		jtWait.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtWait.getColumnModel().getColumn(2).setPreferredWidth(150);
		jtWait.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtWait.getColumnModel().getColumn(4).setPreferredWidth(60);

		JScrollPane jspWait = new JScrollPane(jtWait);

		jspWait.setBounds(0, 0, 800, 500);
		jbWClose.setBounds(700, 505, 60, 25);

		JPanel jpWait = new JPanel();
		jpWait.add(jspWait);
		jpWait.add(jbWClose);
		// 수동배치
		jpWait.setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////// 판매 물품 목록
		/////////////////////////////////////////////////////////////////////////////////////////////// /////////////////////////////////////
		String[] columnlstNames = { "번호", "제품명", "제품코드", "판매제품설명", "가격", "등록일" };
		String[][] lstdata = {};

		jbLClose = new JButton("닫기");

		dtmLst = new DefaultTableModel(lstdata, columnlstNames) {
			// 편집불가
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable

		};

		jtLst = new JTable(dtmLst) {
			// 컬럼에 이미지를 넣기위한 method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}// getColumnClass

		};
		// 컬럼을 선택하여 움직이지 못하도록 설정
		jtLst.getTableHeader().setReorderingAllowed(false);
		// 컬럼의 높이 설정
		jtLst.setRowHeight(40);
		// 컬럼의 넓이 설정
		jtLst.getColumnModel().getColumn(0).setPreferredWidth(40);
		jtLst.getColumnModel().getColumn(1).setPreferredWidth(130);
		jtLst.getColumnModel().getColumn(2).setPreferredWidth(130);
		jtLst.getColumnModel().getColumn(3).setPreferredWidth(300);
		jtLst.getColumnModel().getColumn(4).setPreferredWidth(70);
		jtLst.getColumnModel().getColumn(5).setPreferredWidth(150);

		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);

		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcm2 = jtLst.getColumnModel();

		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcm2.getColumnCount(); i++) {
			tcm2.getColumn(i).setCellRenderer(dtcr2);
		}

		JScrollPane jspLst = new JScrollPane(jtLst);

		jspLst.setBounds(0, 0, 800, 500);
		jbLClose.setBounds(700, 505, 60, 25);

		JPanel jpLst = new JPanel();
		jpLst.add(jspLst);
		jpLst.add(jbLClose);
		// 수동배치
		jpLst.setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////////////////
		jtpTab = new JTabbedPane();
		jtpTab.addTab("판매완료 목록", jpComplete);
		jtpTab.addTab("판매대기 목록", jpWait);
		jtpTab.addTab("내 판매제품 목록", jpLst);
		jtpTab.setSelectedIndex(tabNum);

		add("Center", jtpTab);

		jpComplete.setBackground(Color.WHITE);
		jpWait.setBackground(Color.WHITE);
		jpLst.setBackground(Color.WHITE);

		// 이벤트 추가
		SellListViewEvt slve = new SellListViewEvt(this);
		jbClose.addActionListener(slve);
		jbWClose.addActionListener(slve);
		jbLClose.addActionListener(slve);
		jtpTab.addMouseListener(slve);
		jtComplet.addMouseListener(slve);
		jtWait.addMouseListener(slve);
		jtLst.addMouseListener(slve);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				// System.exit(0);
			}// windowClosing
		});

		setBounds(50, 50, 800, 600);
		// 가시화
		setVisible(true);
		// 창 크기 고정
		setResizable(false);
	}// MenuForm

	public JTable getJtComplet() {
		return jtComplet;
	}

	public JTable getJtWait() {
		return jtWait;
	}

	public DefaultTableModel getDtmComplet() {
		return dtmComplet;
	}

	public DefaultTableModel getDtmWait() {
		return dtmWait;
	}

	public JTabbedPane getJtpTab() {
		return jtpTab;
	}

	public JButton getJbClose() {
		return jbClose;
	}

	public JButton getJbWClose() {
		return jbWClose;
	}

	public JTable getJtLst() {
		return jtLst;
	}

	public DefaultTableModel getDtmLst() {
		return dtmLst;
	}

	public JButton getJbLClose() {
		return jbLClose;
	}

	public static void main(String[] args) {
		new SellListView(0);
	}// main

}// class
