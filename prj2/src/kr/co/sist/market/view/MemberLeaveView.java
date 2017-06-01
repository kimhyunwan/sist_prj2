package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.market.evt.MainViewEvt;
import kr.co.sist.market.evt.MemberLeaveViewEvt;


@SuppressWarnings("serial")
public class MemberLeaveView extends JFrame {
	private JTextField jtfId;
	private JPasswordField jpwPass;
	private JButton jbMemberLeave;
	public MemberLeaveView(){
		super("회원탈퇴");
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/bg_green.jpg");
		JLabel backgroundImg = new JLabel(icon);
		ImageIcon logo = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/logo.jpg");
		JLabel logoImg = new JLabel(logo);
		JLabel jlId = new JLabel("아이디");
		JLabel jlPass = new JLabel("비밀번호");
		jtfId=new JTextField();
		jpwPass = new JPasswordField();
		jbMemberLeave=new JButton("회원탈퇴");
		JLabel jlInfo = new JLabel("회원탈퇴를위해 아이디와 비밀번호를 입력하세요.");

		
		//자동배치 해제
		setLayout(null);
		
		//컴포넌트의 배치 위치설정
		logoImg.setBounds(20, 50, 80, 80);
		jlId.setBounds(110, 60, 50, 15);
		jlPass.setBounds(110, 100, 70, 15);
		jtfId.setBounds(180, 58, 110,25);
		jpwPass.setBounds(180, 95, 110,25);
		jbMemberLeave.setBounds(300, 58 , 100,60);
		backgroundImg.setBounds(0,0, 450, 300);
		jlInfo.setBounds(10,10, 100, 15);
		//컴포넌트 배치.
		add(logoImg);
		add(jlId);
		add(jlPass);
		add(jtfId);
		add(jpwPass);
		add(jbMemberLeave);
		add(backgroundImg);
		add(jlInfo);
		
		//이벤트 추가
		MemberLeaveViewEvt mve = new MemberLeaveViewEvt(this);
		jbMemberLeave.addActionListener(mve);
		//윈도우의 크기
		setBounds(300,80,425,180);
		//가시화
		setVisible(true);
		//창 크기 고정
		setResizable(false);
		//종료이벤트처리
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();                                                     
			}//windowClosing
		});
		
	}//MemberLeaveView
	
	public static void main(String[] args) {
		new MemberLeaveView();
	}


}//class
