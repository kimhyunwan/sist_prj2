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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.market.evt.SignUpItemViewEvt;

public class SignUpItemView extends JFrame {
	private JTextField jtfItemName, jtfPrice, jtfHiredate;
	   private JButton jbImage, jbSignUp, jbCancel;
	   private JComboBox<String> jcbType;
	   private JTextArea jtaItemInfo;
	   
	   public SignUpItemView() {
	      super("판매물품 등록");

	      JPanel jplbBox=new JPanel(); //라벨을 담을 패널
	      JPanel jptfBox=new JPanel(); // 입력칸들을 담을 패널
	      JPanel jpimg=new JPanel(); // 프로필 담을 패널
	      JPanel jpbutton=new JPanel(); // 버튼 담을 패널
	      JPanel jpspace=new JPanel();//버튼 사이 공간 패널
	      JPanel jpSignUpButton=new JPanel(); // 가입 버튼 담을 패널
	      JPanel jpCancelButton=new JPanel(); // 취소 버튼 담을 패널
	      JPanel jpSsn=new JPanel(); // 주민번호 담을 패널
	      
	       jtfItemName=new JTextField(15);
	       jtfPrice=new JTextField(15);
	       jtfHiredate=new JTextField(15);
	       jbImage=new JButton("제품사진 등록");
	       jbSignUp=new JButton("제품등록");
	       jbCancel=new JButton("취소");
	       jcbType=new JComboBox<>();
	       jtaItemInfo=new JTextArea();
	       
	       jtaItemInfo.setBorder(new TitledBorder("제품설명"));
	       JScrollPane jsIntro=new JScrollPane(jtaItemInfo);
	      
	      ImageIcon profile=new ImageIcon("C:/dev/workspace/market_prj2/src/kr/co/sist/market/img/default.jpg");
	      
	      JLabel jlName=new JLabel("상품명");
	      JLabel jlType=new JLabel("제품분류");
	      JLabel jlPrice=new JLabel("가격");
	      JLabel jlHiredate =new JLabel("등록일");
	      JLabel jlimg =new JLabel(profile);
	      
	      setLayout(null); //수동배치
	      
	      //패널 레이아웃 설정
	      jplbBox.setLayout(new GridLayout(4, 1));
	      jptfBox.setLayout(new GridLayout(4, 1));
	      jpimg.setLayout(new BorderLayout());
	      jpbutton.setLayout(new FlowLayout());
	      
	      
	      //jplbBox 에 라벨들 삽입
	      jplbBox.add(jlName);
	      jplbBox.add(jlType);
	      jplbBox.add(jlPrice);
	      jplbBox.add(jlHiredate);
	      
	      
	      //jptfBox 에 입력란들 삽입
	      jptfBox.add(jtfItemName); //상품명
	      jptfBox.add(jcbType); //제품분류
	      jptfBox.add(jtfPrice); //가격
	      jptfBox.add(jtfHiredate); //등록일
	      
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
	      jpbutton.setBounds(360, 400, 200, 100);

		  //이벤트 추가
	      SignUpItemViewEvt blve = new SignUpItemViewEvt(this);
		  jbCancel.addActionListener(blve);
	      
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
	       
	   
	   public JTextField getJtfItemName() {
		return jtfItemName;
	}


	public JTextField getJtfPrice() {
		return jtfPrice;
	}


	public JTextField getJtfHiredate() {
		return jtfHiredate;
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


	public JComboBox<String> getJcbType() {
		return jcbType;
	}


	public JTextArea getJtaItemInfo() {
		return jtaItemInfo;
	}


	public static void main(String[] args) {
	        new SignUpItemView();
	   }//main
	   
	   
	   
	   
	}//class