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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.market.evt.BuyListViewEvt;
import kr.co.sist.market.evt.JoinViewEvt;

/**
 * ȸ�� ���� â
 * @author ������
 *
 */
public class JoinView extends JFrame {

   private JTextField jtfName, jtfSsn, jtfId, jtfAnswer;
   private JButton jbImage, jbSignUp, jbCancel;
   private JComboBox<String> jcbQuest;
   private JTextArea jtaIntro;
   private JPasswordField jpwSsn, jpwPass, jpwPassChk;
   
   public JoinView() {
      super("ȸ������");

      JPanel jplbBox=new JPanel(); //���� ���� �г�
      JPanel jptfBox=new JPanel(); // �Է�ĭ���� ���� �г�
      JPanel jpimg=new JPanel(); // ������ ���� �г�
      JPanel jpbutton=new JPanel(); // ��ư ���� �г�
      JPanel jpspace=new JPanel();//��ư ���� ���� �г�
      JPanel jpSignUpButton=new JPanel(); // ���� ��ư ���� �г�
      JPanel jpCancelButton=new JPanel(); // ��� ��ư ���� �г�
      JPanel jpSsn=new JPanel(); // �ֹι�ȣ ���� �г�
      
      
       jtfName=new JTextField(15);
       jtfSsn=new JTextField(6);
       jtfId=new JTextField(15);
       jtfAnswer=new JTextField(15);
       jbImage=new JButton("�̹������");
       jbSignUp=new JButton("����");
       jbCancel=new JButton("���");
       jcbQuest=new JComboBox<>();
       jtaIntro=new JTextArea();
       jpwSsn=new JPasswordField(7);
       jpwPass=new JPasswordField();
       jpwPassChk=new JPasswordField();
       
       jtaIntro.setBorder(new TitledBorder("�ڱ�Ұ�"));
       JScrollPane jsIntro=new JScrollPane(jtaIntro);
      
      ImageIcon profile=new ImageIcon("C:/dev/workspace/market_prj2/src/kr/co/sist/market/img/default.jpg");
      
      JLabel jlName=new JLabel("�̸�");
      JLabel jlSsn=new JLabel("�ֹι�ȣ");
      JLabel jlId=new JLabel("���̵�");
      JLabel jlPass=new JLabel("��й�ȣ");
      JLabel jlPassChk=new JLabel("��й�ȣ Ȯ��");
      JLabel jlQuest=new JLabel("��й�ȣ ����");
      JLabel jlAnswer=new JLabel("��й�ȣ �亯");
      JLabel jlhyphen=new JLabel("-");
      JLabel jlimg=new JLabel(profile);
      
      setLayout(null); //������ġ
      
      //�г� ���̾ƿ� ����
      jplbBox.setLayout(new GridLayout(7, 1));
      jptfBox.setLayout(new GridLayout(7, 1));
      jpimg.setLayout(new BorderLayout());
      jpbutton.setLayout(new FlowLayout());
      
      
      //jpSsn����
      jpSsn.add(jtfSsn);
      jpSsn.add(jlhyphen);
      jpSsn.add(jpwSsn);
      
      //jplbBox �� �󺧵� ����
      jplbBox.add(jlName);
      jplbBox.add(jlSsn);
      jplbBox.add(jlId);
      jplbBox.add(jlPass);
      jplbBox.add(jlPassChk);
      jplbBox.add(jlQuest);
      jplbBox.add(jlAnswer);
      
      
      //jptfBox �� �Է¶��� ����
      jptfBox.add(jtfName); //�̸�
      jptfBox.add(jpSsn); //�ֹι�ȣ �г�
      jptfBox.add(jtfId); // ���̵�
      jptfBox.add(jpwPass); //��й�ȣ
      jptfBox.add(jpwPassChk); //��й�ȣ Ȯ��
      jptfBox.add(jcbQuest); //��й�ȣ ����
      jptfBox.add(jtfAnswer); //��й�ȣ �亯
      
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
      jpbutton.setBounds(380, 400, 190, 70);
      
      //�̺�Ʈ ó��
      //jbImage.addActionListener();
      //jbSignUp.addActionListener();
      JoinViewEvt jve = new JoinViewEvt(this);
      jbCancel.addActionListener(jve);
      
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
   
	public JTextField getJtfName() {
	return jtfName;
}

public JTextField getJtfSsn() {
	return jtfSsn;
}

public JTextField getJtfId() {
	return jtfId;
}

public JTextField getJtfAnswer() {
	return jtfAnswer;
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

public JComboBox<String> getJcbQuest() {
	return jcbQuest;
}

public JTextArea getJtaIntro() {
	return jtaIntro;
}

public JPasswordField getJpwSsn() {
	return jpwSsn;
}

public JPasswordField getJpwPass() {
	return jpwPass;
}

public JPasswordField getJpwPassChk() {
	return jpwPassChk;
}

	public static void main(String[] args) {
		new JoinView();
	}//main
       

}//class