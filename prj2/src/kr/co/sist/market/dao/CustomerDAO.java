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
 * 고객중심의 일을 처리하는 DAO
 * @author user
 */
public class CustomerDAO {
	
	private static CustomerDAO c_dao;
	
	//기본생성자
	private CustomerDAO(){
	}//CustomerDAO
	
	//Instance얻기
	public static CustomerDAO getInstance(){
		if(c_dao == null){ 
			c_dao=new CustomerDAO();
		}//end if
		return c_dao;
	}//getInstatnce()
	
	//Connection얻기
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
							Class.forName(driver); //드라이버 로딩
							con=DriverManager.getConnection(url,id,pass); 
						} catch (ClassNotFoundException e) {
							System.out.println("드라이버 로딩에서 실패하네 자꾸..");
							e.printStackTrace();
						}//end catch
				}else{
					JOptionPane.showMessageDialog(null, "설정파일의 경로를 확인하세요."); 
				}//end if
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return con;
	}//getConnection()	
	
//	//////////////////////////드라이브로딩 단위테스트//////////////////////////
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
	///////////////////////////////////////////////////////////메소드 시작////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 로그인 성공여부를 결정하는 일
	 * 아이디가 존재하고, 그 아이디에 해당하는 비밀번호와 일치하면 로그인 성공
	 * @return boolean
	 * @param LoginVO
	 */
	public boolean selectLogin(LoginVO lv) throws SQLException{
		Boolean flag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			//1.드라이버 로딩
			//2.Connection 얻기
				con=getConnection();
			//3.쿼리문 생성객체 얻기
				String selectLogin="select id,pass from member";
				pstmt=con.prepareStatement(selectLogin);
			//4.쿼리 실행 후, 결과 얻기 : 이 경우에 바인드변수는 없음
				rs=pstmt.executeQuery(); //select이기 때문에 executeQuery()
				lv=null;
				while(rs.next()){
					lv=new LoginVO();
					//rs에는 쿼리를 조회한 결과가 들어가있으며
					lv.setId(rs.getString("id")); //그 rs에서 "id"라는 컬럼의 값들을 불러와서 LoginVO클래스의 id변수에 담는다.
					lv.setPass(rs.getString("pass")); //그 rs에서 "pass"라는 컬럼의 값들을 불러와서 LoginVO클래스의 pass변수에 담는다.
				}//end while
								
			}finally{
			//5.연결끊기
				if( rs != null) { rs.close(); }
				if( pstmt != null) { pstmt.close(); }
				if( con != null) { con.close(); }
			}//end try
		return flag;
	}//selectLogin

	
	/**
	 * 비밀번호 질문을 선택하여 불러오는 일
	 * @return List<String>
	 */
	public List<String> selectPassQu() throws SQLException{
		List<String> list=new ArrayList<String>();
		
		return list;
	}//selectPassQu
	
	
	/**
	 * 판매할 상품을 물품테이블에 등록하는 일
	 * @param ItemInfoVO
	 */
	public void insertItem(ItemInfoVO iiv) throws SQLException{
		//////////////////////////ItemInfoVO////////////////////////
		//String itemName, itemType, itemInfo, hiredate, image;
		//int price;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
			//1.드라이버 로딩
			//2.Connection 얻기
			con=getConnection();
			//3.쿼리문 생성객체 얻기
			String insertMenu="insert into product(item_code,item_name,item_image,item_info,price,hiredate,buyer_id,sold_date,id,category_num) values(item_num,?,?,?,?,sysdate,'','','hyunwan',?)";
			pstmt=con.prepareStatement(insertMenu);
			//4.쿼리 실행 후, 결과 얻기 : 이 경우에 바인드변수가 8개 존재(id,ssn,name,pass,pass_answer,image,info,qu_num)
			pstmt.setString(1, iiv.getItemName()); //<MemberJoinVO>의 getId()메소드를 호출하여 id변수에 담는다.
			pstmt.setString(2, iiv.getImage()); 
			pstmt.setString(3, iiv.getItemInfo()); 
			pstmt.setInt(4, iiv.getPrice()); 
			pstmt.setString(5, iiv.getItemType()); 
			
			pstmt.executeUpdate(); //insert라서 executeUpdate()를 호출
		}finally{
			//5.연결끊기
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
	}//insertItem
	
