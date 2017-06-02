package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.view.MemberLeaveView;


public class MemberLeaveViewEvt extends WindowAdapter implements ActionListener {
	private MemberLeaveView mlv;
	private CustomerDAO c_dao;
	
	public MemberLeaveViewEvt(MemberLeaveView mlv){
		this.mlv=mlv;
	}//MemberLeaveViewEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mlv.getJbMemberLeave()){
            String id = mlv.getJtfId().getText();
            String pass = mlv.getJpwPass().getText();
            int flag = JOptionPane.showConfirmDialog(mlv, "정말로 탈퇴하시겠습니까?");
            
            switch (flag) {
            case JOptionPane.OK_OPTION:
                c_dao= CustomerDAO.getInstance();
                try {
					c_dao.deleteCustomer(id, pass);
				} catch (SQLException e) {
					e.printStackTrace();
				}
               break;
            }// end switch
		}
		
	}//actionPerformed

}
