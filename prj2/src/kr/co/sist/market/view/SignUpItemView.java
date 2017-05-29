package kr.co.sist.market.view;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.market.evt.SignUpItemViewEvt;

public class SignUpItemView extends JFrame {
	private JTextField jtfItemName, jtfPrice, jtfHiredate;
	private JButton jbImage, jbSignUp, jbCancel;
	private JComboBox<String> jcbType;
	private DefaultComboBoxModel<String> dcbmTp;
	private JTextArea jtaItemInfo;
	private JLabel jlimg;
	
	
	public SignUpItemView() {
		super("�ǸŹ�ǰ ���");
	       jtfItemName=new JTextField(15);
	       jtfPrice=new JTextField(15);
	       jtfHiredate=new JTextField(15);
	       jbImage=new JButton("��ǰ���� ���");
	       jbSignUp=new JButton("��ǰ���");
	       jbCancel=new JButton("���");
	       dcbmTp=new DefaultComboBoxModel<>();
	       jcbType=new JComboBox<>(dcbmTp);
	       jtaItemInfo=new JTextArea();
	       
	       jtaItemInfo.setBorder(new TitledBorder("��ǰ����"));
	       JScrollPane jsIntro=new JScrollPane(jtaItemInfo);
	      
	      ImageIcon profile=new ImageIcon("C:/dev/workspace/prj10/src/kr/co/sist/market/img/default.jpg");
	      
	      JLabel jlName=new JLabel("��ǰ��");
	      JLabel jlType=new JLabel("��ǰ�з�");
	      JLabel jlPrice=new JLabel("����");
	      JLabel jlHiredate =new JLabel("�����");
	      jlimg =new JLabel(profile);
	      
	      setLayout(null); //������ġ
	      //�� ������Ʈ ��ġ
	      setBounds(50, 50, 480, 500);
	      jlimg.setBounds(20, 20, 180, 195);
	      jbImage.setBounds(49, 223, 120, 25);
	      jlName.setBounds(230, 30, 70, 25);
	      jlType.setBounds(230, 70, 70, 25);
	      jlPrice.setBounds(230, 110, 70, 25);
	      jlHiredate.setBounds(230, 150, 70, 25);
	      
	      jtfItemName.setBounds(305, 30, 130, 25);
	      jcbType.setBounds(305, 70, 130, 25);
	      jtfPrice.setBounds(305, 110, 130, 25);
	      jtfHiredate.setBounds(305, 150, 130, 25);
	      
	      jsIntro.setBounds(20, 265, 420, 130);
	      jbSignUp.setBounds(235, 410, 95, 25);
	      jbCancel.setBounds(340, 410, 95, 25);
	      
	      //������Ʈ ���̱�
	      add(jlimg);
	      add(jbImage);
	      add(jlName);
	      add(jlType);
	      add(jlPrice);
	      add(jlHiredate);
	      add(jtfItemName);
	      add(jcbType);
	      add(jtfPrice);
	      add(jtfHiredate);
	      add(jsIntro);
	      add(jbSignUp);
	      add(jbCancel);
	      
			// �̺�Ʈ �߰�
			SignUpItemViewEvt blve = new SignUpItemViewEvt(this);
			jbCancel.addActionListener(blve);
			jbImage.addActionListener(blve);
			jbSignUp.addActionListener(blve);
	      
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

	}// joinView

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

	public JLabel getJlimg() {
		return jlimg;
	}
	
	public DefaultComboBoxModel<String> getDcbmTp() {
		return dcbmTp;
	}

	public static void main(String[] args) {
		new SignUpItemView();
	}// main

}// class