//	///////////////////////////////////insertItem()메소드 단위테스트/////////////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////ItemInfoVO////////////////////////
//			//String itemName, itemType, itemInfo, hiredate, image;
//			//int price;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				ItemInfoVO iiv=new ItemInfoVO("피카츄인형","2","아주귀여워요. 2000v짜리에요!","","pika.jpg",15000);
//				cd.insertItem(iiv);
//				System.out.println("메뉴 추가성공!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 정보를 수정할 회원의 기존 정보를 불러오는 일
	 * @param id
	 * @return MemberInfoVO
	 */
	public MemberInfoVO selectPreMember(String id) throws SQLException{
		MemberInfoVO miv=null;
		
		return miv;
	}//selectPreMember
	
	
	/**
	 * 입력한 회원정보로 회원테이블에 추가하는 일
	 * @param MemberJoinVO
	 */
	public void insertMember(MemberJoinVO mjv) throws SQLException{
		///////////////////////MemberJoinVO/////////////////////
		//	String id, ssn, name, pass, passAnswer, image, info
		// int quNum
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
		//1.드라이버 로딩
		//2.Connection 얻기
			con=getConnection();
		//3.쿼리문 생성객체 얻기
			String insertMenu="insert into member(id,ssn,name,pass,pass_answer,image,info,qu_num) values(?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(insertMenu);
		//4.쿼리 실행 후, 결과 얻기 : 이 경우에 바인드변수가 8개 존재(id,ssn,name,pass,pass_answer,image,info,qu_num)
			pstmt.setString(1, mjv.getId()); //<MemberJoinVO>의 getId()메소드를 호출하여 id변수에 담는다.
			pstmt.setString(2, mjv.getSsn()); //<MemberJoinVO>의 getSsn()메소드를 호출하여 ssn변수에 담는다.
			pstmt.setString(3, mjv.getName()); //<MemberJoinVO>의 getName()메소드를 호출하여 name변수에 담는다.
			pstmt.setString(4, mjv.getPass()); //<MemberJoinVO>의 getPass()메소드를 호출하여 pass변수에 담는다.
			pstmt.setString(5, mjv.getPassAnswer()); //<MemberJoinVO>의 getPassAnswer()메소드를 호출하여 passAnswer변수에 담는다.
			pstmt.setString(6, mjv.getImage()); //<MemberJoinVO>의 getImage()메소드를 호출하여 image변수에 담는다.
			pstmt.setString(7, mjv.getInfo()); //<MemberJoinVO>의 getInfo()메소드를 호출하여 info변수에 담는다.
			pstmt.setInt(8, mjv.getQuNum()); //<MemberJoinVO>의 getQuNum()메소드를 호출하여 quNum변수에 담는다.
			
			pstmt.executeUpdate(); //insert라서 executeUpdate()를 호출
		}finally{
		//5.연결끊기
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
	}//insertMember
	
//	///////////////////////////////////////insertMember()메소드 단위테스트////////////////////////////////////////////
//	public static void main(String[] args){
//		try {
//				///////////////////////MemberJoinVO//////////////////////
//				//	String id, ssn, name, pass, passAnswer, image, info
//				// int quNum
//				CustomerDAO cd=CustomerDAO.getInstance();
//				MemberJoinVO mjv=new MemberJoinVO("dongha","9205061234567","우동하","dongha","의정부","dongha.jpg","안녕하세요 2조 프로젝트의 기둥 우동하입니다",2);
//				cd.insertMember(mjv);
//				System.out.println("메뉴 추가성공!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 회원의 정보를 수정하는 일
	 * @param MemberInfoVO
	 */
	public void updateMember(MemberInfoVO miv) throws SQLException{
		
	}//updateMember
	
	
	/**
	 * 입력한 메세지를 보낸메세지에 추가하는 일
	 * @param MsgListVO
	 */
	public void insertSendMsg(MsgListVO mlv) throws SQLException{
		//////////////////////////MsgListVO////////////////////////
		//String id, item, msgDate;
		//boolean flag;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
			//1.드라이버 로딩
			//2.Connection 얻기
			con=getConnection();
			//3.쿼리문 생성객체 얻기
			String insertMenu="insert into send_msg(msg_num,send_id,message,id,send_date,msg_check_flag,item_code) values(seq_num,'dongha',?,?,sysdate,?,?)";
			pstmt=con.prepareStatement(insertMenu);
			//4.쿼리 실행 후, 결과 얻기 : 바인드변수가 4개 존재
			pstmt.setString(1, mlv.getId()); //<MsgListVO>의 getId()메소드를 호출하여 id변수에 담는다.
			pstmt.setString(2, mlv.getMsgDate()); 
			pstmt.setString(3, mlv.getFlag()); 
			pstmt.setString(4, mlv.getItem()); 
			
			pstmt.executeUpdate(); //insert라서 executeUpdate()를 호출
		}finally{
			//5.연결끊기
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
	}//insertSendMsg
