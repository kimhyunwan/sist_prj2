package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.market.evt.MyInfoChViewEvt;

@SuppressWarnings("serial")
public class MyInfoChView extends JFrame {
	private JTextField jtfAnswer;
	private JButton jbImage,jbChange,jbCancel;
	private JComboBox<String> jcbQuest;
	private JTextArea jtaIntro;
	private JPasswordField jtfPass,jtfPassChk;
	
	public MyInfoChView(){
		super("��������");
		ImageIcon itemImg = new ImageIcon("C:/dev/workspace/prj22/src/kr/co/sist/market/img/profile.jpg");
		JLabel itemImage = new JLabel(itemImg);
		JLabel jlPass = new JLabel("��й�ȣ");
		JLabel jlPassChk = new JLabel("��й�ȣ Ȯ��");
		JLabel jlQuest = new JLabel("��й�ȣ ����");
		JLabel jlAnswer = new JLabel("��й�ȣ �亯");
		
		JLabel jlIntro = new JLabel("�ڱ�Ұ�");
		jtaIntro = new JTextArea();
		JScrollPane jspIntro = new JScrollPane(jtaIntro);

		jtfPass=new JPasswordField();
		jtfPassChk = new JPasswordField();
		String[] questions = {"------------------------ ���� ------------------------","��￡ ���� �߾��� ��Ҵ�?","�ڽ��� ���� ��1ȣ��?","�ڽ��� �λ� �¿����?","���� ��￡ ���� ������ ������?",
				"Ÿ���� �𸣴� �ڽŸ��� ��ü����� �ִٸ�?","�߾��ϰ� ���� ��¥�� �ִٸ�?","�ٽ� �¾�� �ǰ� ���� ����?"};
		jcbQuest = new JComboBox<String>(questions);
		jtfAnswer = new JTextField();		
		jtaIntro = new JTextArea();  //JTextArea ����  
		
		jbImage = new JButton("�̹��� ���");
		jbChange=new JButton("����");
		jbCancel=new JButton("���");
		
		//�ڵ���ġ ����
		setLayout(null);
		//������Ʈ�� ��ġ ��ġ����
		itemImage.setBounds(20, 20, 180, 200);
		jlPass.setBounds(220, 30, 65, 15);
		jtfPass.setBounds(330, 30, 140,25);
		jlPassChk.setBounds(220, 60, 85, 15);
		jtfPassChk.setBounds(330, 60, 140,25);
		jlQuest.setBounds(220, 90, 85, 15);
		jcbQuest.setBounds(330, 90, 256,25);
		jlAnswer.setBounds(220, 120, 85, 15);
		jtfAnswer.setBounds(330, 120, 256,25);
		jlIntro.setBounds(220, 150, 65, 15);
		jspIntro.setBounds(220, 180, 368, 150);  
		jbImage.setBounds(55, 230, 120, 25);
		jbChange.setBounds(450, 350, 60,25);
		jbCancel.setBounds(520, 350, 60,25);		
		
		//������Ʈ ��ġ
		add(itemImage);
		add(jlPass);
		add(jtfPass);
		add(jlPassChk);
		add(jtfPassChk);
		add(jlQuest);
		add(jcbQuest);
		add(jlAnswer);
		add(jtfAnswer);
		add(jlIntro);
		add(jspIntro);
		add(jbImage);
		add(jbChange);
		add(jbCancel);
		
		//�̺�Ʈ �߰�
		MyInfoChViewEvt iive = new MyInfoChViewEvt(this);
		jbCancel.addActionListener(iive);
		
		//�������� ũ��
		setBounds(300,80,630,470);
		//����ȭ
		setVisible(true);
		//�����̺�Ʈó��
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();                                                     
			}//windowClosing
		});
	}//MyInfoChView
	public JTextField getJtfAnswer() {
		return jtfAnswer;
	}
	public JButton getJbImage() {
		return jbImage;
	}
	public JButton getJbChange() {
		return jbChange;
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
	public JPasswordField getJtfPass() {
		return jtfPass;
	}
	public JPasswordField getJtfPassChk() {
		return jtfPassChk;
	}
	public static void main(String[] args) {
		new MyInfoChView();
	}//main
}//class
