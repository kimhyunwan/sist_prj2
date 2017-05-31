package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.MsgListView;
import kr.co.sist.market.view.MsgReadView;
import kr.co.sist.market.vo.MsgListVO;
//import kr.co.sist.market.vo.MsgVO;
import kr.co.sist.market.vo.MsgViewVO;

public class MsgListViewEvt extends MouseAdapter implements ActionListener {
   private MsgListView mlv;
   private CustomerDAO c_dao;
   private MarketDAO m_dao;
   private LoginViewEvt lve;
   private int chkNum;
   public static String msg, msg_id, msg_code, msg_title, item_code;
   
   /**
    * �޼��� ����Ʈ â�� ���� 
    * ���� �޼����� ���� �޼����� ������ ����� Ȯ���ϰ� �ϰ�
    * ����Ŭ���� ���� MsgReadView�� �̵��Ͽ�
    * �޼����� Ȯ���� �� �ֵ��� �Ѵ�
    * @param mlv
    */
   public MsgListViewEvt(MsgListView mlv){
      this.mlv=mlv;
      c_dao=CustomerDAO.getInstance();
      m_dao=MarketDAO.getInstance();
      //�޼����� ��ȸ�Ѵ�
      String id=lve.id;
      
      setSendMsg(id);
      setRecieveMsg(id);
   }//MsgListViewEvt
   
   //////////////////////////////////////////////////////////////////////
   ///////////////���� �޼����� Ȯ���ϴ� ��///////////////////////
   //////////////////////////////////////////////////////////////////////
   /**
    * ���� �޼����� Ȯ���ϴ� ��.
    * @param id
    */
   public void setSendMsg(String id){
      
      chkNum=1; //������ �޼������� Ȯ���ϴ� chkNum
      
      DefaultTableModel dtmMsg=mlv.getDtmSendMsgList();
      
      //����ó���� ���Ͽ� try~catch�� ���
      try{
      List<MsgListVO> lstMsg=m_dao.selectSendMsgList(id);
      Object[] rowData=null;
      MsgListVO mlv=null;
      
      dtmMsg.setRowCount(0);
      
      //����Ʈ�� �޼��� ������ ���
      for(int i=0;i<lstMsg.size();i++){
         mlv=lstMsg.get(i);
         rowData = new Object[5];
         rowData[0]=mlv.getMsgCode();
         rowData[1]=mlv.getId();
         rowData[2]=mlv.getItem();
         rowData[3]=mlv.getMsgDate();
         rowData[4]=mlv.getFlag();
         
         dtmMsg.addRow(rowData);
      }//end for
      }catch(SQLException e){
         JOptionPane.showMessageDialog(mlv, "���񽺰� ��Ȱ���� ���մϴ�");
         e.printStackTrace();
      }//end catch
      
   }//setMsg

   
   //////////////////////////////////////////////////////////////////////
   ///////////////���� �޼����� Ȯ���ϴ� ��///////////////////////
   //////////////////////////////////////////////////////////////////////
   /**
    * ���� �޼����� Ȯ���ϴ� ��
    * @param id
    */
   public void setRecieveMsg(String id){
      DefaultTableModel dtmMsg=mlv.getDtmReceiveMsgList();
      
      try{
      List<MsgListVO> lstMsg=m_dao.selectGetMsgList(id);
      Object[] rowData=null;
      MsgListVO mlv=null;
      
      dtmMsg.setRowCount(0);
      
      //����Ʈ�� �޼��� ������ ���
      for(int i=0;i<lstMsg.size();i++){
         mlv=lstMsg.get(i);
         rowData = new Object[5];
         rowData[0]=mlv.getMsgCode();
         rowData[1]=mlv.getId();
         rowData[2]=mlv.getItem();
         rowData[3]=mlv.getMsgDate();
         rowData[4]=mlv.getFlag();
         
         dtmMsg.addRow(rowData);
      }//end for
      }catch(SQLException e){
         JOptionPane.showMessageDialog(mlv, "���񽺰� ��Ȱ���� ���մϴ�");
         e.printStackTrace();
      }//end catch
      
   }//setMsg
   
   
   
   
   //////////////////////////////////////////////////////////////////////
   ///////////////Ŭ������ ���� �̺�Ʈ ó��///////////////////////
   //////////////////////////////////////////////////////////////////////
   @Override
   public void mouseClicked(MouseEvent me) {
      String id=lve.id;
      m_dao=MarketDAO.getInstance();
      
      //����ó���� ���� try~catch
      try{
         //�� Ŭ���� ���� �Ͼ�� �ε�����
         int indexNum=((JTabbedPane)me.getSource()).getSelectedIndex();
         if(indexNum==0){
            //setSendMsg(id);
            chkNum=1; //������ �޼��� ����Ʈ �� Ȯ������ ��
         }else if(indexNum==1){
            //setRecieveMsg(id);
            chkNum=2; //���� �޼��� ����Ʈ �� Ȯ������ ��
         }//end else
      }catch(ClassCastException cce){
         
      //�� �� �޼����� ����Ŭ������ �� �̺�Ʈ ó��   
      if(me.getClickCount()==2){
         
         //����Ŭ���� ���� ���� �޼��� â�� ���
         if(chkNum==1){//sendMsg
            JTable temp=mlv.getJtSendMsgList();
            int selectedRow=temp.getSelectedRow();
            MsgViewVO mvvo=new MsgViewVO();
            try { //c_dao �� �޼����ڵ带 �־� �� �ҷ�����
               mvvo=c_dao.selectSendMsgInfo((String)temp.getValueAt(selectedRow, 0));
            } catch (SQLException e1) {
               e1.printStackTrace();
            }//end catch
            
            msg=mvvo.getMsg();
            msg_id=mvvo.getSendId();
            msg_title=msg_id+" ���� ���� �޼���";
            item_code=(String)temp.getValueAt(selectedRow, 2);
            
            try { //c_dao �� �޼��� �ڵ带 �־� �� �ҷ�����
               m_dao.updateChkSendMsg((String)temp.getValueAt(selectedRow, 0));
            } catch (SQLException e) {
               e.printStackTrace();
            }//end catch
            new MsgReadView(mvvo);
      
         //���� Ŭ���� â�� ���� �޼��� â�� ���   
         }else if(chkNum==2){//recieveMsg
            JTable temp=mlv.getJtReceiveMsgList();
            int selectedRow=temp.getSelectedRow();
            MsgViewVO mvvo=new MsgViewVO();
            
            try { 
               mvvo=c_dao.selectReceiveMsgInfo((String)temp.getValueAt(selectedRow, 0));
            } catch (SQLException e1) {
               e1.printStackTrace();
            }//end catch
            
            msg=mvvo.getMsg();
            msg_id=mvvo.getSendId();
            msg_title=msg_id+" �� ���� �޼���";
            item_code=(String)temp.getValueAt(selectedRow, 2);
            
            try { //����Ŭ���Ͽ� �а� �Ǿ��� ��� �о��ٴ� ǥ�� flag=Y
               m_dao.updateChkGetMsg((String)temp.getValueAt(selectedRow, 0));
            } catch (SQLException e) {
               e.printStackTrace();
            }//end catch
            
            new MsgReadView(mvvo);
            
         }//end else
      }//end if
      }//end catch
      
   }//mouseClicked

   @Override
   public void actionPerformed(ActionEvent ae) {
      if(ae.getSource()==mlv.getJbSClose()){
         mlv.dispose();
      }//end if
      
      if(ae.getSource()==mlv.getJbRClose()){
         mlv.dispose();
      }//end if
      
   }//actionPerformed
   
}//class