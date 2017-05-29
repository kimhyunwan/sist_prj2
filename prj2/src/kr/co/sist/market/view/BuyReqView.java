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
		super("���Ž�û");
		ImageIcon itemImg = new ImageIcon("C:/dev/workspace/prj22/src/kr/co/sist/market/img/profile.jpg");
		JLabel itemImage = new JLabel(itemImg);
		JLabel jlId = new JLabel("������");
		JLabel jlPayment = new JLabel("��������");
		JLabel jlTotalPrice = new JLabel("�� ���� �ݾ�");
		jtfId=new JTextField("dongha");
		String[] payments = {"----- ���� -----","ī��","����","������ü"};
		JComboBox<String> jcbPayment = new JComboBox<String>(payments);
		jtfTotalPrice = new JTextField();
		jbBuyReq=new JButton("���Ž�û �Ϸ�");
		jbCancel=new JButton("���");
		
		//�ڵ���ġ ����
		setLayout(null);
		//������Ʈ�� ��ġ ��ġ����
		itemImage.setBounds(20, 20, 180, 200);
		jlId.setBounds(220, 30, 50, 15);
		jtfId.setBounds(220, 55, 110,25);
		jlPayment.setBounds(220, 90, 100, 15);
		jcbPayment.setBounds(220,115,100,25);
		jlTotalPrice.setBounds(220, 150, 100, 15);
		jtfTotalPrice.setBounds(220, 175, 110,25);
		jbBuyReq.setBounds(220, 220, 160,25);
		jbCancel.setBounds(390, 220, 100,25);		
		//������Ʈ ��ġ
		add(itemImage);
		add(jlId);
		add(jlTotalPrice);
		add(jtfId);
		add(jlPayment);
		add(jcbPayment);
		add(jtfTotalPrice);
		add(jbBuyReq);
		add(jbCancel);
		
		//�̺�Ʈ �߰�
		BuyReqViewEvt brve = new BuyReqViewEvt(this);
		jbCancel.addActionListener(brve);
		jbBuyReq.addActionListener(brve);
		
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
