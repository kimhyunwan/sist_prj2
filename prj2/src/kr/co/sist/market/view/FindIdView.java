package kr.co.sist.market.view;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import kr.co.sist.market.evt.FindIdViewEvt;

/**
 * ���̵�ã��
 * @author user
 *
 */
@SuppressWarnings("serial")
public class FindIdView  extends JFrame {
	private JTabbedPane jtpTab;

	private JTextField jtfIdName, jtfIdSsn, jtfPwName, jtfPwSsn, jtfId, jtfAnswer;
	private JComboBox<String> jcbQuest;
	private JPasswordField jpwIdSsn, jpwPwSsn;
	private JButton jbFindId, jbFindPass, jbIdCancel, jbPwCancel;
	
	public FindIdView(){
		super("���̵�ã�� / ��й�ȣã��");
		//////////////////////////////////////////////////////////////////////////////
		/////////// ���̵� ã�� â                                     ///////////
		//////////////////////////////////////////////////////////////////////////////
		
		//�󺧻���
		ImageIcon icon = new ImageIcon("C:/Users/user/git/sist_prj2/prj2/src/kr/co/sist/market/img/bg_beige.GIF");
		JLabel backgroundImg = new JLabel(icon);
		JLabel jlidName = new JLabel("�̸�");
		JLabel jlidSsn = new JLabel("�ֹι�ȣ");
		JLabel idhyphen = new JLabel("-");
		//�ʿ��׺� ����
		JPanel idTab = new JPanel();
		jtfIdName = new JTextField();
		jtfIdSsn = new JTextField(6);
		jpwIdSsn = new JPasswordField(7);
		jbFindId = new JButton("ã��");
		jbIdCancel = new JButton("���");
		//��ġ
		jlidName.setBounds(20, 20, 30, 25);
		jlidSsn.setBounds(20, 55, 60, 25);
		idhyphen.setBounds(193, 55, 5, 25);
		jtfIdName.setBounds(115, 20, 160, 25);
		jtfIdSsn.setBounds(115, 55, 72, 25);
		jpwIdSsn.setBounds(205, 55, 72, 25);
		
		jbFindId.setBounds(218, 210, 70, 25);
		jbIdCancel.setBounds(300, 210, 70, 25);
		backgroundImg.setBounds(-60,-65, 450, 450);
		//������ġ
		idTab.add(jlidName);
		idTab.add(jlidSsn);
		idTab.add(idhyphen);
		idTab.add(jtfIdName);
		idTab.add(jtfIdSsn);
		idTab.add(jpwIdSsn);
		idTab.add(jbFindId);
		idTab.add(jbIdCancel);
		idTab.add(backgroundImg);
		//������ġ
		idTab.setLayout(null);
		idTab.setBounds(10,10,800,600);
	
		//////////////////////////////////////////////////////////////////////////////
		/////////// ��й�ȣ ã�� â                                   ///////////
		//////////////////////////////////////////////////////////////////////////////
		
		//�󺧻���
		ImageIcon icon1 = new ImageIcon("C:/Users/user/git/sist_prj2/prj2/src/kr/co/sist/market/img/bg_beige.GIF");
		JLabel backgroundImg1 = new JLabel(icon);
		JLabel jlName = new JLabel("�̸�");
		JLabel jlSsn = new JLabel("�ֹι�ȣ");
		JLabel hyphen = new JLabel("-");
		JLabel jlId = new JLabel("���̵�");
		JLabel jlQuest = new JLabel("��й�ȣ����");
		JLabel jlAnswar = new JLabel("��й�ȣ�亯");
		
		//�ʿ��׺� ����
		jtfPwName = new JTextField();
		jtfPwSsn = new JTextField(6);
		jpwPwSsn = new JPasswordField(7);
		jtfId = new JTextField();
		String[] questions = {"------------------------ ���� ------------------------","��￡ ���� �߾��� ��Ҵ�?","�ڽ��� ���� ��1ȣ��?","�ڽ��� �λ� �¿����?","���� ��￡ ���� ������ ������?",
				"Ÿ���� �𸣴� �ڽŸ��� ��ü����� �ִٸ�?","�߾��ϰ� ���� ��¥�� �ִٸ�?","�ٽ� �¾�� �ǰ� ���� ����?"};
		jcbQuest = new JComboBox<String>(questions);
		jtfAnswer = new JTextField();
		jbFindPass = new JButton("ã��");
		jbPwCancel = new JButton("���");
		
		JPanel pwTab=new JPanel();
		
		//��ġ
		jlName.setBounds(20, 20, 30, 25);
		jlSsn.setBounds(20, 55, 60, 25);
		hyphen.setBounds(193, 55, 5, 25);
		jlId.setBounds(20, 90, 40, 25);
		jlQuest.setBounds(20, 125, 80, 25);
		jlAnswar.setBounds(20, 160, 80, 25);
		
		jtfPwName.setBounds(115, 20, 160, 25);
		jtfPwSsn.setBounds(115, 55, 72, 25);
		jpwPwSsn.setBounds(205, 55, 72, 25);
		jtfId.setBounds(115, 90, 160, 25);
		jcbQuest.setBounds(115, 125, 257, 25);
		jtfAnswer.setBounds(115, 160, 257, 25);
		jbFindPass.setBounds(218, 210, 70, 25);
		jbPwCancel.setBounds(300, 210, 70, 25);
		backgroundImg1.setBounds(-60,-65, 450, 450);
		
		pwTab.add(jlName);
		pwTab.add(jlSsn);
		pwTab.add(hyphen);
		pwTab.add(jlId);
		pwTab.add(jlQuest);
		pwTab.add(jlAnswar);
		pwTab.add(jtfPwName);
		pwTab.add(jtfPwSsn);
		pwTab.add(jpwPwSsn);
		pwTab.add(jtfId);
		pwTab.add(jcbQuest);
		pwTab.add(jtfAnswer);
		pwTab.add(jbFindPass);
		pwTab.add(jbPwCancel);
		pwTab.add(backgroundImg1);

	
		//������ġ
		pwTab.setLayout(null);
		pwTab.setBounds(10,10,800,600);
		
		//���̵� �ǰ� ��й�ȣ �� ����
		jtpTab=new JTabbedPane();
		jtpTab.add("���̵�ã��", idTab);
		jtpTab.addTab("��й�ȣã��", pwTab);
		
		add("Center",jtpTab);

		idTab.setBackground(Color.WHITE);
		
		addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}//windowClosing
			
		});
		
		//�̺�Ʈó��
		FindIdViewEvt five=new FindIdViewEvt(this);
		jbFindId.addActionListener(five);
		jbFindPass.addActionListener(five);
		jbIdCancel.addActionListener(five);
		jbPwCancel.addActionListener(five);
		
		setBounds(200, 200, 400, 315);
		//����ȭ
		setVisible(true);
		//â ũ�� ����
		setResizable(false);
	}//MenuForm

	

	public JTabbedPane getJtpTab() {
		return jtpTab;
	}



	public JTextField getJtfIdName() {
		return jtfIdName;
	}



	public JTextField getJtfIdSsn() {
		return jtfIdSsn;
	}



	public JTextField getJtfPwName() {
		return jtfPwName;
	}



	public JTextField getJtfPwSsn() {
		return jtfPwSsn;
	}



	public JTextField getJtfId() {
		return jtfId;
	}



	public JTextField getJtfAnswer() {
		return jtfAnswer;
	}



	public JComboBox<String> getJcbQuest() {
		return jcbQuest;
	}



	public JPasswordField getJpwIdSsn() {
		return jpwIdSsn;
	}



	public JPasswordField getJpwPwSsn() {
		return jpwPwSsn;
	}



	public JButton getJbFindId() {
		return jbFindId;
	}



	public JButton getJbFindPass() {
		return jbFindPass;
	}



	public JButton getJbIdCancel() {
		return jbIdCancel;
	}



	public JButton getJbPwCancel() {
		return jbPwCancel;
	}



	public static void main(String[] args) {
		new FindIdView();
	}//main
	
}//class
