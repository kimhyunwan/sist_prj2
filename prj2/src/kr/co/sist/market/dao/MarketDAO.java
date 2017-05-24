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
	//뭐지???
	private static MarketDAO getInstance(){
		if(m_dao==null){
			m_dao=new MarketDAO(); 
		}//end if
		return m_dao;
	}//getInstance
	
	private Connection getConnection() throws SQLException{
		Connection con=null;
		
		Properties prop=new Properties();
		try {
		
			File file=new File("C:/dev/sist_prj2/prj2/src/kr/co/sist/market/dao/market.properties");
		
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
				JOptionPane.showMessageDialog(null, "설정파일의 경로를 확인해주세요");
			}//end else
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return con;
	}//getConnection
	
	/**
	 * 물품 목록을 보이기 위한 method
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
				selectItem+="where category_num=?";
			}
			pstmt=con.prepareStatement(selectItem);
			pstmt.setInt(1, typeCode);
			
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
	
	/**
	 * 판매완료정보 리스트를 보여주기 위한 method
	 * @return list
	 * @throws SQLException
	 */
	public List<SellBuyVO> selectSellList(boolean flag) throws SQLException{
		List<SellBuyVO> list=new ArrayList<SellBuyVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{ 
			con=getConnection();
			
			String selectSell="select buyer_id, item_code, item_name, sold_date from product where sold_flag='y";
			pstmt=con.prepareStatement(selectSell);
			
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
	
	public List<SellingVO> selectSellWaitList(){
		List<SellingVO> list=new ArrayList<SellingVO>();
		
		return list;
	}
	
	public void updateSellComp(boolean flag){
		
	}//updateSellComp
	
	/**
	 * 판매자 정보를 조회하는 method
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
			String selectSeller="select m.id id, m.info info, m.image image from member m, product pwhere (m.id=p.id) and item_code=?";
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
	 * 내 구매정보를 보이기 위한 method
	 * @param flag
	 * @param id
	 * @return list
	 * @throws SQLException
	 */
	public List<SellBuyVO> selectBuyList(boolean flag, String id) throws SQLException{
		List<SellBuyVO> list=new ArrayList<SellBuyVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con=getConnection();
			
			String selectSell="select id, item_code, item_name, sold_date from product where sold_flag=? and buyer_id=?";
			pstmt=con.prepareStatement(selectSell);
			if(flag){
				pstmt.setString(1, "y");
			}else{
				pstmt.setString(1, "n");
			}
			pstmt.setString(2, id);
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
		}
		return list;
	}//selectBuyList
	
	/**
	 * 구매가 확정 플래그로 변경해주는 method
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
	 * 보낸 메시지 리스트를 조회하는 method
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
	 * 받은 메시지 리스트를 조회하는 method
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
	 * 메시지 읽기 확인을 업데이트하는 method
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
	
	public static void main(String[] args) throws SQLException{
		System.out.println(MarketDAO.getInstance().getConnection());
	}
}//class
