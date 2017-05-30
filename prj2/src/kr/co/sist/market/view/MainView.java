package kr.co.sist.market.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.evt.MainViewEvt;

/**
 * 탭을 가지고 사용자에게 보여주는 Form
 * @author user
 *
 */
@SuppressWarnings("serial")
public class MainView extends JFrame {
	private JTable jtItemList;
	private DefaultTableModel dtmItem;
	private JTabbedPane jtpTab;
	private JComboBox<String> jcbType;
	private JButton jbType, jbMyInfoCh, jbSellList, jbBuyList, jbSignUp, jbMsgList;
	private static CustomerDAO cd;
	
	public MainView(CustomerDAO cd) throws SQLException{
		super("중고장터에 어서오세요!!"); 
		this.cd=cd;
		String[] columnNames={"번호","상품명","상품코드","판매 상품 설명","가격","등록일"};
		String[][] data = {};
		
		String[] types = {"----- 전체보기 -----","뷰티/잡화","식품/마트/유아","가구/생활/건강","스포츠/자동차/공구","도서","기타"};
		jcbType = new JComboBox<String>(types);
		jbType = new JButton("검색");


		dtmItem=new DefaultTableModel(data, columnNames){
			//편집불가
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
			 
		};
		
		jtItemList=new JTable(dtmItem){
			//컬럼에 이미지를 넣기위한 method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
		};
		//컬럼을 선택하여 움직이지 못하도록 설정
		jtItemList.getTableHeader().setReorderingAllowed(false);
		//컬럼의 높이 설정
		jtItemList.setRowHeight(100);
		//컬럼의 넓이 설정
		jtItemList.getColumnModel().getColumn(0).setPreferredWidth(40);
		jtItemList.getColumnModel().getColumn(1).setPreferredWidth(150);
		jtItemList.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtItemList.getColumnModel().getColumn(3).setPreferredWidth(400);
		jtItemList.getColumnModel().getColumn(4).setPreferredWidth(70);
		jtItemList.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		JScrollPane jspMenu = new JScrollPane(jtItemList);
		
		JLabel jlType = new JLabel("제품분류");
		JPanel jpTop = new JPanel();
		jpTop.add(jlType);
		jpTop.add(jcbType);
		jpTop.add(jbType);
		
		JPanel jpItem = new JPanel();
		jpItem.setLayout(new BorderLayout());
		jpItem.add("North", jpTop);
		jpItem.add("Center", jspMenu);
		
		int cntBuyWait=cd.selectCntBuyWait("dongha");
		int cntSellWait=cd.selectCntSellWait("dongha");
		int cntMsg=cd.selectCntMsg("dongha");
		
		ImageIcon iiInfo = new ImageIcon("C:/dev/workspace/prj22/src/kr/co/sist/market/img/profile.jpg");
		JLabel lblInfo = new JLabel(iiInfo);
		
		JLabel jlId = new JLabel("아이디명 : ");
		JLabel jlSell = new JLabel("판매 대기 현황 : ");
		JLabel jlPurchase = new JLabel("구매 대기 현황 : ");
		JLabel jlNotReadMsg = new JLabel("안읽은 메세지 : ");

		JLabel jlIdRst = new JLabel("XXXX");
		JLabel jlSellRst = new JLabel(cntSellWait+"건 판매 중");
		JLabel jlPurchaseRst = new JLabel(cntBuyWait+"건 구매희망 중");
		JLabel jlNotReadMsgRst = new JLabel(cntMsg+"건");
		
		jbMyInfoCh = new JButton("내 정보 변경");
		jbSellList = new JButton("판매목록");
		jbBuyList = new JButton("구매목록");
		jbSignUp = new JButton("판매물품 등록");
		jbMsgList = new JButton("메세지 확인");
		
		JPanel jpComInfo = new JPanel();
		
		lblInfo.setBounds(30, 30, 180, 195);
		jlId.setBounds(230, 30, 70, 20);
		jlSell.setBounds(230, 70, 120, 20);
		jlPurchase.setBounds(230, 110, 120, 20);
		jlNotReadMsg.setBounds(230, 150, 110, 20);
		jlIdRst.setBounds(350, 30, 150, 20);
		jlSellRst.setBounds(350, 70, 150, 20);
		jlPurchaseRst.setBounds(350, 110, 150, 20);
		jlNotReadMsgRst.setBounds(350, 150, 150, 20);
		jbMyInfoCh.setBounds(530, 30,130 , 20);
		jbSellList.setBounds(30, 250,130 , 20);
		jbBuyList.setBounds(200, 250,130 , 20);
		jbSignUp.setBounds(30, 290,130 , 20);
		jbMsgList.setBounds(200, 290,130 , 20);
		
		jpComInfo.add(lblInfo);
		jpComInfo.add(jlId);
		jpComInfo.add(jlSell);
		jpComInfo.add(jlPurchase);
		jpComInfo.add(jlNotReadMsg);
		jpComInfo.add(jlIdRst);
		jpComInfo.add(jlSellRst);
		jpComInfo.add(jlPurchaseRst);
		jpComInfo.add(jlNotReadMsgRst);
		jpComInfo.add(jbMyInfoCh);
		jpComInfo.add(jbSellList);
		jpComInfo.add(jbBuyList);
		jpComInfo.add(jbSignUp);
		jpComInfo.add(jbMsgList);
		
		//이벤트 추가
		MainViewEvt mve = new MainViewEvt(this);
		jbMyInfoCh.addActionListener(mve);
		jbSellList.addActionListener(mve);
		jbBuyList.addActionListener(mve);
		jbMsgList.addActionListener(mve);
		jbType.addActionListener(mve);
		jtItemList.addMouseListener(mve);
		//수동배치
		jpComInfo.setLayout(null);
		jpComInfo.setBounds(10,10,800,600);
		
		jtpTab=new JTabbedPane();
		jtpTab.add("장터", jpItem);
		jtpTab.addTab("마이페이지", jpComInfo);
		
		add("Center",jtpTab);

		jpItem.setBackground(Color.WHITE);
		
		addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}//windowClosing
			
		});
		
		setBounds(10,10,800,600);
		setVisible(true);
		//창 크기 고정
		setResizable(false);
	}//MenuForm

	public JTable getJtItemList() {
		return jtItemList;
	}

	public DefaultTableModel getDtmItem() {
		return dtmItem;
	}

	public JTabbedPane getJtpTab() {
		return jtpTab;
	}

	public JComboBox<String> getJcbType() {
		return jcbType;
	}

	public JButton getJbType() {
		return jbType;
	}

	public JButton getJbMyInfoCh() {
		return jbMyInfoCh;
	}

	public JButton getJbSellList() {
		return jbSellList;
	}

	public JButton getJbBuyList() {
		return jbBuyList;
	}

	public JButton getJbSignUp() {
		return jbSignUp;
	}

	public JButton getJbMsgList() {
		return jbMsgList;
	}

	public static void main(String[] args) {
		cd=CustomerDAO.getInstance();
		try {
			new MainView(cd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//main
	
}//class

