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

import kr.co.sist.market.vo.ChangeVO;
import kr.co.sist.market.vo.IdVO;
import kr.co.sist.market.vo.ItemInfoVO;
import kr.co.sist.market.vo.LoginVO;
import kr.co.sist.market.vo.MemberInfoVO;
import kr.co.sist.market.vo.MemberJoinVO;
import kr.co.sist.market.vo.MsgListVO;
import kr.co.sist.market.vo.MsgVO;
import kr.co.sist.market.vo.MsgViewVO;
import kr.co.sist.market.vo.PassVO;
import kr.co.sist.market.vo.PhoneVO;
import kr.co.sist.market.vo.SellerInfoVO;

/**
 * test12
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
				File file=new File(System.getProperty("user.dir")+"/src/kr/co/sist/market/dao/market.properties");
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
//	//////////////////////////(테스트완료)드라이브로딩 단위테스트//////////////////////////
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
		//////////////////////////LoginVO////////////////////////
		//String id, pass;
		Boolean flag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			//1.드라이버 로딩
			//2.Connection 얻기
				con=getConnection();
			//3.쿼리문 생성객체 얻기
				String selectLogin="select name from member where id=? and pass=? ";
				pstmt=con.prepareStatement(selectLogin);
			//4.쿼리 실행 후, 결과 얻기 : 바인드변수 id,pass
				pstmt.setString(1, lv.getId()); 
				pstmt.setString(2, lv.getPass()); 
				rs=pstmt.executeQuery(); //select이기 때문에 executeQuery()
				if(rs.next()){
					flag=true;
				}//end if
			
			}finally{
			//5.연결끊기
				if( rs != null) { rs.close(); }
				if( pstmt != null) { pstmt.close(); }
				if( con != null) { con.close(); }
			}//end try
		return flag;
	}//selectLogin
//	///////////////////////////////////(테스트완료)selectLogin()메소드 단위테스트/////////////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////LoginVO////////////////////////
//			//String id, pass;
//				boolean flag=false;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				LoginVO lv=new LoginVO("hyunwan","hyunwan");
//				flag=cd.selectLogin(lv);
//				if(flag){
//					System.out.println("로그인 성공!!");
//				}else{
//					System.out.println("로그인 실패!!");
//				}//end if
//		} catch (SQLException e) {
//			System.out.println("로그인 실패!!");
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	/** => day0419 tableSchemaModel.java 파일보고 따라서 수정하기, 즉 매개변수 없애고 반환형을 List(String)으로
	 * 비밀번호 질문을 선택하여 불러오는 일 
	 * @param int
	 * @return String
	 */
	public List<String> selectPassQu() throws SQLException{
		List<String> list=new ArrayList<String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
		//1.드라이버 로딩
		//2.Connection 얻기
			con=getConnection();
		//3.쿼리문 생성객체 얻기
			String selectPassQu="select question from pass_question";
			pstmt=con.prepareStatement(selectPassQu);
		//4.쿼리 실행 후, 결과 얻기 :  : 바인드변수가 1개 존재(qu_num)
			rs=pstmt.executeQuery(); //select이기 때문에 executeQuery()
			while(rs.next()){
				list.add(rs.getString("question"));
			}//end if
		}finally{
		//5.연결끊기
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		
		return list;
	}//selectPassQu
	///////////////////selectPassQu()단위테스트(완료)/////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////IdVO////////////////////////
