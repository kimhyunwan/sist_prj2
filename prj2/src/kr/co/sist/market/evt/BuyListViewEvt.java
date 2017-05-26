package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.market.view.BuyListView;

public class BuyListViewEvt extends WindowAdapter implements ActionListener {
	private BuyListView blv;
	
	public BuyListViewEvt(BuyListView blv){
		this.blv=blv;
	}//BuyListViewEvt.
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==blv.getJbClose()){
			blv.dispose();
		}//end if
		if(ae.getSource()==blv.getJbWClose()){
			blv.dispose();
		}//end if
	}
}
