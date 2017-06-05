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
 * ���߽��� ���� ó���ϴ� DAO
 * @author ������
 */
public class CustomerDAO {
	private static CustomerDAO c_dao;
	
	// �⺻������
	private CustomerDAO() {
	}// CustomerDAO
	
	// Instance���
	public static CustomerDAO getInstance() {
		if (c_dao == null) {
			c_dao = new CustomerDAO();
		} // end if
		return c_dao;
	}// getInstatnce()

	// Connection���
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
					Class.forName(driver); // ����̹� �ε�
					con = DriverManager.getConnection(url, id, pass);
				} catch (ClassNotFoundException e) {
					System.out.println("����̹� �ε����� �����ϳ� �ڲ�..");
					e.printStackTrace();
				} // end catch
			} else {
				JOptionPane.showMessageDialog(null, "���������� ��θ� Ȯ���ϼ���.");
			} // end if
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch
		return con;
	}// getConnection()


	////////////////�޼ҵ� ����//////////////////
	/**
	 * ȸ��Ż���ϱ�
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
	 * �α��� �������θ� �����ϴ� ��
	 * ���̵� �����ϰ�, �� ���̵� �ش��ϴ� ��й�ȣ�� ��ġ�ϸ� �α��� ����
	 * @return boolean
	 * @param LoginVO
	 * @author ������
	 */
	public boolean selectLogin(LoginVO lv) throws SQLException {
		////////////////////////// LoginVO////////////////////////
		// String id, pass;
		Boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String selectLogin = "select name from member where id=? and pass=? ";
			pstmt = con.prepareStatement(selectLogin);
			// 4.���� ���� ��, ��� ��� : ���ε庯�� id,pass
			pstmt.setString(1, lv.getId());
			pstmt.setString(2, lv.getPass());
			rs = pstmt.executeQuery(); // select�̱� ������ executeQuery()
			if (rs.next()) {
				flag = true;
			} // end if
		} finally {
			// 5.�������
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
	 * �α��� �������θ� �����ϴ� ��
	 * ���̵� �����ϰ�, �� ���̵� �ش��ϴ� ��й�ȣ�� ��ġ�ϸ� �α��� ����
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
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String selectLogin = "select name from member where id=?";
			pstmt = con.prepareStatement(selectLogin);
			// 4.���� ���� ��, ��� ��� : ���ε庯�� id,pass
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // select�̱� ������ executeQuery()
			if (rs.next()) {
				flag = true;
			} // end if
		} finally {
			// 5.�������
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
	 * ��й�ȣ ������ �����Ͽ� �ҷ����� ��
	 * @param int
	 * @return String
	 * @author ������
	 */
	public List<String> selectPassQu() throws SQLException {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String selectPassQu = "select question from pass_question";
			pstmt = con.prepareStatement(selectPassQu);
			// 4.���� ���� ��, ��� ��� : : ���ε庯���� 1�� ����(qu_num)
			rs = pstmt.executeQuery(); // select�̱� ������ executeQuery()
			while (rs.next()) {
				list.add(rs.getString("question"));
			} // end if
		} finally {
			// 5.�������
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
	 * �Ǹ��� ��ǰ�� ��ǰ���̺� ����ϴ� ��
	 * @param ItemInfoVO
	 * @author ������
	 */
	public void insertItem(ItemInfoVO iiv) throws SQLException {
		////////////////////////// ItemInfoVO////////////////////////
		// String itemName, itemType, itemInfo, hiredate, image;
		// int price;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String insertMenu = "insert into product(item_code,item_name,item_image,item_info,price,hiredate,id,category_num) values(item_num,?,?,?,?,sysdate,?,?)";
			pstmt = con.prepareStatement(insertMenu);
			// 4.���� ���� ��, ��� ��� : �� ��쿡 ���ε庯���� 8�� ����(id,ssn,name,pass,pass_answer,image,info,qu_num)
			pstmt.setString(1, iiv.getItemName()); 
			pstmt.setString(2, iiv.getImage());
			pstmt.setString(3, iiv.getItemInfo());
			pstmt.setInt(4, iiv.getPrice());
			pstmt.setString(5, iiv.getId());
			pstmt.setInt(6, iiv.getItemType());
			pstmt.executeUpdate(); // insert�� executeUpdate()�� ȣ��
		} finally {
			// 5.�������
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
	}// insertItem

	/**
	 * ������ ������ ȸ���� ���� ������ �ҷ����� ��
	 * @param id
	 * @return MemberInfoVO
	 * @author ������
	 */
	public MemberInfoVO selectPreMember(String id) throws SQLException {
		MemberInfoVO miv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String selectPreMember = "select name,image,pass,qu_num,pass_answer,info from member where id=?";
			pstmt = con.prepareStatement(selectPreMember);
			// 4.���� ���� ��, ��� ��� : �� ��쿡 ���ε庯���� 1�� ����(id)
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();// select�� executeQuery()�� ȣ��
			if (rs.next()) {
				miv = new MemberInfoVO();
				// �̹���,��й�ȣ,��й�ȣ����,��й�ȣ�亯,�ڱ�Ұ�
				miv.setName(rs.getString("name"));
				miv.setImage(rs.getString("image"));
				miv.setPass(rs.getString("pass"));
				miv.setQuNum(rs.getInt("qu_num"));
				miv.setPassAnswer(rs.getString("pass_answer"));
				miv.setInfo(rs.getString("info"));
			} // end if
		} finally {
			// 5.�������
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
	 * �Է��� ȸ�������� ȸ�����̺� �߰��ϴ� ��
	 * @param MemberJoinVO
	 * @author ������
	 */
	public void insertMember(MemberJoinVO mjv) throws SQLException {
		/////////////////////// MemberJoinVO/////////////////////
		// String id, ssn, name, pass, passAnswer, image, info
		// int quNum
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String insertMember = "insert into member(id,ssn,name,pass,pass_answer,image,info,qu_num) values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(insertMember);
			// 4.���� ���� ��, ��� ��� : �� ��쿡 ���ε庯���� 8��
			// ����(id,ssn,name,pass,pass_answer,image,info,qu_num)
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
			// 5.�������
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
	}// insertMember

	
	/**
	 * ȸ���� ������ �����ϴ� ��
	 * @param MemberInfoVO
	 */
	public void updateMember(ChangeVO cv) throws SQLException {
		///////////// MemberInfoVO////////////////
		// String id, pass, passAnswer, image, info
		// int quNum
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1.����̹� �ε�
			// 2.Connection���
			con = getConnection();
			// 3.������ ������ü ���
			String updateMember = "update member set name=?, pass=?,pass_answer=?,image=?,info=?,qu_num=? where id=?";
			pstmt = con.prepareStatement(updateMember);
			// 4.���� ���� ��, ��� ���
			pstmt.setString(1, cv.getName());
			pstmt.setString(2, cv.getPass()); 
			pstmt.setString(3, cv.getAnswer());
			pstmt.setString(4, cv.getImage()); 
			pstmt.setString(5, cv.getInfo()); 
			pstmt.setInt(6, cv.getQuNum()); 
			pstmt.setString(7, cv.getId()); 
			pstmt.executeUpdate();
		} finally {
			// 5.���� ����
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally
	}// updateMember

	
	/**
	 * �Է��� �޼����� �����޼����Կ� �߰��ϴ� ��
	 * @param MsgListVO
	 * @author ������
	 */
	public void insertSendMsg(MsgVO mv) throws SQLException {
		////////////////////////// MsgVO////////////////////////
		// String sendId, msg, id, itemCode;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String insertSendMsg = "insert into send_msg(msg_num,send_id,message,id,send_date,item_code) values(send_num,?,?,?,sysdate,?)";
			pstmt = con.prepareStatement(insertSendMsg);
			// 4.���� ���� ��, ��� ��� : ���ε庯���� 4�� ����(sendId, msg, id, itemCode)
			pstmt.setString(1, mv.getSendId());
			pstmt.setString(2, mv.getMsg());
			pstmt.setString(3, mv.getId());
			pstmt.setString(4, mv.getItemCode());
			pstmt.executeUpdate(); // insert�� executeUpdate()�� ȣ��
		} finally {
			// 5.�������
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
	}// insertSendMsg

	
	/**
	 * �޼��������� �޾� �޽������̺� �߰��ϴ� ��
	 * @param MsgListVO
	 * @author ������
	 */
	public void insertGetMsg(MsgVO mv) throws SQLException {
		////////////////////////// MsgVO////////////////////////
		// String sendId, msg, id, itemCode;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String insertSendMsg = "insert into receive_msg(msg_num,send_id,message,id,send_date,item_code) values(receive_num,?,?,?,sysdate,?)";
			pstmt = con.prepareStatement(insertSendMsg);
			// 4.���� ���� ��, ��� ��� : ���ε庯���� 4�� ����(sendId, msg, id, itemCode)
			pstmt.setString(1, mv.getSendId());
			pstmt.setString(2, mv.getMsg());
			pstmt.setString(3, mv.getId());
			pstmt.setString(4, mv.getItemCode());
			pstmt.executeUpdate(); // insert�� executeUpdate()�� ȣ��
		} finally {
			// 5.�������
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
	}// insertGetMsg

	
	/**
	 * �ڽ��� ���̵� ��ȸ�ϴ� ��
	 * @param IdVO
	 * @return String
	 * @author ������
	 */
	public String selectMyId(IdVO iv) throws SQLException {
		////////////////////////// IdVO////////////////////////
		// String name, ssn;
		String result = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String selectMyId = "select id from member where name=? and ssn=?";
			pstmt = con.prepareStatement(selectMyId);
			// 4.���� ���� ��, ��� ��� : : ���ε庯���� 2�� ����(name,ssn)
			pstmt.setString(1, iv.getName()); 
			pstmt.setString(2, iv.getSsn()); 
			rs = pstmt.executeQuery(); 
			if (rs.next()) {
				result = rs.getString("id");
			} // end if
		} finally {
			// 5.�������
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
	 * �ڽ��� ��й�ȣ�� ��ȸ�ϴ� ��
	 * @param PassVO
	 * @return String
	 * @author ������
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
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String selectMyPass = "select pass from  member where name=? and ssn=? and id=? and pass_answer=? and qu_num=?";
			pstmt = con.prepareStatement(selectMyPass);
			// 4.���� ���� ��, ��� ��� : : ���ε庯���� 5�� ����(name, ssn, id, passAnswer,quNum)
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
			// 5.�������
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
	 * �޴���ȭ��ȣ�� �߰��ϴ� ��
	 * @param PhoneVO
	 * @author ������
	 */
	public void insertPhone(PhoneVO phv) throws SQLException {
		////////////// PhoneVO//////////////
		// String id, phone, itemCode;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String insertPhone = "insert into buyer_contact values(?,?,?,sysdate)";// item_code,phone,id,date
			pstmt = con.prepareStatement(insertPhone);
			// 4.���� ���� ��, ��� ��� : ���ε庯���� 3�� ����
			pstmt.setString(1, phv.getItemCode());
			pstmt.setString(2, phv.getPhone());
			pstmt.setString(3, phv.getId());
			pstmt.executeUpdate(); // insert�� executeUpdate()�� ȣ��
		} finally {
			// 5.�������
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end try
	}// insertPhone


	/**
	 * �޴���ȭ��ȣ�� ��ȸ�ϴ� ��
	 * @param itemCode,buyerId
	 * @return String
	 * @author ������
	 */
	public String selectPhone(String itemCode, String buyerId) throws SQLException {
		String result = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String selectPhone = "select phone from buyer_contact where item_code=? and id=?";
			pstmt = con.prepareStatement(selectPhone);
			// 4.���� ���� ��, ��� ��� : : ���ε庯���� 2�� ���� (item_code,id)
			pstmt.setString(1, itemCode);
			pstmt.setString(2, buyerId);
			rs = pstmt.executeQuery(); // select�̱� ������ executeQuery()
			if (rs.next()) {
				result = rs.getString("phone");
			} // end if
		} finally {
			// 5.�������
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
	 * ���� �ǸŴ�� �Ǽ��� ���� ��
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
	 * ���� ���Ŵ�� �Ǽ��� ���� ��
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
	 * ���� �� ���� �޽��� �Ǽ��� ���� ��
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
	 * ���� �޼����� ������ ��ȸ�ϴ� ��
	 * @param id
	 * @return String
	 */
	public MsgViewVO selectReceiveMsgInfo(String msg_num) throws SQLException {
		MsgViewVO mvv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String selectReceiveMsgInfo = "select message, send_id from receive_msg where msg_num=?";
			pstmt = con.prepareStatement(selectReceiveMsgInfo);
			// 4.���� ���� ��, ��� ��� : : ���ε庯���� 1�� ���� (msg_num)
			pstmt.setString(1, msg_num);
			rs = pstmt.executeQuery(); // select�̱� ������ executeQuery()
			if (rs.next()) {
				mvv = new MsgViewVO();
				mvv.setMsg(rs.getString("message"));
				mvv.setSendId(rs.getString("send_id"));
			} // end if
		} finally {
			// 5.�������
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
	 * ���� �޼����� ������ ��ȸ�ϴ� ��
	 * @param id
	 * @return String
	 */
	public MsgViewVO selectSendMsgInfo(String msg_num) throws SQLException {
		MsgViewVO mvv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.����̹� �ε�
			// 2.Connection ���
			con = getConnection();
			// 3.������ ������ü ���
			String selectSendMsgInfo = "select message, send_id from send_msg where msg_num=?";
			pstmt = con.prepareStatement(selectSendMsgInfo);
			// 4.���� ���� ��, ��� ��� : : ���ε庯���� 1�� ���� (id)
			pstmt.setString(1, msg_num);
			rs = pstmt.executeQuery(); // select�̱� ������ executeQuery()
			if (rs.next()) {
				mvv = new MsgViewVO();
				mvv.setMsg(rs.getString("message"));
				mvv.setSendId(rs.getString("send_id"));
			} // end if
		} finally {
			// 5.�������
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
	 * ���� �Ǹ����� ��ǰ�� �����ִ� ��
	 * @param id
	 * @return ItemListVO
	 * @throws SQLException
	 * @author ������
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
	 * ID�� �ش��ϴ� �̹��� �̸��� �ҷ����� ��
	 * @param id
	 * @return String
	 * @author ������
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
