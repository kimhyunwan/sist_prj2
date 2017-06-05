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
 * 로그인 이벤트 - 장재훈
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
		
		if(id.isEmpty()){ //ID입력란이 비어있는 경우
			JOptionPane.showMessageDialog(lv, "아이디를 입력하세요");
			lv.getJtfId().requestFocus(); //ID입력란으로 커서 이동
			return;//아랫줄을 실행하지 않고 호출한 곳으로 돌려보낸다
		} else{
			//비밀번호가 입력되지 않을경우 비밀번호를 입력하라는 메세지가 뜬다.
			if(pass.isEmpty()){
				JOptionPane.showMessageDialog(lv, "비밀번호를 입력하세요");
				lv.getJpwPass().requestFocus(); //PASSWORD입력란으로 커서 이동
			}else{
				
		LoginViewEvt logid= new LoginViewEvt(lv);

		
		boolean flag=false;
		//관리자 ID : dongha, password : diet
		lvo=new LoginVO();
		cd=CustomerDAO.getInstance();
		
		lvo.setId(id);
		lvo.setPass(pass);
		
		try {
			boolean chklog=cd.selectLogin(lvo);
			if(chklog){
			JOptionPane.showMessageDialog(lv, "중고장터에 오신 것을 환영합니다!");
			logid.id=id;
			flag=true;
			new MainView(cd);
			}else{
				JOptionPane.showMessageDialog(lv, "가입내용이 존재하지 않습니다.");
				new LoginView();
			}//end else
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		lv.setFlag(flag,1); //로그인 결과를 설정
		lv.dispose(); //로그인 창을 닫아 다음으로 진행하도록 만든다
			}//end else
		}//end if
	}//chkNull
	
	/**
	 * ID를 입력받는 일을 하는 메소드<br>
	 * ID가 입력되지 않은 경우, 아이디를 입력하라는 메시지창을 띄우고<br>
	 * ID가 입력된 경우, PASSWORD를 입력받기위해 포커스를 옮겨준다.
	 */
	public void writeId(){
		String id=lv.getJtfId().getText().trim();
		//ID가 입력되지 않을경우 아이디를 입력하라는 메세지창을 띄워준다.
		if(id.isEmpty()){ //ID입력을 하지 않았을 경우
			JOptionPane.showMessageDialog(lv, "아이디를 입력하세요");
		//id가 입력되었을 경우 엔터를 치면 비밀번호칸으로 넘긴다.
		} else{ //ID입력을 했을 경우 실행
				lv.getJpwPass().requestFocus(); //PASSWORD를 입력받기위해 커서를 옮겨준다.
		}//end if
		
	}//writeId
	
	
	/**
	 *	LOGIN이 가능한지 확인하는 method<br>
	 */
	public void checkLogin(){
		
		String id=lv.getJtfId().getText().trim();
		String pass=new String(lv.getJpwPass().getPassword());
		
		if(id.isEmpty()){ //ID입력란이 비어있는 경우
			JOptionPane.showMessageDialog(lv, "아이디를 입력하세요");
			lv.getJtfId().requestFocus(); //ID입력란으로 커서 이동
			return;//아랫줄을 실행하지 않고 호출한 곳으로 돌려보낸다
		} else{
				//비밀번호가 입력되지 않을경우 비밀번호를 입력하라는 메세지가 뜬다.
				if(pass.isEmpty()){
					JOptionPane.showMessageDialog(lv, "비밀번호를 입력하세요");
					lv.getJpwPass().requestFocus(); //PASSWORD입력란으로 커서 이동
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
