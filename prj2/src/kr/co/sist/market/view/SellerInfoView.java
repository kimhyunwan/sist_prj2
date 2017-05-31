package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.evt.SellerInfoViewEvt;
import kr.co.sist.market.vo.SellerInfoVO;

@SuppressWarnings("serial")
public class SellerInfoView extends JFrame {
	private JButton jbMsg,jbBuyReq;
	private JTextArea jtaIntro;
	private JTextField jtfId;
	private String itemCode;
	private String id="";
	
	public SellerInfoView(SellerInfoVO seller) throws SQLException{
		super("판매자 정보");
//		itemCode=
		ImageIcon itemImg = new ImageIcon("C:/dev/prj2/sist_prj2/prj2/src/kr/co/sist/market/img/default.jpg");
		JLabel itemImage = new JLabel(itemImg);
		JLabel jlId = new JLabel("아이디");
		JLabel jlIntro = new JLabel("자기소개");
	
		jtaIntro = new JTextArea(seller.getInfo());  //JTextArea 생성  
		JScrollPane jspIntro = new JScrollPane(jtaIntro);

		jtfId=new JTextField(seller.getId());
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
		
		id=getJtfId().getText();
		
		//이벤트 추가
		SellerInfoViewEvt sive;
		sive = new SellerInfoViewEvt(this,id);
		jbMsg.addActionListener(sive);
		jbBuyReq.addActionListener(sive);
		
		//윈도우의 크기
		setBounds(300,80,520,320);
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
	}//SellerInfoView
	
	public JTextArea getJtaIntro() {
		return jtaIntro;
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public JButton getJbMsg() {
		return jbMsg;
	}

	public JButton getJbBuyReq() {
		return jbBuyReq;
	}

	public String getItemCode() {
		return itemCode;
	}
	
}//class
