package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.SellListView;

import kr.co.sist.market.vo.ItemListVO;

import kr.co.sist.market.vo.ItemInfoVO;

import kr.co.sist.market.vo.SellBuyVO;
import kr.co.sist.market.vo.SellingVO;

//
public class SellListViewEvt extends MouseAdapter implements ActionListener {
   private SellListView slv;
   private MarketDAO m_dao;
   private CustomerDAO c_dao;
   LoginViewEvt lve;

   int chkNum1 = 0;
   int chkNum2 = 0;
   int chkNum3 = 0;

   int indexNum = 0;
   String phone = "";

   public SellListViewEvt(SellListView slv) {
      this.slv = slv;
      m_dao = MarketDAO.getInstance(); // 판매리스트테이블을 가져오기 위함
      c_dao = CustomerDAO.getInstance();// 내 판매물품목록 테이블을 사용하기위해
      // 판매완료리스트를 조회하여 설정한다.
      setSellListComp();
      // 판매대기리스트를 조회하여 설정한다.

      setSellListWait();
      // 내 판매물품 목록을 조회하여 설정한다.
      setMySellList();

      setSellListWait();

   }// SellListViewEvt

   /////////////////////////////////////////// 판매완료 목록을 띄우는
   /////////////////////////////////////////// method/////////////////////////////////////
   public void setSellListComp() {
      try {
         String id = lve.id; // 진데이터 ID
         // String id="wkdwogns"; //가데이터 ID
         List<SellBuyVO> lstItem1 = m_dao.selectSellCompList(id);
         // "구매자", "번호", "상품명" , "판매완료일시"

         Object[] rowItem1 = new Object[5];

         // DefaultTableModel을 받아와야한다.
         DefaultTableModel dtmItem1 = slv.getDtmComplet(); // SellListView클래스에서
                                                // dtm객체를 가져옴

         SellBuyVO sbv = null;

         dtmItem1.setRowCount(0);

         for (int i = 0; i < lstItem1.size(); i++) {
            // SellBuyVO //String id, itemCode, itemName, tradeDate;
            sbv = lstItem1.get(i); // 리스트메뉴의 값을 가져와서 sbv에 집어넣음
            rowItem1[0] = (i + 1);
            rowItem1[1] = sbv.getId();
            rowItem1[2] = sbv.getItemCode();
            rowItem1[3] = sbv.getItemName();
            rowItem1[4] = sbv.getTradeDate();

            dtmItem1.addRow(rowItem1);
         } // end for
      } catch (SQLException e) {
         JOptionPane.showMessageDialog(slv, "죄송합니다. 판매완료목록을 불러올 수 없습니다.\n잠시후 다시 시도해주세요.");
         e.printStackTrace();
      } catch (NullPointerException npe) {
         JOptionPane.showMessageDialog(slv, "판매가 완료된 물품이 없습니다.");
      }
   }// setSellList()

   /////////////////////////////////////////// 판매대기 목록을 띄우는
   /////////////////////////////////////////// method////////////////////////////////////
   public void setSellListWait() {
      try {
         String id = lve.id; // 진데이터 ID
         // String id="dongha"; //가데이터 ID
         List<SellingVO> lstItem2 = m_dao.selectSellWaitList(id);

         // "구매신청자", "상품코드", "상품명" , "신청일"

         Object[] rowItem2 = new Object[5];

         // DefaultTableModel을 받아와야한다.
         DefaultTableModel dtmItem2 = slv.getDtmWait(); // SellListView클래스에서
                                             // dtm객체를 가져옴

         SellingVO slv = null;

         dtmItem2.setRowCount(0);

         for (int i = 0; i < lstItem2.size(); i++) {
            // SellBuyVO //String id, itemCode, itemName, tradeDate;
            // SellingVO //String itemCode, phone, id, reqDate, itemName;

            slv = lstItem2.get(i); // 리스트메뉴의 값을 가져와서 slv에 집어넣음
            rowItem2[0] = (i + 1);
            rowItem2[1] = slv.getId();
            rowItem2[2] = slv.getItemCode();
            rowItem2[3] = slv.getItemName();
            rowItem2[4] = slv.getReqDate();

            dtmItem2.addRow(rowItem2);
         } // end for
      } catch (SQLException e) {
         JOptionPane.showMessageDialog(slv, "죄송합니다. 판매대기목록을 불러올 수 없습니다.\n잠시후 다시 시도해주세요.");
         e.printStackTrace();
      } catch (NullPointerException npe) {
         JOptionPane.showMessageDialog(slv, "판매대기목록이 없습니다.");
      }
   }// setSellList()

