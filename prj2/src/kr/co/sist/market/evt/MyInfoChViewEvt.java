package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.market.view.MyInfoChView;

public class MyInfoChViewEvt extends WindowAdapter implements ActionListener {
	private MyInfoChView micv;
	
	public MyInfoChViewEvt(MyInfoChView micv){
		this.micv=micv;
	}//MyInfoChViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==micv.getJbCancel()){
			micv.dispose();
		}//end if
	}
	
}
