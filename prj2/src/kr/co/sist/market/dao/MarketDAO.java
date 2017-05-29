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

import kr.co.sist.market.vo.ItemListVO;
import kr.co.sist.market.vo.MsgListVO;
import kr.co.sist.market.vo.SellBuyVO;
import kr.co.sist.market.vo.SellerInfoVO;
import kr.co.sist.market.vo.SellingVO;

public class MarketDAO {
	private static MarketDAO m_dao;
	
	private MarketDAO(){
	}//MarketDAO
	

	public static MarketDAO getInstance(){
		if(m_dao==null){
			m_dao=new MarketDAO(); 
		}//end if
		return m_dao;
	}//getInstance
	
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
					Class.forName(driver);
					con=DriverManager.getConnection(url, id, pass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}//end catch
			} else {
				JOptionPane.showMessageDialog(null, "���������� ��θ� Ȯ�����ּ���");
			}//end else
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return con;
	}//getConnection
	
	/**
	 * ��ǰ ����� ���̱� ���� method
	 * @param typeCode
	 * @return list
	 * @throws SQLException 
	 */
	public List<ItemListVO> selectItemList(int typeCode) throws SQLException{
		List<ItemListVO> list=new ArrayList<ItemListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=getConnection();
			String selectItem="select item_name, item_code, item_info, hiredate, item_image, price from product";
			if(typeCode!=0){
				selectItem+=" where category_num=?";
			}
			pstmt=con.prepareStatement(selectItem);

			
			if(typeCode!=0){
				pstmt.setInt(1, typeCode);
			}
			
			if(typeCode!=0){
				pstmt.setInt(1, typeCode);
			}
			rs=pstmt.executeQuery();
			
			ItemListVO ilv=null;
			
			while(rs.next()){
				ilv=new ItemListVO();
				ilv.setItemName(rs.getString("item_name"));
				ilv.setItemCode(rs.getString("item_code"));
				ilv.setItemInfo(rs.getString("item_info"));
				ilv.setHiredate(rs.getString("hiredate"));
				ilv.setImage(rs.getString("item_image"));
				ilv.setPrice(rs.getInt("price"));
				
				list.add(ilv);
			}//end while
		} finally{
			if (rs != null) {
				rs.close();
			} // end if

			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}//end finally
		return list;
	}//selectItemList
	
	public List<String> selectItemType() throws SQLException{
		List<String> list=new ArrayList<String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
		//1.����̹� �ε�
		//2.Connection ���
			con=getConnection();
		//3.������ ������ü ���
			String selectItemType="select product_group from product_category";
			pstmt=con.prepareStatement(selectItemType);
		//4.���� ���� ��, ��� ��� :  : ���ε庯���� 1�� ����(qu_num)
			rs=pstmt.executeQuery(); //select�̱� ������ executeQuery()
			while(rs.next()){
				list.add(rs.getString("product_group"));
			}//end if
		}finally{
		//5.�������
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		
		return list;
	}//selectItemType

	
	/**
	 * �ǸſϷ����� ����Ʈ�� �����ֱ� ���� method
	 * @return list
	 * @throws SQLException
	 */
	public List<SellBuyVO> selectSellCompList(String id) throws SQLException{
		List<SellBuyVO> list=new ArrayList<SellBuyVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//�ȿö󰣴�
		try{ 
			con=getConnection();
			
			String selectSell="select buyer_id, item_code, item_name, sold_date from product where sold_flag='y' and id=?";
			pstmt=con.prepareStatement(selectSell);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			 
			SellBuyVO sbv=null;
			while(rs.next()){
				sbv=new SellBuyVO();
				sbv.setId(rs.getString("buyer_id"));
				sbv.setItemCode(rs.getString("item_code"));
				sbv.setItemName(rs.getString("item_name"));
				sbv.setTradeDate(rs.getString("sold_date"));
				
				list.add(sbv);
			}//end while
			
		}finally{
			if (rs != null) {
				rs.close();
			} // end if

			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}//end finally
		return list;
	}//selectSellList
	
	/**
	 * �ǸŴ������ ����Ʈ�� �����ֱ� ���� method
	 * @param id
	 * @return list
	 * @throws SQLException
	 */
	public List<SellingVO> selectSellWaitList(String id) throws SQLException{
		List<SellingVO> list=new ArrayList<SellingVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getConnection();
			String selectWait="select b.id id, b.item_code item_code, b.phone phone, p.item_name name, b.requesting_date req_date from product p, buyer_contact b where (p.item_code=b.item_code) and p.id=?";
			pstmt=con.prepareStatement(selectWait);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			SellingVO sv=null;
			while(rs.next()){
				sv=new SellingVO();
				sv.setId(rs.getString("id"));
				sv.setItemCode(rs.getString("item_code"));
				sv.setItemName(rs.getString("name"));
				sv.setPhone(rs.getString("phone"));
				sv.setReqDate(rs.getString("req_date"));
				
				list.add(sv);
			}//end while
		}finally{
			if (rs != null) {
				rs.close();
			} // end if

			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}
		
		return list;
	}
	
	/**
	 * �Ǹ������ ���̺��� �ش��ϴ� ������ �ڵ带 ��û�� ȸ������ �����ϱ�
	 * @param itemCode
	 * @throws SQLException
	 */
	public void deleteSellWait(String itemCode) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getConnection();
			
			String deleteWait="delete from buyer_contact where item_code=?";
			pstmt=con.prepareStatement(deleteWait);
			
			pstmt.setString(1, itemCode);
			
			pstmt.executeUpdate();
			
		}finally{
			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}
	}
	
	/**
	 * �Ǹ��� ������ ��ȸ�ϴ� method
	 * @param ItemCode
	 * @return SellerInfoVO
	 * @throws SQLException 
	 */
	public SellerInfoVO selectSellerInfo(String itemCode) throws SQLException{
		SellerInfoVO siv=new SellerInfoVO();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getConnection();
			String selectSeller="select m.id id, m.info info, m.image image from member m, product p where (m.id=p.id) and item_code=?";
			pstmt=con.prepareStatement(selectSeller);
			
			pstmt.setString(1, itemCode);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				siv=new SellerInfoVO();
				siv.setId(rs.getString("id"));
				siv.setInfo(rs.getString("info"));
				siv.setImg(rs.getString("image"));
				
			}//end if
			
		}finally{
			if (rs != null) {
				rs.close();
			} // end if

			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}
		
		return siv;
	}//selectSellerInfo
	
	/**
	 * �� ���ſϷ������� ���̱� ���� method
	 * @param flag
	 * @param id
	 * @return list
	 * @throws SQLException
	 */
	public List<SellBuyVO> selectBuyCompList(String id) throws SQLException{

		List<SellBuyVO> list=new ArrayList<SellBuyVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con=getConnection();
			
			String selectSell="select id, item_code, item_name, sold_date from product where sold_flag='y' and buyer_id=?";
			pstmt=con.prepareStatement(selectSell);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			SellBuyVO sbv=null;
			while(rs.next()){
				sbv=new SellBuyVO();
				sbv.setId(rs.getString("id"));
				sbv.setItemCode(rs.getString("item_code"));
				sbv.setItemName(rs.getString("item_name"));
				sbv.setTradeDate(rs.getString("sold_date"));
				
				list.add(sbv);
			}//end while
			
		}finally{
			if (rs != null) {
				rs.close();
			} // end if

			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}
		return list;
	}//selectBuyList
	
	/**
	 * �� ���Ŵ�������� ���̱� ���� method
	 * @param id
	 * @return list
	 * @throws SQLException
	 */
	public List<SellBuyVO> selectBuyWaitList(String id) throws SQLException{
		List<SellBuyVO> list=new ArrayList<SellBuyVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getConnection();
			String selectWait="select p.id id, b.item_code item_code, p.item_name name, b.requesting_date req_date from product p, buyer_contact b where (p.item_code=b.item_code) and b.id=?";
			pstmt=con.prepareStatement(selectWait);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			SellBuyVO sbv=null;
			while(rs.next()){
				sbv=new SellBuyVO();
				sbv.setId(rs.getString("id"));
				sbv.setItemCode(rs.getString("item_code"));
				sbv.setItemName(rs.getString("name"));
				sbv.setTradeDate(rs.getString("req_date"));
				
				list.add(sbv);
			}//end while
		}finally{
			if (rs != null) {
				rs.close();
			} // end if

			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}
		
		return list;
	}//selectBuyWaitList
	
	/**
	 * ���Ű� Ȯ�� �÷��׷� �������ִ� method
	 * @param itemCode
	 * @param buyerId
	 * @throws SQLException
	 */
	public void updateBuyComp(String itemCode, String buyerId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con=getConnection();
			
			String updateBuy="update product set sold_flag='y', buyer_id=?, sold_date=sysdate where item_code=?";
			pstmt=con.prepareStatement(updateBuy);
			
			pstmt.setString(1, buyerId);
			pstmt.setString(2, itemCode);
			
			pstmt.executeUpdate();
		}finally{
			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}
	}//updateBuyComp
	
	/**
	 * ���� �޽��� ����Ʈ�� ��ȸ�ϴ� method
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<MsgListVO> selectSendMsgList(String id) throws SQLException{
		List<MsgListVO> list=new ArrayList<MsgListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con=getConnection();
			
			String selectMsg="select msg_num, send_id, item_code, send_date, msg_check_flag from send_msg where id=?";
			pstmt=con.prepareStatement(selectMsg);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			MsgListVO mlv=null;
			while(rs.next()){
				mlv=new MsgListVO();
				mlv.setMsgCode(rs.getString("msg_num"));
				mlv.setId(rs.getString("send_id"));
				mlv.setItem(rs.getString("item_code"));
				mlv.setMsgDate(rs.getString("send_date"));
				mlv.setFlag(rs.getString("msg_check_flag"));
				
				list.add(mlv);
			}//end while
		}finally{
			if (rs != null) {
				rs.close();
			} // end if

			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}//end finally
		
		return list;
	}//selectSendMsgList
	
	/**
	 * ���� �޽��� ����Ʈ�� ��ȸ�ϴ� method
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<MsgListVO> selectGetMsgList(String id) throws SQLException{
		List<MsgListVO> list=new ArrayList<MsgListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con=getConnection();
			
			String selectMsg="select msg_num, send_id, item_code, send_date, msg_check_flag from receive_msg where id=?";
			pstmt=con.prepareStatement(selectMsg);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			MsgListVO mlv=null;
			while(rs.next()){
				mlv=new MsgListVO();
				mlv.setMsgCode(rs.getString("msg_num"));
				mlv.setId(rs.getString("send_id"));
				mlv.setItem(rs.getString("item_code"));
				mlv.setMsgDate(rs.getString("send_date"));
				mlv.setFlag(rs.getString("msg_check_flag"));
				
				list.add(mlv);
			}//end while
		}finally{
			if (rs != null) {
				rs.close();
			} // end if

			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if
		}
		
		return list;
	}//selectGetMsgList
	
	/**
	 * ���� �޽��� �б� Ȯ���� ������Ʈ�ϴ� method
	 * @param msgCode
	 * @throws SQLException
	 */
	public void updateChkSendMsg(String msgCode) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con=getConnection();
			
			String updateFlag="update send_msg set msg_check_flag='y' where msg_num=?";
			pstmt=con.prepareStatement(updateFlag);
			
			pstmt.setString(1, msgCode);
			
			pstmt.executeUpdate();
		}finally{
			if (pstmt != null) {
				pstmt.close();
			} // end if

			if (con != null) {
				con.close();
			} // end if	
		}
	}//updateChkSendMsg
	
	/**
	 * ���� �޽��� �б� Ȯ���� ������Ʈ�ϴ� method
	 * @param msgCode
	 * @throws SQLException
	 */
	public void updateChkGetMsg(String msgCode) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con=getConnection();
			
			String updateFlag="update receive_msg set msg_check_flag='y' where msg_num=?";
			pstmt=con.prepareStatement(updateFlag);
			
			pstmt.setString(1, msgCode);
			
			pstmt.executeUpdate();
		}finally{
			if (pstmt != null) {
				pstmt.close();
			} // end if
			
			if (con != null) {
				con.close();
			} // end if	
		}
	}//updateChkGetMsg
	
