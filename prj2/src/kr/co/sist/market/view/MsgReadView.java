package kr.co.sist.market.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import kr.co.sist.market.evt.MsgReadViewEvt;

public class MsgReadView extends JFrame {
	JTextArea jtaMsg;
	JButton jbSend, jbOk;
	
	public MsgReadView() {
		super("������ �� �޼���");
		
		jtaMsg=new JTextArea();
		jbSend=new JButton("�޼��� ������");
		jbOk=new JButton("Ȯ��");
		
		jtaMsg.setBorder(new TitledBorder("�����ڿ��Լ� �� �޼���"));
		
		//�г� ����
		JPanel jpSend=new JPanel();
		JPanel jpCancel=new JPanel();
		JPanel jpSpace=new JPanel();
		JPanel jpButton=new JPanel();
		JPanel jpMsg=new JPanel();
		
		 JScrollPane jsMsg=new JScrollPane(jtaMsg);
		
		//������ġ
		setLayout(null);
		jpButton.setLayout(new FlowLayout());
		jpMsg.setLayout(new BorderLayout());
		
		//��ġ
		setBounds(100, 100, 450, 285);
		jsMsg.setBounds(5, 5, 425, 195);
		jpButton.setBounds(170, 200, 270, 100);
		
		//����
		jpSend.add(jbSend);
		jpCancel.add(jbOk);
		
		jpButton.add(jpSend);
		jpButton.add(jpSpace);
		jpButton.add(jpCancel);
		
		//jpMsg.add(jsMsg);
		
		add(jsMsg);
		add(jpButton);
		
		//�̺�Ʈó��
		MsgReadViewEvt mrve=new MsgReadViewEvt(this);
		jbOk.addActionListener(mrve);
		jbSend.addActionListener(mrve);
		
		//����ȭ
		setVisible(true);
		
		  //�����̺�Ʈó��
        addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
          dispose();                                                    
         }//windowClosing
        });
    }//joinView
   
   public static void main(String[] args) {
        new MsgReadView();
   }//main

public JTextArea getJtaMsg() {
	return jtaMsg;
}

public void setJtaMsg(JTextArea jtaMsg) {
	this.jtaMsg = jtaMsg;
}

public JButton getJbSend() {
	return jbSend;
}

public JButton getJbOk() {
	return jbOk;
}
   
   
}//class


