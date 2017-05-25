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
import kr.co.sist.market.vo.SellerInfoVO;

/**
 * test
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
				File file=new File("C:/Users/user/git/sist_prj2/prj2/src/kr/co/sist/market/dao/market.properties");
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
//	//////////////////////////(�׽�Ʈ�Ϸ�)����̺�ε� �����׽�Ʈ//////////////////////////
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
		//////////////////////////LoginVO////////////////////////
		//String id, pass;
		Boolean flag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			//1.����̹� �ε�
			//2.Connection ���
				con=getConnection();
			//3.������ ������ü ���
				String selectLogin="select name from member where id=? and pass=? ";
				pstmt=con.prepareStatement(selectLogin);
			//4.���� ���� ��, ��� ��� : ���ε庯�� id,pass
				pstmt.setString(1, lv.getId()); 
				pstmt.setString(2, lv.getPass()); 
				rs=pstmt.executeQuery(); //select�̱� ������ executeQuery()
				if(rs.next()){
					flag=true;
				}//end if
			
			}finally{
			//5.�������
				if( rs != null) { rs.close(); }
				if( pstmt != null) { pstmt.close(); }
				if( con != null) { con.close(); }
			}//end try
		return flag;
	}//selectLogin
//	///////////////////////////////////(�׽�Ʈ�Ϸ�)selectLogin()�޼ҵ� �����׽�Ʈ/////////////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////LoginVO////////////////////////
//			//String id, pass;
//				boolean flag=false;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				LoginVO lv=new LoginVO("hyunwan","hyunwan");
//				flag=cd.selectLogin(lv);
//				if(flag){
//					System.out.println("�α��� ����!!");
//				}else{
//					System.out.println("�α��� ����!!");
//				}//end if
//		} catch (SQLException e) {
//			System.out.println("�α��� ����!!");
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	/**
	 * ��й�ȣ ������ �����Ͽ� �ҷ����� ��
	 * @return List<String>
	 */
	public String selectPassQu(int quNum) throws SQLException{
		String result="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
		//1.����̹� �ε�
		//2.Connection ���
			con=getConnection();
		//3.������ ������ü ���
			String selectPassQu="select question from pass_question where qu_num=?";
			pstmt=con.prepareStatement(selectPassQu);
		//4.���� ���� ��, ��� ��� :  : ���ε庯���� 1�� ����(qu_num)
			pstmt.setInt(1, quNum); //<IdVO>�� getId()�޼ҵ带 ȣ���Ͽ� name������ ��´�.
			rs=pstmt.executeQuery(); //select�̱� ������ executeQuery()
			if(rs.next()){
				result=rs.getString("question");
			}//end if
		}finally{
		//5.�������
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		
		return result;
	}//selectPassQu
//	///////////////////(�׽�Ʈ�Ϸ�)selectPassQu()�����׽�Ʈ/////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////IdVO////////////////////////
//			//String name, ssn;
//				String result="";
//				CustomerDAO cd=CustomerDAO.getInstance();
//				int quNum=2;
//				result=cd.selectPassQu(quNum);
//				System.out.println("������ ��ȣ"+"�� �ش��ϴ� ���� : "+result);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	///////////////////////////////////////////////////////////////////////////
	
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
//	//////////////////////////////(�̿Ϸ�)insertItem()�޼ҵ� �����׽�Ʈ//////////////////////////////
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
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try{
			//1.����̹� �ε�
			//2.Connection ���
				con=getConnection();
			//3.������ ������ü ���
				String selectPreMember="select image,pass,qu_num,pass_answer,info from member where id=?";
				pstmt=con.prepareStatement(selectPreMember);
			//4.���� ���� ��, ��� ��� : �� ��쿡 ���ε庯���� 1�� ����(id)
				pstmt.setString(1, id); 
				rs=pstmt.executeQuery();//select�� executeQuery()�� ȣ��
				
				if(rs.next()){
					miv=new MemberInfoVO();
					//�̹���,��й�ȣ,��й�ȣ����,��й�ȣ�亯,�ڱ�Ұ�
					miv.setImage(rs.getString("image"));
					miv.setPass(rs.getString("pass"));
					miv.setQuNum(rs.getInt("qu_num"));
					miv.setPassAnswer(rs.getString("pass_answer"));
					miv.setInfo(rs.getString("info"));
				}//end if
		}finally{
			//5.�������
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
			if( rs != null) { rs.close(); }
		}//end try
		
		return miv;
	}//selectPreMember
