package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.view.BuyReqView;
import kr.co.sist.market.vo.PhoneVO;

public class BuyReqViewEvt extends WindowAdapter implements ActionListener {
	private BuyReqView brv;
	
	public BuyReqViewEvt(BuyReqView brv){
		this.brv=brv;
	}//BuyReqViewEvt
	
	private void setPhone(){
		//BuyReqView에서 id, phone, itemCode를 가지고 온다.
		String id = brv.getJtfId().getText();
		String phone = brv.getPhone().getSelectedItem().toString()+brv.getJtfPhone2().getText()+brv.getJtfPhone3().getText();
		String itemCode = brv.getJtfItemcode().getText();
		
		CustomerDAO cd = CustomerDAO.getInstance();
		PhoneVO phv = new PhoneVO(id, phone, itemCode);
		
		try {
			cd.insertPhone(phv);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//setPhone
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==brv.getJbCancel()){
			brv.dispose();
		}//end if
		
		if(ae.getSource()==brv.getJbBuyReq()){
			//구매 신청버튼을 눌렀을 때 setItem()함수 호출
			setPhone();
		}
	}
	
}
