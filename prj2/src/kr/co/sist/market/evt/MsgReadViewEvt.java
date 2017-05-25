package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.market.view.MsgReadView;

public class MsgReadViewEvt extends WindowAdapter implements ActionListener {

	MsgReadView mrv;
	
	public MsgReadViewEvt(MsgReadView mrv) {
		this.mrv=mrv;
	}//MsgReadViewEvt
	
	private void SendMsg(){
		

		JOptionPane.showMessageDialog(mrv, "메세지가 전송되었습니다");
		
		
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
