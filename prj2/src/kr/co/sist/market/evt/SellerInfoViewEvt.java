package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.JOptionPane;
import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.BuyReqView;
import kr.co.sist.market.view.MsgWriteView;
import kr.co.sist.market.view.SellerInfoView;
import kr.co.sist.market.vo.MsgVO;
import kr.co.sist.market.vo.ReqVO;

public class SellerInfoViewEvt extends WindowAdapter implements ActionListener {
	private SellerInfoView siv;
	private MarketDAO m_dao;
	private LoginViewEvt lve;
	private ReqVO rv;
	
	public SellerInfoViewEvt(SellerInfoView siv){
		this.siv=siv;
		
	}//SellerInfoViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==siv.getJbMsg()){
			
			m_dao=MarketDAO.getInstance();
			String id=lve.id;
			String sendId=siv.getJtfId().getText().trim();
			String itemCode=siv.getItemCode().trim();
			MsgVO mv=new MsgVO(sendId, id, "", itemCode);
			
				if(!(id.equals(sendId))){ //�α��� ���̵�� �Ǹ��� ���̵� ���� ���, ���Ž�û �Ұ�
					new MsgWriteView(mv);
				}else{
					JOptionPane.showMessageDialog(siv, "�ؾ˸���\n���ο��� �޼����� ���� �� �����ϴ�.");
				}//end if
		}//end if
		
		if(ae.getSource()==siv.getJbBuyReq()){
			m_dao=MarketDAO.getInstance();
			String id=lve.id;
			String seller=siv.getJtfId().getText().trim();
			String itemCode=siv.getItemCode();
			int price=siv.getPrice();
			rv=new ReqVO(id, itemCode, price);
			try{
				if(!(id.equals(seller))){ //�α��� ���̵�� �Ǹ��� ���̵� ���� ���, ���Ž�û �Ұ�
					new BuyReqView(rv);
				}else{
					JOptionPane.showMessageDialog(siv, "�ؾ˸���\n������ ����� ��ǰ�� ���� ���Ž�û�� �� �� �����ϴ�.");
				}//end if
			}catch(NullPointerException ne){
				ne.getStackTrace();
			}
		
		}//end if
	}
}
