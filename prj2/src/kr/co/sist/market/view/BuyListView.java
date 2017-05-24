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


/**
 * ���� ������ ����ڿ��� �����ִ� Form
 * @author user
 *
 */
@SuppressWarnings("serial")
public class BuyListView extends JFrame {
	private JTable jtComplet,jtWait ;
	private DefaultTableModel dtmComplet,dtmWait;
	private JTabbedPane jtpTab;
	private JButton jbClose;
	
	public BuyListView(){
		super("���Ÿ��");
		String[] columnNames={"�Ǹ���","��ǰ�ڵ�","��ǰ��","���ſϷ� �Ͻ�"};
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
		jtComplet.setRowHeight(100);
		//�÷��� ���� ����
		jtComplet.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtComplet.getColumnModel().getColumn(1).setPreferredWidth(150);
		jtComplet.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtComplet.getColumnModel().getColumn(3).setPreferredWidth(60);

		
		JScrollPane jspComplet = new JScrollPane(jtComplet);
		
		jspComplet.setBounds(0, 0, 800, 500);
		jbClose.setBounds(700, 505, 60, 25);
		
		JPanel jpComplete = new JPanel();
		jpComplete.add(jspComplet);
		jpComplete.add(jbClose);
		//������ġ
		jpComplete.setLayout(null);

	////////////////////////////////////// ���� �޼��� �� /////////////////////////////////////////
		String[] columnWNames={"�Ǹŵ����","��ǰ�ڵ�","��ǰ��","��û��"};
		String[][] wdata = {};
		
		jbClose = new JButton("�ݱ�");
		

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
		jtWait.setRowHeight(100);
		//�÷��� ���� ����
		jtWait.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtWait.getColumnModel().getColumn(1).setPreferredWidth(150);
		jtWait.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtWait.getColumnModel().getColumn(3).setPreferredWidth(60);

		
		JScrollPane jspWait = new JScrollPane(jtWait);
		
		jspWait.setBounds(0, 0, 800, 500);
		jbClose.setBounds(700, 505, 60, 25);
		
		JPanel jpWait = new JPanel();
		jpWait.add(jspWait);
		jpWait.add(jbClose);
		//������ġ
		jpWait.setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////////////////
		jtpTab=new JTabbedPane();
		jtpTab.add("���ſϷ� ���", jpComplete);
		jtpTab.addTab("���Ŵ�� ���", jpWait);
		
		add("Center",jtpTab);

		jpComplete.setBackground(Color.WHITE);
		jpWait.setBackground(Color.WHITE);
		
		addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}//windowClosing
			
		});
		
		setBounds(10,10,800,600);
		setVisible(true);
	}//MenuForm


	public static void main(String[] args) {
		new BuyListView();
	}//main
	
}//class

