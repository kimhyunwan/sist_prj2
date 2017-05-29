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
		//1.드라이버 로딩
		//2.Connection 얻기
			con=getConnection();
		//3.쿼리문 생성객체 얻기
			String selectItemType="select product_group from product_category";
			pstmt=con.prepareStatement(selectItemType);
		//4.쿼리 실행 후, 결과 얻기 :  : 바인드변수가 1개 존재(qu_num)
			rs=pstmt.executeQuery(); //select이기 때문에 executeQuery()
			while(rs.next()){
				list.add(rs.getString("product_group"));
			}//end if
		}finally{
		//5.연결끊기
			if( rs != null) { rs.close(); }
			if( pstmt != null) { pstmt.close(); }
			if( con != null) { con.close(); }
		}//end try
		
		return list;
	}//selectItemType

	
	/**
	 * 판매완료정보 리스트를 보여주기 위한 method
	 * @return list
	 * @throws SQLException
	 */
	public List<SellBuyVO> selectSellCompList(String id) throws SQLException{
		List<SellBuyVO> list=new ArrayList<SellBuyVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//안올라간다
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
	 * 판매대기정보 리스트를 보여주기 위한 method
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
	 * 판매희망자 테이블에서 해당하는 아이템 코드를 신청한 회원들을 삭제하기
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
	 * 내 구매완료정보를 보이기 위한 method
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
	 * 내 구매대기정보를 보이기 위한 method
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
	 * 보낸 메시지 읽기 확인을 업데이트하는 method
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
	 * 받은 메시지 읽기 확인을 업데이트하는 method
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
//		//selectItemList 단위테스트
//		List<ItemListVO> list1=md.selectItemList(2);
//		List<ItemListVO> list1=md.selectItemList(0);
//		System.out.println(list1);
//
//		//selectSellCompList 단위테스트
//		List<SellBuyVO> list2=md.selectSellCompList("dongha");
//		System.out.println(list2);
//		
//		//selectSellWaitList 단위 테스트
//		List<SellingVO> list3=md.selectSellWaitList("hyunwan");
//		System.out.println(list3);
//		
//		//deleteSellWait 단위 테스트
//		md.deleteSellWait("HY_1705240021");
//		
//		//selectSellerInfo 단위 테스트
//		System.out.println(md.selectSellerInfo("HY_1705240021"));
//		
//		//selectBuyCompList 단위 테스트
//		List<SellBuyVO> list4=md.selectBuyCompList("hyunwan");
//		System.out.println(list4);
//		
//		//selectBuyWaitList 단위 테스트
//		List<SellBuyVO> list5=md.selectBuyWaitList("hyunwan");
//		System.out.println(list5);
//		
//		//updateBuyComp 단위 테스트
//		md.updateBuyComp("HY_1705240021", "hyunwan");
//	
//		//selectSendMsgList 단위 테스트
//		List<MsgListVO> list6=md.selectSendMsgList("wkdwogns");
//		System.out.println(list6);
//		
//		List<MsgListVO> list7=md.selectGetMsgList("dongha");
//		System.out.println(list7);
//		
//		//updateChkSendMsg 단위 테스트
//		md.updateChkSendMsg("SD_0525000041");
//		
//		//updateChkGetMsg 단위 테스트
//		md.updateChkGetMsg("RC_0525000044");
//	}
}//class