//			//String name, ssn;
//				List<String> list=new ArrayList<String>();
//				CustomerDAO cd=CustomerDAO.getInstance();
//				list=cd.selectPassQu();
//				System.out.println(list);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
	///////////////////////////////////////////////////////////////////////////
	
	/** => ItemInfoVO에 id를 추가하기
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
			String insertMenu="insert into product(item_code,item_name,item_image,item_info,price,hiredate,id,category_num) values(item_num,?,?,?,?,sysdate,?,?)";
			pstmt=con.prepareStatement(insertMenu);
			//4.쿼리 실행 후, 결과 얻기 : 이 경우에 바인드변수가 8개 존재(id,ssn,name,pass,pass_answer,image,info,qu_num)
			pstmt.setString(1, iiv.getItemName()); //<MemberJoinVO>의 getId()메소드를 호출하여 id변수에 담는다.
			pstmt.setString(2, iiv.getImage()); 
			pstmt.setString(3, iiv.getItemInfo()); 
			pstmt.setInt(4, iiv.getPrice()); 
			pstmt.setString(5, iiv.getId());
			pstmt.setInt(6, iiv.getItemType()); 
			
			pstmt.executeUpdate(); //insert라서 executeUpdate()를 호출
		}finally{
			//5.연결끊기
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
	}//insertItem
//	//////////////////////////////(완료)insertItem()메소드 단위테스트////////////////////////////// id받아오는 부분이 약간 이상한듯? VO수정해야하나?
//	public static void main(String[] args){
//		try {
//			//////////////////////////ItemInfoVO////////////////////////
//			//String itemName, itemType, itemInfo, hiredate, image;
//			//int price;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				ItemInfoVO iiv=new ItemInfoVO("라이츄인형", "110v에 충전하세요", "fkdlcb.jpg", "wkdwogns", 3, 30000);
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
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try{
			//1.드라이버 로딩
			//2.Connection 얻기
				con=getConnection();
			//3.쿼리문 생성객체 얻기
				String selectPreMember="select name,image,pass,qu_num,pass_answer,info from member where id=?";
				pstmt=con.prepareStatement(selectPreMember);
			//4.쿼리 실행 후, 결과 얻기 : 이 경우에 바인드변수가 1개 존재(id)
				pstmt.setString(1, id); 
				rs=pstmt.executeQuery();//select라서 executeQuery()를 호출
				
				if(rs.next()){
					miv=new MemberInfoVO();
					//이미지,비밀번호,비밀번호질문,비밀번호답변,자기소개
					miv.setName(rs.getString("name"));
					miv.setImage(rs.getString("image"));
					miv.setPass(rs.getString("pass"));
					miv.setQuNum(rs.getInt("qu_num"));
					miv.setPassAnswer(rs.getString("pass_answer"));
					miv.setInfo(rs.getString("info"));
				}//end if
		}finally{
			//5.연결끊기
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
			if( rs != null) { rs.close(); }
		}//end try
		
		return miv;
	}//selectPreMember
//	//////////////////////////(테스트완료)selectPreMember()메소드 단위테스트/////////////////////////////////
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
			String insertMember="insert into member(id,ssn,name,pass,pass_answer,image,info,qu_num) values(?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(insertMember);
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
//	////////////////////////////////(테스트완료)insertMember()메소드 단위테스트/////////////////////////////////////
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
//	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 회원의 정보를 수정하는 일
	 * @param MemberInfoVO
	 */
	public void updateMember(ChangeVO cv) throws SQLException{
		/////////////MemberInfoVO////////////////
		//String id, pass, passAnswer, image, info
		//int quNum
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
		//1.드라이버 로딩
		//2.Connection얻기
			con=getConnection();
		//3.쿼리문 생성객체 얻기(Statement와의 차이 : 어떤 쿼리문이 들어가는지 모른다)
			String updateMember="update member set name=?, pass=?,pass_answer=?,image=?,info=?,qu_num=? where id=?";
			pstmt=con.prepareStatement(updateMember);
		//4.쿼리 실행 후, 결과 얻기
			//바인드변수에 값을 설정해야한다.
			pstmt.setString(1, cv.getName());
			pstmt.setString(2,cv.getPass());   //VO에 딸린 값을 사용하기위해 매개변수로 EmpVO받아온것
			pstmt.setString(3,cv.getAnswer());  //VO에 딸린 값을 사용하기위해 매개변수로 EmpVO받아온것
			pstmt.setString(4,cv.getImage());  //VO에 딸린 값을 사용하기위해 매개변수로 EmpVO받아온것
			pstmt.setString(5,cv.getInfo());  //VO에 딸린 값을 사용하기위해 매개변수로 EmpVO받아온것
			pstmt.setInt(6,cv.getQuNum());  //VO에 딸린 값을 사용하기위해 매개변수로 EmpVO받아온것
			pstmt.setString(7,cv.getId());  //VO에 딸린 값을 사용하기위해 매개변수로 EmpVO받아온것
			pstmt.executeUpdate();
		}finally{
		//5.연결 끊기
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end finally
	}//updateMember
