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

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.dao.MarketDAO;

import kr.co.sist.market.view.BuyListView;
import kr.co.sist.market.view.ItemInfoView;
import kr.co.sist.market.view.MainView;
import kr.co.sist.market.view.MsgListView;
import kr.co.sist.market.view.MyInfoChView;
import kr.co.sist.market.view.SellListView;
import kr.co.sist.market.vo.ItemListVO;


public class MainViewEvt extends MouseAdapter implements ActionListener {
	private MainView mv;
	private MarketDAO m_dao;
	private CustomerDAO c_dao;
	
	
	LoginViewEvt lve;
	public MainViewEvt(MainView mv){
		this.mv=mv;
		m_dao=MarketDAO.getInstance();
		
		
	}//MainViewEvtEvt
	
	private void setItem(int item){
		DefaultTableModel dtmItem = mv.getDtmItem();
		try {
			List<ItemListVO> lstItem = m_dao.selectItemList(item);
			Object[] rowData=null;
			ItemListVO iv = null;
			
			dtmItem.setRowCount(0); //for���� ������, ���� ��� ���̰� �ȴٴ� ������ �ذ��ϱ� ���� �̰Ÿ� ���ش�.
			
			for(int i=0;i<lstItem.size();i++){
				iv = lstItem.get(i);
				rowData = new Object[6]; //��ȣ,�÷���,��������,ũ�� �̷��� �� 6��
				rowData[0]=i+1;
				rowData[1] = iv.getItemName();
				rowData[2]=iv.getItemCode();
				rowData[3]=iv.getItemInfo();
				rowData[4]=iv.getPrice();
				rowData[5]=iv.getHiredate();
				
				dtmItem.addRow(rowData);
			}//end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(mv, "���񽺰� �������� ���մϴ�.");
			e.printStackTrace();
		}//end catch 
	}//setItem
	
	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getClickCount()==2){
			JTable temp = mv.getJtItemList();
			int selectedRow = temp.getSelectedRow();
			ItemListVO iv = new ItemListVO();
			iv.setItemName((String) temp.getValueAt(selectedRow, 2));
/*			iv.setMenu((String) temp.getValueAt(selectedRow, 3));
			iv.setInfo((String) temp.getValueAt(selectedRow, 4));
			iv.setPrice((Integer) temp.getValueAt(selectedRow, 5));*/
			
			new ItemInfoView(mv,iv);//����־���� �׸���� mv�� �����������Ƿ� �ȿ� �־��ִ� ���̴�.
		}//end if
	}//mouseClicked
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mv.getJbMyInfoCh()){
			c_dao=CustomerDAO.getInstance();
			try {
				new MyInfoChView(c_dao);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end if
		if(ae.getSource()==mv.getJbSellList()){
			new SellListView();
		}//end if
		if(ae.getSource()==mv.getJbBuyList()){
			new BuyListView();
		}//end if
		if(ae.getSource()==mv.getJbMsgList()){
			new MsgListView();
		}//end if
		if(ae.getSource()==mv.getJbType()){
			int item=mv.getJcbType().getSelectedIndex();
			setItem(item);
		}//end if
		

	}

}
