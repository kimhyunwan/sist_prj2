
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
 * ���� ������ ����ڿ��� �����ִ� Form.
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

		super("���Ÿ��");

		String[] columnNames = { "��ȣ", "�Ǹ���", "��ǰ�ڵ�", "��ǰ��", "���ſϷ��Ͻ�" };

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
		JScrollPane jspComplet = new JScrollPane(jtComplet);
		jspComplet.setBounds(0, 0, 800, 500);
		jbClose.setBounds(700, 505, 60, 25);
		JPanel jpComplete = new JPanel();
		jpComplete.add(jspComplet);
		jpComplete.add(jbClose);

		// ������ġ

		jpComplete.setLayout(null);

		////////////////////////////////////// ���Ŵ�� ��� ��
		////////////////////////////////////// /////////////////////////////////////////

		String[] columnWNames = { "��ȣ", "�Ǹŵ����", "��ǰ�ڵ�", "��ǰ��", "��û��" };

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

		jtpTab = new JTabbedPane();

		jtpTab.add("���ſϷ� ���", jpComplete);

		jtpTab.addTab("���Ŵ�� ���", jpWait);

		jtpTab.setSelectedIndex(tabNum);

		add("Center", jtpTab);

		jpComplete.setBackground(Color.WHITE);

		jpWait.setBackground(Color.WHITE);

		// �̺�Ʈ �߰�

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

}// class