   /////////////////////////////////////////// 내 판매물품 목록을 띄우는
   /////////////////////////////////////////// method////////////////////////////////////
   public void setMySellList() {
      try {
         String id = lve.id; // 진데이터 ID
         // String id="dongha"; //가데이터 ID
         List<ItemListVO> myItemLst = c_dao.selectMyItemList(id);
         // "구매신청자", "상품코드", "상품명" , "신청일"

         Object[] rowItem3 = new Object[6];

         // DefaultTableModel을 받아와야한다.
         DefaultTableModel dtmItem3 = slv.getDtmLst(); // SellListView클래스에서
                                             // dtm객체를 가져옴

         ItemListVO ilvo = null;

         dtmItem3.setRowCount(0);

         for (int i = 0; i < myItemLst.size(); i++) {
            // SellBuyVO //String id, itemCode, itemName, tradeDate;
            ilvo = myItemLst.get(i); // 리스트메뉴의 값을 가져와서 sbv에 집어넣음
            rowItem3[0] = (i + 1);
            rowItem3[1] = ilvo.getItemName();
            rowItem3[2] = ilvo.getItemCode();
            rowItem3[3] = ilvo.getItemInfo();
            rowItem3[4] = ilvo.getPrice();
            rowItem3[5] = ilvo.getHiredate();

            dtmItem3.addRow(rowItem3);
         } // end for
      } catch (SQLException e) {
         JOptionPane.showMessageDialog(slv, "죄송합니다. 판매대기목록을 불러올 수 없습니다.\n잠시후 다시 시도해주세요.");
         e.printStackTrace();
      }
   }// setMySellList()

   /////////////////////////////////////////////////////////////////////////////////////////////////////

   @Override
   public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == slv.getJbClose()) {
         slv.dispose();
      } // end if
      if (ae.getSource() == slv.getJbWClose()) {
         slv.dispose();
      } // end if
      if (ae.getSource() == slv.getJbLClose()) {
         slv.dispose();
      } // end if
   }

   ///////////////////////// 판매대기 목록에서 상품를 클릭했을 때////////////////////////////
   @Override
   public void mouseClicked(MouseEvent me) {
      try {
         indexNum = ((JTabbedPane) me.getSource()).getSelectedIndex();
      } catch (ClassCastException cce) {
         if (me.getClickCount() == 2) { // 더블클릭을 했다면 실행
            if (indexNum == 0) {
               JTable temp = slv.getJtComplet(); // 테이블을 가져오고
               int selectedRow = temp.getSelectedRow();
               // String selectPhone(String itemCode, String buyerId)
               // 번호,구매자,상품코드,상품명,판매완료일시
               String buyerId = ((String) temp.getValueAt(selectedRow, 1));
               String itemCode = ((String) temp.getValueAt(selectedRow, 2));
               String itemName = ((String) temp.getValueAt(selectedRow, 3));
               String soldDate = ((String) temp.getValueAt(selectedRow, 4));
               // String selectPhone(String itemCode, String buyerId)
               try {
                  c_dao = CustomerDAO.getInstance();
                  phone = c_dao.selectPhone(itemCode, buyerId);
               } catch (SQLException e) {
                  e.printStackTrace();
               }
               // 번호, 구매자, 상품코드, 상품명, 판매완료일시
               JOptionPane.showMessageDialog(slv, "<상품 거래정보>\n구매자: " + buyerId + "\n상품명 : " + itemName + "\n상품코드: "
                     + itemCode + "\n연락처: " + phone,"구매자 정보", JOptionPane.INFORMATION_MESSAGE);
            } // end if

            if (indexNum == 1) {
               JTable temp = slv.getJtWait(); // 테이블을 가져오고
               int selectedRow = temp.getSelectedRow();
               // 번호,구매신청자,상품코드,상품명,신청일
               String buyerId = ((String) temp.getValueAt(selectedRow, 1));
               String itemCode = ((String) temp.getValueAt(selectedRow, 2));
               String itemName = ((String) temp.getValueAt(selectedRow, 3));
               String reqDate = ((String) temp.getValueAt(selectedRow, 4));
               // updateBuyComp(String itemCode, String buyerId);
               int flag = JOptionPane.showConfirmDialog(slv, "<판매완료 승인>\n구매신청자 : " + buyerId + "\n상품명 : "
                     + itemName + "\n상품코드 : " + itemCode + "\n신청일 : " + reqDate + "\n\n위의 정보로 판매하시겠습니까?");

               switch (flag) {
               case JOptionPane.OK_OPTION:
                  modifyBuyComp(itemCode, buyerId);
                  new SellListView(indexNum);
                  break;
               }// end switch
            } // end if
            
            if (indexNum == 2) {
               
               JTable temp = slv.getJtLst(); // 테이블을 가져오고
               int selectedRow = temp.getSelectedRow();
               // 번호,구매신청자,상품코드,상품명,신청일
               String buyerId = ((String) temp.getValueAt(selectedRow, 1));
               String itemCode = ((String) temp.getValueAt(selectedRow, 2));
               // updateBuyComp(String itemCode, String buyerId);
               int flag = JOptionPane.showConfirmDialog(slv, "판매 취소하시겠습니까?");
               
               switch (flag) {
               case JOptionPane.OK_OPTION:
                  try {
                     m_dao= MarketDAO.getInstance();
                     m_dao.deleteProduct(itemCode);
                     new SellListView(indexNum );
                     slv.dispose();
                  } catch (SQLException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }//end catch
                  break;
               }// end switch
            } // end if
         } // end if
      } // end catch
   }// mouseClicked

   private void modifyBuyComp(String itemCode, String buyerId) {
      try {
         m_dao.updateBuyComp(itemCode, buyerId);
         m_dao.deleteSellWait(itemCode, buyerId);
         JOptionPane.showMessageDialog(slv, "판매가 완료되었습니다!");
         setSellListComp(); // 판매완료목록 최신화
         setSellListWait(); // 판매대기목록 최신화
      } catch (SQLException e) {
         e.printStackTrace();
      } // end catch
   }// modifyBuyComp
}// class