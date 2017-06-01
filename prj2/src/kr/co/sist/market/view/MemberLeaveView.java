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
		super("ȸ��Ż��");
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/bg_green.jpg");
		JLabel backgroundImg = new JLabel(icon);
		ImageIcon logo = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/logo.jpg");
		JLabel logoImg = new JLabel(logo);
		JLabel jlId = new JLabel("���̵�");
		JLabel jlPass = new JLabel("��й�ȣ");
		jtfId=new JTextField();
		jpwPass = new JPasswordField();
		jbMemberLeave=new JButton("ȸ��Ż��");
		JLabel jlInfo = new JLabel("ȸ��Ż������ ���̵�� ��й�ȣ�� �Է��ϼ���.");

		
		//�ڵ���ġ ����
		setLayout(null);
		
		//������Ʈ�� ��ġ ��ġ����
		logoImg.setBounds(20, 50, 80, 80);
		jlId.setBounds(110, 60, 50, 15);
		jlPass.setBounds(110, 100, 70, 15);
		jtfId.setBounds(180, 58, 110,25);
		jpwPass.setBounds(180, 95, 110,25);
		jbMemberLeave.setBounds(300, 58 , 100,60);
		backgroundImg.setBounds(0,0, 450, 300);
		jlInfo.setBounds(10,10, 100, 15);
		//������Ʈ ��ġ.
		add(logoImg);
		add(jlId);
		add(jlPass);
		add(jtfId);
		add(jpwPass);
		add(jbMemberLeave);
		add(backgroundImg);
		add(jlInfo);
		
		//�̺�Ʈ �߰�
		MemberLeaveViewEvt mve = new MemberLeaveViewEvt(this);
		jbMemberLeave.addActionListener(mve);
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
		
	}//MemberLeaveView
	
	public static void main(String[] args) {
		new MemberLeaveView();
	}


}//class