//	////////////////////////////////(테스트완료)updateMember()메소드 단위테스트///////////////////////////////
//	public static void main(String[] args){
//		try {
//			/////////////MemberInfoVO////////////////
//			//String id, pass, passAnswer, image, info
//			//int quNum
//				CustomerDAO cd=CustomerDAO.getInstance();
//				MemberInfoVO miv=new MemberInfoVO("hyunwan","tiger","피카츄","hyunwan.jpg","김현완입니다!!",3); // id, pass, passAnswer, image, info, quNum
//				cd.updateMember(miv);
//				System.out.println("회원정보 업데이트 성공!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/** 
	 * 입력한 메세지를 보낸메세지함에 추가하는 일
	 * @param MsgListVO
	 */
	public void insertSendMsg(MsgVO mv) throws SQLException{
		//////////////////////////MsgVO////////////////////////
		//String sendId, msg, id, itemCode;		
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
			//1.드라이버 로딩
			//2.Connection 얻기
				con=getConnection();
			//3.쿼리문 생성객체 얻기
				String insertSendMsg="insert into send_msg(msg_num,send_id,message,id,send_date,item_code) values(send_num,?,?,?,sysdate,?)";
				pstmt=con.prepareStatement(insertSendMsg);
			//4.쿼리 실행 후, 결과 얻기 : 바인드변수가 4개 존재(sendId, msg, id, itemCode)
				pstmt.setString(1, mv.getSendId()); 
				pstmt.setString(2, mv.getMsg()); 
				pstmt.setString(3, mv.getId()); 
				pstmt.setString(4, mv.getItemCode()); 
			
				pstmt.executeUpdate(); //insert라서 executeUpdate()를 호출
		}finally{
			//5.연결끊기
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
	}//insertSendMsg
	////////////////////////////////(완료)insertSendMsg()메소드 단위테스트///////////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////MsgVO////////////////////////
