package kr.co.sist.market.dao;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;

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

import kr.co.sist.market.vo.ItemListVO;

import kr.co.sist.market.vo.LoginVO;

import kr.co.sist.market.vo.MemberInfoVO;

import kr.co.sist.market.vo.MemberJoinVO;

import kr.co.sist.market.vo.MsgVO;

import kr.co.sist.market.vo.MsgViewVO;

import kr.co.sist.market.vo.PassVO;

import kr.co.sist.market.vo.PhoneVO;

/**
 * 고객중심의 일을 처리하는 DAO
 * @author 김현완
 */
public class CustomerDAO {
	private static CustomerDAO c_dao;
	
	// 기본생성자
	private CustomerDAO() {
	}// CustomerDAO
	
	// Instance얻기
	public static CustomerDAO getInstance() {
		if (c_dao == null) {
			c_dao = new CustomerDAO();
		} // end if
		return c_dao;
	}// getInstatnce()

	// Connection얻기
	private Connection getConnection() throws SQLException {
		Connection con = null;
		Properties prop = new Properties();

		try {
			File file = new File(System.getProperty("user.dir") + "/src/kr/co/sist/market/dao/market.properties");
			if (file.exists()) {
				prop.load(new FileInputStream(file));
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String id = prop.getProperty("dboid");
				String pass = prop.getProperty("dbopwd");

				try {
					Class.forName(driver); // 드라이버 로딩
					con = DriverManager.getConnection(url, id, pass);
				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩에서 실패하네 자꾸..");
					e.printStackTrace();
				} // end catch
			} else {
				JOptionPane.showMessageDialog(null, "설정파일의 경로를 확인하세요.");
			} // end if
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch
		return con;
	}// getConnection()


	////////////////메소드 시작//////////////////
	/**
	 * 회원탈퇴하기
	 * @param itemCode
	 * @throws SQLException
	 */
	public int deleteCustomer(String id, String pass) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			con = getConnection();
			String deleteCustomer = "delete from member where  id=? and pass=? ";
			pstmt = con.prepareStatement(deleteCustomer);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			result = pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		}
		return result;
	}


	/**
	 * 로그인 성공여부를 결정하는 일
	 * 아이디가 존재하고, 그 아이디에 해당하는 비밀번호와 일치하면 로그인 성공
	 * @return boolean
	 * @param LoginVO
	 * @author 김현완
	 */
	public boolean selectLogin(LoginVO lv) throws SQLException {
		////////////////////////// LoginVO////////////////////////
		// String id, pass;
		Boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String selectLogin = "select name from member where id=? and pass=? ";
			pstmt = con.prepareStatement(selectLogin);
			// 4.쿼리 실행 후, 결과 얻기 : 바인드변수 id,pass
			pstmt.setString(1, lv.getId());
			pstmt.setString(2, lv.getPass());
			rs = pstmt.executeQuery(); // select이기 때문에 executeQuery()
			if (rs.next()) {
				flag = true;
			} // end if
		} finally {
			// 5.연결끊기
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
		return flag;
	}// selectLogin


	/**
	 * 로그인 성공여부를 결정하는 일
	 * 아이디가 존재하고, 그 아이디에 해당하는 비밀번호와 일치하면 로그인 성공
	 * @return boolean
	 * @param LoginVO
	 */
	public boolean selectDoubleId(String id) throws SQLException {
		////////////////////////// LoginVO////////////////////////
		// String id, pass;
		Boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String selectLogin = "select name from member where id=?";
			pstmt = con.prepareStatement(selectLogin);
			// 4.쿼리 실행 후, 결과 얻기 : 바인드변수 id,pass
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // select이기 때문에 executeQuery()
			if (rs.next()) {
				flag = true;
			} // end if
		} finally {
			// 5.연결끊기
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
		return flag;
	}// selectLogin

	/**
	 * 비밀번호 질문을 선택하여 불러오는 일
	 * @param int
	 * @return String
	 * @author 김현완
	 */
	public List<String> selectPassQu() throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String selectPassQu = "select question from pass_question";
			pstmt = con.prepareStatement(selectPassQu);
			// 4.쿼리 실행 후, 결과 얻기 : : 바인드변수가 1개 존재(qu_num)
			rs = pstmt.executeQuery(); // select이기 때문에 executeQuery()
			while (rs.next()) {
				list.add(rs.getString("question"));
			} // end if
		} finally {
			// 5.연결끊기
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
		return list;
	}// selectPassQu

	
	/**
	 * 판매할 상품을 물품테이블에 등록하는 일
	 * @param ItemInfoVO
	 * @author 김현완
	 */
	public void insertItem(ItemInfoVO iiv) throws SQLException {
		////////////////////////// ItemInfoVO////////////////////////
		// String itemName, itemType, itemInfo, hiredate, image;
		// int price;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String insertMenu = "insert into product(item_code,item_name,item_image,item_info,price,hiredate,id,category_num) values(item_num,?,?,?,?,sysdate,?,?)";
			pstmt = con.prepareStatement(insertMenu);
			// 4.쿼리 실행 후, 결과 얻기 : 이 경우에 바인드변수가 8개 존재(id,ssn,name,pass,pass_answer,image,info,qu_num)
			pstmt.setString(1, iiv.getItemName()); 
			pstmt.setString(2, iiv.getImage());
			pstmt.setString(3, iiv.getItemInfo());
			pstmt.setInt(4, iiv.getPrice());
			pstmt.setString(5, iiv.getId());
			pstmt.setInt(6, iiv.getItemType());
			pstmt.executeUpdate(); // insert라서 executeUpdate()를 호출
		} finally {
			// 5.연결끊기
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
	}// insertItem

	/**
	 * 정보를 수정할 회원의 기존 정보를 불러오는 일
	 * @param id
	 * @return MemberInfoVO
	 * @author 김현완
	 */
	public MemberInfoVO selectPreMember(String id) throws SQLException {
		MemberInfoVO miv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String selectPreMember = "select name,image,pass,qu_num,pass_answer,info from member where id=?";
			pstmt = con.prepareStatement(selectPreMember);
			// 4.쿼리 실행 후, 결과 얻기 : 이 경우에 바인드변수가 1개 존재(id)
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();// select라서 executeQuery()를 호출
			if (rs.next()) {
				miv = new MemberInfoVO();
				// 이미지,비밀번호,비밀번호질문,비밀번호답변,자기소개
				miv.setName(rs.getString("name"));
				miv.setImage(rs.getString("image"));
				miv.setPass(rs.getString("pass"));
				miv.setQuNum(rs.getInt("qu_num"));
				miv.setPassAnswer(rs.getString("pass_answer"));
				miv.setInfo(rs.getString("info"));
			} // end if
		} finally {
			// 5.연결끊기
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
			if (rs != null) {
				rs.close();
			}
		} // end try
		return miv;
	}// selectPreMember

	
	/**
	 * 입력한 회원정보로 회원테이블에 추가하는 일
	 * @param MemberJoinVO
	 * @author 김현완
	 */
	public void insertMember(MemberJoinVO mjv) throws SQLException {
		/////////////////////// MemberJoinVO/////////////////////
		// String id, ssn, name, pass, passAnswer, image, info
		// int quNum
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String insertMember = "insert into member(id,ssn,name,pass,pass_answer,image,info,qu_num) values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(insertMember);
			// 4.쿼리 실행 후, 결과 얻기 : 이 경우에 바인드변수가 8개
			// 존재(id,ssn,name,pass,pass_answer,image,info,qu_num)
			pstmt.setString(1, mjv.getId()); 
			pstmt.setString(2, mjv.getSsn()); 
			pstmt.setString(3, mjv.getName()); 
			pstmt.setString(4, mjv.getPass()); 
			pstmt.setString(5, mjv.getPassAnswer()); 
			pstmt.setString(6, mjv.getImage()); 
			pstmt.setString(7, mjv.getInfo()); 
			pstmt.setInt(8, mjv.getQuNum()); 
			pstmt.executeUpdate(); 
		} finally {
			// 5.연결끊기
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
	}// insertMember

	
	/**
	 * 회원의 정보를 수정하는 일
	 * @param MemberInfoVO
	 */
	public void updateMember(ChangeVO cv) throws SQLException {
		///////////// MemberInfoVO////////////////
		// String id, pass, passAnswer, image, info
		// int quNum
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1.드라이버 로딩
			// 2.Connection얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String updateMember = "update member set name=?, pass=?,pass_answer=?,image=?,info=?,qu_num=? where id=?";
			pstmt = con.prepareStatement(updateMember);
			// 4.쿼리 실행 후, 결과 얻기
			pstmt.setString(1, cv.getName());
			pstmt.setString(2, cv.getPass()); 
			pstmt.setString(3, cv.getAnswer());
			pstmt.setString(4, cv.getImage()); 
			pstmt.setString(5, cv.getInfo()); 
			pstmt.setInt(6, cv.getQuNum()); 
			pstmt.setString(7, cv.getId()); 
			pstmt.executeUpdate();
		} finally {
			// 5.연결 끊기
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
	}// updateMember

	
	/**
	 * 입력한 메세지를 보낸메세지함에 추가하는 일
	 * @param MsgListVO
	 * @author 김현완
	 */
	public void insertSendMsg(MsgVO mv) throws SQLException {
		////////////////////////// MsgVO////////////////////////
		// String sendId, msg, id, itemCode;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String insertSendMsg = "insert into send_msg(msg_num,send_id,message,id,send_date,item_code) values(send_num,?,?,?,sysdate,?)";
			pstmt = con.prepareStatement(insertSendMsg);
			// 4.쿼리 실행 후, 결과 얻기 : 바인드변수가 4개 존재(sendId, msg, id, itemCode)
			pstmt.setString(1, mv.getSendId());
			pstmt.setString(2, mv.getMsg());
			pstmt.setString(3, mv.getId());
			pstmt.setString(4, mv.getItemCode());
			pstmt.executeUpdate(); // insert라서 executeUpdate()를 호출
		} finally {
			// 5.연결끊기
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
	}// insertSendMsg

	
	/**
	 * 메세지정보를 받아 메시지테이블에 추가하는 일
	 * @param MsgListVO
	 * @author 김현완
	 */
	public void insertGetMsg(MsgVO mv) throws SQLException {
		////////////////////////// MsgVO////////////////////////
		// String sendId, msg, id, itemCode;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String insertSendMsg = "insert into receive_msg(msg_num,send_id,message,id,send_date,item_code) values(receive_num,?,?,?,sysdate,?)";
			pstmt = con.prepareStatement(insertSendMsg);
			// 4.쿼리 실행 후, 결과 얻기 : 바인드변수가 4개 존재(sendId, msg, id, itemCode)
			pstmt.setString(1, mv.getSendId());
			pstmt.setString(2, mv.getMsg());
			pstmt.setString(3, mv.getId());
			pstmt.setString(4, mv.getItemCode());
			pstmt.executeUpdate(); // insert라서 executeUpdate()를 호출
		} finally {
			// 5.연결끊기
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
	}// insertGetMsg

	
	/**
	 * 자신의 아이디를 조회하는 일
	 * @param IdVO
	 * @return String
	 * @author 김현완
	 */
	public String selectMyId(IdVO iv) throws SQLException {
		////////////////////////// IdVO////////////////////////
		// String name, ssn;
		String result = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String selectMyId = "select id from member where name=? and ssn=?";
			pstmt = con.prepareStatement(selectMyId);
			// 4.쿼리 실행 후, 결과 얻기 : : 바인드변수가 2개 존재(name,ssn)
			pstmt.setString(1, iv.getName()); 
			pstmt.setString(2, iv.getSsn()); 
			rs = pstmt.executeQuery(); 
			if (rs.next()) {
				result = rs.getString("id");
			} // end if
		} finally {
			// 5.연결끊기
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
		return result;
	}// selectMyId

	
	/**
	 * 자신의 비밀번호를 조회하는 일
	 * @param PassVO
	 * @return String
	 * @author 김현완
	 */
	public String selectMyPass(PassVO pv) throws SQLException {
		////////////////////////// PassVO////////////////////////
		// String name, ssn, id, passAnswer;
		// int quNum;
		String result = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String selectMyPass = "select pass from  member where name=? and ssn=? and id=? and pass_answer=? and qu_num=?";
			pstmt = con.prepareStatement(selectMyPass);
			// 4.쿼리 실행 후, 결과 얻기 : : 바인드변수가 5개 존재(name, ssn, id, passAnswer,quNum)
			pstmt.setString(1, pv.getName()); 
			pstmt.setString(2, pv.getSsn()); 
			pstmt.setString(3, pv.getId()); 
			pstmt.setString(4, pv.getPassAnswer()); 
			pstmt.setInt(5, pv.getQuNum()); 
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("pass");
			} // end if
		} finally {
			// 5.연결끊기
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
		return result;
	}// selectMyPass


	/**
	 * 휴대전화번호를 추가하는 일
	 * @param PhoneVO
	 * @author 김현완
	 */
	public void insertPhone(PhoneVO phv) throws SQLException {
		////////////// PhoneVO//////////////
		// String id, phone, itemCode;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String insertPhone = "insert into buyer_contact values(?,?,?,sysdate)";// item_code,phone,id,date
			pstmt = con.prepareStatement(insertPhone);
			// 4.쿼리 실행 후, 결과 얻기 : 바인드변수가 3개 존재
			pstmt.setString(1, phv.getItemCode());
			pstmt.setString(2, phv.getPhone());
			pstmt.setString(3, phv.getId());
			pstmt.executeUpdate(); // insert라서 executeUpdate()를 호출
		} finally {
			// 5.연결끊기
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
	}// insertPhone


	/**
	 * 휴대전화번호를 조회하는 일
	 * @param itemCode,buyerId
	 * @return String
	 * @author 김현완
	 */
	public String selectPhone(String itemCode, String buyerId) throws SQLException {
		String result = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String selectPhone = "select phone from buyer_contact where item_code=? and id=?";
			pstmt = con.prepareStatement(selectPhone);
			// 4.쿼리 실행 후, 결과 얻기 : : 바인드변수가 2개 존재 (item_code,id)
			pstmt.setString(1, itemCode);
			pstmt.setString(2, buyerId);
			rs = pstmt.executeQuery(); // select이기 때문에 executeQuery()
			if (rs.next()) {
				result = rs.getString("phone");
			} // end if
		} finally {
			// 5.연결끊기
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
		return result;
	}// selectPhone

	
	/**
	 * 나의 판매대기 건수를 보는 일
	 * @param id
	 * @return MyInfoVO
	 */
	public int selectCntSellWait(String id) throws SQLException {
		int cntBuyWait = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			String selectCnt = "select count(*) count from product where sold_flag='n' group by id having id=?";
			pstmt = con.prepareStatement(selectCnt);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cntBuyWait = rs.getInt("count");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return cntBuyWait;
	}// selectMyInfo

	
	/**
	 * 나의 구매대기 건수를 보는 일
	 * @param id
	 * @return MyInfoVO
	 */
	public int selectCntBuyWait(String id) throws SQLException {
		int cntSellWait = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String selectCnt = "select count(*) count from product p, buyer_contact b where (b.item_code=p.item_code) and p.sold_flag='n' and b.id=? group by b.id";
			pstmt = con.prepareStatement(selectCnt);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cntSellWait = rs.getInt("count");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return cntSellWait;
	}// selectMyInfo

	
	/**
	 * 나의 안 읽은 메시지 건수를 보는 일
	 * @param id
	 * @return MyInfoVO
	 */
	public int selectCntMsg(String id) throws SQLException {
		int cntNonChk = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			String selectCnt = "select count(*) count from receive_msg where msg_check_flag='n' group by id having id=?";
			pstmt = con.prepareStatement(selectCnt);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cntNonChk = rs.getInt("count");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return cntNonChk;
	}// selectMyInfo

	
	/**
	 * 받은 메세지의 내용을 조회하는 일
	 * @param id
	 * @return String
	 */
	public MsgViewVO selectReceiveMsgInfo(String msg_num) throws SQLException {
		MsgViewVO mvv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String selectReceiveMsgInfo = "select message, send_id from receive_msg where msg_num=?";
			pstmt = con.prepareStatement(selectReceiveMsgInfo);
			// 4.쿼리 실행 후, 결과 얻기 : : 바인드변수가 1개 존재 (msg_num)
			pstmt.setString(1, msg_num);
			rs = pstmt.executeQuery(); // select이기 때문에 executeQuery()
			if (rs.next()) {
				mvv = new MsgViewVO();
				mvv.setMsg(rs.getString("message"));
				mvv.setSendId(rs.getString("send_id"));
			} // end if
		} finally {
			// 5.연결끊기
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
		return mvv;
	}// selectMsgInfo


	/**
	 * 보낸 메세지의 내용을 조회하는 일
	 * @param id
	 * @return String
	 */
	public MsgViewVO selectSendMsgInfo(String msg_num) throws SQLException {
		MsgViewVO mvv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩
			// 2.Connection 얻기
			con = getConnection();
			// 3.쿼리문 생성객체 얻기
			String selectSendMsgInfo = "select message, send_id from send_msg where msg_num=?";
			pstmt = con.prepareStatement(selectSendMsgInfo);
			// 4.쿼리 실행 후, 결과 얻기 : : 바인드변수가 1개 존재 (id)
			pstmt.setString(1, msg_num);
			rs = pstmt.executeQuery(); // select이기 때문에 executeQuery()
			if (rs.next()) {
				mvv = new MsgViewVO();
				mvv.setMsg(rs.getString("message"));
				mvv.setSendId(rs.getString("send_id"));
			} // end if
		} finally {
			// 5.연결끊기
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
		return mvv;
	}// selectMsgInfo


	/**
	 * 내가 판매중인 물품을 보여주는 일
	 * @param id
	 * @return ItemListVO
	 * @throws SQLException
	 * @author 김현완
	 */
	public List<ItemListVO> selectMyItemList(String id) throws SQLException {
		List<ItemListVO> list = new ArrayList<ItemListVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			String selectItem = "select item_name, item_code, item_info, hiredate, item_image, price from product where id=? and sold_flag='n'";
			pstmt = con.prepareStatement(selectItem);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			ItemListVO ilv = null;
			while (rs.next()) {
				ilv = new ItemListVO();
				ilv.setItemName(rs.getString("item_name"));
				ilv.setItemCode(rs.getString("item_code"));
				ilv.setItemInfo(rs.getString("item_info"));
				ilv.setHiredate(rs.getString("hiredate"));
				ilv.setImage(rs.getString("item_image"));
				ilv.setPrice(rs.getInt("price"));
				list.add(ilv);
			} // end while
		} finally {
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
		return list;
	}// selectMyItemList

	
	/**
	 * ID에 해당하는 이미지 이름을 불러오는 일
	 * @param id
	 * @return String
	 * @author 김현완
	 */
	public String selectImgName(String id) throws SQLException {
		String result = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			String selectImgName = "select image from member where id=?";
			pstmt = con.prepareStatement(selectImgName);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("Image");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return result;
	}// selectImgName

}// class
