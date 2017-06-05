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

	// ���̵� ã�� â
	private void findId() {
		try {
			String name = fiv.getJtfIdName().getText().trim();
			String ssnBack = new String(fiv.getJpwIdSsn().getPassword()).trim();
			String ssnFront = fiv.getJtfIdSsn().getText().trim();
			String ssn = ssnFront + ssnBack;

			// �̸� ĭ�� ������� ���
			if (name.equals("")) {
				JOptionPane.showMessageDialog(fiv, "�̸��� �Է��� �ּ���");
				fiv.getJtfIdName().requestFocus();
			} else {

				// �ֹι�ȣ ĭ�� ������� ���
				if (ssnBack.equals("") || ssnFront.equals("")) {
					JOptionPane.showMessageDialog(fiv, "�ֹι�ȣ�� �Է��� �ּ���");
					fiv.getJtfIdSsn().requestFocus();

					// �ֹι�ȣ �ڸ��� Ȯ��
				} else if (ssnFront.length() != 6 || ssnBack.length() != 7) {
					JOptionPane.showMessageDialog(fiv, "�ùٸ� �ֹι�ȣ�� �Է����ּ���");
					fiv.getJtfIdSsn().requestFocus();
				} else { // �ֹι�ȣ â�� ���� �Էµǰ� �ڸ����� �´ٸ�

					IdVO iv = new IdVO(name, ssn);
					CustomerDAO cd = CustomerDAO.getInstance();
					String id;
					id = cd.selectMyId(iv);

					// id�� ���翩��
					if (id.equals("")) { // id�� �������� ���Ѵٸ� ���ٴ� ���̴�
						JOptionPane.showMessageDialog(fiv, "�����Ͻ� �̷��� �����ϴ�.");
					} else { // �����´ٸ� �ش� ���̵� �˷��ش�.
						String msg = "�ش� ���̵�� " + id + " �Դϴ�.";
						JOptionPane.showMessageDialog(fiv, msg);
					} // end else ���̵� ��������
				} // else �ֹι�ȣ ĭ �� �ڸ���
			} // else �̸�ĭ
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		fiv.getJtfIdName().setText("");
		fiv.getJtfIdSsn().setText("");
		fiv.getJpwIdSsn().setText("");
	}// findId

	private void findPass() {
		String name = fiv.getJtfPwName().getText().trim();
		String ssnBack = new String(fiv.getJpwPwSsn().getPassword()).trim();
		String ssnFront = fiv.getJtfPwSsn().getText().trim();
		String ssn = ssnFront + ssnBack;
		String id = fiv.getJtfId().getText().trim();
		int quNum = fiv.getJcbQuest().getSelectedIndex();
		String answer = fiv.getJtfAnswer().getText().trim();

		// �̸� ĭ�� ������� ���
		if (name.equals("")) {
			JOptionPane.showMessageDialog(fiv, "�̸��� �Է��� �ּ���");
			fiv.getJtfPwName().requestFocus();
			return;
		} else {

			// �ֹι�ȣ ĭ�� ������� ���
			if (ssnBack.equals("") || ssnFront.equals("")) {
				JOptionPane.showMessageDialog(fiv, "�ֹι�ȣ�� �Է��� �ּ���");
				fiv.getJtfPwSsn().requestFocus();
				return;

			} else if (ssnFront.length() != 6 || ssnBack.length() != 7) {
				// �ֹι�ȣ ���̰� �´���
				JOptionPane.showMessageDialog(fiv, "�ùٸ� �ֹι�ȣ�� �Է����ּ���");
				fiv.getJtfPwSsn().requestFocus();
				return;
			} else {

				// ���̵� ĭ�� ��ĭ�� ���
				if (id.equals("")) {
					JOptionPane.showMessageDialog(fiv, "���̵� �Է��� �ּ���");
					fiv.getJtfId().requestFocus();
					return;
				} else {
					CustomerDAO cd = CustomerDAO.getInstance();

					// ��й�ȣ ���� �亯�� ��ĭ�� ���
					if (answer.equals("")) {
						JOptionPane.showMessageDialog(fiv, "��й�ȣ ������ �����ּ���");
						return;
					} else {
						try {
							PassVO pv = new PassVO(name, ssn, id, answer, quNum);
							String pass = cd.selectMyPass(pv);
							String msg = "�ش� ���̵��� ��й�ȣ�� " + pass + "�Դϴ�.";

							// �ش� ��й�ȣ ����
							if (pass.equals("")) {
								JOptionPane.showMessageDialog(fiv,
										"�ش� ���̵�� �������� �ʽ��ϴ�" + "\n�̰����ڽø� ȸ��������" + "\n�����ڽø� ������ ��Ȯ�� �Է��� �ּ���");
							} else {
								JOptionPane.showMessageDialog(fiv, msg);
							} // end else
						} catch (SQLException e) {
							e.printStackTrace();
						} // end catch
					} // end else ��й�ȣ ���� �亯 ��ĭ
				} // end ���̵�ĭ
			} // end else �ֹι�ȣ ĭ �� ����
		} // end else �̸�ĭ

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
