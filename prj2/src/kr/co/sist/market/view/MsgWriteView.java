package kr.co.sist.market.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import kr.co.sist.market.evt.MsgWriteViewEvt;

public class MsgWriteView extends JFrame {

	JTextArea jtaMsg;
	JButton jbSend, jbCancel;
	
	public MsgWriteView() {
		super("메세지 보내기");
		
		jtaMsg=new JTextArea();
		jbSend=new JButton("메세지 보내기");
		jbCancel=new JButton("취소");
		
		jtaMsg.setBorder(new TitledBorder("판매자에게 보낼 메세지"));
		
		//패널 생성
		JPanel jpSend=new JPanel();
		JPanel jpCancel=new JPanel();
		JPanel jpSpace=new JPanel();
		JPanel jpButton=new JPanel();
		JPanel jpMsg=new JPanel();
		
		 JScrollPane jsMsg=new JScrollPane(jtaMsg);
		
		//수동배치
		setLayout(null);
		jpButton.setLayout(new FlowLayout());
		jpMsg.setLayout(new BorderLayout());
		
		//배치
		setBounds(100, 100, 450, 285);
		jsMsg.setBounds(5, 5, 425, 195);
		jpButton.setBounds(170, 200, 270, 100);
		
		//삽입
		jpSend.add(jbSend);
		jpCancel.add(jbCancel);
		
		jpButton.add(jpSend);
		jpButton.add(jpSpace);
		jpButton.add(jpCancel);
		
		
		add(jsMsg);
		add(jpButton);
		
		//이벤트 처리
		MsgWriteViewEvt mwve = new MsgWriteViewEvt(this);
		jbSend.addActionListener(mwve);
		jbCancel.addActionListener(mwve);
		
		
		//가시화
		setVisible(true);
		
		  //종료이벤트처리
        addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
          dispose();                                                    
         }//windowClosing
        });
    }//joinView
   
   /**
    * 검정과정. 추후 삭제요망
 * @param args
 */
public static void main(String[] args) {
        new MsgWriteView();
   }//main

//메세지 값 내보내기
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
   

}//class
