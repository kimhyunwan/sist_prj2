package kr.co.sist.market.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import kr.co.sist.market.vo.ItemInfoVO;
import kr.co.sist.market.vo.LoginVO;
import kr.co.sist.market.vo.MemberInfoVO;


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
				File file=new File("../market_db.properties");
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 로그인 성공여부를 결정하는 일
	 * @return boolean
	 * @param LoginVO
	 */
	public boolean selectLogin(LoginVO lv){
		Boolean flag=false;
		
		return flag;
	}//selectLogin
	

	/**
	 * 비밀번호 질문을 선택하여 불러오는 일
	 * @return List<String>
	 */
	public List<String> selectPassQu(){
		List<String> list=new ArrayList<>();
		
		return list;
	}//selectPassQu
	
	
	/**
	 * 판매할 상품을 물품테이블에 등록하는 일
	 * @param itemInfoVO
	 */
	public void insertItem(ItemInfoVO iiv){
		
	}//insertItem
	
	
	/**
	 * 정보를 수정할 회원의 기존 정보를 불러오는 일
	 * @param id
	 * @return MemberInfoVO
	 */
	public MemberInfoVO selectPreMember(String id){
		MemberInfoVO miv=null;
		
		return miv;
	}//selectPreMember
//	
//	
//	/**
//	 * 입력한 회원정보로 회원테이블에 추가하는 일
//	 * @param MemberJoinVO
//	 */
//	public void insertMember(MemberJoinVO){
//		
//	}//insertMember
//	
//	
//	/**
//	 * 회원의 정보를 수정하는 일
//	 * @param MemberInfoVO
//	 */
//	public void updateMember(MemberInfoVO){
//		
//	}//updateMember
//	
//	
//	/**
//	 * 입력한 메세지를 보낸메세지에 추가하는 일
//	 * @param MsgListVO
//	 */
//	public void insertSendMsg(MsgListVO){
//		
//	}//insertSendMsg
//	
//	
//	/**
//	 *  메세지정보를 받은 메세지에 추가하는 일
//	 * @param MsgListVO
//	 */
//	public void insertGetMsg(MsgListVO){
//		
//	}//insertGetMsg
//	
//	
//	/**
//	 * 자신의 아이디를 조회하는 일
//	 * @param IdVO
//	 * @return String
//	 */
//	public String selectMyId(IdVO){
//		
//	}
//	
//	
//	/**
//	 * 자신의 비밀번호를 조회하는 일
//	 * @param PassVO
//	 * @return String
//	 */
//	public String selectMyPass(PassVO){
//		
//	}
//	
//	
//	/**
//	 * 휴대전화번호를 추가하는 일
//	 * @param PhoneVO
//	 */
//	public void insertPhone(PhoneVO){
//	
//	}
//	
//	
//	/**
//	 * 휴대전화번호를 조회하는 일
//	 * @param itemCode
//	 * @return String
//	 */
//	public String selectPhone(String itemCode){
//	
//	}
//	
//	
//	/**
//	 * 나의 회원정보를 조회하는 일
//	 * @param id
//	 * @return MyInfoVO
//	 */
//	public MyInfoVO selectMyInfo(String id){
//	
//	}
//	
//	
//	/**
//	 * 메세지의 내용을 조회하는 일
//	 * @param id
//	 * @return String
//	 */
//	public String selectMsgInfo(String id){
//	
//	}
	
	
}//class
