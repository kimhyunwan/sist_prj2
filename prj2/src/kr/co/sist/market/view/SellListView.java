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
 * ���� ������ ����ڿ��� �����ִ� Form
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
		super("�ǸŸ��");
		////////////////////////////////////// �Ǹ� �Ϸ� ���
		////////////////////////////////////// /////////////////////////////////////////
		String[] columnNames = { "��ȣ", "������", "��ǰ�ڵ�", "��ǰ��", "�ǸſϷ��Ͻ�" };
		String[][] data = {};

		jbClose = new JButton("�ݱ�");

		dtmComplet = new DefaultTableModel(data, columnNames) {
			// �����Ұ�
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable

		};

		jtComplet = new JTable(dtmComplet) {
			// �÷��� �̹����� �ֱ����� method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}// getColumnClass

		};
		// �÷��� �����Ͽ� �������� ���ϵ��� ����
		jtComplet.getTableHeader().setReorderingAllowed(false);
		// �÷��� ���� ����
		jtComplet.setRowHeight(40);
		// �÷��� ���� ����
		jtComplet.getColumnModel().getColumn(0).setPreferredWidth(10);
		jtComplet.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtComplet.getColumnModel().getColumn(2).setPreferredWidth(150);
		jtComplet.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtComplet.getColumnModel().getColumn(4).setPreferredWidth(60);

		// DefaultTableCellHeaderRenderer ���� (��� ������ ����)
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer�� ������ ��� ���ķ� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		// ������ ���̺��� ColumnModel�� ������
		TableColumnModel tcm = jtComplet.getColumnModel();

		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		JScrollPane jspComplet = new JScrollPane(jtComplet);

		jspComplet.setBounds(0, 0, 800, 500);
		jbClose.setBounds(700, 505, 60, 25);

		JPanel jpComplete = new JPanel();
		jpComplete.add(jspComplet);
		jpComplete.add(jbClose);
		// ������ġ
		jpComplete.setLayout(null);

		////////////////////////////////////// �Ǹ� ��� ���
		////////////////////////////////////// /////////////////////////////////////////
		String[] columnWNames = { "��ȣ", "���Ž�û��", "��ǰ�ڵ�", "��ǰ��", "��û��" };
		String[][] wdata = {};

		jbWClose = new JButton("�ݱ�");

		dtmWait = new DefaultTableModel(wdata, columnWNames) {
			// �����Ұ�
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable

		};

		jtWait = new JTable(dtmWait) {
			// �÷��� �̹����� �ֱ����� method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}// getColumnClass

		};

		// DefaultTableCellHeaderRenderer ���� (��� ������ ����)
		DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer�� ������ ��� ���ķ� ����
		dtcr1.setHorizontalAlignment(SwingConstants.CENTER);

		// ������ ���̺��� ColumnModel�� ������
		TableColumnModel tcm1 = jtWait.getColumnModel();

		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int i = 0; i < tcm1.getColumnCount(); i++) {
			tcm1.getColumn(i).setCellRenderer(dtcr1);
		}
		// �÷��� �����Ͽ� �������� ���ϵ��� ����
		jtWait.getTableHeader().setReorderingAllowed(false);
		// �÷��� ���� ����
		jtWait.setRowHeight(40);
		// �÷��� ���� ����
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
		// ������ġ
		jpWait.setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////// �Ǹ� ��ǰ ���
		/////////////////////////////////////////////////////////////////////////////////////////////// /////////////////////////////////////
		String[] columnlstNames = { "��ȣ", "��ǰ��", "��ǰ�ڵ�", "�Ǹ���ǰ����", "����", "�����" };
		String[][] lstdata = {};

		jbLClose = new JButton("�ݱ�");

		dtmLst = new DefaultTableModel(lstdata, columnlstNames) {
			// �����Ұ�
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}// isCellEditable

		};

		jtLst = new JTable(dtmLst) {
			// �÷��� �̹����� �ֱ����� method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}// getColumnClass

		};
		// �÷��� �����Ͽ� �������� ���ϵ��� ����
		jtLst.getTableHeader().setReorderingAllowed(false);
		// �÷��� ���� ����
		jtLst.setRowHeight(40);
		// �÷��� ���� ����
		jtLst.getColumnModel().getColumn(0).setPreferredWidth(40);
		jtLst.getColumnModel().getColumn(1).setPreferredWidth(130);
		jtLst.getColumnModel().getColumn(2).setPreferredWidth(130);
		jtLst.getColumnModel().getColumn(3).setPreferredWidth(300);
		jtLst.getColumnModel().getColumn(4).setPreferredWidth(70);
		jtLst.getColumnModel().getColumn(5).setPreferredWidth(150);

		// DefaultTableCellHeaderRenderer ���� (��� ������ ����)
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer�� ������ ��� ���ķ� ����
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);

		// ������ ���̺��� ColumnModel�� ������
		TableColumnModel tcm2 = jtLst.getColumnModel();

		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int i = 0; i < tcm2.getColumnCount(); i++) {
			tcm2.getColumn(i).setCellRenderer(dtcr2);
		}

		JScrollPane jspLst = new JScrollPane(jtLst);

		jspLst.setBounds(0, 0, 800, 500);
		jbLClose.setBounds(700, 505, 60, 25);

		JPanel jpLst = new JPanel();
		jpLst.add(jspLst);
		jpLst.add(jbLClose);
		// ������ġ
		jpLst.setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////////////////
		jtpTab = new JTabbedPane();
		jtpTab.addTab("�ǸſϷ� ���", jpComplete);
		jtpTab.addTab("�ǸŴ�� ���", jpWait);
		jtpTab.addTab("�� �Ǹ���ǰ ���", jpLst);
		jtpTab.setSelectedIndex(tabNum);

		add("Center", jtpTab);

		jpComplete.setBackground(Color.WHITE);
		jpWait.setBackground(Color.WHITE);
		jpLst.setBackground(Color.WHITE);

		// �̺�Ʈ �߰�
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
		// ����ȭ
		setVisible(true);
		// â ũ�� ����
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
