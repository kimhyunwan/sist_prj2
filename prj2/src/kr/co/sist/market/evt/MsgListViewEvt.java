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
    * 메세지 리스트 창을 통해 
    * 보낸 메세지와 받은 메세지를 탭으로 나우어 확인하게 하고
    * 더블클릭을 통해 MsgReadView로 이동하여
    * 메세지를 확인할 수 있도록 한다
    * @param mlv
    */
   public MsgListViewEvt(MsgListView mlv){
      this.mlv=mlv;
      c_dao=CustomerDAO.getInstance();
      m_dao=MarketDAO.getInstance();
      //메세지를 조회한다
      String id=lve.id;
      
      setSendMsg(id);
      setRecieveMsg(id);
   }//MsgListViewEvt
   
   //////////////////////////////////////////////////////////////////////
   ///////////////보낸 메세지를 확인하는 텝///////////////////////
   //////////////////////////////////////////////////////////////////////
   /**
    * 보낸 메세지를 확인하는 탭.
    * @param id
    */
   public void setSendMsg(String id){
      
      chkNum=1; //보내는 메세지탭을 확인하는 chkNum
      
      DefaultTableModel dtmMsg=mlv.getDtmSendMsgList();
      
      //예외처리를 위하여 try~catch를 사용
      try{
      List<MsgListVO> lstMsg=m_dao.selectSendMsgList(id);
      Object[] rowData=null;
      MsgListVO mlv=null;
      
      dtmMsg.setRowCount(0);
      
      //리스트에 메세지 정보를 담기
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
         JOptionPane.showMessageDialog(mlv, "서비스가 원활하지 못합니다");
         e.printStackTrace();
      }//end catch
      
   }//setMsg

   
   //////////////////////////////////////////////////////////////////////
   ///////////////받은 메세지를 확인하는 텝///////////////////////
   //////////////////////////////////////////////////////////////////////
   /**
    * 받은 메세지를 확인하는 탭
    * @param id
    */
   public void setRecieveMsg(String id){
      DefaultTableModel dtmMsg=mlv.getDtmReceiveMsgList();
      
      try{
      List<MsgListVO> lstMsg=m_dao.selectGetMsgList(id);
      Object[] rowData=null;
      MsgListVO mlv=null;
      
      dtmMsg.setRowCount(0);
      
      //리스트에 메세지 정보를 담기
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
         JOptionPane.showMessageDialog(mlv, "서비스가 원활하지 못합니다");
         e.printStackTrace();
      }//end catch
      
   }//setMsg
   
   
   
   
   //////////////////////////////////////////////////////////////////////
   ///////////////클릭으로 인한 이벤트 처리///////////////////////
   //////////////////////////////////////////////////////////////////////
   @Override
   public void mouseClicked(MouseEvent me) {
      String id=lve.id;
      m_dao=MarketDAO.getInstance();
      
      //예외처리를 위한 try~catch
      try{
         //탭 클릭을 통해 일어나는 인덱스값
         int indexNum=((JTabbedPane)me.getSource()).getSelectedIndex();
         if(indexNum==0){
            //setSendMsg(id);
            chkNum=1; //보내는 메세지 리스트 텝 확인위한 값
         }else if(indexNum==1){
            //setRecieveMsg(id);
            chkNum=2; //받은 메세지 리스트 텝 확인위한 값
         }//end else
      }catch(ClassCastException cce){
         
      //탭 위 메세지를 더블클릭했을 때 이벤트 처리   
      if(me.getClickCount()==2){
         
         //더블클릭한 탭이 보낸 메세지 창일 경우
         if(chkNum==1){//sendMsg
            JTable temp=mlv.getJtSendMsgList();
            int selectedRow=temp.getSelectedRow();
            MsgViewVO mvvo=new MsgViewVO();
            try { //c_dao 에 메세지코드를 넣어 값 불러오기
               mvvo=c_dao.selectSendMsgInfo((String)temp.getValueAt(selectedRow, 0));
            } catch (SQLException e1) {
               e1.printStackTrace();
            }//end catch
            
            msg=mvvo.getMsg();
            msg_id=mvvo.getSendId();
            msg_title=msg_id+" 에게 보낸 메세지";
            item_code=(String)temp.getValueAt(selectedRow, 2);
            
            try { //c_dao 에 메세지 코드를 넣어 값 불러오기
               m_dao.updateChkSendMsg((String)temp.getValueAt(selectedRow, 0));
            } catch (SQLException e) {
               e.printStackTrace();
            }//end catch
            new MsgReadView(mvvo);
      
         //더블 클릭한 창이 받은 메세지 창일 경우   
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
            msg_title=msg_id+" 가 보낸 메세지";
            item_code=(String)temp.getValueAt(selectedRow, 2);
            
            try { //더블클릭하여 읽게 되었을 경우 읽었다는 표시 flag=Y
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