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

@SuppressWarnings("serial")
public class BuyReqView extends JFrame {
	private JTextField jtfId, jtfTotalPrice,jtfPhone2,jtfPhone3;
	private JButton jbBuyReq,jbCancel;
	private JComboBox<String> phone;
	
	public BuyReqView(){
		super("���Ž�û");
		ImageIcon icon = new ImageIcon("C:/Users/user/git/sist_prj2/prj2/src/kr/co/sist/market/img/bg_pink.jpg");
		JLabel backgroundImg = new JLabel(icon);
		ImageIcon itemImg = new ImageIcon("C:/dev/workspace/prj22/src/kr/co/sist/market/img/profile.jpg");
		JLabel itemImage = new JLabel(itemImg);
		JLabel jlId = new JLabel("������");
		JLabel jlPayment = new JLabel("��������");
		JLabel jlTotalPrice = new JLabel("�� ���� �ݾ�");
		jtfId=new JTextField();
		
		JLabel jlPhone = new JLabel("����ó");
		String[] phone1 = {"010","011", "016", "017", "018", "019"};
		phone = new JComboBox<String>(phone1);
		jtfPhone2 = new JTextField(4);
		JLabel hyphen = new JLabel("-");
		JLabel hyphen1 = new JLabel("-");
		jtfPhone3 = new JTextField(4);
		
		String[] payments = {"----- ���� -----","ī��","����","������ü"};
		JComboBox<String> jcbPayment = new JComboBox<String>(payments);
		jtfTotalPrice = new JTextField();
		jbBuyReq=new JButton("���Ž�û �Ϸ�");
		jbCancel=new JButton("���");
		
		//�ڵ���ġ ����
		setLayout(null);
		//������Ʈ�� ��ġ ��ġ����
		itemImage.setBounds(20, 20, 180, 200);
		jlId.setBounds(220, 20, 50, 15);
		jtfId.setBounds(220, 45, 110,25);
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
		//������Ʈ ��ġ
		add(itemImage);
		add(jlId);
		add(jlTotalPrice);
		add(jtfId);
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
		//�̺�Ʈ �߰�
		BuyReqViewEvt brve = new BuyReqViewEvt(this);
		jbCancel.addActionListener(brve);
		
		//�������� ũ��
		setBounds(300,80,520,370);
		//����ȭ
		setVisible(true);
		//â ũ�� ����
		setResizable(false);
		//�����̺�Ʈó��
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
	public static void main(String[] args) {
		new BuyReqView();
	}//main

}//class
