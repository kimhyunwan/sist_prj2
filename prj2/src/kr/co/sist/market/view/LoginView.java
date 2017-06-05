package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.security.auth.login.LoginException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.market.evt.LoginViewEvt;

@SuppressWarnings("serial")
public class LoginView extends JFrame {
	private JTextField jtfId;
	private JPasswordField jpwPass;
	private JButton jbLogin,jbJoin,jbFind;
	private boolean flag;
	private int cnt;
	public LoginView(){
		super("sist중고장터");
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/bg_green.jpg");
		JLabel backgroundImg = new JLabel(icon);
		ImageIcon logo = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/logo.jpg");
		JLabel logoImg = new JLabel(logo);
		JLabel jlId = new JLabel("아이디");
		JLabel jlPass = new JLabel("비밀번호");
		jtfId=new JTextField();
		jpwPass = new JPasswordField();
		jbLogin=new JButton("Login");
		jbJoin=new JButton("회원가입");
		jbFind=new JButton("아이디/비밀번호 찾기");
		
		//자동배치 해제
		setLayout(null);
		
		//컴포넌트의 배치 위치설정
		logoImg.setBounds(20, 20, 80, 80);
		jlId.setBounds(110, 30, 50, 15);
		jlPass.setBounds(110, 70, 70, 15);
		jtfId.setBounds(180, 28, 110,25);
		jpwPass.setBounds(180, 65, 110,25);
		jbLogin.setBounds(300, 28 , 80,60);
		jbJoin.setBounds(110, 100, 100,25);
		jbFind.setBounds(220, 100, 160,25);	
		backgroundImg.setBounds(0,0, 450, 300);
		//컴포넌트 배치.
		add(logoImg);
		add(jlId);
		add(jlPass);
		add(jtfId);
		add(jpwPass);
		add(jbLogin);
		add(jbJoin);
		add(jbFind);
		add(backgroundImg);
		
		//이벤트 추가
		LoginViewEvt lve = new LoginViewEvt(this);
		jbLogin.addActionListener(lve);
		jbJoin.addActionListener(lve);
		jbFind.addActionListener(lve);
		jtfId.addActionListener(lve);
		jpwPass.addActionListener(lve);
		
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
		
	}//LoginView

	//LoginViewEvt에서 로그인 결과를 얻어간다.
	public void setFlag(boolean flag, int cnt){
		this.flag=flag;
		this.cnt=cnt;
	}//setFlag
	
	public boolean getFlag() throws LoginException{
		//로그인을 수행했지만 아이디나 비밀번호가 틀려서 로그인 익셉션 강제발생
		if(cnt==1&&!flag){
			throw new LoginException();
		}//end if
		//로그인을 수행하지 않고 닫기를 클릭하면 무조건 false가 반환
		return flag;
	}//getFlag
	
	public   JTextField getJtfId() {
		return jtfId;
	}
	public JPasswordField getJpwPass() {
		return jpwPass;
	}
	public JButton getJbLogin() {
		return jbLogin;
	}
	public JButton getJbJoin() {
		return jbJoin;
	}
	public JButton getJbFind() {
		return jbFind;
	}
	public static void main(String[] args) {
		new LoginView();
	}//main
}//class
