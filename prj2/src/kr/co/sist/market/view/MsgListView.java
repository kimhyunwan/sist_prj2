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

import kr.co.sist.market.evt.MsgListViewEvt;

/**
 * ���� ������ ����ڿ��� �����ִ� Form
 * @author user
 *
 */
@SuppressWarnings("serial")
public class MsgListView extends JFrame {
	private JTable jtSendMsgList,jtReceiveMsgList    ;
	private DefaultTableModel dtmSendMsgList,dtmReceiveMsgList;
	private JTabbedPane jtpTab;
	private JButton jbSClose,jbRClose;
	
	public MsgListView(){
		super("�޼��� Ȯ��");
	//////////////////////////////////////���� �޼��� �� /////////////////////////////////////////
		String[] columnNames={"��ȣ","�������","��ǰ��","�޼��� ������¥","�޼��� Ȯ�� ����"};
		String[][] data = {};
		
		jbSClose = new JButton("�ݱ�");
		

		dtmSendMsgList=new DefaultTableModel(data, columnNames){
			//�����Ұ�
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
			
		};
		
		jtSendMsgList=new JTable(dtmSendMsgList){
			//�÷��� �̹����� �ֱ����� method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
		};
		//�÷��� �����Ͽ� �������� ���ϵ��� ����
		jtSendMsgList.getTableHeader().setReorderingAllowed(false);
		//�÷��� ���� ����
		jtSendMsgList.setRowHeight(100);
		//�÷��� ���� ����
		jtSendMsgList.getColumnModel().getColumn(0).setPreferredWidth(15);
		jtSendMsgList.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtSendMsgList.getColumnModel().getColumn(2).setPreferredWidth(150);
		jtSendMsgList.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtSendMsgList.getColumnModel().getColumn(4).setPreferredWidth(60);

		
		JScrollPane jspSendMsgList = new JScrollPane(jtSendMsgList);
		
		jspSendMsgList.setBounds(0, 0, 800, 500);
		jbSClose.setBounds(700, 505, 60, 25);
		
		JPanel jpSendMsgList = new JPanel();
		jpSendMsgList.add(jspSendMsgList);
		jpSendMsgList.add(jbSClose);
		//������ġ
		jpSendMsgList.setLayout(null);

	////////////////////////////////////// ���� �޼��� �� /////////////////////////////////////////
		String[] columnRNames={"��ȣ","�������","��ǰ��","�޼��� ������¥","�޼��� Ȯ�� ����"};
		String[][] rdata = {};
		
		jbRClose = new JButton("�ݱ�");
		

		dtmReceiveMsgList=new DefaultTableModel(rdata, columnRNames){
			//�����Ұ�
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
			
		};
		
		jtReceiveMsgList=new JTable(dtmReceiveMsgList){
			//�÷��� �̹����� �ֱ����� method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
		};
		//�÷��� �����Ͽ� �������� ���ϵ��� ����
		jtReceiveMsgList.getTableHeader().setReorderingAllowed(false);
		//�÷��� ���� ����
		jtReceiveMsgList.setRowHeight(100);
		//�÷��� ���� ����
		jtReceiveMsgList.getColumnModel().getColumn(0).setPreferredWidth(15);
		jtReceiveMsgList.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtReceiveMsgList.getColumnModel().getColumn(2).setPreferredWidth(150);
		jtReceiveMsgList.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtReceiveMsgList.getColumnModel().getColumn(3).setPreferredWidth(60);

		
		JScrollPane jspReceiveMsgList = new JScrollPane(jtReceiveMsgList);
		
		jspReceiveMsgList.setBounds(0, 0, 800, 500);
		jbRClose.setBounds(700, 505, 60, 25);
		
		JPanel jpReceiveMsgList = new JPanel();
		jpReceiveMsgList.add(jspReceiveMsgList);
		jpReceiveMsgList.add(jbRClose);
		//������ġ
		jpReceiveMsgList.setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////////////////
		jtpTab=new JTabbedPane();
		jtpTab.add("�����޼���", jpSendMsgList);
		jtpTab.addTab("�����޼���", jpReceiveMsgList);
		
		add("Center",jtpTab);

		jpSendMsgList.setBackground(Color.WHITE);
		jpReceiveMsgList.setBackground(Color.WHITE);
		
		//�̺�Ʈ �߰�
		MsgListViewEvt mlve = new MsgListViewEvt(this);
		jbSClose.addActionListener(mlve);
		jbRClose.addActionListener(mlve);
		jtpTab.addMouseListener(mlve);
		jtSendMsgList.addMouseListener(mlve);
		jtReceiveMsgList.addMouseListener(mlve);
		
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


	public JTable getJtSendMsgList() {
		return jtSendMsgList;
	}


	public JTable getJtReceiveMsgList() {
		return jtReceiveMsgList;
	}


	public DefaultTableModel getDtmSendMsgList() {
		return dtmSendMsgList;
	}


	public DefaultTableModel getDtmReceiveMsgList() {
		return dtmReceiveMsgList;
	}


	public JTabbedPane getJtpTab() {
		return jtpTab;
	}

	public JButton getJbSClose() {
		return jbSClose;
	}


	public JButton getJbRClose() {
		return jbRClose;
	}


	public static void main(String[] args) {
		new MsgListView();
	}//main
	
}//class

