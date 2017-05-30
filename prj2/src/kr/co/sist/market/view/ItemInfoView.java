package kr.co.sist.market.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.sist.market.evt.ItemInfoViewEvt;
import kr.co.sist.market.vo.ItemListVO;

@SuppressWarnings("serial")
public class ItemInfoView extends JFrame {
   private JTextField jtfItemName, jtfItemcode,jtfItemType, jtfPrice, jtfHiredate;
   private JButton jbSellerInfo,jbBuyReq,jbCancel;
   private JTextArea jtProExplain;
   private ImageIcon pro;
   public ItemInfoView(JFrame jf, ItemListVO iv){
      super("�Ǹ� �� ����");
      pro = new ImageIcon("C:/dev/workspace/prj22/src/kr/co/sist/market/img/profile.jpg");
      JLabel itemImg = new JLabel(pro);
      JLabel jlItemName = new JLabel("��ǰ��");
      JLabel jlItemCode = new JLabel("��ǰ�ڵ�");
      JLabel jlItemType = new JLabel("��ǰ�з�");
      JLabel jlPrice = new JLabel("����");
      JLabel jlHiredate = new JLabel("�����");
      JLabel jlProExplain = new JLabel("��ǰ ����");
      jtProExplain = new JTextArea(iv.getItemInfo());
      JScrollPane jspProExplain = new JScrollPane(jtProExplain);
      
      jtfItemName = new JTextField(iv.getItemName());
      jtfItemcode = new JTextField(iv.getItemCode());
      jtfItemType = new JTextField();
      jtfPrice = new JTextField(String.valueOf(iv.getPrice()));
      jtfHiredate = new JTextField(iv.getHiredate());
      
      jbSellerInfo = new JButton("�Ǹ��� ����");
      jbBuyReq = new JButton("���Ž�û");
      jbCancel = new JButton("���");
      
      //��ǰ�ڵ�, ��ǰ��, ������ �б� ����
      jtfItemcode.setEditable(false);
      jtfItemName.setEditable(false);
      jtfPrice.setEditable(false);
      jtfHiredate.setEditable(false);
      jtProExplain.setEditable(false);
      
      //�ڵ���ġ ����
      setLayout(null);
      //������Ʈ�� ��ġ ��ġ����
      itemImg.setBounds(20, 20, 200, 200);
      jlItemCode.setBounds(235, 23, 70, 15);
      jlItemName.setBounds(235, 55, 50, 15);
      jlItemType.setBounds(235, 90, 70, 15);
      jlPrice.setBounds(235, 125, 70, 15);
      jlHiredate.setBounds(235, 160, 70, 15);
      jlProExplain.setBounds(235, 195, 70, 15);
      
      jtfItemcode.setBounds(310, 23, 115, 23);
      jtfItemName.setBounds(310, 53, 115, 23);
      jtfItemType.setBounds(310, 90, 115, 23);
      jtfPrice.setBounds(310, 125, 115, 23);
      jtfHiredate.setBounds(310, 160, 115, 23);
      jspProExplain.setBounds(235, 215, 190, 100);
      
      jbSellerInfo.setBounds(60, 230, 130 , 50);
      jbBuyReq.setBounds(235, 330, 90, 30);
      jbCancel.setBounds(330, 330, 90, 30);
      //������Ʈ ��ġ
      add(itemImg);
      
      add(jlItemCode);
      add(jlItemName);
      add(jlItemType);
      add(jlPrice);
      add(jlHiredate);
      add(jlProExplain);
      
      add(jtfItemcode);
      add(jtfItemName);
      add(jtfItemType);
      add(jtfPrice);
      add(jtfHiredate);
      add(jspProExplain);
      
      add(jbSellerInfo);
      add(jbBuyReq);
      add(jbCancel);
      
      //�̺�Ʈ �߰�
      ItemInfoViewEvt iive = new ItemInfoViewEvt(this);
      jbSellerInfo.addActionListener(iive);
      jbBuyReq.addActionListener(iive);
      jbCancel.addActionListener(iive);

      //�������� ũ��
      setBounds(300,80,470,430);
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
   }//LoginView
   

   public JTextField getJtfItemName() {
      return jtfItemName;
   }
   public JTextField getJtfItemType() {
      return jtfItemType;
   }
   public JTextField getJtfPrice() {
      return jtfPrice;
   }
   public JTextField getJtfHiredate() {
      return jtfHiredate;
   }
   public JButton getJbSellerInfo() {
      return jbSellerInfo;
   }
   public JButton getJbBuyReq() {
      return jbBuyReq;
   }
   public JButton getJbCancel() {
      return jbCancel;
   }
   public void setJtfItemName(JTextField jtfItemName) {
      this.jtfItemName = jtfItemName;
   }
   public void setJtfItemType(JTextField jtfItemType) {
      this.jtfItemType = jtfItemType;
   }
   public void setJtfPrice(JTextField jtfPrice) {
      this.jtfPrice = jtfPrice;
   }
   public void setJtfHiredate(JTextField jtfHiredate) {
      this.jtfHiredate = jtfHiredate;
   }
   public void setJbSellerInfo(JButton jbSellerInfo) {
      this.jbSellerInfo = jbSellerInfo;
   }
   public void setJbBuyReq(JButton jbBuyReq) {
      this.jbBuyReq = jbBuyReq;
   }
   public void setJbCancel(JButton jbCancel) {
      this.jbCancel = jbCancel;
   }

   public JTextField getJtfItemcode() {
      return jtfItemcode;
   }

   public void setJtfItemcode(JTextField jtfItemcode) {
      this.jtfItemcode = jtfItemcode;
   }


   public JTextArea getJtProExplain() {
      return jtProExplain;
   }


   public void setJtProExplain(JTextArea jtProExplain) {
      this.jtProExplain = jtProExplain;
   }


public ImageIcon getPro() {
	return pro;
}
   

}//class