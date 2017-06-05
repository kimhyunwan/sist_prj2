package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.market.evt.BuyReqViewEvt;
import kr.co.sist.market.evt.LoginViewEvt;
import kr.co.sist.market.vo.PhoneVO;
import kr.co.sist.market.vo.ReqVO;

@SuppressWarnings("serial")
public class BuyReqView extends JFrame {
	private JTextField jtfId, jtfTotalPrice,jtfPhone2,jtfPhone3,jtfItemcode;
	private JButton jbBuyReq,jbCancel;
	private JComboBox<String> phone;
	private LoginViewEvt lve;
	
	public BuyReqView(ReqVO rv){
		super("구매신청");
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/bg_pink.jpg");
		JLabel backgroundImg = new JLabel(icon);
		ImageIcon itemImg = new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/default.jpg");
		JLabel itemImage = new JLabel(itemImg);
		JLabel jlId = new JLabel("구매자");
		JLabel jlItemcode = new JLabel("아이템 코드");
		jtfItemcode = new JTextField(rv.getItemCode());
		jtfTotalPrice = new JTextField();
		jtfTotalPrice.getText();
		jtfTotalPrice.setText(String.valueOf(rv.getPrice()));
		
	
		JLabel jlPayment = new JLabel("결제수단");
		JLabel jlTotalPrice = new JLabel("결제 금액");
		jtfId=new JTextField();
		
		JLabel jlPhone = new JLabel("연락처");
		String[] phone1 = {"010","011", "016", "017", "018", "019"};
		phone = new JComboBox<String>(phone1);
		jtfPhone2 = new JTextField(4);
		JLabel hyphen = new JLabel("-");
		JLabel hyphen1 = new JLabel("-");
		jtfPhone3 = new JTextField(4);
		
		jtfId=new JTextField(lve.id);
		String[] payments = {"----- 선택 -----","카드","현금","계좌이체"};
		JComboBox<String> jcbPayment = new JComboBox<String>(payments);
		jbBuyReq=new JButton("구매신청 완료");
		jbCancel=new JButton("취소");
		//제품코드는 읽기 전용
		jtfId.setEditable(false);
		jtfItemcode.setEditable(false);
		jtfTotalPrice.setEditable(false);
		//자동배치 해제
		setLayout(null);
		//컴포넌트의 배치 위치설정
		itemImage.setBounds(20, 20, 180, 200);
		jlId.setBounds(220, 20, 50, 15);
		jtfId.setBounds(220, 45, 110,25);
		jlItemcode.setBounds(350, 20, 100, 15);
		jtfItemcode.setBounds(350, 45, 110, 25);
		jlPhone.setBounds(220, 85, 50	, 15);
		phone.setBounds(220, 110, 50, 25);
		hyphen.setBounds(275, 110, 20, 25);
		jtfPhone2.setBounds(285, 110, 50, 25);
		hyphen1.setBounds(340, 110, 20, 25);
		jtfPhone3.setBounds(350, 110, 50, 25);
		jlPayment.setBounds(220, 150, 100, 15);
		jcbPayment.setBounds(220,180,100,25);
		jlTotalPrice.setBounds(220, 220, 100, 15);
		jtfTotalPrice.setBounds(220, 245, 110,25);
		jbBuyReq.setBounds(220, 300, 160,25);
		jbCancel.setBounds(390, 300, 100,25);		
		backgroundImg.setBounds(0,-70, 650, 600);
		//컴포넌트 배치
		add(itemImage);
		add(jlId);
		add(jlTotalPrice);
		add(jtfId);
		add(jlItemcode);
		add(jtfItemcode);
		add(jlPhone);
		add(phone);
		add(hyphen);
		add(jtfPhone2);
		add(hyphen1);
		add(jtfPhone3);
		add(jlPayment);
		add(jcbPayment);
		add(jtfTotalPrice);
		add(jbBuyReq);
		add(jbCancel);
		add(backgroundImg);
		//이벤트 추가
		BuyReqViewEvt brve = new BuyReqViewEvt(this);
		jbCancel.addActionListener(brve);
		jbBuyReq.addActionListener(brve);
		
		//윈도우의 크기
		setBounds(300,80,520,370);
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
	}//BuyReqView
	public JTextField getJtfId() {
		return jtfId;
	}
	public JTextField getJtfTotalPrice() {
		return jtfTotalPrice;
	}
	public JButton getJbBuyReq() {
		return jbBuyReq;
	}
	public JButton getJbCancel() {
		return jbCancel;
	}
	
	public JTextField getJtfPhone2() {
		return jtfPhone2;
	}
	public JTextField getJtfPhone3() {
		return jtfPhone3;
	}
	public JComboBox<String> getPhone() {
		return phone;
	}
	public JTextField getJtfItemcode() {
		return jtfItemcode;
	}
	public LoginViewEvt getLve() {
		return lve;
	}


}//class
