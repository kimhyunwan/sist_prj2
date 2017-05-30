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
      super("판매 글 제목");
      pro = new ImageIcon("C:/dev/workspace/prj22/src/kr/co/sist/market/img/profile.jpg");
      JLabel itemImg = new JLabel(pro);
      JLabel jlItemName = new JLabel("상품명");
      JLabel jlItemCode = new JLabel("제품코드");
      JLabel jlItemType = new JLabel("제품분류");
      JLabel jlPrice = new JLabel("가격");
      JLabel jlHiredate = new JLabel("등록일");
      JLabel jlProExplain = new JLabel("제품 설명");
      jtProExplain = new JTextArea(iv.getItemInfo());
      JScrollPane jspProExplain = new JScrollPane(jtProExplain);
      
      jtfItemName = new JTextField(iv.getItemName());
      jtfItemcode = new JTextField(iv.getItemCode());
      jtfItemType = new JTextField();
      jtfPrice = new JTextField(String.valueOf(iv.getPrice()));
      jtfHiredate = new JTextField(iv.getHiredate());
      
      jbSellerInfo = new JButton("판매자 정보");
      jbBuyReq = new JButton("구매신청");
      jbCancel = new JButton("취소");
      
      //제품코드, 상품명, 가격은 읽기 전용
      jtfItemcode.setEditable(false);
      jtfItemName.setEditable(false);
      jtfPrice.setEditable(false);
      jtfHiredate.setEditable(false);
      jtProExplain.setEditable(false);
      
      //자동배치 해제
      setLayout(null);
      //컴포넌트의 배치 위치설정
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
      //컴포넌트 배치
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
      
      //이벤트 추가
      ItemInfoViewEvt iive = new ItemInfoViewEvt(this);
      jbSellerInfo.addActionListener(iive);
      jbBuyReq.addActionListener(iive);
      jbCancel.addActionListener(iive);

      //윈도우의 크기
      setBounds(300,80,470,430);
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