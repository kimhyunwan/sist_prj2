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

	// 아이디 찾기 창
	private void findId() {
		try {
			String name = fiv.getJtfIdName().getText().trim();
			String ssnBack = new String(fiv.getJpwIdSsn().getPassword()).trim();
			String ssnFront = fiv.getJtfIdSsn().getText().trim();
			String ssn = ssnFront + ssnBack;

			// 이름 칸이 비어있을 경우
			if (name.equals("")) {
				JOptionPane.showMessageDialog(fiv, "이름을 입력해 주세요");
				fiv.getJtfIdName().requestFocus();
			} else {

				// 주민번호 칸이 비어있을 경우
				if (ssnBack.equals("") || ssnFront.equals("")) {
					JOptionPane.showMessageDialog(fiv, "주민번호를 입력해 주세요");
					fiv.getJtfIdSsn().requestFocus();

					// 주민번호 자리수 확인
				} else if (ssnFront.length() != 6 || ssnBack.length() != 7) {
					JOptionPane.showMessageDialog(fiv, "올바른 주민번호를 입력해주세요");
					fiv.getJtfIdSsn().requestFocus();
				} else { // 주민번호 창에 수가 입력되고 자릿수가 맞다면

					IdVO iv = new IdVO(name, ssn);
					CustomerDAO cd = CustomerDAO.getInstance();
					String id;
					id = cd.selectMyId(iv);

					// id의 존재여부
					if (id.equals("")) { // id를 가져오지 못한다면 없다는 것이니
						JOptionPane.showMessageDialog(fiv, "가입하신 이력이 없습니다.");
					} else { // 가져온다면 해당 아이디를 알려준다.
						String msg = "해당 아이디는 " + id + " 입니다.";
						JOptionPane.showMessageDialog(fiv, msg);
					} // end else 아이디 진위여부
				} // else 주민번호 칸 과 자릿수
			} // else 이름칸
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

		// 이름 칸이 비어있을 경우
		if (name.equals("")) {
			JOptionPane.showMessageDialog(fiv, "이름을 입력해 주세요");
			fiv.getJtfPwName().requestFocus();
			return;
		} else {

			// 주민번호 칸이 비어있을 경우
			if (ssnBack.equals("") || ssnFront.equals("")) {
				JOptionPane.showMessageDialog(fiv, "주민번호를 입력해 주세요");
				fiv.getJtfPwSsn().requestFocus();
				return;

			} else if (ssnFront.length() != 6 || ssnBack.length() != 7) {
				// 주민번호 길이가 맞는지
				JOptionPane.showMessageDialog(fiv, "올바른 주민번호를 입력해주세요");
				fiv.getJtfPwSsn().requestFocus();
				return;
			} else {

				// 아이디 칸이 빈칸일 경우
				if (id.equals("")) {
					JOptionPane.showMessageDialog(fiv, "아이디를 입력해 주세요");
					fiv.getJtfId().requestFocus();
					return;
				} else {
					CustomerDAO cd = CustomerDAO.getInstance();

					// 비밀번호 질문 답변이 빈칸일 경우
					if (answer.equals("")) {
						JOptionPane.showMessageDialog(fiv, "비밀번호 질문에 답해주세요");
						return;
					} else {
						try {
							PassVO pv = new PassVO(name, ssn, id, answer, quNum);
							String pass = cd.selectMyPass(pv);
							String msg = "해당 아이디의 비밀번호는 " + pass + "입니다.";

							// 해당 비밀번호 도출
							if (pass.equals("")) {
								JOptionPane.showMessageDialog(fiv,
										"해당 아이디는 존재하지 않습니다" + "\n미가입자시면 회원가입을" + "\n가입자시면 정보를 정확히 입력해 주세요");
							} else {
								JOptionPane.showMessageDialog(fiv, msg);
							} // end else
						} catch (SQLException e) {
							e.printStackTrace();
						} // end catch
					} // end else 비밀번호 질문 답변 빈칸
				} // end 아이디칸
			} // end else 주민번호 칸 과 길이
		} // end else 이름칸

		fiv.getJtfPwName().setText("");
		fiv.getJtfPwSsn().setText("");
		fiv.getJpwPwSsn().setText("");
		fiv.getJtfId().setText("");
		fiv.getJtfAnswer().setText("");
	}// findPass

	@Override
	public void actionPerformed(ActionEvent ae) {

		// 아이디찾기 찾기버튼 사용시
		if (ae.getSource() == fiv.getJbFindId()) {
			findId();
		} // end if

		// 비밀번호찾기 찾기버튼 사용시
		if (ae.getSource() == fiv.getJbFindPass()) {
			findPass();
			// fiv.getJcbQuest()
		} // end if

		// 아이디찾기, 비밀번호찾기 취소버튼 사용시
		if (ae.getSource() == fiv.getJbIdCancel() || ae.getSource() == fiv.getJbPwCancel()) {
			fiv.dispose();
		} // end if
	}// actionPerformed

	@Override
	public void windowClosing(WindowEvent e) {
		fiv.dispose();
	}

}// class
