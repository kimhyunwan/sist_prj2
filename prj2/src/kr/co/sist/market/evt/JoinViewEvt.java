package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.market.view.JoinView;
import kr.co.sist.market.view.SellListView;

public class JoinViewEvt extends WindowAdapter implements ActionListener {
	private JoinView jv;
	
	public JoinViewEvt(JoinView jv){
		this.jv=jv;
	}//JoinViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==jv.getJbCancel()){
			jv.dispose();
		}//end if
	}

}
