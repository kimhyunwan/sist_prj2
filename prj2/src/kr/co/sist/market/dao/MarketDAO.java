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
		
			File file=new File("C:/dev/workspace/market_prj/src/kr/co/sist/market/dao/market.properties");
		
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
	
	public List<ItemListVO> selectItemList(int typeCode){
		List<ItemListVO> list=new ArrayList<ItemListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}//selectItemList
	
	public List<SellBuyVO> selectSellList(){
		List<SellBuyVO> list=new ArrayList<SellBuyVO>();
		return list;
	}//selectSellList
	
	public SellerInfoVO selectSellerInfo(String ItemCode){
		SellerInfoVO seller=new SellerInfoVO();
		return seller;
	}//selectSellerInfo
	
	public void updateSellComp(boolean flag){
		
	}//updateSellComp
	
	public List<SellBuyVO> selectBuyList(){
		List<SellBuyVO> list=new ArrayList<SellBuyVO>();
		return list;
	}//selectBuyList
	
	public void updateBuyComp(boolean flag){
		
	}//updateBuyComp
	
	public List<MsgListVO> selectSendMsgList(){
		List<MsgListVO> list=new ArrayList<MsgListVO>();
		return list;
	}//selectSendMsgList
	
	public List<MsgListVO> selectGetMsgList(){
		List<MsgListVO> list=new ArrayList<MsgListVO>();
		return list;
	}//selectGetMsgList
	
	public void updateChkMsg(){
		
	}//updateChkMsg
	
	
}//class
