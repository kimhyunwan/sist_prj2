package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.SellListView;
import kr.co.sist.market.vo.SellBuyVO;

public class SellListViewEvt extends MouseAdapter implements ActionListener {
	private SellListView slv;
	private MarketDAO m_dao;
	LoginViewEvt lve;
	int chkNum1=0;
	int chkNum2=0;
	
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
			String id=lve.id; //�������� ID
			//String id="wkdwogns"; //�������� ID
			List<SellBuyVO> lstItem1=m_dao.selectSellCompList(id);
			//"������", "��ȣ", "��ǰ��" , "�ǸſϷ��Ͻ�"
			
			Object[] rowItem1=new Object[5];
			
			//DefaultTableModel�� �޾ƿ;��Ѵ�.
			DefaultTableModel dtmItem1=slv.getDtmComplet(); //SellListViewŬ�������� dtm��ü�� ������
			
			SellBuyVO sbv=null;
			
			dtmItem1.setRowCount(0);
			
			for(int i=0; i<lstItem1.size(); i++){
				//SellBuyVO //String id, itemCode, itemName, tradeDate;
				sbv=lstItem1.get(i); //����Ʈ�޴��� ���� �����ͼ� sbv�� �������
				rowItem1[0]=(i+1);
				rowItem1[1]=sbv.getId();
				rowItem1[2]=sbv.getItemCode();
				rowItem1[3]=sbv.getItemName();
				rowItem1[4]=sbv.getTradeDate();
				
				dtmItem1.addRow(rowItem1);
			}//end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(slv, "�˼��մϴ�. �ǸſϷ����� �ҷ��� �� �����ϴ�.\n����� �ٽ� �õ����ּ���.");
			e.printStackTrace();
		} catch (NullPointerException npe){
			JOptionPane.showMessageDialog(slv, "�ǸŰ� �Ϸ�� ��ǰ�� �����ϴ�.");
		}
	}//setSellList()
	
///////////////////////////////////////////�ǸŴ�� ����� ���� method////////////////////////////////////
	public void setSellListWait(){
		try {
			String id=lve.id; //�������� ID
			//String id="dongha"; //�������� ID
			List<SellBuyVO> lstItem2=m_dao.selectBuyWaitList(id);
			//"���Ž�û��", "��ǰ�ڵ�", "��ǰ��" , "��û��"
			
			Object[] rowItem2=new Object[5];
			
			//DefaultTableModel�� �޾ƿ;��Ѵ�.
			DefaultTableModel dtmItem2=slv.getDtmWait(); //SellListViewŬ�������� dtm��ü�� ������
			
			SellBuyVO sbv=null;
			
			dtmItem2.setRowCount(0);
			
			for(int i=0; i<lstItem2.size(); i++){
				//SellBuyVO //String id, itemCode, itemName, tradeDate;
				sbv=lstItem2.get(i); //����Ʈ�޴��� ���� �����ͼ� sbv�� �������
				rowItem2[0]=(i+1);
				rowItem2[1]=sbv.getId();
				rowItem2[2]=sbv.getItemCode();
				rowItem2[3]=sbv.getItemName();
				rowItem2[4]=sbv.getTradeDate();
				
				dtmItem2.addRow(rowItem2);
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
	

	/////////////////////////�ǸŴ�� ��Ͽ��� ��ǰ�� Ŭ������ ��////////////////////////////
	@Override
	public void mouseClicked(MouseEvent me) {
		System.out.println(me.getSource());
//		if(chkNum==1){
//			System.out.println("1111111111111");
//		}else if(chkNum==2){
//			System.out.println("2222222222222");
//		}
//		JTabbedPane jtp=(JTabbedPane)me.getSource();
//		int tabIndex=jtp.getSelectedIndex();
		
//		if(tabIndex==0){
//			System.out.println("�ǸſϷ�â�Դϴ�.");
//		}else if(tabIndex==1){
//			System.out.println("�ǸŴ��â�Դϴ�.");
//		}//end if
		
		if(me.getClickCount()==2){ //����Ŭ���� �ߴٸ� ����
					JTable temp=slv.getJtWait(); //���̺��� ��������
					int selectedRow=temp.getSelectedRow();
		//			MenuVO mv=new MenuVO(); //MenuVO�� ����Ͽ�, ������ �׸��� �̹���,�޴��ڵ�,�޴���,����,������ �����ͼ� [�󼼺���dialogâ]�� ����ٰ��̴�.
		//			mv.setImg( ((ImageIcon)temp.getValueAt(selectedRow, 1)).toString()); 
		//			mv.setItem_code((String) temp.getValueAt(selectedRow, 2)); 
		//			mv.setMenu((String) temp.getValueAt(selectedRow, 3)); 
		//			mv.setInfo((String) temp.getValueAt(selectedRow, 4)); 
		//			mv.setPrice((Integer)temp.getValueAt(selectedRow, 5)); 
					
					int flag=JOptionPane.showConfirmDialog(slv, "[ "+slv.getName()+" ]�� �ֹ��Ͻðڽ��ϱ�?");
					
					switch (flag) {
						case JOptionPane.OK_OPTION:
		//					new OrderForm(mf,mv); //mf�� mv�� �Ǿ ����
							System.out.println("�ǸŽ���â���� �̵��մϴ�!");
						break;
					}//end switch
				}//end if
	}//mouseClicked

}//class
