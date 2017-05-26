package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.market.view.SellListView;

public class SellListViewEvt extends WindowAdapter implements ActionListener {
	private SellListView slv;
	
	public SellListViewEvt(SellListView slv){
		this.slv=slv;
	}//SellListViewEvt

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
