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

public class MsgListViewEvt extends MouseAdapter implements ActionListener {
	private MsgListView mlv;
	private CustomerDAO c_dao;
	private MarketDAO m_dao;
	private LoginViewEvt lve;
	private int chkNum;
	
	public MsgListViewEvt(MsgListView mlv){
		this.mlv=mlv;
		c_dao=CustomerDAO.getInstance();
		m_dao=MarketDAO.getInstance();
		
		//메세지를 조회한다
		String id=lve.id;
		setSendMsg(id);
		setRecieveMsg(id);
	}//MsgListViewEvt
	
	public void setSendMsg(String id){
		chkNum=1;
		DefaultTableModel dtmMsg=mlv.getDtmSendMsgList();
		
		try{
		List<MsgListVO> lstMsg=m_dao.selectSendMsgList(id);
		Object[] rowData=null;
		MsgListVO mlv=null;
		
		dtmMsg.setRowCount(0);
		
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

	public void setRecieveMsg(String id){
		chkNum=2;
		DefaultTableModel dtmMsg=mlv.getDtmReceiveMsgList();
		
		try{
		List<MsgListVO> lstMsg=m_dao.selectGetMsgList(id);
		Object[] rowData=null;
		MsgListVO mlv=null;
		
		dtmMsg.setRowCount(0);
		
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
	
	public void setRecieveMsg(){
		
	}//setMsg
	
	
	
	@Override
	public void mouseClicked(MouseEvent me) {
		
		if(me.getClickCount()==2){
			JOptionPane.showMessageDialog(null, chkNum);
			System.out.println(chkNum);
			if(chkNum==1){//sendMsg
				JTable temp=mlv.getJtSendMsgList();
				int selectedRow=temp.getSelectedRow();
				MsgListVO mlv=new MsgListVO();
				mlv.setMsgCode((String)temp.getValueAt(selectedRow, 0));
				JOptionPane.showMessageDialog(null, "메세지 탄다");
				new MsgReadView();
			}else if(chkNum==2){//recieveMsg
				JTable temp=mlv.getJtReceiveMsgList();
				int selectedRow=temp.getSelectedRow();
				MsgListVO mlv=new MsgListVO();
				mlv.setMsgCode((String)temp.getValueAt(selectedRow, 0));
				
				JOptionPane.showMessageDialog(null, "메세지 탄다2222");
				new MsgReadView();
				
			}//end else
		}//end if
	}//couseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mlv.getJbSClose()){
			mlv.dispose();
		}
		if(ae.getSource()==mlv.getJbRClose()){
			mlv.dispose();
		}
	}
	
}
