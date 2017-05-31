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
 * ���� ������ ����ڿ��� �����ִ� Form
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
		super("�߰����Ϳ� �������!!"); 
		this.cd=cd;
		String[] columnNames={"��ȣ","��ǰ��","��ǰ�ڵ�","�Ǹ� ��ǰ ����","����","�����"};
		String[][] data = {};
		
		String[] types = {"----- ��ü���� -----","��Ƽ/��ȭ","��ǰ/��Ʈ/����","����/��Ȱ/�ǰ�","������/�ڵ���/����","����","��Ÿ"};
		jcbType = new JComboBox<String>(types);
		jbType = new JButton("�˻�");


		dtmItem=new DefaultTableModel(data, columnNames){
			//�����Ұ�
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
			 
		};
		
		jtItemList=new JTable(dtmItem){
			//�÷��� �̹����� �ֱ����� method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
		};
		//�÷��� �����Ͽ� �������� ���ϵ��� ����
		jtItemList.getTableHeader().setReorderingAllowed(false);
		//�÷��� ���� ����
		jtItemList.setRowHeight(100);
		//�÷��� ���� ����
		jtItemList.getColumnModel().getColumn(0).setPreferredWidth(40);
		jtItemList.getColumnModel().getColumn(1).setPreferredWidth(150);
		jtItemList.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtItemList.getColumnModel().getColumn(3).setPreferredWidth(400);
		jtItemList.getColumnModel().getColumn(4).setPreferredWidth(70);
		jtItemList.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		JScrollPane jspMenu = new JScrollPane(jtItemList);
		
		JLabel jlType = new JLabel("��ǰ�з�");
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
		
		JLabel jlId = new JLabel("���̵�� : ");
		JLabel jlSell = new JLabel("�Ǹ� ��� ��Ȳ : ");
		JLabel jlPurchase = new JLabel("���� ��� ��Ȳ : ");
		JLabel jlNotReadMsg = new JLabel("������ �޼��� : ");

		JLabel jlIdRst = new JLabel("XXXX");
		JLabel jlSellRst = new JLabel(cntSellWait+"�� �Ǹ� ��");
		JLabel jlPurchaseRst = new JLabel(cntBuyWait+"�� ������� ��");
		JLabel jlNotReadMsgRst = new JLabel(cntMsg+"��");
		
		jbMyInfoCh = new JButton("�� ���� ����");
		jbSellList = new JButton("�ǸŸ��");
		jbBuyList = new JButton("���Ÿ��");
		jbSignUp = new JButton("�ǸŹ�ǰ ���");
		jbMsgList = new JButton("�޼��� Ȯ��");
		
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
		
		//�̺�Ʈ �߰�
		MainViewEvt mve = new MainViewEvt(this);
		jbMyInfoCh.addActionListener(mve);
		jbSellList.addActionListener(mve);
		jbBuyList.addActionListener(mve);
		jbMsgList.addActionListener(mve);
		jbType.addActionListener(mve);
		jtItemList.addMouseListener(mve);
		//������ġ
		jpComInfo.setLayout(null);
		jpComInfo.setBounds(10,10,800,600);
		
		jtpTab=new JTabbedPane();
		jtpTab.add("����", jpItem);
		jtpTab.addTab("����������", jpComInfo);
		
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
		//â ũ�� ����
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

