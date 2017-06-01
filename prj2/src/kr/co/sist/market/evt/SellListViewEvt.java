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
      m_dao = MarketDAO.getInstance(); // �ǸŸ���Ʈ���̺��� �������� ����
      c_dao = CustomerDAO.getInstance();// �� �ǸŹ�ǰ��� ���̺��� ����ϱ�����
      // �ǸſϷḮ��Ʈ�� ��ȸ�Ͽ� �����Ѵ�.
      setSellListComp();
      // �ǸŴ�⸮��Ʈ�� ��ȸ�Ͽ� �����Ѵ�.

      setSellListWait();
      // �� �ǸŹ�ǰ ����� ��ȸ�Ͽ� �����Ѵ�.
      setMySellList();

      setSellListWait();

   }// SellListViewEvt

   /////////////////////////////////////////// �ǸſϷ� ����� ����
   /////////////////////////////////////////// method/////////////////////////////////////
   public void setSellListComp() {
      try {
         String id = lve.id; // �������� ID
         // String id="wkdwogns"; //�������� ID
         List<SellBuyVO> lstItem1 = m_dao.selectSellCompList(id);
         // "������", "��ȣ", "��ǰ��" , "�ǸſϷ��Ͻ�"

         Object[] rowItem1 = new Object[5];

         // DefaultTableModel�� �޾ƿ;��Ѵ�.
         DefaultTableModel dtmItem1 = slv.getDtmComplet(); // SellListViewŬ��������
                                                // dtm��ü�� ������

         SellBuyVO sbv = null;

         dtmItem1.setRowCount(0);

         for (int i = 0; i < lstItem1.size(); i++) {
            // SellBuyVO //String id, itemCode, itemName, tradeDate;
            sbv = lstItem1.get(i); // ����Ʈ�޴��� ���� �����ͼ� sbv�� �������
            rowItem1[0] = (i + 1);
            rowItem1[1] = sbv.getId();
            rowItem1[2] = sbv.getItemCode();
            rowItem1[3] = sbv.getItemName();
            rowItem1[4] = sbv.getTradeDate();

            dtmItem1.addRow(rowItem1);
         } // end for
      } catch (SQLException e) {
         JOptionPane.showMessageDialog(slv, "�˼��մϴ�. �ǸſϷ����� �ҷ��� �� �����ϴ�.\n����� �ٽ� �õ����ּ���.");
         e.printStackTrace();
      } catch (NullPointerException npe) {
         JOptionPane.showMessageDialog(slv, "�ǸŰ� �Ϸ�� ��ǰ�� �����ϴ�.");
      }
   }// setSellList()

   /////////////////////////////////////////// �ǸŴ�� ����� ����
   /////////////////////////////////////////// method////////////////////////////////////
   public void setSellListWait() {
      try {
         String id = lve.id; // �������� ID
         // String id="dongha"; //�������� ID
         List<SellingVO> lstItem2 = m_dao.selectSellWaitList(id);

         // "���Ž�û��", "��ǰ�ڵ�", "��ǰ��" , "��û��"

         Object[] rowItem2 = new Object[5];

         // DefaultTableModel�� �޾ƿ;��Ѵ�.
         DefaultTableModel dtmItem2 = slv.getDtmWait(); // SellListViewŬ��������
                                             // dtm��ü�� ������

         SellingVO slv = null;

         dtmItem2.setRowCount(0);

         for (int i = 0; i < lstItem2.size(); i++) {
            // SellBuyVO //String id, itemCode, itemName, tradeDate;
            // SellingVO //String itemCode, phone, id, reqDate, itemName;

            slv = lstItem2.get(i); // ����Ʈ�޴��� ���� �����ͼ� slv�� �������
            rowItem2[0] = (i + 1);
            rowItem2[1] = slv.getId();
            rowItem2[2] = slv.getItemCode();
            rowItem2[3] = slv.getItemName();
            rowItem2[4] = slv.getReqDate();

            dtmItem2.addRow(rowItem2);
         } // end for
      } catch (SQLException e) {
         JOptionPane.showMessageDialog(slv, "�˼��մϴ�. �ǸŴ������ �ҷ��� �� �����ϴ�.\n����� �ٽ� �õ����ּ���.");
         e.printStackTrace();
      } catch (NullPointerException npe) {
         JOptionPane.showMessageDialog(slv, "�ǸŴ������ �����ϴ�.");
      }
   }// setSellList()

   /////////////////////////////////////////// �� �ǸŹ�ǰ ����� ����
   /////////////////////////////////////////// method////////////////////////////////////
   public void setMySellList() {
      try {
         String id = lve.id; // �������� ID
         // String id="dongha"; //�������� ID
         List<ItemListVO> myItemLst = c_dao.selectMyItemList(id);
         // "���Ž�û��", "��ǰ�ڵ�", "��ǰ��" , "��û��"

         Object[] rowItem3 = new Object[6];

         // DefaultTableModel�� �޾ƿ;��Ѵ�.
         DefaultTableModel dtmItem3 = slv.getDtmLst(); // SellListViewŬ��������
                                             // dtm��ü�� ������

         ItemListVO ilvo = null;

         dtmItem3.setRowCount(0);

         for (int i = 0; i < myItemLst.size(); i++) {
            // SellBuyVO //String id, itemCode, itemName, tradeDate;
            ilvo = myItemLst.get(i); // ����Ʈ�޴��� ���� �����ͼ� sbv�� �������
            rowItem3[0] = (i + 1);
            rowItem3[1] = ilvo.getItemName();
            rowItem3[2] = ilvo.getItemCode();
            rowItem3[3] = ilvo.getItemInfo();
            rowItem3[4] = ilvo.getPrice();
            rowItem3[5] = ilvo.getHiredate();

            dtmItem3.addRow(rowItem3);
         } // end for
      } catch (SQLException e) {
         JOptionPane.showMessageDialog(slv, "�˼��մϴ�. �ǸŴ������ �ҷ��� �� �����ϴ�.\n����� �ٽ� �õ����ּ���.");
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

   ///////////////////////// �ǸŴ�� ��Ͽ��� ��ǰ�� Ŭ������ ��////////////////////////////
   @Override
   public void mouseClicked(MouseEvent me) {
      try {
         indexNum = ((JTabbedPane) me.getSource()).getSelectedIndex();
      } catch (ClassCastException cce) {
         if (me.getClickCount() == 2) { // ����Ŭ���� �ߴٸ� ����
            if (indexNum == 0) {
               JTable temp = slv.getJtComplet(); // ���̺��� ��������
               int selectedRow = temp.getSelectedRow();
               // String selectPhone(String itemCode, String buyerId)
               // ��ȣ,������,��ǰ�ڵ�,��ǰ��,�ǸſϷ��Ͻ�
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
               // ��ȣ, ������, ��ǰ�ڵ�, ��ǰ��, �ǸſϷ��Ͻ�
               JOptionPane.showMessageDialog(slv, "<��ǰ �ŷ�����>\n������: " + buyerId + "\n��ǰ�� : " + itemName + "\n��ǰ�ڵ�: "
                     + itemCode + "\n����ó: " + phone,"������ ����", JOptionPane.INFORMATION_MESSAGE);
            } // end if

            if (indexNum == 1) {
               JTable temp = slv.getJtWait(); // ���̺��� ��������
               int selectedRow = temp.getSelectedRow();
               // ��ȣ,���Ž�û��,��ǰ�ڵ�,��ǰ��,��û��
               String buyerId = ((String) temp.getValueAt(selectedRow, 1));
               String itemCode = ((String) temp.getValueAt(selectedRow, 2));
               String itemName = ((String) temp.getValueAt(selectedRow, 3));
               String reqDate = ((String) temp.getValueAt(selectedRow, 4));
               // updateBuyComp(String itemCode, String buyerId);
               int flag = JOptionPane.showConfirmDialog(slv, "<�ǸſϷ� ����>\n���Ž�û�� : " + buyerId + "\n��ǰ�� : "
                     + itemName + "\n��ǰ�ڵ� : " + itemCode + "\n��û�� : " + reqDate + "\n\n���� ������ �Ǹ��Ͻðڽ��ϱ�?");

               switch (flag) {
               case JOptionPane.OK_OPTION:
                  modifyBuyComp(itemCode, buyerId);
                  new SellListView(indexNum);
                  break;
               }// end switch
            } // end if
            
            if (indexNum == 2) {
               
               JTable temp = slv.getJtLst(); // ���̺��� ��������
               int selectedRow = temp.getSelectedRow();
               // ��ȣ,���Ž�û��,��ǰ�ڵ�,��ǰ��,��û��
               String buyerId = ((String) temp.getValueAt(selectedRow, 1));
               String itemCode = ((String) temp.getValueAt(selectedRow, 2));
               // updateBuyComp(String itemCode, String buyerId);
               int flag = JOptionPane.showConfirmDialog(slv, "�Ǹ� ����Ͻðڽ��ϱ�?");
               
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
         JOptionPane.showMessageDialog(slv, "�ǸŰ� �Ϸ�Ǿ����ϴ�!");
         setSellListComp(); // �ǸſϷ��� �ֽ�ȭ
         setSellListWait(); // �ǸŴ���� �ֽ�ȭ
      } catch (SQLException e) {
         e.printStackTrace();
      } // end catch
   }// modifyBuyComp
}// class