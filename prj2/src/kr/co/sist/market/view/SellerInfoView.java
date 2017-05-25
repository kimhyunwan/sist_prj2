package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.market.evt.ItemInfoViewEvt;
import kr.co.sist.market.evt.SellerInfoViewEvt;


@SuppressWarnings("serial")
public class SellerInfoView extends JFrame {
	private JButton jbMsg,jbBuyReq;
	
	public SellerInfoView(){
		super("판매자 정보");
		ImageIcon itemImg = new ImageIcon("C:/dev/workspace/prj22/src/kr/co/sist/market/img/profile.jpg");
		JLabel itemImage = new JLabel(itemImg);
		JLabel jlId = new JLabel("아이디");
		JLabel jlIntro = new JLabel("자기소개");
		JTextArea jtaIntro = new JTextArea();  //JTextArea 생성  
		JScrollPane jspIntro = new JScrollPane(jtaIntro);

		JTextField jtfId=new JTextField();
		jbMsg=new JButton("메세지 보내기");
		jbBuyReq=new JButton("구매신청");
		
		//자동배치 해제
		setLayout(null);
		//컴포넌트의 배치 위치설정
		itemImage.setBounds(20, 20, 180, 200);
		jlId.setBounds(220, 30, 50, 15);
		jtfId.setBounds(220, 55, 110,25);
		jlIntro.setBounds(220, 90, 100, 15);
		jspIntro.setBounds(220, 110, 200, 100);  
		jbMsg.setBounds(220, 220, 160,25);
		jbBuyReq.setBounds(390, 220, 100,25);		
		//컴포넌트 배치
		add(itemImage);
		add(jlId);
		add(jtfId);
		add(jlIntro);
		add(jspIntro);
		add(jbMsg);
		add(jbBuyReq);
		
		//이벤트 추가
		SellerInfoViewEvt sive = new SellerInfoViewEvt(this);
		jbMsg.addActionListener(sive);
		jbBuyReq.addActionListener(sive);
		
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
	}//SellerInfoView
	
	public JButton getJbMsg() {
		return jbMsg;
	}

	public JButton getJbBuyReq() {
		return jbBuyReq;
	}

	public static void main(String[] args) {
		new SellerInfoView();
	}//main

}//class
