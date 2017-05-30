package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import kr.co.sist.market.view.BuyReqView;
import kr.co.sist.market.view.MsgWriteView;
import kr.co.sist.market.view.SellerInfoView;
import kr.co.sist.market.vo.MsgVO;

public class SellerInfoViewEvt extends WindowAdapter implements ActionListener {
	private SellerInfoView siv;
	private LoginViewEvt lve;
	
	public SellerInfoViewEvt(SellerInfoView siv){
		this.siv=siv;
		
	}//SellerInfoViewEvt
	
	private void SendMsg(){
		MsgVO mv=new MsgVO();
		mv.setId(lve.id);
		mv.setItemCode(siv.getItemCode());
		mv.setSendId(siv.getJtfId().getText().trim());
		mv.setMsg("");
		new MsgWriteView(mv);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==siv.getJbMsg()){
			
		}//end if
		
		if(ae.getSource()==siv.getJbBuyReq()){
			new BuyReqView();
		}//end if
	}
}
