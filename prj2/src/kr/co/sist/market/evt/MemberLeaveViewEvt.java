package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.view.LoginView;
import kr.co.sist.market.view.MainView;
import kr.co.sist.market.view.MemberLeaveView;


public class MemberLeaveViewEvt extends WindowAdapter implements ActionListener {
	private MemberLeaveView mlv;
	private MainView mv;
	private CustomerDAO c_dao;
	
	public MemberLeaveViewEvt(MemberLeaveView mlv, MainView mv){
		this.mlv=mlv;
		this.mv=mv;
	}//MemberLeaveViewEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mlv.getJbMemberLeave()){
            String id = mlv.getJtfId().getText();
			String pass= new String(mlv.getJpwPass().getPassword()).trim();
            int flag = JOptionPane.showConfirmDialog(mlv, "정말로 탈퇴하시겠습니까?");
            
            switch (flag) {
            case JOptionPane.OK_OPTION:
                c_dao= CustomerDAO.getInstance();
                try {
                	if(c_dao.deleteCustomer(id, pass) != 0){
                		mlv.dispose();
                		mv.dispose();
                		new LoginView();
                	}else{
                		JOptionPane.showMessageDialog(mlv, "잘못된 비밀번호 입니다. 다시 입력해주세요.");
                		return;
                	}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(mlv, "올바른 비밀번호를 입력해주세요.");
					e.printStackTrace();
				}
               break;
            }// end switch
		}
		
	}//actionPerformed

}
