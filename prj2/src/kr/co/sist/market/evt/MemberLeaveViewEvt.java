package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.view.MemberLeaveView;


public class MemberLeaveViewEvt extends WindowAdapter implements ActionListener {
	private MemberLeaveView mlv;
	private CustomerDAO cd;
	
	public MemberLeaveViewEvt(MemberLeaveView mlv){
		this.mlv=mlv;
	}//MemberLeaveViewEvt


	
	

	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
	
		
	}//actionPerformed

}
