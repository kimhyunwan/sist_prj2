package kr.co.sist.market.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import kr.co.sist.market.evt.FindIdViewEvt;

public class FindIdView extends JFrame {

	private JTextField jtfIdName, jtfIdSsn, jtfPwName, jtfPwSsn, jtfId, jtfAnswer;
	private JComboBox<String> jcbQuest;
	private JPasswordField jpwIdSsn, jpwPwSsn;
	private JButton jbFindId, jbFindPass, jbIdCancel, jbPwCancel;
	
	private JPanel idTab,pwTab;
	
	
	public FindIdView() {

		//���� �г� ����
		JPanel mainPanel = new JPanel();
		
		//�� �ǵ��� ����� ���� �޼ҵ� ȣ��
		idTabPane();
		pwTabPane();

		//Ʋ ����
		setBounds(300, 300, 330, 350);
		setTitle("���̵�ã�� / ��й�ȣã��");
		setLayout(null);
		
		//�� �ǵ��� ���� �����г� ����
		mainPanel.setBounds(2, 0, 310, 310);
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);
		
		//�����гο� �� �ǵ��� ����
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.addTab( "���̵� ã��", idTab);
		tabPane.addTab( "��й�ȣ ã��", pwTab);
		mainPanel.add(tabPane);
		add(mainPanel);
		
		//�̺�Ʈó��
		FindIdViewEvt five=new FindIdViewEvt(this);
		jbFindId.addActionListener(five);
		jbFindPass.addActionListener(five);
		jbIdCancel.addActionListener(five);
		jbPwCancel.addActionListener(five);
		
		//����ȭ
		setVisible(true);
		//���ἳ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//FindIdView
	
	//////////////////////////////////////////////////////////////////////////////
	/////////// ID ã�� â                                          ///////////
	//////////////////////////////////////////////////////////////////////////////
	
	/**
	 * idã�� â
	 */
	public void idTabPane(){
		
		//�гλ���
		idTab=new JPanel();
		
		JPanel jplb=new JPanel();
		JPanel jptf=new JPanel();
		JPanel jpbtn=new JPanel();
		
		JPanel jpSsn=new JPanel();
		
		//�󺧻���
		JLabel jlName = new JLabel("�̸�");
		JLabel jlSsn = new JLabel("�ֹι�ȣ");
		JLabel hyphen = new JLabel("-");
		
		//�ʿ��׺� ����
		jtfIdName = new JTextField();
		jtfIdSsn = new JTextField(6);
		jpwIdSsn = new JPasswordField(7);
		jbFindId = new JButton("ã��");
		jbIdCancel = new JButton("���");
		
		//ȭ�鼳��
		idTab.setLayout(null);
		jpSsn.setLayout(new FlowLayout());
		jpbtn.setLayout(new FlowLayout());
		jplb.setLayout(new GridLayout(2, 1));
		jptf.setLayout(new GridLayout(2, 1));
		
		//��ġ
		//idTab.setBounds(2, 0, 300, 300);
		jplb.setBounds(25, 15, 100, 60);
		jptf.setBounds(125, 15, 170, 60);
		jpbtn.setBounds(150, 210, 150, 150);
		
		//����
		jplb.add(jlName);
		jplb.add(jlSsn);

		jptf.add(jtfIdName);
		jptf.add(jpSsn);
		
		jpSsn.add(jtfIdSsn);
		jpSsn.add(hyphen);
		jpSsn.add(jpwIdSsn);
		
		jpbtn.add(jbFindId);
		jpbtn.add(jbIdCancel);
		
		idTab.add(jplb);
		idTab.add(jptf);
		idTab.add(jpbtn);
		
	}//idTabPane
	
	//////////////////////////////////////////////////////////////////////////////
	/////////// ��й�ȣ ã�� â                                   ///////////
	//////////////////////////////////////////////////////////////////////////////
	/**
	 * ��й�ȣ ã�� â
	 */
	public void pwTabPane(){
		
		//�гλ���
		pwTab=new JPanel();
		
		JPanel jplb=new JPanel();
		JPanel jptf=new JPanel();
		JPanel jpbtn=new JPanel();
		
		JPanel jpSsn=new JPanel();
		
		//�󺧻���
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
		jcbQuest = new JComboBox<String>();
		jtfAnswer = new JTextField();
		jbFindPass = new JButton("ã��");
		jbPwCancel = new JButton("���");
		
		//ȭ�鼳��
		pwTab.setLayout(null);
		jpSsn.setLayout(new FlowLayout());
		jpbtn.setLayout(new FlowLayout());
		jplb.setLayout(new GridLayout(5, 1));
		jptf.setLayout(new GridLayout(5, 1));
		
		//��ġ
		idTab.setBounds(100, 100, 200, 360);
		jplb.setBounds(25, 15, 100, 150);
		jptf.setBounds(125, 15, 170, 150);
		jpbtn.setBounds(150, 210, 150, 150);
		
		//����
			//�󺧸���
		jplb.add(jlName);
		jplb.add(jlSsn);
		jplb.add(jlId);
		jplb.add(jlQuest);
		jplb.add(jlAnswar);
			//����κ� ����
		jptf.add(jtfPwName);
		jptf.add(jpSsn);
		jptf.add(jtfId);
		jptf.add(jcbQuest);
		jptf.add(jtfAnswer);
			//�ֹι�ȣâ �г�
		jpSsn.add(jtfPwSsn);
		jpSsn.add(hyphen);
		jpSsn.add(jpwPwSsn);
			//��ư �г�
		jpbtn.add(jbFindPass);
		jpbtn.add(jbPwCancel);
		//���� ����
		pwTab.add(jplb);
		pwTab.add(jptf);
		pwTab.add(jpbtn);
		
	}//pwTabPane
	
	public static void main(String[] args) {
		new FindIdView();
	}//main

	public JTextField getJtfIdName() {
		return jtfIdName;
	}

	public void setJtfIdName(JTextField jtfIdName) {
		this.jtfIdName = jtfIdName;
	}

	public JTextField getJtfIdSsn() {
		return jtfIdSsn;
	}

	public void setJtfIdSsn(JTextField jtfIdSsn) {
		this.jtfIdSsn = jtfIdSsn;
	}

	public JTextField getJtfPwName() {
		return jtfPwName;
	}

	public void setJtfPwName(JTextField jtfPwName) {
		this.jtfPwName = jtfPwName;
	}

	public JTextField getJtfPwSsn() {
		return jtfPwSsn;
	}

	public void setJtfPwSsn(JTextField jtfPwSsn) {
		this.jtfPwSsn = jtfPwSsn;
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public void setJtfId(JTextField jtfId) {
		this.jtfId = jtfId;
	}

	public JTextField getJtfAnswer() {
		return jtfAnswer;
	}

	public void setJtfAnswer(JTextField jtfAnswer) {
		this.jtfAnswer = jtfAnswer;
	}

	public JComboBox<String> getJcbQuest() {
		return jcbQuest;
	}

	public void setJcbQuest(JComboBox<String> jcbQuest) {
		this.jcbQuest = jcbQuest;
	}

	public JPasswordField getJpwIdSsn() {
		return jpwIdSsn;
	}

	public void setJpwIdSsn(JPasswordField jpwIdSsn) {
		this.jpwIdSsn = jpwIdSsn;
	}

	public JPasswordField getJpwPwSsn() {
		return jpwPwSsn;
	}

	public void setJpwPwSsn(JPasswordField jpwPwSsn) {
		this.jpwPwSsn = jpwPwSsn;
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
	
	
}//class