//	public static void main(String[] args) throws SQLException{
//		MarketDAO md=new MarketDAO();
//		
//		System.out.println(MarketDAO.getInstance().getConnection());
//
//		//selectItemList �����׽�Ʈ
//		List<ItemListVO> list1=md.selectItemList(2);
//		List<ItemListVO> list1=md.selectItemList(0);
//		System.out.println(list1);
//
//		//selectSellCompList �����׽�Ʈ
//		List<SellBuyVO> list2=md.selectSellCompList("dongha");
//		System.out.println(list2);
//		
//		//selectSellWaitList ���� �׽�Ʈ
//		List<SellingVO> list3=md.selectSellWaitList("hyunwan");
//		System.out.println(list3);
//		
//		//deleteSellWait ���� �׽�Ʈ
//		md.deleteSellWait("HY_1705240021");
//		
//		//selectSellerInfo ���� �׽�Ʈ
//		System.out.println(md.selectSellerInfo("HY_1705240021"));
//		
//		//selectBuyCompList ���� �׽�Ʈ
//		List<SellBuyVO> list4=md.selectBuyCompList("hyunwan");
//		System.out.println(list4);
//		
//		//selectBuyWaitList ���� �׽�Ʈ
//		List<SellBuyVO> list5=md.selectBuyWaitList("hyunwan");
//		System.out.println(list5);
//		
//		//updateBuyComp ���� �׽�Ʈ
//		md.updateBuyComp("HY_1705240021", "hyunwan");
//	
//		//selectSendMsgList ���� �׽�Ʈ
//		List<MsgListVO> list6=md.selectSendMsgList("wkdwogns");
//		System.out.println(list6);
//		
//		List<MsgListVO> list7=md.selectGetMsgList("dongha");
//		System.out.println(list7);
//		
//		//updateChkSendMsg ���� �׽�Ʈ
//		md.updateChkSendMsg("SD_0525000041");
//		
//		//updateChkGetMsg ���� �׽�Ʈ
//		md.updateChkGetMsg("RC_0525000044");
//	}
}//class
