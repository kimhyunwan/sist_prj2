package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.market.view.FindIdView;
import kr.co.sist.market.view.JoinView;
import kr.co.sist.market.view.LoginView;
import kr.co.sist.market.view.MainView;

public class LoginViewEvt extends WindowAdapter implements ActionListener {
	private LoginView lv;
	
	public LoginViewEvt(LoginView lv){
		this.lv=lv;
	}//LoginViewEvt
	

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==lv.getJbLogin()){
			new MainView();
		}//end if
		
		if(ae.getSource()==lv.getJbJoin()){
			new JoinView();
		}//end if
		
		if(ae.getSource()==lv.getJbFind()){
				
			new FindIdView();
		}//end if
		
	}//actionPerformed

}
