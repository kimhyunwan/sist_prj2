package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.BuyReqView;
import kr.co.sist.market.view.ItemInfoView;
import kr.co.sist.market.view.SellerInfoView;
import kr.co.sist.market.vo.PhoneVO;
import kr.co.sist.market.vo.ReqVO;
import kr.co.sist.market.vo.SellerInfoVO;

public class ItemInfoViewEvt extends WindowAdapter implements ActionListener {
	private ItemInfoView iiv;
	private SellerInfoVO seller;
	private MarketDAO md;
	private LoginViewEvt lve;
	private ReqVO rv;
	
	public ItemInfoViewEvt(ItemInfoView iiv){
		this.iiv=iiv;
	}//ItemInfoViewEvt
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==iiv.getJbSellerInfo()){
			md=MarketDAO.getInstance();
			SellerInfoVO seller=new SellerInfoVO();
			String id=lve.id;
			String itemCode=iiv.getJtfItemcode().getText().trim();
			int price=Integer.parseInt(iiv.getJtfPrice().getText().trim());
			rv=new ReqVO(id, itemCode, price);
			try {
				seller=md.selectSellerInfo(iiv.getJtfItemcode().getText().trim());
				new SellerInfoView(seller, rv);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}//end if
		if(ae.getSource()==iiv.getJbBuyReq()){
			md=MarketDAO.getInstance();
			SellerInfoVO seller=new SellerInfoVO();
			String id=lve.id;
			String itemCode=iiv.getJtfItemcode().getText().trim();
			int price=Integer.parseInt(iiv.getJtfPrice().getText().trim());
			System.out.println(id+" "+itemCode+" "+price);
			rv=new ReqVO(id, itemCode, price);
			/////SellerInfoVO///////
			//String id, info, img
			try {
				seller=md.selectSellerInfo(iiv.getJtfItemcode().getText().trim());
				if(!(lve.id.equals(seller.getId()))){ //로그인 아이디와 판매자 아이디가 같은 경우, 구매신청 불가
					new BuyReqView(rv);
				}else{
					JOptionPane.showMessageDialog(iiv, "본인이 등록한 상품입니다.");
				}//end if
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end if
		if(ae.getSource()==iiv.getJbCancel()){
			iiv.dispose();
		}//end if
	}
}
