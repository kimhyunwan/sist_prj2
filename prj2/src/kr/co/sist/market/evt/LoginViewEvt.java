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
		
		if(id.isEmpty()){ //ID�Է¶��� ����ִ� ���
			JOptionPane.showMessageDialog(lv, "���̵� �Է��ϼ���");
			lv.getJtfId().requestFocus(); //ID�Է¶����� Ŀ�� �̵�
			return;//�Ʒ����� �������� �ʰ� ȣ���� ������ ����������
		} else{
			//��й�ȣ�� �Էµ��� ������� ��й�ȣ�� �Է��϶�� �޼����� ���.
			if(pass.isEmpty()){
				JOptionPane.showMessageDialog(lv, "��й�ȣ�� �Է��ϼ���");
				lv.getJpwPass().requestFocus(); //PASSWORD�Է¶����� Ŀ�� �̵�
			}else{
				
		LoginViewEvt logid= new LoginViewEvt(lv);

		
		boolean flag=false;
		//������ ID : dongha, password : diet
		lvo=new LoginVO();
		cd=CustomerDAO.getInstance();
		
		lvo.setId(id);
		lvo.setPass(pass);
		
		try {
			boolean chklog=cd.selectLogin(lvo);
			if(chklog){
			JOptionPane.showMessageDialog(lv, "�߰����Ϳ� ���� ���� ȯ���մϴ�!");
			logid.id=id;
			flag=true;
			new MainView(cd);
			}else{
				JOptionPane.showMessageDialog(lv, "���Գ����� �������� �ʽ��ϴ�.");
				new LoginView();
			}//end else
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		lv.setFlag(flag,1); //�α��� ����� ����
		lv.dispose(); //�α��� â�� �ݾ� �������� �����ϵ��� �����
			}//end else
		}//end if
	}//chkNull
	
	/**
	 * ID�� �Է¹޴� ���� �ϴ� �޼ҵ�<br>
	 * ID�� �Էµ��� ���� ���, ���̵� �Է��϶�� �޽���â�� ����<br>
	 * ID�� �Էµ� ���, PASSWORD�� �Է¹ޱ����� ��Ŀ���� �Ű��ش�.
	 */
	public void writeId(){
		String id=lv.getJtfId().getText().trim();
		//ID�� �Էµ��� ������� ���̵� �Է��϶�� �޼���â�� ����ش�.
		if(id.isEmpty()){ //ID�Է��� ���� �ʾ��� ���
			JOptionPane.showMessageDialog(lv, "���̵� �Է��ϼ���");
		//id�� �ԷµǾ��� ��� ���͸� ġ�� ��й�ȣĭ���� �ѱ��.
		} else{ //ID�Է��� ���� ��� ����
				lv.getJpwPass().requestFocus(); //PASSWORD�� �Է¹ޱ����� Ŀ���� �Ű��ش�.
		}//end if
		
	}//writeId
	
	
	/**
	 *	LOGIN�� �������� Ȯ���ϴ� method<br>
	 */
	public void checkLogin(){
		
		String id=lv.getJtfId().getText().trim();
		String pass=new String(lv.getJpwPass().getPassword());
		
		if(id.isEmpty()){ //ID�Է¶��� ����ִ� ���
			JOptionPane.showMessageDialog(lv, "���̵� �Է��ϼ���");
			lv.getJtfId().requestFocus(); //ID�Է¶����� Ŀ�� �̵�
			return;//�Ʒ����� �������� �ʰ� ȣ���� ������ ����������
		} else{
				//��й�ȣ�� �Էµ��� ������� ��й�ȣ�� �Է��϶�� �޼����� ���.
				if(pass.isEmpty()){
					JOptionPane.showMessageDialog(lv, "��й�ȣ�� �Է��ϼ���");
					lv.getJpwPass().requestFocus(); //PASSWORD�Է¶����� Ŀ�� �̵�
			}//end if
		}//end if
	}//
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==lv.getJbLogin()||ae.getSource()==lv.getJpwPass()||ae.getSource()==lv.getJtfId()){
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
