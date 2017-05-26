package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.market.evt.MyInfoChViewEvt;

@SuppressWarnings("serial")
public class MyInfoChView extends JFrame {
	private JTextField jtfAnswer;
	private JButton jbImage,jbChange,jbCancel;
	private JComboBox<String> jcbQuest;
	private JTextArea jtaIntro;
	private JPasswordField jtfPass,jtfPassChk;
	
	public MyInfoChView(){
		super("정보변경");
		ImageIcon itemImg = new ImageIcon("C:/dev/workspace/prj22/src/kr/co/sist/market/img/profile.jpg");
		JLabel itemImage = new JLabel(itemImg);
		JLabel jlPass = new JLabel("비밀번호");
		JLabel jlPassChk = new JLabel("비밀번호 확인");
		JLabel jlQuest = new JLabel("비밀번호 질문");
		JLabel jlAnswer = new JLabel("비밀번호 답변");
		
		JLabel jlIntro = new JLabel("자기소개");
		jtaIntro = new JTextArea();
		JScrollPane jspIntro = new JScrollPane(jtaIntro);

		jtfPass=new JPasswordField();
		jtfPassChk = new JPasswordField();
		String[] questions = {"------------------------ 선택 ------------------------","기억에 남는 추억의 장소는?","자신의 보물 제1호는?","자신의 인생 좌우명은?","가장 기억에 남는 선생님 성함은?",
				"타인이 모르는 자신만의 신체비밀이 있다면?","추억하고 싶은 날짜가 있다면?","다시 태어나면 되고 싶은 것은?"};
		jcbQuest = new JComboBox<String>(questions);
		jtfAnswer = new JTextField();		
		jtaIntro = new JTextArea();  //JTextArea 생성  
		
		jbImage = new JButton("이미지 등록");
		jbChange=new JButton("수정");
		jbCancel=new JButton("취소");
		
		//자동배치 해제
		setLayout(null);
		//컴포넌트의 배치 위치설정
		itemImage.setBounds(20, 20, 180, 200);
		jlPass.setBounds(220, 30, 65, 15);
		jtfPass.setBounds(330, 30, 140,25);
		jlPassChk.setBounds(220, 60, 85, 15);
		jtfPassChk.setBounds(330, 60, 140,25);
		jlQuest.setBounds(220, 90, 85, 15);
		jcbQuest.setBounds(330, 90, 256,25);
		jlAnswer.setBounds(220, 120, 85, 15);
		jtfAnswer.setBounds(330, 120, 256,25);
		jlIntro.setBounds(220, 150, 65, 15);
		jspIntro.setBounds(220, 180, 368, 150);  
		jbImage.setBounds(55, 230, 120, 25);
		jbChange.setBounds(450, 350, 60,25);
		jbCancel.setBounds(520, 350, 60,25);		
		
		//컴포넌트 배치
		add(itemImage);
		add(jlPass);
		add(jtfPass);
		add(jlPassChk);
		add(jtfPassChk);
		add(jlQuest);
		add(jcbQuest);
		add(jlAnswer);
		add(jtfAnswer);
		add(jlIntro);
		add(jspIntro);
		add(jbImage);
		add(jbChange);
		add(jbCancel);
		
		//이벤트 추가
		MyInfoChViewEvt iive = new MyInfoChViewEvt(this);
		jbCancel.addActionListener(iive);
		
		//윈도우의 크기
		setBounds(300,80,630,470);
		//가시화
		setVisible(true);
		//종료이벤트처리
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();                                                     
			}//windowClosing
		});
	}//MyInfoChView
	public JTextField getJtfAnswer() {
		return jtfAnswer;
	}
	public JButton getJbImage() {
		return jbImage;
	}
	public JButton getJbChange() {
		return jbChange;
	}
	public JButton getJbCancel() {
		return jbCancel;
	}
	public JComboBox<String> getJcbQuest() {
		return jcbQuest;
	}
	public JTextArea getJtaIntro() {
		return jtaIntro;
	}
	public JPasswordField getJtfPass() {
		return jtfPass;
	}
	public JPasswordField getJtfPassChk() {
		return jtfPassChk;
	}
	public static void main(String[] args) {
		new MyInfoChView();
	}//main
}//class
