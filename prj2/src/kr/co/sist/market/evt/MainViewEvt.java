package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import kr.co.sist.market.view.BuyListView;
import kr.co.sist.market.view.MainView;
import kr.co.sist.market.view.MsgListView;
import kr.co.sist.market.view.MyInfoChView;
import kr.co.sist.market.view.SellListView;



public class MainViewEvt extends MouseAdapter implements ActionListener {
	private MainView mv;
	
	public MainViewEvt(MainView mv){
		this.mv=mv;
	}//MainViewEvtEvt
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mv.getJbMyInfoCh()){
			new MyInfoChView();
		}//end if
		if(ae.getSource()==mv.getJbSellList()){
			new SellListView();
		}//end if
		if(ae.getSource()==mv.getJbBuyList()){
			new BuyListView();
		}//end if
		if(ae.getSource()==mv.getJbMsgList()){
			new MsgListView();
		}//end if
		
		

	}

}