//	//////////////////////////(�׽�Ʈ�Ϸ�)selectPreMember()�޼ҵ� �����׽�Ʈ/////////////////////////////////
//	public static void main(String[] args){
//		try {
//				MemberInfoVO miv=new MemberInfoVO();
//				CustomerDAO cd=CustomerDAO.getInstance();
//				miv=cd.selectPreMember("hyunwan");
//				System.out.println(miv);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
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
			String insertMember="insert into member(id,ssn,name,pass,pass_answer,image,info,qu_num) values(?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(insertMember);
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
//	////////////////////////////////(�׽�Ʈ�Ϸ�)insertMember()�޼ҵ� �����׽�Ʈ/////////////////////////////////////
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
//	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * ȸ���� ������ �����ϴ� ��
	 * @param MemberInfoVO
	 */
	public void updateMember(MemberInfoVO miv) throws SQLException{
		/////////////MemberInfoVO////////////////
		//String id, pass, passAnswer, image, info
		//int quNum
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
		//1.����̹� �ε�
		//2.Connection���
			con=getConnection();
		//3.������ ������ü ���(Statement���� ���� : � �������� ������ �𸥴�)
			String updateMember="update member set  pass=?,pass_answer=?,image=?,info=?,qu_num=? where id=?";
			pstmt=con.prepareStatement(updateMember);
		//4.���� ���� ��, ��� ���
			//���ε庯���� ���� �����ؾ��Ѵ�.
			pstmt.setString(1,miv.getPass());   //VO�� ���� ���� ����ϱ����� �Ű������� EmpVO�޾ƿ°�
			pstmt.setString(2,miv.getPassAnswer());  //VO�� ���� ���� ����ϱ����� �Ű������� EmpVO�޾ƿ°�
			pstmt.setString(3,miv.getImage());  //VO�� ���� ���� ����ϱ����� �Ű������� EmpVO�޾ƿ°�
			pstmt.setString(4,miv.getInfo());  //VO�� ���� ���� ����ϱ����� �Ű������� EmpVO�޾ƿ°�
			pstmt.setInt(5,miv.getQuNum());  //VO�� ���� ���� ����ϱ����� �Ű������� EmpVO�޾ƿ°�
			pstmt.setString(6,miv.getId());  //VO�� ���� ���� ����ϱ����� �Ű������� EmpVO�޾ƿ°�
			pstmt.executeUpdate();
		}finally{
		//5.���� ����
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end finally
	}//updateMember
