package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import kr.co.sist.market.evt.MsgWriteViewEvt;
import kr.co.sist.market.vo.MsgVO;

//import kr.co.sist.market.evt.MsgWriteViewEvt;

public class MsgWriteView extends JFrame {

	private JTextArea jtaMsg;
	private JButton jbSend, jbCancel;
	private String itemCode, sendId, id;
	

	public MsgWriteView(MsgVO mv) {
		super("�޼��� ������");

		jtaMsg = new JTextArea();
		jtaMsg.setText(mv.getMsg());
		
		itemCode = mv.getItemCode();
		sendId = mv.getSendId();
		id = mv.getId();
		
		jbSend = new JButton("�޼��� ������");
		jbCancel = new JButton("���");

		jtaMsg.setBorder(new TitledBorder(mv.getSendId()+"�Կ��� ���� �޼���"));

		JScrollPane jsMsg = new JScrollPane(jtaMsg);

		// ������ġ
		setLayout(null);

		// ��ġ
		setBounds(100, 100, 450, 285);
		jsMsg.setBounds(5, 5, 425, 195);
		jbSend.setBounds(230, 210, 115, 25);
		jbCancel.setBounds(350, 210, 75, 25);
		// ����

		add(jsMsg);
		add(jbSend);
		add(jbCancel);

		// �̺�Ʈ ó��
		MsgWriteViewEvt mwve = new MsgWriteViewEvt(this);
		jbSend.addActionListener(mwve);
		jbCancel.addActionListener(mwve);

		// ����ȭ
		setVisible(true);

		// �����̺�Ʈó��
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}// windowClosing
		});
	}// joinView

	public String getItemCode() {
		return itemCode;
	}

	public String getSendId() {
		return sendId;
	}

	public String getId() {
		return id;
	}

	/**
	 * ��������. ���� �������
	 * 
	 * @param args
	 */
	// public static void main(String[] args) {
	// new MsgWriteView();
	// }//main

	// �޼��� �� ��������
	public JTextArea getJtaMsg() {
		return jtaMsg;
	}

	public void setJtaMsg(JTextArea jtaMsg) {
		this.jtaMsg = jtaMsg;
	}

	public JButton getJbSend() {
		return jbSend;
	}

	public JButton getJbCancel() {
		return jbCancel;
	}

}// class
