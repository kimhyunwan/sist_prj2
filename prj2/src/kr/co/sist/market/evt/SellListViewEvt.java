package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.SellListView;
import kr.co.sist.market.vo.SellBuyVO;

public class SellListViewEvt extends WindowAdapter implements ActionListener {
	private SellListView slv;
	private MarketDAO m_dao;
	LoginViewEvt lve;
	
	public SellListViewEvt(SellListView slv){
		this.slv=slv;
		m_dao=MarketDAO.getInstance(); //�ǸŸ���Ʈ���̺��� �������� ����
		//�ǸſϷḮ��Ʈ�� ��ȸ�Ͽ� �����Ѵ�.
		setSellListComp();
		//�ǸŴ�⸮��Ʈ�� ��ȸ�Ͽ� �����Ѵ�.
		setSellListWait();
	}//SellListViewEvt

	///////////////////////////////////////////�ǸſϷ� ����� ���� method////////////////////////////////////
	public void setSellListComp(){
		try {
//			String id=lve.id; //�������� ID
			String id="wkdwogns"; //�������� ID
			List<SellBuyVO> lstItem=m_dao.selectSellCompList(id);
			//"������", "��ȣ", "��ǰ��" , "�ǸſϷ��Ͻ�"
			
			Object[] rowItem=new Object[5];
			
			//DefaultTableModel�� �޾ƿ;��Ѵ�.
			DefaultTableModel dtmItem=slv.getDtmComplet(); //SellListViewŬ�������� dtm��ü�� ������
			
			SellBuyVO sbv=null;
			
			dtmItem.setRowCount(0);
			
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
			JOptionPane.showMessageDialog(slv, "�˼��մϴ�. �ǸſϷ����� �ҷ��� �� �����ϴ�.\n����� �ٽ� �õ����ּ���.");
			e.printStackTrace();
		}
	}//setSellList()
	
///////////////////////////////////////////�ǸŴ�� ����� ���� method////////////////////////////////////
	public void setSellListWait(){
		try {
			String id="dongha"; //�������� ID
			List<SellBuyVO> lstItem=m_dao.selectBuyWaitList(id);
			//"���Ž�û��", "��ǰ�ڵ�", "��ǰ��" , "��û��"
			
			Object[] rowItem=new Object[5];
			
			//DefaultTableModel�� �޾ƿ;��Ѵ�.
			DefaultTableModel dtmItem=slv.getDtmWait(); //SellListViewŬ�������� dtm��ü�� ������
			
			SellBuyVO sbv=null;
			
			//dtmItem.setRowCount(0);
			
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
			JOptionPane.showMessageDialog(slv, "�˼��մϴ�. �ǸŴ������ �ҷ��� �� �����ϴ�.\n����� �ٽ� �õ����ּ���.");
			e.printStackTrace();
		}
	}//setSellList()
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==slv.getJbClose()){
			slv.dispose();
		}//end if
		if(ae.getSource()==slv.getJbWClose()){
			slv.dispose();
		}//end if
	}
	
}
