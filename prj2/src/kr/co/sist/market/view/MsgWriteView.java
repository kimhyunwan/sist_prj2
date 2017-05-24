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

public class MsgWriteView extends JFrame {

	JTextArea jtaMsg;
	JButton jbSend, jbCancel;
	
	public MsgWriteView() {
		super("�޼��� ������");
		
		jtaMsg=new JTextArea();
		jbSend=new JButton("�޼��� ������");
		jbCancel=new JButton("���");
		
		jtaMsg.setBorder(new TitledBorder("�Ǹ��ڿ��� ���� �޼���"));
		
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
		jpCancel.add(jbCancel);
		
		jpButton.add(jpSend);
		jpButton.add(jpSpace);
		jpButton.add(jpCancel);
		
		//jpMsg.add(jsMsg);
		
		add(jsMsg);
		add(jpButton);
		
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
   
   /**
    * ��������. ���� �������
 * @param args
 */
public static void main(String[] args) {
        new MsgWriteView();
   }//main
   
}//class
