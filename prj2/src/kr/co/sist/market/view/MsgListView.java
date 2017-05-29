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
 * 탭을 가지고 사용자에게 보여주는 Form
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
		super("메세지 확인");
	//////////////////////////////////////보낸 메세지 탭 /////////////////////////////////////////
		String[] columnNames={"번호","받은사람","상품명","메세지 보낸날짜","메세지 확인 여부"};
		String[][] data = {};
		
		jbSClose = new JButton("닫기");
		

		dtmSendMsgList=new DefaultTableModel(data, columnNames){
			//편집불가
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
			
		};
		
		jtSendMsgList=new JTable(dtmSendMsgList){
			//컬럼에 이미지를 넣기위한 method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
		};
		//컬럼을 선택하여 움직이지 못하도록 설정
		jtSendMsgList.getTableHeader().setReorderingAllowed(false);
		//컬럼의 높이 설정
		jtSendMsgList.setRowHeight(100);
		//컬럼의 넓이 설정
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
		//수동배치
		jpSendMsgList.setLayout(null);

	////////////////////////////////////// 받은 메세지 탭 /////////////////////////////////////////
		String[] columnRNames={"번호","보낸사람","상품명","메세지 받은날짜","메세지 확인 여부"};
		String[][] rdata = {};
		
		jbRClose = new JButton("닫기");
		

		dtmReceiveMsgList=new DefaultTableModel(rdata, columnRNames){
			//편집불가
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
			
		};
		
		jtReceiveMsgList=new JTable(dtmReceiveMsgList){
			//컬럼에 이미지를 넣기위한 method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
		};
		//컬럼을 선택하여 움직이지 못하도록 설정
		jtReceiveMsgList.getTableHeader().setReorderingAllowed(false);
		//컬럼의 높이 설정
		jtReceiveMsgList.setRowHeight(100);
		//컬럼의 넓이 설정
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
		//수동배치
		jpReceiveMsgList.setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////////////////
		jtpTab=new JTabbedPane();
		jtpTab.add("보낸메세지", jpSendMsgList);
		jtpTab.addTab("받은메세지", jpReceiveMsgList);
		
		add("Center",jtpTab);

		jpSendMsgList.setBackground(Color.WHITE);
		jpReceiveMsgList.setBackground(Color.WHITE);
		
		//이벤트 추가
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

