package kr.co.sist.market.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.view.FindIdView;
import kr.co.sist.market.view.JoinView;
import kr.co.sist.market.view.LoginView;
import kr.co.sist.market.view.MainView;
import kr.co.sist.market.vo.LoginVO;

/**
 * �α��� �̺�Ʈ - ������
 * @author user
 *
 */
public class LoginViewEvt extends WindowAdapter implements ActionListener {
	private LoginView lv;
	private LoginVO lvo;
	private CustomerDAO cd;
	public static String id="";
	
	public LoginViewEvt(LoginView lv){
		this.lv=lv;
	}//LoginViewEvt
	
	private void chkNull(){
		String id=lv.getJtfId().getText().trim();
		String pass=new String(lv.getJpwPass().getPassword()).trim();
		LoginViewEvt logid= new LoginViewEvt(lv);
		if(id.equals("")||pass.equals("")){
			JOptionPane.showMessageDialog(lv, "���̵�� ��й�ȣ�� �Է��� �ּ���");
			return;
		}//end if
		
		boolean flag=false;
		//������ ID : dongha, password : diet
		lvo=new LoginVO();
		cd=CustomerDAO.getInstance();
		
		lvo.setId(id);
		lvo.setPass(pass);
		
		try {
			boolean chklog=cd.selectLogin(lvo);
			if(chklog){
			JOptionPane.showMessageDialog(lv, "��ſ� �ð� �Ǽ���");
			logid.id=id;
			flag=true;
			new MainView(cd);
			}else{
				JOptionPane.showMessageDialog(lv, "���Գ����� �����ϴ�.");
				new LoginView();
			}//end else
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		lv.setFlag(flag,1); //�α��� ����� ����
		lv.dispose(); //�α��� â�� �ݾ� �������� �����ϵ��� �����
	}//chkNull
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==lv.getJbLogin()){
			chkNull();
		}//end if
		
		if(ae.getSource()==lv.getJbJoin()){
			new JoinView();
		}//end if
		
		if(ae.getSource()==lv.getJbFind()){
				
			new FindIdView();
		}//end if
		
	}//actionPerformed

}
