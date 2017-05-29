package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.BuyListView;
import kr.co.sist.market.vo.SellBuyVO;

public class BuyListViewEvt extends WindowAdapter implements ActionListener {
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
//			String id="hyunwan"; //�������� ID
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
	
}
