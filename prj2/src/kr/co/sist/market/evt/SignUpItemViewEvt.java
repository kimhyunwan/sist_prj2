package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.market.view.SignUpItemView;

public class SignUpItemViewEvt extends WindowAdapter implements ActionListener {
	private SignUpItemView suiv;
	
	public SignUpItemViewEvt(SignUpItemView suiv){
		this.suiv=suiv;
	}//SignUpItemViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==suiv.getJbCancel()){
			suiv.dispose();
		}//end if
	}
}
