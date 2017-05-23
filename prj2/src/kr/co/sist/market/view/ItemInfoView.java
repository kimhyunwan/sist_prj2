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

@SuppressWarnings("serial")
public class ItemInfoView extends JFrame {
	private JTextField jtfItemName, jtfItemType, jtfPrice, jtfHiredate;
	private JButton jbSellerInfo,jbBuyReq,jbCancel;
	
	public ItemInfoView(){
		super("판매 글 제목");
		ImageIcon pro = new ImageIcon("C:/dev/workspace/prj2/src/kr/co/sist/market/img/profile.jpg");
		JLabel proImg = new JLabel(pro);
		JLabel jlItemName = new JLabel("상품명");
		JLabel jlItemType = new JLabel("제품분류");
		JLabel jlPrice = new JLabel("가격");
		JLabel jlHiredate = new JLabel("등록일");
		JLabel jlProExplain = new JLabel("제품 설명");
		JTextArea jtProExplain = new JTextArea();
		
		jtfItemName = new JTextField();
		jtfItemType = new JTextField();
		jtfPrice = new JTextField();
		jtfHiredate = new JTextField();
		
		jbSellerInfo = new JButton("판매자 정보");
		jbBuyReq = new JButton("구매신청");
		jbCancel = new JButton("취소");
		
		//자동배치 해제
		setLayout(null);
		//컴포넌트의 배치 위치설정
		proImg.setBounds(20, 20, 200, 200);
		jlItemName.setBounds(235, 25, 50, 15);
		jlItemType.setBounds(235, 60, 70, 15);
		jlPrice.setBounds(235, 95, 70, 15);
		jlHiredate.setBounds(235, 130, 70, 15);
		jlProExplain.setBounds(235, 165, 70, 15);
		
		jtfItemName.setBounds(310, 23, 115, 23);
		jtfItemType.setBounds(310, 60, 115, 23);
		jtfPrice.setBounds(310, 95, 115, 23);
		jtfHiredate.setBounds(310, 130, 115, 23);
		jtProExplain.setBounds(235, 185, 190, 100);
		
		jbSellerInfo.setBounds(60, 230, 130 , 50);
		jbBuyReq.setBounds(235, 300, 90, 30);
		jbCancel.setBounds(330, 300, 90, 30);
		//컴포넌트 배치
		add(proImg);
		
		add(jlItemName);
		add(jlItemType);
		add(jlPrice);
		add(jlHiredate);
		add(jlProExplain);
		
		add(jtfItemName);
		add(jtfItemType);
		add(jtfPrice);
		add(jtfHiredate);
		add(jtProExplain);
		
		add(jbSellerInfo);
		add(jbBuyReq);
		add(jbCancel);
		
		//윈도우의 크기
		setBounds(300,80,470,400);
		//가시화
		setVisible(true);
		//종료이벤트처리
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();                                                     
			}//windowClosing
		});
	}//LoginView
	public static void main(String[] args) {
		new ItemInfoView();
	}//main

}//class
