package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.view.FindIdView;
import kr.co.sist.market.vo.IdVO;
import kr.co.sist.market.vo.PassVO;

public class FindIdViewEvt extends WindowAdapter implements ActionListener {

	FindIdView fiv;

	public FindIdViewEvt(FindIdView fiv) {
		this.fiv = fiv;

	}// FindIdViewEvt

	private void findId() {
		try {
			String name = fiv.getJtfIdName().getText().trim();
			String ssnBack = new String(fiv.getJpwIdSsn().getPassword()).trim();
			String ssnFront = fiv.getJtfIdSsn().getText().trim();
			String ssn = ssnFront + ssnBack;
			System.out.println(ssn);

			 if(ssnFront.length()!=6||ssnBack.length()!=7){
				 JOptionPane.showMessageDialog(fiv, "�ùٸ� �ֹι�ȣ�� �Է����ּ���");
			 }

			IdVO iv = new IdVO(name, ssn);

			CustomerDAO cd = CustomerDAO.getInstance();

			String id;
			id = cd.selectMyId(iv);
			String msg = "�ش� ���̵�� " + id + "�Դϴ�.";
			JOptionPane.showMessageDialog(fiv, msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		fiv.getJtfIdName().setText("");
		fiv.getJtfIdSsn().setText("");
		fiv.getJpwIdSsn().setText("");
	}// findId

	private void findPass() {
		String name = fiv.getJtfPwName().getText().trim();
		String ssnBack = new String(fiv.getJpwIdSsn().getPassword()).trim();
		String ssnFront = fiv.getJtfIdSsn().getText().trim();
		String ssn = ssnFront + ssnBack;
		String id = fiv.getJtfId().getText().trim();
		int quNum = fiv.getJcbQuest().getSelectedIndex() + 1;
		String answer = fiv.getJtfAnswer().getText().trim();

		if (ssnFront.length() != 6 || ssnBack.length() != 7) {
			JOptionPane.showMessageDialog(fiv, "�ùٸ� �ֹι�ȣ�� �Է����ּ���");
			return;
		}
		

		CustomerDAO cd = CustomerDAO.getInstance();

		PassVO pv = new PassVO(name, ssn, id, answer, quNum);

		try {
			String pass = cd.selectMyPass(pv);
			String msg = "�ش� ���̵��� ��й�ȣ�� " + pass + "�Դϴ�.";
			JOptionPane.showMessageDialog(fiv, msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		fiv.getJtfPwName().setText("");
		fiv.getJtfPwSsn().setText("");
		fiv.getJpwPwSsn().setText("");
		fiv.getJtfId().setText("");
		fiv.getJtfAnswer().setText("");
	}// findPass

	@Override
	public void actionPerformed(ActionEvent ae) {

		// ���̵�ã�� ã���ư ����
		if (ae.getSource() == fiv.getJbFindId()) {
			findId();
		} // end if

		// ��й�ȣã�� ã���ư ����
		if (ae.getSource() == fiv.getJbFindPass()) {
			findPass();
			// fiv.getJcbQuest()
		} // end if

		// ���̵�ã��, ��й�ȣã�� ��ҹ�ư ����
		if (ae.getSource() == fiv.getJbIdCancel() || ae.getSource() == fiv.getJbPwCancel()) {
			fiv.dispose();
		} // end if
	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		fiv.dispose();
	}

}// class
