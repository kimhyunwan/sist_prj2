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
 * 탭을 가지고 사용자에게 보여주는 Form
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
		super("구매목록");
		String[] columnNames={"판매자","상품코드","상품명","구매완료 일시"};
		String[][] data = {};
		
		jbClose = new JButton("닫기");
		

		dtmComplet=new DefaultTableModel(data, columnNames){
			//편집불가
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
			
		};
		
		jtComplet=new JTable(dtmComplet){
			//컬럼에 이미지를 넣기위한 method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
		};
		//컬럼을 선택하여 움직이지 못하도록 설정
		jtComplet.getTableHeader().setReorderingAllowed(false);
		//컬럼의 높이 설정
		jtComplet.setRowHeight(100);
		//컬럼의 넓이 설정
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
		//수동배치
		jpComplete.setLayout(null);

	////////////////////////////////////// 받은 메세지 탭 /////////////////////////////////////////
		String[] columnWNames={"판매등록자","상품코드","상품명","신청일"};
		String[][] wdata = {};
		
		jbClose = new JButton("닫기");
		

		dtmWait=new DefaultTableModel(wdata, columnWNames){
			//편집불가
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
			
		};
		
		jtWait=new JTable(dtmWait){
			//컬럼에 이미지를 넣기위한 method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
		};
		//컬럼을 선택하여 움직이지 못하도록 설정
		jtWait.getTableHeader().setReorderingAllowed(false);
		//컬럼의 높이 설정
		jtWait.setRowHeight(100);
		//컬럼의 넓이 설정
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
		//수동배치
		jpWait.setLayout(null);
		///////////////////////////////////////////////////////////////////////////////////////////////
		jtpTab=new JTabbedPane();
		jtpTab.add("구매완료 목록", jpComplete);
		jtpTab.addTab("구매대기 목록", jpWait);
		
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