//	////////////////////////////////(�׽�Ʈ�Ϸ�)updateMember()�޼ҵ� �����׽�Ʈ///////////////////////////////
//	public static void main(String[] args){
//		try {
//			/////////////MemberInfoVO////////////////
//			//String id, pass, passAnswer, image, info
//			//int quNum
//				CustomerDAO cd=CustomerDAO.getInstance();
//				MemberInfoVO miv=new MemberInfoVO("hyunwan","tiger","��ī��","hyunwan.jpg","�������Դϴ�!!",3); // id, pass, passAnswer, image, info, quNum
//				cd.updateMember(miv);
//				System.out.println("ȸ������ ������Ʈ ����!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
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
//	////////////////////////////////(�̿Ϸ�)insertSendMsg()�޼ҵ� �����׽�Ʈ///////////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////MsgListVO////////////////////////
//			//String msgCode, id, item, msgDate, flag;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				MsgListVO mlv=new MsgListVO("HY_1705240023","2017","n"); //id, msgDate, item, flag
//				cd.insertSendMsg(mlv);
//				System.out.println("�޴� �߰�����!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 *  �޼��������� �޾� �޽������̺� �߰��ϴ� ��
	 * @param MsgListVO
	 */
	public void insertGetMsg(MsgListVO mlv) throws SQLException{
		//////////////////////////MsgListVO////////////////////////
		//String msgCode, id, item, msgDate, flag;
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
//	///////////////////(�׽�Ʈ�Ϸ�)selectMyId(IdVO iv)�����׽�Ʈ/////////////////////////
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
//	////////////////////////////////////////////////////////////////////////////////////////////
	
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
//	///////////////////(�׽�Ʈ�Ϸ�)selectMyPass(PassVO pv)�����׽�Ʈ/////////////////////////
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
//	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * �޴���ȭ��ȣ�� �߰��ϴ� ��
	 * @param PhoneVO
	 */
	public void insertPhone(PhoneVO phv) throws SQLException{
		//////////////PhoneVO//////////////
		//String id, phone, itemCode;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
			//1.����̹� �ε�
			//2.Connection ���
			con=getConnection();
			//3.������ ������ü ���
			String insertPhone="insert into buyer_contact values(?,?,?,)";//item_code,phone,id,date
			pstmt=con.prepareStatement(insertPhone);
			//4.���� ���� ��, ��� ��� : ���ε庯���� 3�� ����
			pstmt.setString(1, phv.getItemCode()); 
			pstmt.setString(2, phv.getPhone()); 
			pstmt.setString(3, phv.getId()); 
			
			pstmt.executeUpdate(); //insert�� executeUpdate()�� ȣ��
		}finally{
			//5.�������
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
	}//insertPhone
////////////////////////////////(�̿Ϸ�)insertPhone()�޼ҵ� �����׽�Ʈ///////////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////MsgListVO////////////////////////
//			//String msgCode, id, item, msgDate, flag;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				MsgListVO mlv=new MsgListVO("HY_1705240023","2017","n"); //id, msgDate, item, flag
//				cd.insertSendMsg(mlv);
//				System.out.println("�޴� �߰�����!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	////////////
	
	
	/**
	 * �޴���ȭ��ȣ�� ��ȸ�ϴ� ��
	 * @param itemCode
	 * @return String
	 */
	public String selectPhone(String itemCode) throws SQLException{
		String result="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			//1.����̹� �ε�
			//2.Connection ���
				con=getConnection();
			//3.������ ������ü ���
				String selectPhone="select phone from buyer_contact where item_code=?";
				pstmt=con.prepareStatement(selectPhone);
			//4.���� ���� ��, ��� ��� :  : ���ε庯���� 1�� ���� (item_code)
				pstmt.setString(1, itemCode); 
				rs=pstmt.executeQuery(); //select�̱� ������ executeQuery()
				if(rs.next()){
					result=rs.getString("phone");
				}//end if
		}finally{
			//5.�������
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		
		return result;
	}//selectPhone
//	///////////////////(�׽�Ʈ�Ϸ�)selectPhone()�����׽�Ʈ/////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////IdVO////////////////////////
//			//String name, ssn;
//				String result="";
//				CustomerDAO cd=CustomerDAO.getInstance();
//				String itemCode="HY_1705240021";
//				result=cd.selectPhone(itemCode);
//				System.out.println(itemCode+" ȸ���� �ڵ�����ȣ : "+result);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * ���� ȸ�������� ��ȸ�ϴ� ��
	 * @param id
	 * @return MyInfoVO
	 */
	public MyInfoVO selectMyInfo(String id) throws SQLException{
		MyInfoVO  mifv=null;
		
		return mifv;
	}//selectMyInfo
	
	
	/**
	 * �޼����� ������ ��ȸ�ϴ� ��
	 * @param id
	 * @return String
	 */
	public String selectMsgInfo(String id) throws SQLException{
		String result="";
		
		return result;
	}//selectMsgInfo

}//class
