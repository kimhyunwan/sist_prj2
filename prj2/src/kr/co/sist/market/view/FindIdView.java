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
 * 아이디찾기
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
		super("아이디찾기 / 비밀번호찾기");
		//////////////////////////////////////////////////////////////////////////////
		/////////// 아이디 찾기 창                                     ///////////
		//////////////////////////////////////////////////////////////////////////////
		
		//라벨생성
		ImageIcon icon = new ImageIcon("C:/Users/user/git/sist_prj2/prj2/src/kr/co/sist/market/img/bg_beige.GIF");
		JLabel backgroundImg = new JLabel(icon);
		JLabel jlidName = new JLabel("이름");
		JLabel jlidSsn = new JLabel("주민번호");
		JLabel idhyphen = new JLabel("-");
		//필요항복 설정
		JPanel idTab = new JPanel();
		jtfIdName = new JTextField();
		jtfIdSsn = new JTextField(6);
		jpwIdSsn = new JPasswordField(7);
		jbFindId = new JButton("찾기");
		jbIdCancel = new JButton("취소");
		//배치
		jlidName.setBounds(20, 20, 30, 25);
		jlidSsn.setBounds(20, 55, 60, 25);
		idhyphen.setBounds(193, 55, 5, 25);
		jtfIdName.setBounds(115, 20, 160, 25);
		jtfIdSsn.setBounds(115, 55, 72, 25);
		jpwIdSsn.setBounds(205, 55, 72, 25);
		
		jbFindId.setBounds(218, 210, 70, 25);
		jbIdCancel.setBounds(300, 210, 70, 25);
		backgroundImg.setBounds(-60,-65, 450, 450);
		//수동배치
		idTab.add(jlidName);
		idTab.add(jlidSsn);
		idTab.add(idhyphen);
		idTab.add(jtfIdName);
		idTab.add(jtfIdSsn);
		idTab.add(jpwIdSsn);
		idTab.add(jbFindId);
		idTab.add(jbIdCancel);
		idTab.add(backgroundImg);
		//수동배치
		idTab.setLayout(null);
		idTab.setBounds(10,10,800,600);
	
		//////////////////////////////////////////////////////////////////////////////
		/////////// 비밀번호 찾기 창                                   ///////////
		//////////////////////////////////////////////////////////////////////////////
		
		//라벨생성
		ImageIcon icon1 = new ImageIcon("C:/Users/user/git/sist_prj2/prj2/src/kr/co/sist/market/img/bg_beige.GIF");
		JLabel backgroundImg1 = new JLabel(icon);
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
		String[] questions = {"------------------------ 선택 ------------------------","기억에 남는 추억의 장소는?","자신의 보물 제1호는?","자신의 인생 좌우명은?","가장 기억에 남는 선생님 성함은?",
				"타인이 모르는 자신만의 신체비밀이 있다면?","추억하고 싶은 날짜가 있다면?","다시 태어나면 되고 싶은 것은?"};
		jcbQuest = new JComboBox<String>(questions);
		jtfAnswer = new JTextField();
		jbFindPass = new JButton("찾기");
		jbPwCancel = new JButton("취소");
		
		JPanel pwTab=new JPanel();
		
		//배치
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

	
		//수동배치
		pwTab.setLayout(null);
		pwTab.setBounds(10,10,800,600);
		
		//아이디 탭과 비밀번호 탭 삽입
		jtpTab=new JTabbedPane();
		jtpTab.add("아이디찾기", idTab);
		jtpTab.addTab("비밀번호찾기", pwTab);
		
		add("Center",jtpTab);

		idTab.setBackground(Color.WHITE);
		
		addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}//windowClosing
			
		});
		
		//이벤트처리
		FindIdViewEvt five=new FindIdViewEvt(this);
		jbFindId.addActionListener(five);
		jbFindPass.addActionListener(five);
		jbIdCancel.addActionListener(five);
		jbPwCancel.addActionListener(five);
		
		setBounds(200, 200, 400, 315);
		//가시화
		setVisible(true);
		//창 크기 고정
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
