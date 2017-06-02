package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.view.MsgListView;
import kr.co.sist.market.view.MsgReadView;
import kr.co.sist.market.view.MsgWriteView;
import kr.co.sist.market.vo.MsgVO;

public class MsgReadViewEvt extends WindowAdapter implements ActionListener {

	private MsgReadView mrv;
	private CustomerDAO cd;
	private MsgListViewEvt mlve;
	private LoginViewEvt lve;
	private MsgListView mlv;
	
	public MsgReadViewEvt(MsgReadView mrv, MsgListView mlv) {
		this.mrv=mrv;
		this.mlv=mlv;
	}//MsgReadViewEvt
	
	private void SendMsg(){
		MsgVO mv=new MsgVO();
		
		String id=lve.id;
		String sendId=mlve.msg_id;
		
		if(id.equals(sendId)){
			JOptionPane.showMessageDialog(mrv, "자기 자신에게 메시지를 보낼 수 없습니다.");
			return;
		}	
		
		mv.setId(id);
		mv.setItemCode(mlve.item_code);
		mv.setSendId(sendId);
		mv.setMsg("");
		
		new MsgWriteView(mv);
	}//SendMsg
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mrv.getJbOk()){
			mrv.dispose();
			mlve=new MsgListViewEvt(mlv);
			mlve.setRecieveMsg(lve.id);
			mlve.setSendMsg(lve.id);
		}//end if
		
		if(ae.getSource()==mrv.getJbSend()){
			SendMsg();
			mrv.dispose();
		}//end if

	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		mrv.dispose();
	}//windowClosing

	@Override
	public void windowClosed(WindowEvent e) {
		super.windowClosed(e);
	}
	
}
