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
            int flag = JOptionPane.showConfirmDialog(mlv, "������ Ż���Ͻðڽ��ϱ�?");
            
            switch (flag) {
            case JOptionPane.OK_OPTION:
                c_dao= CustomerDAO.getInstance();
                try {
                	if(c_dao.deleteCustomer(id, pass) != 0){
                		mlv.dispose();
                		mv.dispose();
                		new LoginView();
                	}else{
                		JOptionPane.showMessageDialog(mlv, "�߸��� ��й�ȣ �Դϴ�. �ٽ� �Է����ּ���.");
                		return;
                	}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(mlv, "�ùٸ� ��й�ȣ�� �Է����ּ���.");
					e.printStackTrace();
				}
               break;
            }// end switch
		}
		
	}//actionPerformed

}
