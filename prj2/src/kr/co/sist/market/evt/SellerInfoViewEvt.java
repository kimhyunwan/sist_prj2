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
			
			System.out.println("1로그인 아이디:"+lve.id);
			System.out.println("1물품판매자 아이디:"+id);
			System.out.println(lve.id.equals(id));
			
				if(!(lve.id.equals(id))){ //로그인 아이디와 판매자 아이디가 같은 경우, 구매신청 불가
					//new MsgWriteView();
				}else{
					JOptionPane.showMessageDialog(siv, "※알림※\n본인에게 메세지를 보낼 수 없습니다.");
				}//end if
		}//end if
		
		if(ae.getSource()==siv.getJbBuyReq()){
			m_dao=MarketDAO.getInstance();
			SellerInfoVO seller=new SellerInfoVO();
			System.out.println("2로그인 아이디:"+lve.id);
			System.out.println("2물품판매자 아이디:"+id);
			System.out.println(lve.id.equals(id));
			try{
				if(!(lve.id.equals(id))){ //로그인 아이디와 판매자 아이디가 같은 경우, 구매신청 불가
					new BuyReqView(iiv);
				}else{
					JOptionPane.showMessageDialog(siv, "※알림※\n본인이 등록한 상품에 대해 구매신청을 할 수 없습니다.");
				}//end if
			}catch(NullPointerException ne){
				ne.getStackTrace();
			}
		
		}//end if
	}
}
