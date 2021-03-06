package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.evt.LoginViewEvt;
import kr.co.sist.market.evt.MainViewEvt;
import kr.co.sist.market.evt.MemberLeaveViewEvt;


@SuppressWarnings("serial")
public class MemberLeaveView extends JFrame {
	private JTextField jtfId;
	private JPasswordField jpwPass;
	private JButton jbMemberLeave;
	private LoginViewEvt lve;
	private CustomerDAO c_dao;
	private MainView mv;
	
	@SuppressWarnings("static-access")
	public MemberLeaveView(MainView mv){
		super("회원탈퇴");
		this.mv=mv;
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/bg_green.jpg");
		JLabel backgroundImg = new JLabel(icon);
		ImageIcon logo = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/logo.jpg");
		JLabel logoImg = new JLabel(logo);
		JLabel jlId = new JLabel("아이디");
		JLabel jlPass = new JLabel("비밀번호");
		jtfId=new JTextField(lve.id);
		jpwPass = new JPasswordField();
		jbMemberLeave=new JButton("회원탈퇴");
		JLabel jlInfo = new JLabel("회원탈퇴를위해 비밀번호를 다시 입력하세요.");
		
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
		jlInfo.setBounds(25,20, 300, 15);
		//컴포넌트 배치.
		add(logoImg);
		add(jlId);
		add(jlPass);
		add(jtfId);
		add(jpwPass);
		add(jbMemberLeave);
		add(jlInfo);
		add(backgroundImg);
	     
		jtfId.setEditable(false);
		
		//이벤트 추가
			MemberLeaveViewEvt mve = new MemberLeaveViewEvt(this,mv);
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
	public JTextField getJtfId() {
		return jtfId;
	}
	public JPasswordField getJpwPass() {
		return jpwPass;
	}
	public JButton getJbMemberLeave() {
		return jbMemberLeave;
	}

}//class
