package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.market.evt.LoginViewEvt;
import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.evt.MyInfoChViewEvt;

import kr.co.sist.market.vo.MemberInfoVO;

@SuppressWarnings("serial")
public class MyInfoChView extends JFrame {
	private JTextField jtfAnswer,jtfName;
	private JButton jbImage,jbChange,jbCancel;
	private JComboBox<String> jcbQuest;
	private JTextArea jtaIntro;
	private JPasswordField jtfPass,jtfPassChk;
	private LoginViewEvt lve;

	private JLabel jlItemImage;
	private static CustomerDAO cd;
	
	public MyInfoChView(CustomerDAO cd) throws SQLException{
		super("정보변경");
		
		this.cd=cd;
		ImageIcon itemImg = new ImageIcon("C:/dev/prj2/sist_prj2/prj2/src/kr/co/sist/market/img/default.jpg");
		jlItemImage = new JLabel(itemImg);
		JLabel jlName = new JLabel("이름");
		JLabel jlPass = new JLabel("비밀번호");
		JLabel jlPassChk = new JLabel("비밀번호 확인");
		JLabel jlQuest = new JLabel("비밀번호 질문");
		JLabel jlAnswer = new JLabel("비밀번호 답변");
		
		JLabel jlIntro = new JLabel("자기소개");
		jtfName = new JTextField();
		jtaIntro = new JTextArea();
		
		MemberInfoVO miv=cd.selectPreMember("dongha");
		
		String pass=miv.getPass();
		String answer=miv.getPassAnswer();
		String info=miv.getInfo();
		int quNum=miv.getQuNum();
		
		jtfPass=new JPasswordField(pass);
		jtfPassChk = new JPasswordField(pass);
		String[] questions = {"------------------------ 선택 ------------------------","기억에 남는 추억의 장소는?","자신의 보물 제1호는?","자신의 인생 좌우명은?","가장 기억에 남는 선생님 성함은?",
				"타인이 모르는 자신만의 신체비밀이 있다면?","추억하고 싶은 날짜가 있다면?","다시 태어나면 되고 싶은 것은?"};
		jcbQuest = new JComboBox<String>(questions);
		jcbQuest.setSelectedIndex(quNum);
		jtfAnswer = new JTextField(answer);		
		jtaIntro = new JTextArea(); //JTextArea 생성
		jtaIntro.setLineWrap(true);
		jtaIntro.setWrapStyleWord(true);
		jtaIntro.setText(info);
		
		JScrollPane jspIntro = new JScrollPane(jtaIntro);
		
		jbImage = new JButton("이미지 등록");
		jbChange=new JButton("수정");
		jbCancel=new JButton("취소");
		
		//자동배치 해제
		setLayout(null);
		//컴포넌트의 배치 위치설정
		jlName.setBounds(220, 30, 65, 15);
		jtfName.setBounds(330, 30, 140, 25);
		jlItemImage.setBounds(20, 20, 180, 200);
		jlPass.setBounds(220, 60, 65, 15);
		jtfPass.setBounds(330, 60, 140,25);
		jlPassChk.setBounds(220, 90, 85, 15);
		jtfPassChk.setBounds(330, 90, 140,25);
		jlQuest.setBounds(220, 120, 85, 15);
		jcbQuest.setBounds(330, 120, 256,25);
		jlAnswer.setBounds(220, 150, 85, 15);
		jtfAnswer.setBounds(330, 150, 256,25);
		jlIntro.setBounds(220, 180, 65, 15);
		jspIntro.setBounds(220, 210, 368, 150);  
		jlItemImage.setBounds(20, 20, 180, 200);
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
		jbChange.setBounds(450, 380, 60,25);
		jbCancel.setBounds(520, 380, 60,25);		
		
		//컴포넌트 배치
		add(jlItemImage);
		add(jlName);
		add(jtfName);
		add(jlItemImage);
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
		jbChange.addActionListener(iive);
		jbImage.addActionListener(iive);
		
		//윈도우의 크기
		setBounds(300,80,630,470);
		//가시화
		setVisible(true);
		//창 크기 고정
		setResizable(false);
		setTitle(lve.id+"정보변경");
		//종료이벤트처리
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();                                                     
			}//windowClosing
		});
	}//MyInfoChView
	public JLabel getJlItemImage() {
		return jlItemImage;
	}
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
	
	public JTextField getJtfName() {
		return jtfName;
	}
	
	public void setJtfAnswer(JTextField jtfAnswer) {
		this.jtfAnswer = jtfAnswer;
	}
	public void setJtfName(JTextField jtfName) {
		this.jtfName = jtfName;
	}
	public void setJbImage(JButton jbImage) {
		this.jbImage = jbImage;
	}
	public void setJbChange(JButton jbChange) {
		this.jbChange = jbChange;
	}
	public void setJbCancel(JButton jbCancel) {
		this.jbCancel = jbCancel;
	}
	public void setJcbQuest(JComboBox<String> jcbQuest) {
		this.jcbQuest = jcbQuest;
	}
	public void setJtaIntro(JTextArea jtaIntro) {
		this.jtaIntro = jtaIntro;
	}
	public void setJtfPass(JPasswordField jtfPass) {
		this.jtfPass = jtfPass;
	}
	public void setJtfPassChk(JPasswordField jtfPassChk) {
		this.jtfPassChk = jtfPassChk;
	}
	public static void main(String[] args) {
		cd=CustomerDAO.getInstance();
		try {
			new MyInfoChView(cd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}//main
}//class