//			//String sendId, msg, id, itemCode;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				MsgVO mv=new MsgVO("dongha","ㅋㅋㅋㅋㅋㅋㅋㅋㅋ","hyunwan","HY_1705240024"); //sendId, msg, id, itemCode
//				cd.insertSendMsg(mv);
//				System.out.println("메세지 추가성공!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/** 
	 *  메세지정보를 받아 메시지테이블에 추가하는 일
	 * @param MsgListVO
	 */
	public void insertGetMsg(MsgVO mv) throws SQLException{
			//////////////////////////MsgVO////////////////////////
			//String sendId, msg, id, itemCode;		
			
			Connection con=null;
			PreparedStatement pstmt=null;
		
		try{
			//1.드라이버 로딩
			//2.Connection 얻기
				con=getConnection();
			//3.쿼리문 생성객체 얻기
				String insertSendMsg="insert into receive_msg(msg_num,send_id,message,id,send_date,item_code) values(receive_num,?,?,?,sysdate,?)";
				pstmt=con.prepareStatement(insertSendMsg);
			//4.쿼리 실행 후, 결과 얻기 : 바인드변수가 4개 존재(sendId, msg, id, itemCode)
				pstmt.setString(1, mv.getSendId()); 
				pstmt.setString(2, mv.getMsg()); 
				pstmt.setString(3, mv.getId()); 
				pstmt.setString(4, mv.getItemCode()); 
				
				pstmt.executeUpdate(); //insert라서 executeUpdate()를 호출
		}finally{
			//5.연결끊기
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
	}//insertGetMsg
	//////////////////////////////(완료)insertSendMsg()메소드 단위테스트///////////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////MsgVO////////////////////////
//			//String sendId, msg, id, itemCode;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				MsgVO mv=new MsgVO("dongha","ㅋㅋㅋㅋㅋㅋㅋㅋㅋ","hyunwan","HY_1705240024"); //sendId, msg, id, itemCode
//				cd.insertGetMsg(mv);
//				System.out.println("메세지 추가성공!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
//	///////////////////(테스트완료)selectMyId(IdVO iv)단위테스트/////////////////////////
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
//	////////////////////////////////////////////////////////////////////////////////////////////
	
	
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
//	///////////////////(테스트완료)selectMyPass(PassVO pv)단위테스트/////////////////////////
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
//	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 휴대전화번호를 추가하는 일
	 * @param PhoneVO
	 */
	public void insertPhone(PhoneVO phv) throws SQLException{
		//////////////PhoneVO//////////////
		//String id, phone, itemCode;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
			//1.드라이버 로딩
			//2.Connection 얻기
				con=getConnection();
			//3.쿼리문 생성객체 얻기
				String insertPhone="insert into buyer_contact values(?,?,?,sysdate)";//item_code,phone,id,date
				pstmt=con.prepareStatement(insertPhone);
			//4.쿼리 실행 후, 결과 얻기 : 바인드변수가 3개 존재
				pstmt.setString(1, phv.getItemCode()); 
				pstmt.setString(2, phv.getPhone()); 
				pstmt.setString(3, phv.getId()); 
				pstmt.executeUpdate(); //insert라서 executeUpdate()를 호출
		}finally{
			//5.연결끊기
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
	}//insertPhone
//  //////////////////////////////(테스트완료)insertPhone()메소드 단위테스트///////////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////PhoneVO//////////////
//			//String id, phone, itemCode;
//				CustomerDAO cd=CustomerDAO.getInstance();
//				PhoneVO phv=new PhoneVO("dongha","01077777777","HY_1705240025"); //id, phone, itemCode;
//				cd.insertPhone(phv);
//				System.out.println("휴대전화번호 추가 성공!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 휴대전화번호를 조회하는 일
	 * @param itemCode, buyerId
	 * @return String
	 */
	public String selectPhone(String itemCode,  String buyerId) throws SQLException{
		String result="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			//1.드라이버 로딩
			//2.Connection 얻기
				con=getConnection();
			//3.쿼리문 생성객체 얻기
				String selectPhone="select phone from buyer_contact where item_code=? and buyer_id=?";
				pstmt=con.prepareStatement(selectPhone);
			//4.쿼리 실행 후, 결과 얻기 :  : 바인드변수가 2개 존재 (item_code,buyer_id)
				pstmt.setString(1, itemCode); 
				pstmt.setString(2, buyerId);
				rs=pstmt.executeQuery(); //select이기 때문에 executeQuery()
				if(rs.next()){
					result=rs.getString("phone");
				}//end if
		}finally{
			//5.연결끊기
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		
		return result;
	}//selectPhone
//	///////////////////(테스트완료)selectPhone()단위테스트/////////////////////////
//	public static void main(String[] args){
//		try {
//			//////////////////////////IdVO////////////////////////
//			//String name, ssn;
//				String result="";
//				CustomerDAO cd=CustomerDAO.getInstance();
//				String itemCode="HY_1705240021";
//				result=cd.selectPhone(itemCode);
//				System.out.println(itemCode+" 회원의 핸드폰번호 : "+result);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 나의 판매대기 건수를 보는 일
	 * @param id
	 * @return MyInfoVO
	 */
	public int selectCntSellWait(String id) throws SQLException{
		int cntBuyWait=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=getConnection();
			
			String selectCnt="select count(*) count from product where sold_flag='n' group by id having id=?";
			pstmt=con.prepareStatement(selectCnt);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cntBuyWait=rs.getInt("count");
			}
			
		}finally{
			if( rs != null) {
				rs.close(); 
			}
			
			if( pstmt != null) {
				pstmt.close(); 
			}
			
			if( con != null) { 
				con.close(); 
			}
		}
		
		return cntBuyWait;
	}//selectMyInfo
	
	/**
	 * 나의 구매대기 건수를 보는 일
	 * @param id
	 * @return MyInfoVO
	 */
	public int selectCntBuyWait(String id) throws SQLException{
		int cntSellWait=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=getConnection();
			
			String selectCnt="select count(*) count from buyer_contact group by id having id=?";
			pstmt=con.prepareStatement(selectCnt);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cntSellWait=rs.getInt("count");
			}
			
		}finally{
			if( rs != null) {
				rs.close(); 
			}
			
			if( pstmt != null) {
				pstmt.close(); 
			}
			
			if( con != null) { 
				con.close(); 
			}
		}
		
		return cntSellWait;
	}//selectMyInfo
	
	/**
	 * 나의 안 읽은 메시지 건수를 보는 일
	 * @param id
	 * @return MyInfoVO
	 */
	public int selectCntMsg(String id) throws SQLException{
		int cntNonChk=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con=getConnection();
			
			String selectCnt="select count(*) count from receive_msg where msg_check_flag='n' group by id having id=?";
			pstmt=con.prepareStatement(selectCnt);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cntNonChk=rs.getInt("count");
			}
			
		}finally{
			if( rs != null) {
				rs.close(); 
			}
			
			if( pstmt != null) {
				pstmt.close(); 
			}
			
			if( con != null) { 
				con.close(); 
			}
		}
		
		return cntNonChk;
	}//selectMyInfo
	
	/** 
	 * 메세지의 내용을 조회하는 일
	 * @param id
	 * @return String
	 */
	public MsgViewVO selectReceiveMsgInfo(String msg_num) throws SQLException{
		MsgViewVO mvv=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			//1.드라이버 로딩
			//2.Connection 얻기
				con=getConnection();
			//3.쿼리문 생성객체 얻기
				String selectReceiveMsgInfo="select message, send_id from receive_msg where msg_num=?";
				
				pstmt=con.prepareStatement(selectReceiveMsgInfo);
			//4.쿼리 실행 후, 결과 얻기 :  : 바인드변수가 1개 존재 (msg_num)
				pstmt.setString(1, msg_num); 
				rs=pstmt.executeQuery(); //select이기 때문에 executeQuery()
				if(rs.next()){
					mvv=new MsgViewVO();
					mvv.setMsg(rs.getString("message"));
					mvv.setSendId(rs.getString("send_id"));
				}//end if
		}finally{
			//5.연결끊기
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		return mvv;
	}//selectMsgInfo
