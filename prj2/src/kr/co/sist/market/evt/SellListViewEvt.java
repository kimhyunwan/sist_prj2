package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.SellListView;
import kr.co.sist.market.vo.SellBuyVO;

public class SellListViewEvt extends WindowAdapter implements ActionListener {
	private SellListView slv;
	private MarketDAO m_dao;
	LoginViewEvt lve;
	
	public SellListViewEvt(SellListView slv){
		this.slv=slv;
		m_dao=MarketDAO.getInstance(); //판매리스트테이블을 가져오기 위함
		//판매완료리스트를 조회하여 설정한다.
		setSellListComp();
		//판매대기리스트를 조회하여 설정한다.
		setSellListWait();
	}//SellListViewEvt

	///////////////////////////////////////////판매완료 목록을 띄우는 method////////////////////////////////////
	public void setSellListComp(){
		try {
//			String id=lve.id; //진데이터 ID
			String id="wkdwogns"; //가데이터 ID
			List<SellBuyVO> lstItem=m_dao.selectSellCompList(id);
			//"구매자", "번호", "상품명" , "판매완료일시"
			
			Object[] rowItem=new Object[5];
			
			//DefaultTableModel을 받아와야한다.
			DefaultTableModel dtmItem=slv.getDtmComplet(); //SellListView클래스에서 dtm객체를 가져옴
			
			SellBuyVO sbv=null;
			
			dtmItem.setRowCount(0);
			
			for(int i=0; i<lstItem.size(); i++){
				//SellBuyVO //String id, itemCode, itemName, tradeDate;
				sbv=lstItem.get(i); //리스트메뉴의 값을 가져와서 sbv에 집어넣음
				rowItem[0]=(i+1);
				rowItem[1]=sbv.getId();
				rowItem[2]=sbv.getItemCode();
				rowItem[3]=sbv.getItemName();
				rowItem[4]=sbv.getTradeDate();
				
				dtmItem.addRow(rowItem);
			}//end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(slv, "죄송합니다. 판매완료목록을 불러올 수 없습니다.\n잠시후 다시 시도해주세요.");
			e.printStackTrace();
		}
	}//setSellList()
	
///////////////////////////////////////////판매대기 목록을 띄우는 method////////////////////////////////////
	public void setSellListWait(){
		try {
			String id="dongha"; //가데이터 ID
			List<SellBuyVO> lstItem=m_dao.selectBuyWaitList(id);
			//"구매신청자", "상품코드", "상품명" , "신청일"
			
			Object[] rowItem=new Object[5];
			
			//DefaultTableModel을 받아와야한다.
			DefaultTableModel dtmItem=slv.getDtmWait(); //SellListView클래스에서 dtm객체를 가져옴
			
			SellBuyVO sbv=null;
			
			//dtmItem.setRowCount(0);
			
			for(int i=0; i<lstItem.size(); i++){
				//SellBuyVO //String id, itemCode, itemName, tradeDate;
				sbv=lstItem.get(i); //리스트메뉴의 값을 가져와서 sbv에 집어넣음
				rowItem[0]=(i+1);
				rowItem[1]=sbv.getId();
				rowItem[2]=sbv.getItemCode();
				rowItem[3]=sbv.getItemName();
				rowItem[4]=sbv.getTradeDate();
				
				dtmItem.addRow(rowItem);
			}//end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(slv, "죄송합니다. 판매대기목록을 불러올 수 없습니다.\n잠시후 다시 시도해주세요.");
			e.printStackTrace();
		}
	}//setSellList()
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==slv.getJbClose()){
			slv.dispose();
		}//end if
		if(ae.getSource()==slv.getJbWClose()){
			slv.dispose();
		}//end if
	}
	
}
