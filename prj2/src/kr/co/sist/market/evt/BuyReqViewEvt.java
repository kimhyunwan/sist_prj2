package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.market.view.BuyReqView;

public class BuyReqViewEvt extends WindowAdapter implements ActionListener {
	private BuyReqView brv;
	
	public BuyReqViewEvt(BuyReqView brv){
		this.brv=brv;
	}//BuyReqViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==brv.getJbCancel()){
			brv.dispose();
		}//end if
	}
	
}
