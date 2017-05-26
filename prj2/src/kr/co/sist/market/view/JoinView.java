package kr.co.sist.market.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.market.evt.BuyListViewEvt;
import kr.co.sist.market.evt.JoinViewEvt;

/**
 * 회원 가입 창
 * @author 장재훈
 *
 */
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
      JPanel jpimg=new JPanel(); // 프로필 담을 패널
      JPanel jpbutton=new JPanel(); // 버튼 담을 패널
      JPanel jpspace=new JPanel();//버튼 사이 공간 패널
      JPanel jpSignUpButton=new JPanel(); // 가입 버튼 담을 패널
      JPanel jpCancelButton=new JPanel(); // 취소 버튼 담을 패널
      JPanel jpSsn=new JPanel(); // 주민번호 담을 패널
      
      
       jtfName=new JTextField(15);
       jtfSsn=new JTextField(6);
       jtfId=new JTextField(15);
       jtfAnswer=new JTextField(15);
       jbImage=new JButton("이미지등록");
       jbSignUp=new JButton("가입");
       jbCancel=new JButton("취소");
       jcbQuest=new JComboBox<>();
       jtaIntro=new JTextArea();
       jpwSsn=new JPasswordField(7);
       jpwPass=new JPasswordField();
       jpwPassChk=new JPasswordField();
       
       jtaIntro.setBorder(new TitledBorder("자기소개"));
       JScrollPane jsIntro=new JScrollPane(jtaIntro);
      
      ImageIcon profile=new ImageIcon("C:/dev/workspace/market_prj2/src/kr/co/sist/market/img/default.jpg");
      
      JLabel jlName=new JLabel("이름");
      JLabel jlSsn=new JLabel("주민번호");
      JLabel jlId=new JLabel("아이디");
      JLabel jlPass=new JLabel("비밀번호");
      JLabel jlPassChk=new JLabel("비밀번호 확인");
      JLabel jlQuest=new JLabel("비밀번호 질문");
      JLabel jlAnswer=new JLabel("비밀번호 답변");
      JLabel jlhyphen=new JLabel("-");
      JLabel jlimg=new JLabel(profile);
      
      setLayout(null); //수동배치
      
      //패널 레이아웃 설정
      jplbBox.setLayout(new GridLayout(7, 1));
      jptfBox.setLayout(new GridLayout(7, 1));
      jpimg.setLayout(new BorderLayout());
      jpbutton.setLayout(new FlowLayout());
      
      
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
      
     //각 버튼 패널에 버튼 넣기
      jpSignUpButton.add(jbSignUp);
      jpCancelButton.add(jbCancel);
      
      //버튼 패널
      jpbutton.add(jpSignUpButton);
      jpbutton.add(jpspace);
      jpbutton.add(jpCancelButton);
      
      //패널 넣기
      add(jptfBox);
      add(jplbBox);
      add(jpimg);
      add(jsIntro);
      add(jpbutton);

      //각 패널 배치
      setBounds(50, 50, 585, 500);
      jpimg.setBounds(10, 10, 180, 230);
      jplbBox.setBounds(250, 10, 100, 230);
      jptfBox.setBounds(360, 10, 200, 230);
      jsIntro.setBounds(10, 250, 550, 150);
      jpbutton.setBounds(380, 400, 190, 70);
      
      //이벤트 처리
      //jbImage.addActionListener();
      //jbSignUp.addActionListener();
      JoinViewEvt jve = new JoinViewEvt(this);
      jbCancel.addActionListener(jve);
      
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
   
	public JTextField getJtfName() {
	return jtfName;
}

public JTextField getJtfSsn() {
	return jtfSsn;
}

public JTextField getJtfId() {
	return jtfId;
}

public JTextField getJtfAnswer() {
	return jtfAnswer;
}

public JButton getJbImage() {
	return jbImage;
}

public JButton getJbSignUp() {
	return jbSignUp;
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

public JPasswordField getJpwSsn() {
	return jpwSsn;
}

public JPasswordField getJpwPass() {
	return jpwPass;
}

public JPasswordField getJpwPassChk() {
	return jpwPassChk;
}

	public static void main(String[] args) {
		new JoinView();
	}//main
       

}//class