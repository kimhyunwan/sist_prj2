package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;


import kr.co.sist.market.view.BuyReqView;
import kr.co.sist.market.view.ItemInfoView;
import kr.co.sist.market.view.SellerInfoView;

public class ItemInfoViewEvt extends WindowAdapter implements ActionListener {
	private ItemInfoView iiv;
	
	public ItemInfoViewEvt(ItemInfoView iiv){
		this.iiv=iiv;
	}//ItemInfoViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==iiv.getJbSellerInfo()){
			new SellerInfoView();
		}//end if
		if(ae.getSource()==iiv.getJbBuyReq()){
			new BuyReqView();
		}//end if
		if(ae.getSource()==iiv.getJbCancel()){
			iiv.dispose();
		}//end if
	}
}
