package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.channels.ShutdownChannelGroupException;

import javax.swing.JOptionPane;

import kr.co.sist.market.view.MsgWriteView;

/**
 * @author user
 *
 */
public class MsgWriteViewEvt extends WindowAdapter implements ActionListener {
	
	private MsgWriteView mwv;

	
	public MsgWriteViewEvt(MsgWriteView mwv) {
		this.mwv=mwv;
	}//MsgWriteViewEvt
	
	private void SendMsg(){
		
		
		JOptionPane.showMessageDialog(mwv, "메세지가 전송되었습니다");
		
		mwv.getJtaMsg().setText("");
	}//sendMsg
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		//취소버튼 실행시 꺼짐 동작
		if(ae.getSource()==mwv.getJbCancel()){
			mwv.dispose();
		}//end if

		//메세지 보내기 실행시 상대방에게 보내고 메세지 띄움
		if(ae.getSource()==mwv.getJbSend()){
			SendMsg();
		}
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		mwv.dispose();
	}//windowClosing


	
}//class
