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
import javax.swing.table.DefaultTableModel;

import kr.co.sist.market.evt.SellListViewEvt;

/**
 * ���� ������ ����ڿ��� �����ִ� Form
 * @author user
 *
 */
@SuppressWarnings("serial")
public class SellListView extends JFrame {
	private JTable jtComplet,jtWait ;
	private DefaultTableModel dtmComplet,dtmWait;
	private JTabbedPane jtpTab;
	private JButton jbClose,jbWClose;
	
	public SellListView(){
		super("�ǸŸ��");
		String[] columnNames={"��ȣ","������","��ǰ�ڵ�","��ǰ��","�ǸſϷ��Ͻ�"};
		String[][] data = {};
		
		jbClose = new JButton("�ݱ�");
		

		dtmComplet=new DefaultTableModel(data, columnNames){
			//�����Ұ�
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
			
		};
		
		jtComplet=new JTable(dtmComplet){
			//�÷��� �̹����� �ֱ����� method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
		};
		//�÷��� �����Ͽ� �������� ���ϵ��� ����
		jtComplet.getTableHeader().setReorderingAllowed(false);
		//�÷��� ���� ����
		jtComplet.setRowHeight(40);
		//�÷��� ���� ����
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
		//������ġ
		jpComplete.setLayout(null);

	////////////////////////////////////// ���� �޼��� �� /////////////////////////////////////////
		String[] columnWNames={"��ȣ","���Ž�û��","��ǰ�ڵ�","��ǰ��","��û��"};
		String[][] wdata = {};
		
		jbWClose = new JButton("�ݱ�");
		

		dtmWait=new DefaultTableModel(wdata, columnWNames){
			//�����Ұ�
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
			
		};
		
		jtWait=new JTable(dtmWait){
			//�÷��� �̹����� �ֱ����� method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
		};
		//�÷��� �����Ͽ� �������� ���ϵ��� ����
		jtWait.getTableHeader().setReorderingAllowed(false);
		//�÷��� ���� ����
		jtWait.setRowHeight(40);
		//�÷��� ���� ����
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
		//������ġ
		jpWait.setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////////////////
		jtpTab=new JTabbedPane();
		jtpTab.add("�ǸſϷ� ���", jpComplete);
		jtpTab.addTab("�ǸŴ�� ���", jpWait);
		
		
		
		add("Center",jtpTab);

		jpComplete.setBackground(Color.WHITE);
		jpWait.setBackground(Color.WHITE);
		
		//�̺�Ʈ �߰�
		SellListViewEvt slve = new SellListViewEvt(this);
		jbClose.addActionListener(slve);
		jbWClose.addActionListener(slve);
		jtpTab.addMouseListener(slve);
		jtComplet.addMouseListener(slve);
		jtWait.addMouseListener(slve);
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}//windowClosing
		});
		
		setBounds(50,50,800,600);
		setVisible(true);
	}//MenuForm


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


	public static void main(String[] args) {
		new SellListView();
	}//main
	
}//class