//	///////////////////(테스트완료)selectMsgInfo()단위테스트////////////////////////
//	public static void main(String[] args){
//		try {
//				String result="";
//				CustomerDAO cd=CustomerDAO.getInstance();
//				String id="dongha";
//				result=cd.selectMsgInfo(id);
//				System.out.println(id+" 회원이 받은 메세지는 : "+result);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	///////////////////////////////////////////////////////////////////////////////////////

	
	/** 
	 * 메세지의 내용을 조회하는 일
	 * @param id
	 * @return String
	 */
	public MsgViewVO selectSendMsgInfo(String msg_num) throws SQLException{
		MsgViewVO mvv=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			//1.드라이버 로딩
			//2.Connection 얻기
				con=getConnection();
			//3.쿼리문 생성객체 얻기
				String selectSendMsgInfo="select message, send_id from send_msg where msg_num=?";
				pstmt=con.prepareStatement(selectSendMsgInfo);
			//4.쿼리 실행 후, 결과 얻기 :  : 바인드변수가 1개 존재 (id)
				pstmt.setString(1, msg_num); 
				rs=pstmt.executeQuery(); //select이기 때문에 executeQuery()
				if(rs.next()){
					mvv=new MsgViewVO();
					mvv.setMsg(rs.getString("message"));
					mvv.setSendId(rs.getString("send_id"));
				}//end if
		}finally{
			//5.연결끊기
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		return mvv;
	}//selectMsgInfo
//	///////////////////(테스트완료)selectMsgInfo()단위테스트////////////////////////
//	public static void main(String[] args){
//		try {
//				String result="";
//				CustomerDAO cd=CustomerDAO.getInstance();
//				String id="dongha";
//				result=cd.selectMsgInfo(id);
//				System.out.println(id+" 회원이 받은 메세지는 : "+result);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
//	/////////////////////////////////////////////////////////
	
}//class
