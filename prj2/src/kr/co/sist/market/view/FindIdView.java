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

public class FindIdView extends JFrame {

	private JTextField jtfIdName, jtfIdSsn, jtfPwName, jtfPwSsn, jtfId, jtfAnswer;
	private JComboBox<String> jcbQuest;
	private JPasswordField jpwIdSsn, jpwPwSsn;
	private JButton jbFindId, jbFindPass, jbIdCancel, jbPwCancel;
	
	private JPanel idTab,pwTab;
	
	
	public FindIdView() {

		JPanel mainPanel = new JPanel();
		
		idTabPane();
		pwTabPane();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 350);
		setTitle("���̵�ã�� / ��й�ȣã��");
		setLayout(null);
		
		
		mainPanel.setBounds(2, 0, 310, 310);
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);
		
		
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.addTab( "���̵� ã��", idTab);
		tabPane.addTab( "��й�ȣ ã��", pwTab);
		mainPanel.add(tabPane);
		add(mainPanel);
		
		setVisible(true);
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
		jplb.add(jlName);
		jplb.add(jlSsn);
		jplb.add(jlId);
		jplb.add(jlQuest);
		jplb.add(jlAnswar);

		jptf.add(jtfPwName);
		jptf.add(jpSsn);
		jptf.add(jtfId);
		jptf.add(jcbQuest);
		jptf.add(jtfAnswer);
		
		jpSsn.add(jtfPwSsn);
		jpSsn.add(hyphen);
		jpSsn.add(jpwPwSsn);
		
		jpbtn.add(jbFindPass);
		jpbtn.add(jbPwCancel);
		
		pwTab.add(jplb);
		pwTab.add(jptf);
		pwTab.add(jpbtn);
	}//pwTabPane
	
	public static void main(String[] args) {
		new FindIdView();
	}//main
	
}//class
