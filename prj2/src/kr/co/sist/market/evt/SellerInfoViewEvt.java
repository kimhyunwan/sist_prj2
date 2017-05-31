package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.BuyReqView;
import kr.co.sist.market.view.ItemInfoView;
import kr.co.sist.market.view.MsgWriteView;
import kr.co.sist.market.view.SellerInfoView;
import kr.co.sist.market.vo.SellerInfoVO;

public class SellerInfoViewEvt extends WindowAdapter implements ActionListener {
	private SellerInfoView siv;
	private ItemInfoView iiv;
	private MarketDAO m_dao;
	private LoginViewEvt lve;
	private String id;
	
	public SellerInfoViewEvt(SellerInfoView siv,String id){
		this.siv=siv;
		this.id=id;
		
	}//SellerInfoViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==siv.getJbMsg()){
			
			m_dao=MarketDAO.getInstance();
			SellerInfoVO seller=new SellerInfoVO();
			
			System.out.println("1�α��� ���̵�:"+lve.id);
			System.out.println("1��ǰ�Ǹ��� ���̵�:"+id);
			System.out.println(lve.id.equals(id));
			
				if(!(lve.id.equals(id))){ //�α��� ���̵�� �Ǹ��� ���̵� ���� ���, ���Ž�û �Ұ�
					//new MsgWriteView();
				}else{
					JOptionPane.showMessageDialog(siv, "�ؾ˸���\n���ο��� �޼����� ���� �� �����ϴ�.");
				}//end if
		}//end if
		
		if(ae.getSource()==siv.getJbBuyReq()){
			m_dao=MarketDAO.getInstance();
			SellerInfoVO seller=new SellerInfoVO();
			System.out.println("2�α��� ���̵�:"+lve.id);
			System.out.println("2��ǰ�Ǹ��� ���̵�:"+id);
			System.out.println(lve.id.equals(id));
			try{
				if(!(lve.id.equals(id))){ //�α��� ���̵�� �Ǹ��� ���̵� ���� ���, ���Ž�û �Ұ�
					new BuyReqView(iiv);
				}else{
					JOptionPane.showMessageDialog(siv, "�ؾ˸���\n������ ����� ��ǰ�� ���� ���Ž�û�� �� �� �����ϴ�.");
				}//end if
			}catch(NullPointerException ne){
				ne.getStackTrace();
			}
		
		}//end if
	}
}
