package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.BuyReqView;
import kr.co.sist.market.view.ItemInfoView;
import kr.co.sist.market.view.SellerInfoView;
import kr.co.sist.market.vo.SellerInfoVO;

public class ItemInfoViewEvt extends WindowAdapter implements ActionListener {
	private ItemInfoView iiv;
	private SellerInfoVO seller;
	private MarketDAO md;
	
	public ItemInfoViewEvt(ItemInfoView iiv){
		this.iiv=iiv;
	}//ItemInfoViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==iiv.getJbSellerInfo()){
			md=MarketDAO.getInstance();
			SellerInfoVO seller=new SellerInfoVO();
			
			try {
				seller=md.selectSellerInfo(iiv.getJtfItemcode().getText().trim());
				new SellerInfoView(seller);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}//end if
		if(ae.getSource()==iiv.getJbBuyReq()){
			new BuyReqView();
		}//end if
		if(ae.getSource()==iiv.getJbCancel()){
			iiv.dispose();
		}//end if
	}
}