//	//////////////////////////////insertSendMsg()메소드 단위테스트///////////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////MsgListVO////////////////////////
//			//String id, item, msgDate;
//			//boolean flag;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				MsgListVO mlv=new MsgListVO("hyunwan","상태 A급입니다. 직거래 원해요! 연락주세요ㅎㅎ","HY_1705240023",true); //id, msgDate, item, flag
//				cd.insertSendMsg(mlv);
//				System.out.println("메뉴 추가성공!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 *  메세지정보를 받은 메세지에 추가하는 일
	 * @param MsgListVO
	 */
	public void insertGetMsg(MsgListVO mlv) throws SQLException{
		
	}//insertGetMsg
	
	
	/**
	 * 자신의 아이디를 조회하는 일
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
		//1.드라이버 로딩
		//2.Connection 얻기
			con=getConnection();
		//3.쿼리문 생성객체 얻기
			String selectMyId="select id from member where name=? and ssn=?";
			pstmt=con.prepareStatement(selectMyId);
		//4.쿼리 실행 후, 결과 얻기 :  : 바인드변수가 2개 존재(name,ssn)
			pstmt.setString(1, iv.getName()); //<IdVO>의 getId()메소드를 호출하여 name변수에 담는다.
			pstmt.setString(2, iv.getSsn()); //<IdVO>의 getSsn()메소드를 호출하여 ssn변수에 담는다.
			rs=pstmt.executeQuery(); //select이기 때문에 executeQuery()
			if(rs.next()){
				result=rs.getString("id");
			}//end if
		}finally{
		//5.연결끊기
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		
		return result;
	}//selectMyId
//	///////////////////selectMyId(IdVO iv)단위테스트/////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////IdVO////////////////////////
//			//String name, ssn;
//				String result="";
//				CustomerDAO cd=CustomerDAO.getInstance();
//				IdVO iv=new IdVO("김현완","9106071234567"); //name, ssn
//				result=cd.selectMyId(iv);
//				System.out.println("ID : "+result);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 자신의 비밀번호를 조회하는 일
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
		//1.드라이버 로딩
		//2.Connection 얻기
			con=getConnection();
		//3.쿼리문 생성객체 얻기
			String selectMyPass="select pass from  member where name=? and ssn=? and id=? and pass_answer=? and qu_num=?";
			pstmt=con.prepareStatement(selectMyPass);
		//4.쿼리 실행 후, 결과 얻기 :  : 바인드변수가 5개 존재(name, ssn, id, passAnswer, quNum)
			pstmt.setString(1, pv.getName()); //<PassVO>의 getName()메소드를 호출하여 name변수에 담는다.
			pstmt.setString(2, pv.getSsn()); //<PassVO>의 getSsn()메소드를 호출하여 ssn변수에 담는다.
			pstmt.setString(3, pv.getId()); //<PassVO>의 getId()메소드를 호출하여 id변수에 담는다.
			pstmt.setString(4, pv.getPassAnswer()); //<PassVO>의 getPassAnswer()메소드를 호출하여 pass_answer변수에 담는다.
			pstmt.setInt(5, pv.getQuNum()); //<PassVO>의 getQuNum()메소드를 호출하여 qu_num변수에 담는다.
			rs=pstmt.executeQuery(); //select이기 때문에 executeQuery()
			if(rs.next()){
				result=rs.getString("pass");
			}//end if
		}finally{
		//5.연결끊기
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		
		return result;
	}//selectMyPass
//	///////////////////selectMyPass(PassVO pv)단위테스트/////////////////////////
//	public static void main(String[] args){
//		try {
//				//////////////////////////PassVO////////////////////////
//				//String name, ssn, id, passAnswer;
//				//int quNum;
//				String result="";
//				CustomerDAO cd=CustomerDAO.getInstance();
//				PassVO pv=new PassVO("김현완","9106071234567","hyunwan","부산",1); 
//				result=cd.selectMyPass(pv);
//				System.out.println("Pass : "+result);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 휴대전화번호를 추가하는 일
	 * @param PhoneVO
	 */
	public void insertPhone(PhoneVO phv) throws SQLException{
		
	}//insertPhone
	
	
	/**
	 * 휴대전화번호를 조회하는 일
	 * @param itemCode
	 * @return String
	 */
	public String selectPhone(String itemCode) throws SQLException{
		String phone="";
		
		return phone;
	}//selectPhone
	
	/**
	 * 나의 회원정보를 조회하는 일
	 * @param id
	 * @return MyInfoVO
	 */
	public MyInfoVO selectMyInfo(String id) throws SQLException{
		MyInfoVO miv=null;
		
		return  miv;
	}//selectMyInfo
	
	/**
	 * 메세지의 내용을 조회하는 일
	 * @param id
	 * @return String
	 */
	public String selectMsgInfo(String id) throws SQLException{
		String msg="";
		
		return msg;
	}//selectMsgInfo

}//class
