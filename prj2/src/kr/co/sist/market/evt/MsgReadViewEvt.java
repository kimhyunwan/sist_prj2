package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.view.MsgReadView;
import kr.co.sist.market.view.MsgWriteView;
import kr.co.sist.market.vo.MsgVO;

public class MsgReadViewEvt extends WindowAdapter implements ActionListener {

	private MsgReadView mrv;
	private CustomerDAO cd;
	private MsgListViewEvt mlve;
	private LoginViewEvt lve;
	
	public MsgReadViewEvt(MsgReadView mrv) {
		this.mrv=mrv;
		
//		if(mrv.flag==1){
//			mrv.setJtaMsg(cd.selectReceiveMsgInfo()));
//		}else if(mrv.flag==2){
//			mrv.setJtaMsg();
//	}//end if
	}//MsgReadViewEvt
	
	private void SendMsg(){
		MsgVO mv=new MsgVO();
		mv.setId(lve.id);
		mv.setItemCode(mlve.item_code);
		mv.setSendId(mlve.msg_id);
		mv.setMsg("");
		
		new MsgWriteView(mv);
		
	}//SendMsg
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mrv.getJbOk()){
			mrv.dispose();
		}//end if
		
		if(ae.getSource()==mrv.getJbSend()){
			SendMsg();
		}//end if

	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		mrv.dispose();
	}//windowClosing

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowClosed(e);
	}

	
}
