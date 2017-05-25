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
		setTitle("아이디찾기 / 비밀번호찾기");
		setLayout(null);
		
		
		mainPanel.setBounds(2, 0, 310, 310);
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);
		
		
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.addTab( "아이디 찾기", idTab);
		tabPane.addTab( "비밀번호 찾기", pwTab);
		mainPanel.add(tabPane);
		add(mainPanel);
		
		setVisible(true);
	}//FindIdView
	
	//////////////////////////////////////////////////////////////////////////////
	/////////// ID 찾기 창                                          ///////////
	//////////////////////////////////////////////////////////////////////////////
	
	/**
	 * id찾기 창
	 */
	public void idTabPane(){
		
		//패널생성
		idTab=new JPanel();
		
		JPanel jplb=new JPanel();
		JPanel jptf=new JPanel();
		JPanel jpbtn=new JPanel();
		
		JPanel jpSsn=new JPanel();
		
		//라벨생성
		JLabel jlName = new JLabel("이름");
		JLabel jlSsn = new JLabel("주민번호");
		JLabel hyphen = new JLabel("-");
		
		//필요항복 설정
		jtfIdName = new JTextField();
		jtfIdSsn = new JTextField(6);
		jpwIdSsn = new JPasswordField(7);
		jbFindId = new JButton("찾기");
		jbIdCancel = new JButton("취소");
		
		//화면설정
		idTab.setLayout(null);
		jpSsn.setLayout(new FlowLayout());
		jpbtn.setLayout(new FlowLayout());
		jplb.setLayout(new GridLayout(2, 1));
		jptf.setLayout(new GridLayout(2, 1));
		
		//배치
		//idTab.setBounds(2, 0, 300, 300);
		jplb.setBounds(25, 15, 100, 60);
		jptf.setBounds(125, 15, 170, 60);
		jpbtn.setBounds(150, 210, 150, 150);
		
		//삽입
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
	/////////// 비밀번호 찾기 창                                   ///////////
	//////////////////////////////////////////////////////////////////////////////
	/**
	 * 비밀번호 찾기 창
	 */
	public void pwTabPane(){
		
		//패널생성
		pwTab=new JPanel();
		
		JPanel jplb=new JPanel();
		JPanel jptf=new JPanel();
		JPanel jpbtn=new JPanel();
		
		JPanel jpSsn=new JPanel();
		
		//라벨생성
		JLabel jlName = new JLabel("이름");
		JLabel jlSsn = new JLabel("주민번호");
		JLabel hyphen = new JLabel("-");
		JLabel jlId = new JLabel("아이디");
		JLabel jlQuest = new JLabel("비밀번호질문");
		JLabel jlAnswar = new JLabel("비밀번호답변");
		
		//필요항복 설정
		jtfPwName = new JTextField();
		jtfPwSsn = new JTextField(6);
		jpwPwSsn = new JPasswordField(7);
		jtfId = new JTextField();
		jcbQuest = new JComboBox<String>();
		jtfAnswer = new JTextField();
		jbFindPass = new JButton("찾기");
		jbPwCancel = new JButton("취소");
		
		//화면설정
		pwTab.setLayout(null);
		jpSsn.setLayout(new FlowLayout());
		jpbtn.setLayout(new FlowLayout());
		jplb.setLayout(new GridLayout(5, 1));
		jptf.setLayout(new GridLayout(5, 1));
		
		//배치
		idTab.setBounds(100, 100, 200, 360);
		jplb.setBounds(25, 15, 100, 150);
		jptf.setBounds(125, 15, 170, 150);
		jpbtn.setBounds(150, 210, 150, 150);
		
		//삽입
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
