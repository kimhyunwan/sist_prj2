package kr.co.sist.market.view;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.market.evt.SignUpItemViewEvt;

public class SignUpItemView extends JFrame {
	private JTextField jtfItemName, jtfPrice, jtfHiredate;
	private JButton jbImage, jbSignUp, jbCancel;
	private JComboBox<String> jcbType;
	private DefaultComboBoxModel<String> dcbmTp;
	private JTextArea jtaItemInfo;
	private JLabel jlimg;

<<<<<<< HEAD

	       jtfItemName=new JTextField(15);
	       jtfPrice=new JTextField(15);
	       jtfHiredate=new JTextField(15);
	       jbImage=new JButton("제품사진 등록");
	       jbSignUp=new JButton("제품등록");
	       jbCancel=new JButton("취소");
	       String[] types = {"----- 전체보기 -----","뷰티/잡화","식품/마트/유아","가구/생활/건강","스포츠/자동차/공구","도서","기타"};
	       jcbType=new JComboBox<>(types);
	       jtaItemInfo=new JTextArea();
	       
	       jtaItemInfo.setBorder(new TitledBorder("제품설명"));
	       JScrollPane jsIntro=new JScrollPane(jtaItemInfo);
	      
	      ImageIcon profile=new ImageIcon("C:/dev/workspace/prj10/src/kr/co/sist/market/img/default.jpg");
	      
	      JLabel jlName=new JLabel("상품명");
	      JLabel jlType=new JLabel("제품분류");
	      JLabel jlPrice=new JLabel("가격");
	      JLabel jlHiredate =new JLabel("등록일");
	      JLabel jlimg =new JLabel(profile);
	      
	      setLayout(null); //수동배치
	      
	      //각 컴포넌트 배치
	      setBounds(50, 50, 480, 500);
	      jlimg.setBounds(20, 20, 180, 195);
	      jbImage.setBounds(49, 223, 120, 25);
	      jlName.setBounds(230, 30, 70, 25);
	      jlType.setBounds(230, 70, 70, 25);
	      jlPrice.setBounds(230, 110, 70, 25);
	      jlHiredate.setBounds(230, 150, 70, 25);
	      
	      jtfItemName.setBounds(305, 30, 130, 25);
	      jcbType.setBounds(305, 70, 130, 25);
	      jtfPrice.setBounds(305, 110, 130, 25);
	      jtfHiredate.setBounds(305, 150, 130, 25);
	      
	      jsIntro.setBounds(20, 265, 420, 130);
	      jbSignUp.setBounds(235, 410, 95, 25);
	      jbCancel.setBounds(340, 410, 95, 25);
	      
	      //컴포넌트 붙이기
	      add(jlimg);
	      add(jbImage);
	      add(jlName);
	      add(jlType);
	      add(jlPrice);
	      add(jlHiredate);
	      add(jtfItemName);
	      add(jcbType);
	      add(jtfPrice);
	      add(jtfHiredate);
	      add(jsIntro);
	      add(jbSignUp);
	      add(jbCancel);
	      
		  //이벤트 추가
	      SignUpItemViewEvt blve = new SignUpItemViewEvt(this);
		  jbCancel.addActionListener(blve);
	      
	      //가시화
	      setVisible(true);
			//창 크기 고정
			setResizable(false);
	      
	       //종료이벤트처리
	        addWindowListener(new WindowAdapter() {
	         @Override
	         public void windowClosing(WindowEvent e) {
	          dispose();                                                    
	         }//windowClosing
	        });
	    }//joinView
	       
	   
	   public JTextField getJtfItemName() {
=======
	public SignUpItemView() {
		super("판매물품 등록");

		JPanel jplbBox = new JPanel(); // 라벨을 담을 패널
		JPanel jptfBox = new JPanel(); // 입력칸들을 담을 패널
		JPanel jpimg = new JPanel(); // 프로필 담을 패널
		JPanel jpbutton = new JPanel(); // 버튼 담을 패널
		JPanel jpspace = new JPanel();// 버튼 사이 공간 패널
		JPanel jpSignUpButton = new JPanel(); // 가입 버튼 담을 패널
		JPanel jpCancelButton = new JPanel(); // 취소 버튼 담을 패널
		JPanel jpSsn = new JPanel(); // 주민번호 담을 패널
		
		dcbmTp=new DefaultComboBoxModel<>();
		jtfItemName = new JTextField(15);
		jtfPrice = new JTextField(15);
		jtfHiredate = new JTextField(15);
		jbImage = new JButton("제품사진 등록");
		jbSignUp = new JButton("제품등록");
		jbCancel = new JButton("취소");
		jcbType = new JComboBox<>(dcbmTp);
		jtaItemInfo = new JTextArea();

		jtaItemInfo.setBorder(new TitledBorder("제품설명"));
		JScrollPane jsIntro = new JScrollPane(jtaItemInfo);

		ImageIcon profile = new ImageIcon("C:/dev/workspace/market_prj2/src/kr/co/sist/market/img/default.jpg");

		JLabel jlName = new JLabel("상품명");
		JLabel jlType = new JLabel("제품분류");
		JLabel jlPrice = new JLabel("가격");
		JLabel jlHiredate = new JLabel("등록일");
		jlimg = new JLabel(profile);

		setLayout(null); // 수동배치

		// 패널 레이아웃 설정
		jplbBox.setLayout(new GridLayout(4, 1));
		jptfBox.setLayout(new GridLayout(4, 1));
		jpimg.setLayout(new BorderLayout());
		jpbutton.setLayout(new FlowLayout());

		// jplbBox 에 라벨들 삽입
		jplbBox.add(jlName);
		jplbBox.add(jlType);
		jplbBox.add(jlPrice);
		jplbBox.add(jlHiredate);

		// jptfBox 에 입력란들 삽입
		jptfBox.add(jtfItemName); // 상품명
		jptfBox.add(jcbType); // 제품분류
		jptfBox.add(jtfPrice); // 가격
		jptfBox.add(jtfHiredate); // 등록일

		// 이미지 패널
		jpimg.add(jlimg);
		jpimg.add("South", jbImage);

		// 각 버튼 패널에 버튼 넣기
		jpSignUpButton.add(jbSignUp);
		jpCancelButton.add(jbCancel);

		// 버튼 패널
		jpbutton.add(jpSignUpButton);
		jpbutton.add(jpspace);
		jpbutton.add(jpCancelButton);

		// 패널 넣기
		add(jptfBox);
		add(jplbBox);
		add(jpimg);
		add(jsIntro);
		add(jpbutton);

		// 각 패널 배치
		setBounds(50, 50, 585, 500);
		jpimg.setBounds(10, 10, 180, 230);
		jplbBox.setBounds(250, 10, 100, 230);
		jptfBox.setBounds(360, 10, 200, 230);
		jsIntro.setBounds(10, 250, 550, 150);
		jpbutton.setBounds(360, 400, 200, 100);

		// 이벤트 추가
		SignUpItemViewEvt blve = new SignUpItemViewEvt(this);
		jbCancel.addActionListener(blve);
		jbImage.addActionListener(blve);
		jbSignUp.addActionListener(blve);

		// 가시화
		setVisible(true);

		// 종료이벤트처리
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}// windowClosing
		});
	}// joinView

	public JTextField getJtfItemName() {
>>>>>>> refs/heads/master
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

	public JLabel getJlimg() {
		return jlimg;
	}
	
	public DefaultComboBoxModel<String> getDcbmTp() {
		return dcbmTp;
	}

	public static void main(String[] args) {
		new SignUpItemView();
	}// main

}// class