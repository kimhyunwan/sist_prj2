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
		super("sist�߰�����");
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/bg_green.jpg");
		JLabel backgroundImg = new JLabel(icon);
		ImageIcon logo = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/logo.jpg");
		JLabel logoImg = new JLabel(logo);
		JLabel jlId = new JLabel("���̵�");
		JLabel jlPass = new JLabel("��й�ȣ");
		jtfId=new JTextField();
		jpwPass = new JPasswordField();
		jbLogin=new JButton("Login");
		jbJoin=new JButton("ȸ������");
		jbFind=new JButton("���̵�/��й�ȣ ã��");
		
		//�ڵ���ġ ����
		setLayout(null);
		
		//������Ʈ�� ��ġ ��ġ����
		logoImg.setBounds(20, 20, 80, 80);
		jlId.setBounds(110, 30, 50, 15);
		jlPass.setBounds(110, 70, 70, 15);
		jtfId.setBounds(180, 28, 110,25);
		jpwPass.setBounds(180, 65, 110,25);
		jbLogin.setBounds(300, 28 , 80,60);
		jbJoin.setBounds(110, 100, 100,25);
		jbFind.setBounds(220, 100, 160,25);	
		backgroundImg.setBounds(0,0, 450, 300);
		//������Ʈ ��ġ.
		add(logoImg);
		add(jlId);
		add(jlPass);
		add(jtfId);
		add(jpwPass);
		add(jbLogin);
		add(jbJoin);
		add(jbFind);
		add(backgroundImg);
		
		//�̺�Ʈ �߰�
		LoginViewEvt lve = new LoginViewEvt(this);
		jbLogin.addActionListener(lve);
		jbJoin.addActionListener(lve);
		jbFind.addActionListener(lve);
		jtfId.addActionListener(lve);
		jpwPass.addActionListener(lve);
		
		//�������� ũ��
		setBounds(300,80,425,180);
		//����ȭ
		setVisible(true);
		//â ũ�� ����
		setResizable(false);
		//�����̺�Ʈó��
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();                                                     
			}//windowClosing
		});
		
	}//LoginView

	//LoginViewEvt���� �α��� ����� ����.
	public void setFlag(boolean flag, int cnt){
		this.flag=flag;
		this.cnt=cnt;
	}//setFlag
	
	public boolean getFlag() throws LoginException{
		//�α����� ���������� ���̵� ��й�ȣ�� Ʋ���� �α��� �ͼ��� �����߻�
		if(cnt==1&&!flag){
			throw new LoginException();
		}//end if
		//�α����� �������� �ʰ� �ݱ⸦ Ŭ���ϸ� ������ false�� ��ȯ
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
