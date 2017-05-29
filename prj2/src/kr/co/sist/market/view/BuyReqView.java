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

@SuppressWarnings("serial")
public class BuyReqView extends JFrame {
	private JTextField jtfId, jtfTotalPrice;
	private JButton jbBuyReq,jbCancel;
	private LoginViewEvt lve;
	
	public BuyReqView(){
		super("구매신청");
		ImageIcon itemImg = new ImageIcon("C:/dev/workspace/prj22/src/kr/co/sist/market/img/profile.jpg");
		JLabel itemImage = new JLabel(itemImg);
		JLabel jlId = new JLabel("구매자");
		JLabel jlPayment = new JLabel("결제수단");
		JLabel jlTotalPrice = new JLabel("총 결제 금액");
		jtfId=new JTextField("dongha");
		String[] payments = {"----- 선택 -----","카드","현금","계좌이체"};
		JComboBox<String> jcbPayment = new JComboBox<String>(payments);
		jtfTotalPrice = new JTextField();
		jbBuyReq=new JButton("구매신청 완료");
		jbCancel=new JButton("취소");
		
		//자동배치 해제
		setLayout(null);
		//컴포넌트의 배치 위치설정
		itemImage.setBounds(20, 20, 180, 200);
		jlId.setBounds(220, 30, 50, 15);
		jtfId.setBounds(220, 55, 110,25);
		jlPayment.setBounds(220, 90, 100, 15);
		jcbPayment.setBounds(220,115,100,25);
		jlTotalPrice.setBounds(220, 150, 100, 15);
		jtfTotalPrice.setBounds(220, 175, 110,25);
		jbBuyReq.setBounds(220, 220, 160,25);
		jbCancel.setBounds(390, 220, 100,25);		
		//컴포넌트 배치
		add(itemImage);
		add(jlId);
		add(jlTotalPrice);
		add(jtfId);
		add(jlPayment);
		add(jcbPayment);
		add(jtfTotalPrice);
		add(jbBuyReq);
		add(jbCancel);
		
		//이벤트 추가
		BuyReqViewEvt brve = new BuyReqViewEvt(this);
		jbCancel.addActionListener(brve);
		jbBuyReq.addActionListener(brve);
		
		//윈도우의 크기
		setBounds(300,80,520,320);
		//가시화
		setVisible(true);
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
	public static void main(String[] args) {
		new BuyReqView();
	}//main

}//class
