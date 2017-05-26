package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import kr.co.sist.market.view.MsgListView;

public class MsgListViewEvt extends MouseAdapter implements ActionListener {
	private MsgListView mlv;
	
	public MsgListViewEvt(MsgListView mlv){
		this.mlv=mlv;
	}//MsgListViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mlv.getJbSClose()){
			mlv.dispose();
		}
		if(ae.getSource()==mlv.getJbRClose()){
			mlv.dispose();
		}
	}
}
