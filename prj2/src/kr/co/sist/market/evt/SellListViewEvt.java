package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.SellListView;
import kr.co.sist.market.vo.SellBuyVO;

public class SellListViewEvt extends MouseAdapter implements ActionListener {
	private SellListView slv;
	private MarketDAO m_dao;
	LoginViewEvt lve;
	int chkNum1=0;
	int chkNum2=0;
	
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
			String id=lve.id; //진데이터 ID
			//String id="wkdwogns"; //가데이터 ID
			List<SellBuyVO> lstItem1=m_dao.selectSellCompList(id);
			//"구매자", "번호", "상품명" , "판매완료일시"
			
			Object[] rowItem1=new Object[5];
			
			//DefaultTableModel을 받아와야한다.
			DefaultTableModel dtmItem1=slv.getDtmComplet(); //SellListView클래스에서 dtm객체를 가져옴
			
			SellBuyVO sbv=null;
			
			dtmItem1.setRowCount(0);
			
			for(int i=0; i<lstItem1.size(); i++){
				//SellBuyVO //String id, itemCode, itemName, tradeDate;
				sbv=lstItem1.get(i); //리스트메뉴의 값을 가져와서 sbv에 집어넣음
				rowItem1[0]=(i+1);
				rowItem1[1]=sbv.getId();
				rowItem1[2]=sbv.getItemCode();
				rowItem1[3]=sbv.getItemName();
				rowItem1[4]=sbv.getTradeDate();
				
				dtmItem1.addRow(rowItem1);
			}//end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(slv, "죄송합니다. 판매완료목록을 불러올 수 없습니다.\n잠시후 다시 시도해주세요.");
			e.printStackTrace();
		} catch (NullPointerException npe){
			JOptionPane.showMessageDialog(slv, "판매가 완료된 물품이 없습니다.");
		}
	}//setSellList()
	
///////////////////////////////////////////판매대기 목록을 띄우는 method////////////////////////////////////
	public void setSellListWait(){
		try {
			String id=lve.id; //진데이터 ID
			//String id="dongha"; //가데이터 ID
			List<SellBuyVO> lstItem2=m_dao.selectBuyWaitList(id);
			//"구매신청자", "상품코드", "상품명" , "신청일"
			
			Object[] rowItem2=new Object[5];
			
			//DefaultTableModel을 받아와야한다.
			DefaultTableModel dtmItem2=slv.getDtmWait(); //SellListView클래스에서 dtm객체를 가져옴
			
			SellBuyVO sbv=null;
			
			dtmItem2.setRowCount(0);
			
			for(int i=0; i<lstItem2.size(); i++){
				//SellBuyVO //String id, itemCode, itemName, tradeDate;
				sbv=lstItem2.get(i); //리스트메뉴의 값을 가져와서 sbv에 집어넣음
				rowItem2[0]=(i+1);
				rowItem2[1]=sbv.getId();
				rowItem2[2]=sbv.getItemCode();
				rowItem2[3]=sbv.getItemName();
				rowItem2[4]=sbv.getTradeDate();
				
				dtmItem2.addRow(rowItem2);
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
	

	/////////////////////////판매대기 목록에서 상품를 클릭했을 때////////////////////////////
	@Override
	public void mouseClicked(MouseEvent me) {
		System.out.println(me.getSource());
//		if(chkNum==1){
//			System.out.println("1111111111111");
//		}else if(chkNum==2){
//			System.out.println("2222222222222");
//		}
//		JTabbedPane jtp=(JTabbedPane)me.getSource();
//		int tabIndex=jtp.getSelectedIndex();
		
//		if(tabIndex==0){
//			System.out.println("판매완료창입니다.");
//		}else if(tabIndex==1){
//			System.out.println("판매대기창입니다.");
//		}//end if
		
		if(me.getClickCount()==2){ //더블클릭을 했다면 실행
					JTable temp=slv.getJtWait(); //테이블을 가져오고
					int selectedRow=temp.getSelectedRow();
		//			MenuVO mv=new MenuVO(); //MenuVO를 사용하여, 선택한 항목의 이미지,메뉴코드,메뉴명,설명,가격을 가져와서 [상세보기dialog창]에 띄워줄것이다.
		//			mv.setImg( ((ImageIcon)temp.getValueAt(selectedRow, 1)).toString()); 
		//			mv.setItem_code((String) temp.getValueAt(selectedRow, 2)); 
		//			mv.setMenu((String) temp.getValueAt(selectedRow, 3)); 
		//			mv.setInfo((String) temp.getValueAt(selectedRow, 4)); 
		//			mv.setPrice((Integer)temp.getValueAt(selectedRow, 5)); 
					
					int flag=JOptionPane.showConfirmDialog(slv, "[ "+slv.getName()+" ]를 주문하시겠습니까?");
					
					switch (flag) {
						case JOptionPane.OK_OPTION:
		//					new OrderForm(mf,mv); //mf와 mv를 실어서 보냄
							System.out.println("판매승인창으로 이동합니다!");
						break;
					}//end switch
				}//end if
	}//mouseClicked

}//class
