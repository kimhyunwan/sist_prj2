package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.view.BuyReqView;
import kr.co.sist.market.vo.PhoneVO;

public class BuyReqViewEvt extends WindowAdapter implements ActionListener {
	private BuyReqView brv;
	
	public BuyReqViewEvt(BuyReqView brv){
		this.brv=brv;
	}//BuyReqViewEvt
	
	private void setPhone(){
		//BuyReqView���� id, phone, itemCode�� ������ �´�.
		String id = brv.getJtfId().getText();
		String phone = brv.getPhone().getSelectedItem().toString()+brv.getJtfPhone2().getText()+brv.getJtfPhone3().getText();
		String itemCode = brv.getJtfItemcode().getText();
		
		CustomerDAO cd = CustomerDAO.getInstance();
		PhoneVO phv = new PhoneVO(id, phone, itemCode);
		
		try {
			cd.insertPhone(phv);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(brv, "���Ž�û ������ ��� �Է����ּ���.");
			e.printStackTrace();
		}
	}//setPhone
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==brv.getJbCancel()){
			brv.dispose();
		}//end if
		
		if(ae.getSource()==brv.getJbBuyReq()){
			//���� ��û��ư�� ������ �� setItem()�Լ� ȣ��
			if(brv.getJcbPayment().getSelectedIndex()==0){
				JOptionPane.showMessageDialog(brv, "���������� ������ �ּ���");
				return;
			}
			setPhone();
			JOptionPane.showMessageDialog(brv, "���Ž�û�� �Ϸ�Ǿ����ϴ�.");
			brv.dispose();
		}
	}
	
}
