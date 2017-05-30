package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import kr.co.sist.market.evt.MsgReadViewEvt;

//import kr.co.sist.market.evt.MsgReadViewEvt;

public class MsgReadView extends JFrame {
	JTextArea jtaMsg;
	JButton jbSend, jbOk;
	
	public MsgReadView() {
		super("나에게 온 메세지");
		
		jtaMsg=new JTextArea();
		jbSend=new JButton("메세지 보내기");
		jbOk=new JButton("확인");
		
		jtaMsg.setBorder(new TitledBorder("구매자에게서 온 메세지"));
		
		 JScrollPane jsMsg=new JScrollPane(jtaMsg);
		
		//수동배치
		setLayout(null);

		
		//배치
		setBounds(100, 100, 450, 285);
		jsMsg.setBounds(5, 5, 425, 195);
		jbSend.setBounds(230, 210, 115, 25);
		jbOk.setBounds(350, 210, 75, 25);
		
		//삽입
		add(jsMsg);
		add(jbSend);
		add(jbOk);
		
	//이벤트처리
		MsgReadViewEvt mrve=new MsgReadViewEvt(this);
		jbOk.addActionListener(mrve);
		jbSend.addActionListener(mrve);
		
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


