package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.market.evt.JoinViewEvt;

/**
 * 회원 가입 창
 * 
 * @author 장재훈
 *
 */
public class JoinView extends JFrame {

	private JTextField jtfName, jtfSsn, jtfId, jtfAnswer;
	private JButton jbImage, jbSignUp, jbCancel;
	private JComboBox<String> jcbQuest;
	private JTextArea jtaIntro;
	private JPasswordField jpwSsn, jpwPass, jpwPassChk;
	private JLabel jlimg;
	private DefaultComboBoxModel<String> dcbmQu;

	public JoinView() {
		super("회원가입");

		ImageIcon icon = new ImageIcon("C:/dev/workspace/test/src/test/background.jpg");
		JLabel backgroundImg = new JLabel(icon);
		
		dcbmQu=new DefaultComboBoxModel<String>();
		
		jtfName = new JTextField(15);
		jtfSsn = new JTextField(6);
		jtfId = new JTextField(15);
		jtfAnswer = new JTextField(15);
		jbImage = new JButton("이미지등록");
		jbSignUp = new JButton("가입");
		jbCancel = new JButton("취소");
		jcbQuest = new JComboBox<>(dcbmQu);
		jtaIntro = new JTextArea();
		jpwSsn = new JPasswordField(7);
		jpwPass = new JPasswordField();
		jpwPassChk = new JPasswordField();

		jtaIntro.setBorder(new TitledBorder("자기소개"));
		JScrollPane jsIntro = new JScrollPane(jtaIntro);
		
		ImageIcon profile = new ImageIcon("C:/Users/user/git/sist_prj2/prj2/src/kr/co/sist/market/img/default.jpg");
		
		JLabel jlName = new JLabel("이름");
		JLabel jlSsn = new JLabel("주민번호");
		JLabel jlId = new JLabel("아이디");
		JLabel jlPass = new JLabel("비밀번호");
		JLabel jlPassChk = new JLabel("비밀번호 확인");
		JLabel jlQuest = new JLabel("비밀번호 질문");
		JLabel jlAnswer = new JLabel("비밀번호 답변");
		JLabel jlhyphen = new JLabel("-");
		jlimg = new JLabel(profile);

		setLayout(null); // 수동배치
		
		// 각 컴포넌트 배치
		setBounds(50, 50, 610, 498);
		jlimg.setBounds(20, 20, 180,195);
		jlName.setBounds(230, 30, 100, 25);
		jlSsn.setBounds(230, 63, 100, 25);
		jlId.setBounds(230, 96, 100, 25);
		jlPass.setBounds(230, 129, 100, 25);
		jlPassChk.setBounds(230, 162, 100, 25);
		jlQuest.setBounds(230, 195, 100, 25);
		jlAnswer.setBounds(230, 228, 100, 25);
		
		jtfName.setBounds(325, 30, 160, 25);
		jtfSsn.setBounds(325, 63, 72, 25);
		jlhyphen.setBounds(405, 63, 5, 25);
		jpwSsn.setBounds(412, 63, 72, 25);
		jtfId.setBounds(325, 96, 160, 25);
		jpwPass.setBounds(325, 129, 160, 25);
		jpwPassChk.setBounds(325, 162, 160, 25);
		jcbQuest.setBounds(325, 195, 257, 25);
		jtfAnswer.setBounds(325, 228, 257, 25);
		
		jbImage.setBounds(58, 223, 100, 25);
		jsIntro.setBounds(20, 270, 560, 160);
		jbSignUp.setBounds(420, 440, 70, 25);
		jbCancel.setBounds(505, 440, 70, 25);
		backgroundImg.setBounds(0,-63, 650, 600);
		
		add(jlimg);
		add(jlName);
		add(jlSsn);
		add(jlId);
		add(jlPass);
		add(jlPassChk);
		add(jlQuest);
		add(jlAnswer);
		add(jtfName);
		add(jtfSsn);
		add(jlhyphen);
		add(jpwSsn);
		add(jpwSsn);
		add(jtfId);
		add(jpwPass);
		add(jpwPassChk);
		add(jcbQuest);
		add(jtfAnswer);
		add(jbImage);
		add(jsIntro);
		add(jbSignUp);
		add(jbCancel);
		add(backgroundImg);


		// 이벤트 처리
		// jbImage.addActionListener();
		// jbSignUp.addActionListener();
		JoinViewEvt jve = new JoinViewEvt(this);
		jbCancel.addActionListener(jve);
		jbImage.addActionListener(jve);
		jbSignUp.addActionListener(jve);

		// 가시화
		setVisible(true);
		//창 크기 고정
		setResizable(false);
		// 종료이벤트처리
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}// windowClosing
		});
	}// joinView
	
	

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfSsn() {
		return jtfSsn;
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public JTextField getJtfAnswer() {
		return jtfAnswer;
	}

	public JButton getJbImage() {
		return jbImage;
	}
	
	public DefaultComboBoxModel<String> getDcbmQu() {
		return dcbmQu;
	}

	public JButton getJbSignUp() {
		return jbSignUp;
	}

	public JButton getJbCancel() {
		return jbCancel;
	}

	public JComboBox<String> getJcbQuest() {
		return jcbQuest;
	}

	public JTextArea getJtaIntro() {
		return jtaIntro;
	}

	public JPasswordField getJpwSsn() {
		return jpwSsn;
	}

	public JPasswordField getJpwPass() {
		return jpwPass;
	}

	public JPasswordField getJpwPassChk() {
		return jpwPassChk;
	}
	
	public JLabel getJlimg() {
		return jlimg;
	}

	public static void main(String[] args) {
		new JoinView();
	}// main

}// class