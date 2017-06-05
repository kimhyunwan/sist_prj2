
package kr.co.sist.market.evt;

 

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.sql.SQLException;

import java.util.List;

 

import javax.swing.JOptionPane;

import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

 

import kr.co.sist.market.dao.MarketDAO;

import kr.co.sist.market.view.BuyListView;

import kr.co.sist.market.vo.SellBuyVO;

 

public class BuyListViewEvt extends MouseAdapter implements ActionListener {

    private BuyListView blv;

    private MarketDAO m_dao;

    LoginViewEvt lve;

    

    

    public BuyListViewEvt(BuyListView blv){

        this.blv=blv;

        m_dao=MarketDAO.getInstance(); //�ǸŸ���Ʈ���̺��� �������� ����

        //���ſϷ� ����� ��ȸ�Ͽ� �����Ѵ�.

        setBuyListComp();

        //���Ŵ�� ����� ��ȸ�Ͽ� �����Ѵ�.

        setBuyListWait();

    }//BuyListViewEvt

    

///////////////////////////////////////////���ſϷ� ����� ���� method////////////////////////////////////

    public void setBuyListComp(){

        try {

            String id=lve.id; //�������� ID

//            String id="hyunwan"; //�������� ID

            List<SellBuyVO> lstItem=m_dao.selectBuyCompList(id);

            //"�Ǹ���", "��ȣ", "��ǰ��" , "���ſϷ��Ͻ�"

            

            Object[] rowItem=new Object[5];

            

            //DefaultTableModel�� �޾ƿ;��Ѵ�.

            DefaultTableModel dtmItem=blv.getDtmComplet(); //BuyListViewŬ�������� dtm��ü�� ������

            

            SellBuyVO sbv=null;

            for(int i=0; i<lstItem.size(); i++){

                //SellBuyVO //String id, itemCode, itemName, tradeDate;

                sbv=lstItem.get(i); //����Ʈ�޴��� ���� �����ͼ� sbv�� �������

                rowItem[0]=(i+1);

                rowItem[1]=sbv.getId();

                rowItem[2]=sbv.getItemCode();

                rowItem[3]=sbv.getItemName();

                rowItem[4]=sbv.getTradeDate();

                dtmItem.addRow(rowItem);

            }//end for

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(blv, "�˼��մϴ�. �ǸſϷ����� �ҷ��� �� �����ϴ�.\n����� �ٽ� �õ����ּ���.");

            e.printStackTrace();

        }

    }//setSellList()

 

///////////////////////////////////////////���Ŵ�� ����� ���� method////////////////////////////////////

    public void setBuyListWait(){

        try {

            String id=lve.id; //�������� ID

            //String id="hyunwan"; //�������� ID

            List<SellBuyVO> lstItem=m_dao.selectBuyWaitList(id);

            //"���Ž�û��", "��ǰ�ڵ�", "��ǰ��" , "��û��"

            

            Object[] rowItem=new Object[5];

            

            //DefaultTableModel�� �޾ƿ;��Ѵ�.

            DefaultTableModel dtmItem=blv.getDtmWait(); // //BuyListViewŬ�������� dtm��ü�� ������

            

            SellBuyVO sbv=null;

            for(int i=0; i<lstItem.size(); i++){

            //SellBuyVO //String id, itemCode, itemName, tradeDate;

            sbv=lstItem.get(i); //����Ʈ�޴��� ���� �����ͼ� sbv�� �������

            rowItem[0]=(i+1);

            rowItem[1]=sbv.getId();

            rowItem[2]=sbv.getItemCode();

            rowItem[3]=sbv.getItemName();

            rowItem[4]=sbv.getTradeDate();

            

            dtmItem.addRow(rowItem);

            }//end for

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(blv, "�˼��մϴ�. �ǸŴ������ �ҷ��� �� �����ϴ�.\n����� �ٽ� �õ����ּ���.");

            e.printStackTrace();

        }

    }//setSellList()

    

    @Override

    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource()==blv.getJbClose()){

            blv.dispose();

        }//end if

        if(ae.getSource()==blv.getJbWClose()){

            blv.dispose();

        }//end if

    }

 

    //���Ŵ�� ��Ͽ��� ��ǰ�� Ŭ������ ��

    @Override

    public void mouseClicked(MouseEvent me) {

        

        //�ش� ��ǰ�� ���� Ŭ������ ��

        if(me.getClickCount()==2){

            JTable temp = blv.getJtWait(); //���Ŵ�� â ȣ��

             int selectedRow = temp.getSelectedRow();

             // ��ȣ,���Ž�û��,��ǰ�ڵ�,��ǰ��,��û��

            String buyerId = lve.id;

             String itemCode = ((String) temp.getValueAt(selectedRow, 2));

             // updateBuyComp(String itemCode, String buyerId);

             int flag = JOptionPane.showConfirmDialog(blv, "���ſ�û�� ����Ͻðڽ��ϱ�?");

             

             switch (flag) {

             case JOptionPane.OK_OPTION:

                try {

                   m_dao= MarketDAO.getInstance();

                   m_dao.deletePurchase(itemCode,buyerId);

                   new BuyListView(1); //����â�� ���Ŵ��â���� ����

                  blv.dispose();

                } catch (SQLException e) {

                   e.printStackTrace();

                }//end catch

                break;

             }// end switch

        }

    }//mouseClicked

    

    

    

}//class

 

 

 

