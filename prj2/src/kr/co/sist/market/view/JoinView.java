package kr.co.sist.market.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JoinView extends JFrame {

	private JTextField jtfName, jtfSsn, jtfId, jtfAnswer;
	private JButton jbImage, jbSignUp, jbCancel;
	private JComboBox<String> jcbQuest;
	private JTextArea jtaIntro;
	private JPasswordField jpwSsn, jpwPass, jpwPassChk;
	
	public JoinView() {
		super("회원가입");

		JPanel jplbBox=new JPanel(); //라벨을 담을 패널
		JPanel jptfBox=new JPanel(); // 입력칸들을 담을 패널
		JPanel jptaIntro=new JPanel(); // 자기소개 담을 패널
		JPanel jpimg=new JPanel(); // 프로필 담을 패널
		JPanel jpbutton=new JPanel(); // 버튼 담을 패널
		JPanel jpSsn=new JPanel(); // 주민번호 담을 패널
		
		 jtfName=new JTextField();
		 jtfSsn=new JTextField();
		 jtfId=new JTextField();
		 jtfAnswer=new JTextField();
		 jbImage=new JButton("사진등록");
		 jbSignUp=new JButton("가입");
		 jbCancel=new JButton("취소");
		 jcbQuest=new JComboBox<>();
		 jtaIntro=new JTextArea();
		 jpwSsn=new JPasswordField();
		 jpwPass=new JPasswordField();
		 jpwPassChk=new JPasswordField();
		
		ImageIcon profile=new ImageIcon("C:/dev/workspace/prj2/src/prj2/img/default.jpg");
		
		JLabel jlName=new JLabel("이름");
		JLabel jlSsn=new JLabel("주민번호");
		JLabel jlId=new JLabel("아이디");
		JLabel jlPass=new JLabel("비밀번호");
		JLabel jlPassChk=new JLabel("비밀번호 확인");
		JLabel jlQuest=new JLabel("비밀번호 질문");
		JLabel jlAnswer=new JLabel("비밀번호 답변");
		JLabel jlhyphen=new JLabel("-");
		JLabel jlimg=new JLabel(profile);
		JLabel jlIntro=new JLabel("자기소개");
		
		setLayout(null); //수동배치
		
		//패널 레이아웃 설정
		jplbBox.setLayout(new GridLayout(7, 1));
		jptfBox.setLayout(new GridLayout(7, 1));
		jptaIntro.setLayout(new BorderLayout());
		jpimg.setLayout(new BorderLayout());
		jpbutton.setLayout(new GridLayout(1, 2));
		jpSsn.setLayout(new FlowLayout());
		
		//jpSsn삽입
		jpSsn.add(jtfSsn);
		jpSsn.add(jlhyphen);
		jpSsn.add(jpwSsn);
		
		//jplbBox 에 라벨들 삽입
		jplbBox.add(jlName);
		jplbBox.add(jlSsn);
		jplbBox.add(jlId);
		jplbBox.add(jlPass);
		jplbBox.add(jlPassChk);
		jplbBox.add(jlQuest);
		jplbBox.add(jlAnswer);
		
		//jptfBox 에 입력란들 삽입
		jptfBox.add(jtfName); //이름
		jptfBox.add(jpSsn); //주민번호 패널
		jptfBox.add(jtfId); // 아이디
		jptfBox.add(jpwPass); //비밀번호
		jptfBox.add(jpwPassChk); //비밀번호 확인
		jptfBox.add(jcbQuest); //비밀번호 질문
		jptfBox.add(jtfAnswer); //비밀번호 답변
		
		//이미지 패널
		jpimg.add(jlimg);
		jpimg.add("South", jbImage);
		
		//자기소개 패널
		jptaIntro.add("North", jlIntro);
		jptaIntro.add(jtaIntro);
		
		//버튼 패널
		jpbutton.add(jbSignUp);
		jpbutton.add(jbCancel);
		
		//패널 넣기
		add(jptfBox);
		add(jplbBox);
		add(jpimg);
		add(jptaIntro);
		add(jpbutton);

		//각 패널 배치
		setBounds(50, 50, 500, 500);
		jptfBox.setBounds(250, 5, 240, 200);
		jplbBox.setBounds(150, 5, 50, 200);
		jpimg.setBounds(5, 5, 100, 100);
		jptaIntro.setBounds(150, 220, 250, 250);
		jpbutton.setBounds(5, 250, 100, 30);
	
		
		
		//가시화
		setVisible(true);
		
		 //종료이벤트처리
		  addWindowListener(new WindowAdapter() {
		   @Override
		   public void windowClosing(WindowEvent e) {
		    dispose();                                                 	
		   }//windowClosing
		  });
	 }//joinView
		 
	
	public static void main(String[] args) {
		  new JoinView();
	}//main
	
	
	
	
}//class
