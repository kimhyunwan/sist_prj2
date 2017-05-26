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

		//메인 패널 설정
		JPanel mainPanel = new JPanel();
		
		//각 탭들을 만들어 놓은 메소드 호출
		idTabPane();
		pwTabPane();

		//틀 설정
		setBounds(300, 300, 330, 350);
		setTitle("아이디찾기 / 비밀번호찾기");
		setLayout(null);
		
		//각 탭들을 담을 메인패널 설정
		mainPanel.setBounds(2, 0, 310, 310);
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);
		
		//메인패널에 각 탭들을 삽입
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.addTab( "아이디 찾기", idTab);
		tabPane.addTab( "비밀번호 찾기", pwTab);
		mainPanel.add(tabPane);
		add(mainPanel);
		
		//이벤트처리
		FindIdViewEvt five=new FindIdViewEvt(this);
		jbFindId.addActionListener(five);
		jbFindPass.addActionListener(five);
		jbIdCancel.addActionListener(five);
		jbPwCancel.addActionListener(five);
		
		//가시화
		setVisible(true);
		//종료설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			//라벨모음
		jplb.add(jlName);
		jplb.add(jlSsn);
		jplb.add(jlId);
		jplb.add(jlQuest);
		jplb.add(jlAnswar);
			//기재부분 모음
		jptf.add(jtfPwName);
		jptf.add(jpSsn);
		jptf.add(jtfId);
		jptf.add(jcbQuest);
		jptf.add(jtfAnswer);
			//주민번호창 패널
		jpSsn.add(jtfPwSsn);
		jpSsn.add(hyphen);
		jpSsn.add(jpwPwSsn);
			//버튼 패널
		jpbtn.add(jbFindPass);
		jpbtn.add(jbPwCancel);
		//최종 삽입
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
