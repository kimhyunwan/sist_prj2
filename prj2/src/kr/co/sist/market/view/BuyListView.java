
package kr.co.sist.market.view;

import java.awt.Color;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTabbedPane;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.co.sist.market.evt.BuyListViewEvt;

/**
 * 
 * 탭을 가지고 사용자에게 보여주는 Form.
 * 
 * @author user
 *
 * 
 * 
 */

@SuppressWarnings("serial")

public class BuyListView extends JFrame {

	private JTable jtComplet, jtWait;

	private DefaultTableModel dtmComplet, dtmWait;

	private JTabbedPane jtpTab;

	private JButton jbClose, jbWClose;

	public BuyListView(int tabNum) {

		super("구매목록");

		String[] columnNames = { "번호", "판매자", "제품코드", "제품명", "구매완료일시" };

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
		JScrollPane jspComplet = new JScrollPane(jtComplet);
		jspComplet.setBounds(0, 0, 800, 500);
		jbClose.setBounds(700, 505, 60, 25);
		JPanel jpComplete = new JPanel();
		jpComplete.add(jspComplet);
		jpComplete.add(jbClose);

		// 수동배치

		jpComplete.setLayout(null);

		////////////////////////////////////// 구매대기 목록 탭
		////////////////////////////////////// /////////////////////////////////////////

		String[] columnWNames = { "번호", "판매등록자", "제품코드", "제품명", "신청일" };

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

		jtpTab = new JTabbedPane();

		jtpTab.add("구매완료 목록", jpComplete);

		jtpTab.addTab("구매대기 목록", jpWait);

		jtpTab.setSelectedIndex(tabNum);

		add("Center", jtpTab);

		jpComplete.setBackground(Color.WHITE);

		jpWait.setBackground(Color.WHITE);

		// 이벤트 추가

		BuyListViewEvt blve = new BuyListViewEvt(this);

		jbClose.addActionListener(blve);

		jbWClose.addActionListener(blve);

		jtWait.addMouseListener(blve);

		addWindowListener(new WindowAdapter() {

			@Override

			public void windowClosing(WindowEvent e) {

				dispose();

			}// windowClosing

		});

		setBounds(100, 100, 800, 600);

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

}// class
