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

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.evt.LoginViewEvt;
import kr.co.sist.market.evt.SellerInfoViewEvt;
import kr.co.sist.market.vo.ReqVO;
import kr.co.sist.market.vo.SellerInfoVO;

@SuppressWarnings("serial")
public class SellerInfoView extends JFrame {
	private JButton jbMsg, jbBuyReq;
	private JTextArea jtaIntro;
	private JTextField jtfId;
	private String itemCode;
	private int price;
	private CustomerDAO c_dao;
	private LoginViewEvt lve;

	public SellerInfoView(SellerInfoVO seller, ReqVO rv) throws SQLException {
		super("�Ǹ��� ����");
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/src/kr/co/sist/market/img/bg_pink.jpg");
		JLabel backgroundImg = new JLabel(icon);
		c_dao = CustomerDAO.getInstance();

		String imgName = c_dao.selectImgName(seller.getId());
		File file=new File(System.getProperty("user.dir") + "/src/kr/co/sist/market/img/customer/" + imgName);
		ImageIcon itemImg=null;
		if(!file.exists()){
			itemImg=new ImageIcon(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/default.jpg");
		}else{
			itemImg = new ImageIcon(System.getProperty("user.dir") + "/src/kr/co/sist/market/img/customer/" + imgName);
		}
		JLabel itemImage = new JLabel(itemImg);
		JLabel jlId = new JLabel("���̵�");
		JLabel jlIntro = new JLabel("�ڱ�Ұ�");

		jtaIntro = new JTextArea(seller.getInfo()); // JTextArea ����

		jtaIntro.setEditable(false);
		JScrollPane jspIntro = new JScrollPane(jtaIntro);

		jtfId = new JTextField(seller.getId());
		jtfId.setEditable(false);
		jbMsg = new JButton("�޼��� ������");
		jbBuyReq = new JButton("���Ž�û");

		itemCode = rv.getItemCode();
		price = rv.getPrice();
		// �ڵ���ġ ����
		setLayout(null);
		// ������Ʈ�� ��ġ ��ġ����
		itemImage.setBounds(20, 20, 180, 200);
		jlId.setBounds(220, 30, 50, 15);
		jtfId.setBounds(220, 55, 110, 25);
		jlIntro.setBounds(220, 90, 100, 15);
		jspIntro.setBounds(220, 110, 200, 100);
		jbMsg.setBounds(220, 220, 160, 25);
		jbBuyReq.setBounds(390, 220, 100, 25);
		backgroundImg.setBounds(0, 0, 530, 350);
		// ������Ʈ ��ġ
		add(itemImage);
		add(jlId);
		add(jtfId);
		add(jlIntro);
		add(jspIntro);
		add(jbMsg);
		add(jbBuyReq);
		add(backgroundImg);

		// �̺�Ʈ �߰�
		SellerInfoViewEvt sive;
		sive = new SellerInfoViewEvt(this);
		jbMsg.addActionListener(sive);
		jbBuyReq.addActionListener(sive);

		// �������� ũ��
		setBounds(330, 120, 520, 320);
		// ����ȭ
		setVisible(true);
		// â ũ�� ����
		setResizable(false);
		// �����̺�Ʈó��
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}// windowClosing
		});
	}// SellerInfoView

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

	public int getPrice() {
		return price;
	}

}// class
