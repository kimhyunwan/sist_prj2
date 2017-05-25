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
		super("�Ǹ��� ����");
		ImageIcon itemImg = new ImageIcon("C:/dev/workspace/prj22/src/kr/co/sist/market/img/profile.jpg");
		JLabel itemImage = new JLabel(itemImg);
		JLabel jlId = new JLabel("���̵�");
		JLabel jlIntro = new JLabel("�ڱ�Ұ�");
		JTextArea jtaIntro = new JTextArea();  //JTextArea ����  
		JScrollPane jspIntro = new JScrollPane(jtaIntro);

		JTextField jtfId=new JTextField();
		jbMsg=new JButton("�޼��� ������");
		jbBuyReq=new JButton("���Ž�û");
		
		//�ڵ���ġ ����
		setLayout(null);
		//������Ʈ�� ��ġ ��ġ����
		itemImage.setBounds(20, 20, 180, 200);
		jlId.setBounds(220, 30, 50, 15);
		jtfId.setBounds(220, 55, 110,25);
		jlIntro.setBounds(220, 90, 100, 15);
		jspIntro.setBounds(220, 110, 200, 100);  
		jbMsg.setBounds(220, 220, 160,25);
		jbBuyReq.setBounds(390, 220, 100,25);		
		//������Ʈ ��ġ
		add(itemImage);
		add(jlId);
		add(jtfId);
		add(jlIntro);
		add(jspIntro);
		add(jbMsg);
		add(jbBuyReq);
		
		//�̺�Ʈ �߰�
		SellerInfoViewEvt sive = new SellerInfoViewEvt(this);
		jbMsg.addActionListener(sive);
		jbBuyReq.addActionListener(sive);
		
		//�������� ũ��
		setBounds(300,80,520,320);
		//����ȭ
		setVisible(true);
		//�����̺�Ʈó��
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
