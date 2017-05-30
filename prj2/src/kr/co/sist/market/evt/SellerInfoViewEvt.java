package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import kr.co.sist.market.view.BuyReqView;
import kr.co.sist.market.view.MsgWriteView;
import kr.co.sist.market.view.SellerInfoView;

public class SellerInfoViewEvt extends WindowAdapter implements ActionListener {
	private SellerInfoView siv;
	
	public SellerInfoViewEvt(SellerInfoView siv){
		this.siv=siv;
		
	}//SellerInfoViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==siv.getJbMsg()){
			//new MsgWriteView();
		}//end if
		
		if(ae.getSource()==siv.getJbBuyReq()){
			new BuyReqView();
		}//end if
	}
}
