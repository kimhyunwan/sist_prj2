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
		
		//���̵�ã�� ã���ư ����
		if(ae.getSource()==fiv.getJbFindId()){
			JOptionPane.showMessageDialog(fiv, "�ش� ���̵�� 000 �Դϴ�.");
			fiv.getJtfIdName().setText("");
			fiv.getJtfIdSsn().setText("");
			fiv.getJpwIdSsn().setText("");
		}//end if
		
		//��й�ȣã�� ã���ư ����
		if(ae.getSource()==fiv.getJbFindPass()){
			JOptionPane.showMessageDialog(fiv, "�ش� ���̵��� ��й�ȣ�� 000 �Դϴ�.");
			fiv.getJtfPwName().setText("");
			fiv.getJtfPwSsn().setText("");
			fiv.getJpwPwSsn().setText("");
			fiv.getJtfId().setText("");
			fiv.getJtfAnswer().setText("");
			//fiv.getJcbQuest()
		}//end if

		//���̵�ã��, ��й�ȣã�� ��ҹ�ư ����
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
