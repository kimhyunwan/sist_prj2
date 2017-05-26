package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.co.sist.market.view.FindIdView;

public class FindIdViewEvt extends WindowAdapter implements ActionListener {

	FindIdView fiv;
	
	public FindIdViewEvt(FindIdView fiv) {
		this.fiv=fiv;
	}//FindIdViewEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		//아이디찾기 찾기버튼 사용시
		if(ae.getSource()==fiv.getJbFindId()){
			JOptionPane.showMessageDialog(fiv, "해당 아이디는 000 입니다.");
			fiv.getJtfIdName().setText("");
			fiv.getJtfIdSsn().setText("");
			fiv.getJpwIdSsn().setText("");
		}//end if
		
		//비밀번호찾기 찾기버튼 사용시
		if(ae.getSource()==fiv.getJbFindPass()){
			JOptionPane.showMessageDialog(fiv, "해당 아이디의 비밀번호는 000 입니다.");
			fiv.getJtfPwName().setText("");
			fiv.getJtfPwSsn().setText("");
			fiv.getJpwPwSsn().setText("");
			fiv.getJtfId().setText("");
			fiv.getJtfAnswer().setText("");
			//fiv.getJcbQuest()
		}//end if

		//아이디찾기, 비밀번호찾기 취소버튼 사용시
		if(ae.getSource()==fiv.getJbIdCancel()||ae.getSource()==fiv.getJbPwCancel()){
			fiv.dispose();
		}//end if
	}//actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		fiv.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	
}//class
