package kr.co.sist.market.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import kr.co.sist.market.vo.IdVO;
import kr.co.sist.market.vo.ItemInfoVO;
import kr.co.sist.market.vo.LoginVO;
import kr.co.sist.market.vo.MemberInfoVO;
import kr.co.sist.market.vo.MemberJoinVO;
import kr.co.sist.market.vo.MsgListVO;
import kr.co.sist.market.vo.MyInfoVO;
import kr.co.sist.market.vo.PassVO;
import kr.co.sist.market.vo.PhoneVO;

/**
 * ���߽��� ���� ó���ϴ� DAO
 * @author user
 */
public class CustomerDAO {
	
	private static CustomerDAO c_dao;
	
	//�⺻������
	private CustomerDAO(){
	}//CustomerDAO
	
	//Instance���
	public static CustomerDAO getInstance(){
		if(c_dao == null){ 
			c_dao=new CustomerDAO();
		}//end if
		return c_dao;
	}//getInstatnce()
	
	//Connection���
	private Connection getConnection() throws SQLException{
		Connection con=null;
		Properties prop=new Properties();
		
		try {
				File file=new File("C:/dev/170523_1/sist_prj2/prj2/src/kr/co/sist/market/dao/market.properties");
				if(file.exists()){ 
						prop.load(new FileInputStream(file)); 
						String driver=prop.getProperty("driver"); 
						String url=prop.getProperty("url");  
						String id=prop.getProperty("dboid"); 
						String pass=prop.getProperty("dbopwd"); 
						try {
							Class.forName(driver); //����̹� �ε�
							con=DriverManager.getConnection(url,id,pass); 
						} catch (ClassNotFoundException e) {
							System.out.println("����̹� �ε����� �����ϳ� �ڲ�..");
							e.printStackTrace();
						}//end catch
				}else{
					JOptionPane.showMessageDialog(null, "���������� ��θ� Ȯ���ϼ���."); 
				}//end if
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return con;
	}//getConnection()	
	
//	//////////////////////////����̺�ε� �����׽�Ʈ//////////////////////////
//	public static void main(String[] args){
//		try {
//			System.out.println(CustomerDAO.getInstance().getConnection());
//			try {
//				System.out.println(InetAddress.getLocalHost());
//			} catch (UnknownHostException e) {
//				e.printStackTrace();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}//main
//	///////////////////////////////////////////////////////////////////////////////////
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////�޼ҵ� ����////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * �α��� �������θ� �����ϴ� ��
	 * ���̵� �����ϰ�, �� ���̵� �ش��ϴ� ��й�ȣ�� ��ġ�ϸ� �α��� ����
	 * @return boolean
	 * @param LoginVO
	 */
	public boolean selectLogin(LoginVO lv) throws SQLException{
		Boolean flag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			//1.����̹� �ε�
			//2.Connection ���
				con=getConnection();
			//3.������ ������ü ���
				String selectLogin="select id,pass from member";
				pstmt=con.prepareStatement(selectLogin);
			//4.���� ���� ��, ��� ��� : �� ��쿡 ���ε庯���� ����
				rs=pstmt.executeQuery(); //select�̱� ������ executeQuery()
				lv=null;
				while(rs.next()){
					lv=new LoginVO();
					//rs���� ������ ��ȸ�� ����� ��������
					lv.setId(rs.getString("id")); //�� rs���� "id"��� �÷��� ������ �ҷ��ͼ� LoginVOŬ������ id������ ��´�.
					lv.setPass(rs.getString("pass")); //�� rs���� "pass"��� �÷��� ������ �ҷ��ͼ� LoginVOŬ������ pass������ ��´�.
				}//end while
								
			}finally{
			//5.�������
				if( rs != null) { rs.close(); }
				if( pstmt != null) { pstmt.close(); }
				if( con != null) { con.close(); }
			}//end try
		return flag;
	}//selectLogin

	
	/**
	 * ��й�ȣ ������ �����Ͽ� �ҷ����� ��
	 * @return List<String>
	 */
	public List<String> selectPassQu() throws SQLException{
		List<String> list=new ArrayList<String>();
		
		return list;
	}//selectPassQu
	
	
	/**
	 * �Ǹ��� ��ǰ�� ��ǰ���̺� ����ϴ� ��
	 * @param ItemInfoVO
	 */
	public void insertItem(ItemInfoVO iiv) throws SQLException{
		//////////////////////////ItemInfoVO////////////////////////
		//String itemName, itemType, itemInfo, hiredate, image;
		//int price;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
			//1.����̹� �ε�
			//2.Connection ���
			con=getConnection();
			//3.������ ������ü ���
			String insertMenu="insert into product(item_code,item_name,item_image,item_info,price,hiredate,buyer_id,sold_date,id,category_num) values(item_num,?,?,?,?,sysdate,'','','hyunwan',?)";
			pstmt=con.prepareStatement(insertMenu);
			//4.���� ���� ��, ��� ��� : �� ��쿡 ���ε庯���� 8�� ����(id,ssn,name,pass,pass_answer,image,info,qu_num)
			pstmt.setString(1, iiv.getItemName()); //<MemberJoinVO>�� getId()�޼ҵ带 ȣ���Ͽ� id������ ��´�.
			pstmt.setString(2, iiv.getImage()); 
			pstmt.setString(3, iiv.getItemInfo()); 
			pstmt.setInt(4, iiv.getPrice()); 
			pstmt.setString(5, iiv.getItemType()); 
			
			pstmt.executeUpdate(); //insert�� executeUpdate()�� ȣ��
		}finally{
			//5.�������
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
	}//insertItem
	
//	///////////////////////////////////insertItem()�޼ҵ� �����׽�Ʈ/////////////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////ItemInfoVO////////////////////////
//			//String itemName, itemType, itemInfo, hiredate, image;
//			//int price;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				ItemInfoVO iiv=new ItemInfoVO("��ī������","2","���ֱͿ�����. 2000v¥������!","","pika.jpg",15000);
//				cd.insertItem(iiv);
//				System.out.println("�޴� �߰�����!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * ������ ������ ȸ���� ���� ������ �ҷ����� ��
	 * @param id
	 * @return MemberInfoVO
	 */
	public MemberInfoVO selectPreMember(String id) throws SQLException{
		MemberInfoVO miv=null;
		
		return miv;
	}//selectPreMember
	
	
	/**
	 * �Է��� ȸ�������� ȸ�����̺� �߰��ϴ� ��
	 * @param MemberJoinVO
	 */
	public void insertMember(MemberJoinVO mjv) throws SQLException{
		///////////////////////MemberJoinVO/////////////////////
		//	String id, ssn, name, pass, passAnswer, image, info
		// int quNum
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
		//1.����̹� �ε�
		//2.Connection ���
			con=getConnection();
		//3.������ ������ü ���
			String insertMenu="insert into member(id,ssn,name,pass,pass_answer,image,info,qu_num) values(?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(insertMenu);
		//4.���� ���� ��, ��� ��� : �� ��쿡 ���ε庯���� 8�� ����(id,ssn,name,pass,pass_answer,image,info,qu_num)
			pstmt.setString(1, mjv.getId()); //<MemberJoinVO>�� getId()�޼ҵ带 ȣ���Ͽ� id������ ��´�.
			pstmt.setString(2, mjv.getSsn()); //<MemberJoinVO>�� getSsn()�޼ҵ带 ȣ���Ͽ� ssn������ ��´�.
			pstmt.setString(3, mjv.getName()); //<MemberJoinVO>�� getName()�޼ҵ带 ȣ���Ͽ� name������ ��´�.
			pstmt.setString(4, mjv.getPass()); //<MemberJoinVO>�� getPass()�޼ҵ带 ȣ���Ͽ� pass������ ��´�.
			pstmt.setString(5, mjv.getPassAnswer()); //<MemberJoinVO>�� getPassAnswer()�޼ҵ带 ȣ���Ͽ� passAnswer������ ��´�.
			pstmt.setString(6, mjv.getImage()); //<MemberJoinVO>�� getImage()�޼ҵ带 ȣ���Ͽ� image������ ��´�.
			pstmt.setString(7, mjv.getInfo()); //<MemberJoinVO>�� getInfo()�޼ҵ带 ȣ���Ͽ� info������ ��´�.
			pstmt.setInt(8, mjv.getQuNum()); //<MemberJoinVO>�� getQuNum()�޼ҵ带 ȣ���Ͽ� quNum������ ��´�.
			
			pstmt.executeUpdate(); //insert�� executeUpdate()�� ȣ��
		}finally{
		//5.�������
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
	}//insertMember
	
//	///////////////////////////////////////insertMember()�޼ҵ� �����׽�Ʈ////////////////////////////////////////////
//	public static void main(String[] args){
//		try {
//				///////////////////////MemberJoinVO//////////////////////
//				//	String id, ssn, name, pass, passAnswer, image, info
//				// int quNum
//				CustomerDAO cd=CustomerDAO.getInstance();
//				MemberJoinVO mjv=new MemberJoinVO("dongha","9205061234567","�쵿��","dongha","������","dongha.jpg","�ȳ��ϼ��� 2�� ������Ʈ�� ��� �쵿���Դϴ�",2);
//				cd.insertMember(mjv);
//				System.out.println("�޴� �߰�����!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * ȸ���� ������ �����ϴ� ��
	 * @param MemberInfoVO
	 */
	public void updateMember(MemberInfoVO miv) throws SQLException{
		
	}//updateMember
	
	
	/**
	 * �Է��� �޼����� �����޼����� �߰��ϴ� ��
	 * @param MsgListVO
	 */
	public void insertSendMsg(MsgListVO mlv) throws SQLException{
		//////////////////////////MsgListVO////////////////////////
		//String id, item, msgDate;
		//boolean flag;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
			//1.����̹� �ε�
			//2.Connection ���
			con=getConnection();
			//3.������ ������ü ���
			String insertMenu="insert into send_msg(msg_num,send_id,message,id,send_date,msg_check_flag,item_code) values(seq_num,'dongha',?,?,sysdate,?,?)";
			pstmt=con.prepareStatement(insertMenu);
			//4.���� ���� ��, ��� ��� : ���ε庯���� 4�� ����
			pstmt.setString(1, mlv.getId()); //<MsgListVO>�� getId()�޼ҵ带 ȣ���Ͽ� id������ ��´�.
			pstmt.setString(2, mlv.getMsgDate()); 
			pstmt.setString(3, mlv.getFlag()); 
			pstmt.setString(4, mlv.getItem()); 
			
			pstmt.executeUpdate(); //insert�� executeUpdate()�� ȣ��
		}finally{
			//5.�������
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
	}//insertSendMsg
//	//////////////////////////////insertSendMsg()�޼ҵ� �����׽�Ʈ///////////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////MsgListVO////////////////////////
//			//String id, item, msgDate;
//			//boolean flag;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				MsgListVO mlv=new MsgListVO("hyunwan","���� A���Դϴ�. ���ŷ� ���ؿ�! �����ּ��䤾��","HY_1705240023",true); //id, msgDate, item, flag
//				cd.insertSendMsg(mlv);
//				System.out.println("�޴� �߰�����!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 *  �޼��������� ���� �޼����� �߰��ϴ� ��
	 * @param MsgListVO
	 */
	public void insertGetMsg(MsgListVO mlv) throws SQLException{
		
	}//insertGetMsg
	
	
	/**
	 * �ڽ��� ���̵� ��ȸ�ϴ� ��
	 * @param IdVO
	 * @return String
	 */
	public String selectMyId(IdVO iv) throws SQLException{
		//////////////////////////IdVO////////////////////////
		//String name, ssn;
		String result="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
		//1.����̹� �ε�
		//2.Connection ���
			con=getConnection();
		//3.������ ������ü ���
			String selectMyId="select id from member where name=? and ssn=?";
			pstmt=con.prepareStatement(selectMyId);
		//4.���� ���� ��, ��� ��� :  : ���ε庯���� 2�� ����(name,ssn)
			pstmt.setString(1, iv.getName()); //<IdVO>�� getId()�޼ҵ带 ȣ���Ͽ� name������ ��´�.
			pstmt.setString(2, iv.getSsn()); //<IdVO>�� getSsn()�޼ҵ带 ȣ���Ͽ� ssn������ ��´�.
			rs=pstmt.executeQuery(); //select�̱� ������ executeQuery()
			if(rs.next()){
				result=rs.getString("id");
			}//end if
		}finally{
		//5.�������
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		
		return result;
	}//selectMyId
//	///////////////////selectMyId(IdVO iv)�����׽�Ʈ/////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////IdVO////////////////////////
//			//String name, ssn;
//				String result="";
//				CustomerDAO cd=CustomerDAO.getInstance();
//				IdVO iv=new IdVO("������","9106071234567"); //name, ssn
//				result=cd.selectMyId(iv);
//				System.out.println("ID : "+result);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * �ڽ��� ��й�ȣ�� ��ȸ�ϴ� ��
	 * @param PassVO
	 * @return String
	 */
	public String selectMyPass(PassVO pv) throws SQLException{
		//////////////////////////PassVO////////////////////////
		//String name, ssn, id, passAnswer;
		//int quNum;
		String result="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
		//1.����̹� �ε�
		//2.Connection ���
			con=getConnection();
		//3.������ ������ü ���
			String selectMyPass="select pass from  member where name=? and ssn=? and id=? and pass_answer=? and qu_num=?";
			pstmt=con.prepareStatement(selectMyPass);
		//4.���� ���� ��, ��� ��� :  : ���ε庯���� 5�� ����(name, ssn, id, passAnswer, quNum)
			pstmt.setString(1, pv.getName()); //<PassVO>�� getName()�޼ҵ带 ȣ���Ͽ� name������ ��´�.
			pstmt.setString(2, pv.getSsn()); //<PassVO>�� getSsn()�޼ҵ带 ȣ���Ͽ� ssn������ ��´�.
			pstmt.setString(3, pv.getId()); //<PassVO>�� getId()�޼ҵ带 ȣ���Ͽ� id������ ��´�.
			pstmt.setString(4, pv.getPassAnswer()); //<PassVO>�� getPassAnswer()�޼ҵ带 ȣ���Ͽ� pass_answer������ ��´�.
			pstmt.setInt(5, pv.getQuNum()); //<PassVO>�� getQuNum()�޼ҵ带 ȣ���Ͽ� qu_num������ ��´�.
			rs=pstmt.executeQuery(); //select�̱� ������ executeQuery()
			if(rs.next()){
				result=rs.getString("pass");
			}//end if
		}finally{
		//5.�������
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		
		return result;
	}//selectMyPass
//	///////////////////selectMyPass(PassVO pv)�����׽�Ʈ/////////////////////////
//	public static void main(String[] args){
//		try {
//				//////////////////////////PassVO////////////////////////
//				//String name, ssn, id, passAnswer;
//				//int quNum;
//				String result="";
//				CustomerDAO cd=CustomerDAO.getInstance();
//				PassVO pv=new PassVO("������","9106071234567","hyunwan","�λ�",1); 
//				result=cd.selectMyPass(pv);
//				System.out.println("Pass : "+result);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * �޴���ȭ��ȣ�� �߰��ϴ� ��
	 * @param PhoneVO
	 */
	public void insertPhone(PhoneVO phv) throws SQLException{
		
	}//insertPhone
	
	
	/**
	 * �޴���ȭ��ȣ�� ��ȸ�ϴ� ��
	 * @param itemCode
	 * @return String
	 */
	public String selectPhone(String itemCode) throws SQLException{
		String phone="";
		
		return phone;
	}//selectPhone
	
	/**
	 * ���� ȸ�������� ��ȸ�ϴ� ��
	 * @param id
	 * @return MyInfoVO
	 */
	public MyInfoVO selectMyInfo(String id) throws SQLException{
		MyInfoVO miv=null;
		
		return  miv;
	}//selectMyInfo
	
	/**
	 * �޼����� ������ ��ȸ�ϴ� ��
	 * @param id
	 * @return String
	 */
	public String selectMsgInfo(String id) throws SQLException{
		String msg="";
		
		return msg;
	}//selectMsgInfo

}//class
