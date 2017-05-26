package kr.co.sist.market.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.market.evt.SignUpItemViewEvt;

public class SignUpItemView extends JFrame {
	private JTextField jtfItemName, jtfPrice, jtfHiredate;
	   private JButton jbImage, jbSignUp, jbCancel;
	   private JComboBox<String> jcbType;
	   private JTextArea jtaItemInfo;
	   
	   public SignUpItemView() {
	      super("�ǸŹ�ǰ ���");

	      JPanel jplbBox=new JPanel(); //���� ���� �г�
	      JPanel jptfBox=new JPanel(); // �Է�ĭ���� ���� �г�
	      JPanel jpimg=new JPanel(); // ������ ���� �г�
	      JPanel jpbutton=new JPanel(); // ��ư ���� �г�
	      JPanel jpspace=new JPanel();//��ư ���� ���� �г�
	      JPanel jpSignUpButton=new JPanel(); // ���� ��ư ���� �г�
	      JPanel jpCancelButton=new JPanel(); // ��� ��ư ���� �г�
	      JPanel jpSsn=new JPanel(); // �ֹι�ȣ ���� �г�
	      
	       jtfItemName=new JTextField(15);
	       jtfPrice=new JTextField(15);
	       jtfHiredate=new JTextField(15);
	       jbImage=new JButton("��ǰ���� ���");
	       jbSignUp=new JButton("��ǰ���");
	       jbCancel=new JButton("���");
	       jcbType=new JComboBox<>();
	       jtaItemInfo=new JTextArea();
	       
	       jtaItemInfo.setBorder(new TitledBorder("��ǰ����"));
	       JScrollPane jsIntro=new JScrollPane(jtaItemInfo);
	      
	      ImageIcon profile=new ImageIcon("C:/dev/workspace/market_prj2/src/kr/co/sist/market/img/default.jpg");
	      
	      JLabel jlName=new JLabel("��ǰ��");
	      JLabel jlType=new JLabel("��ǰ�з�");
	      JLabel jlPrice=new JLabel("����");
	      JLabel jlHiredate =new JLabel("�����");
	      JLabel jlimg =new JLabel(profile);
	      
	      setLayout(null); //������ġ
	      
	      //�г� ���̾ƿ� ����
	      jplbBox.setLayout(new GridLayout(4, 1));
	      jptfBox.setLayout(new GridLayout(4, 1));
	      jpimg.setLayout(new BorderLayout());
	      jpbutton.setLayout(new FlowLayout());
	      
	      
	      //jplbBox �� �󺧵� ����
	      jplbBox.add(jlName);
	      jplbBox.add(jlType);
	      jplbBox.add(jlPrice);
	      jplbBox.add(jlHiredate);
	      
	      
	      //jptfBox �� �Է¶��� ����
	      jptfBox.add(jtfItemName); //��ǰ��
	      jptfBox.add(jcbType); //��ǰ�з�
	      jptfBox.add(jtfPrice); //����
	      jptfBox.add(jtfHiredate); //�����
	      
	      //�̹��� �г�
	      jpimg.add(jlimg);
	      jpimg.add("South", jbImage);
	      
	     //�� ��ư �гο� ��ư �ֱ�
	      jpSignUpButton.add(jbSignUp);
	      jpCancelButton.add(jbCancel);
	      
	      //��ư �г�
	      jpbutton.add(jpSignUpButton);
	      jpbutton.add(jpspace);
	      jpbutton.add(jpCancelButton);
	      
	      //�г� �ֱ�
	      add(jptfBox);
	      add(jplbBox);
	      add(jpimg);
	      add(jsIntro);
	      add(jpbutton);

	      //�� �г� ��ġ
	      setBounds(50, 50, 585, 500);
	      jpimg.setBounds(10, 10, 180, 230);
	      jplbBox.setBounds(250, 10, 100, 230);
	      jptfBox.setBounds(360, 10, 200, 230);
	      jsIntro.setBounds(10, 250, 550, 150);
	      jpbutton.setBounds(360, 400, 200, 100);

		  //�̺�Ʈ �߰�
	      SignUpItemViewEvt blve = new SignUpItemViewEvt(this);
		  jbCancel.addActionListener(blve);
	      
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
	       
	   
	   public JTextField getJtfItemName() {
		return jtfItemName;
	}


	public JTextField getJtfPrice() {
		return jtfPrice;
	}


	public JTextField getJtfHiredate() {
		return jtfHiredate;
	}


	public JButton getJbImage() {
		return jbImage;
	}


	public JButton getJbSignUp() {
		return jbSignUp;
	}


	public JButton getJbCancel() {
		return jbCancel;
	}


	public JComboBox<String> getJcbType() {
		return jcbType;
	}


	public JTextArea getJtaItemInfo() {
		return jtaItemInfo;
	}


	public static void main(String[] args) {
	        new SignUpItemView();
	   }//main
	   
	   
	   
	   
	}//class