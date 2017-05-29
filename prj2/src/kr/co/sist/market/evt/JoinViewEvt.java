package kr.co.sist.market.evt;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.view.JoinView;
import kr.co.sist.market.vo.MemberJoinVO;

public class JoinViewEvt extends WindowAdapter implements ActionListener {
	private JoinView jv;
	private CustomerDAO cd;
	
	public JoinViewEvt(JoinView jv){
		this.jv=jv;
		cd=CustomerDAO.getInstance();
		
		DefaultComboBoxModel<String> dcbm=jv.getDcbmQu();
		
		try {
			List<String> listQu=cd.selectPassQu();
			
			for(String qu : listQu){
				dcbm.addElement(qu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//JoinViewEvt
	
	private void addImg(){
		FileDialog fdImg=new FileDialog(jv, "�����ʻ��� ����", FileDialog.LOAD);
		fdImg.setVisible(true);
		
		String path=fdImg.getDirectory();
		String file=fdImg.getFile();
		if(file !=null){
			String validFile="jpg, gif, png, bmp";
			if(!validFile.contains(file.substring(file.lastIndexOf(".")+1))){
				JOptionPane.showMessageDialog(jv, "�����Ͻ� ������ �̹����� �ƴմϴ�.");
				return;
			}//end if
			
			ImageIcon temp=new ImageIcon(path+file);
			jv.getJlimg().setIcon(temp);
		}//end if
	}//addImg
	
	private void signUpMember(){
		ImageIcon icon=(ImageIcon)jv.getJlimg().getIcon();
		
		File file=new File(icon.toString());
		String ssnBack=new String(jv.getJpwSsn().getPassword()).trim();
		String tempFile=file.getName();
		String name=jv.getJtfName().getText().trim();
		String ssn=jv.getJtfSsn().getText().trim()+ssnBack;
		String id=jv.getJtfId().getText().trim();
		String pass=new String(jv.getJpwPass().getPassword()).trim();
		
		String passChk=new String(jv.getJpwPassChk().getPassword()).trim();
		int quNum=jv.getJcbQuest().getSelectedIndex()+1;
		String answer=jv.getJtfAnswer().getText().trim();
		String info=jv.getJtaIntro().getText().trim();
		
		MemberJoinVO mjv=new MemberJoinVO();
		cd=CustomerDAO.getInstance();
		
		try {
			if(pass.equals(passChk)){
				mjv.setImage(tempFile);
				mjv.setName(name);
				mjv.setSsn(ssn);
				mjv.setId(id);
				mjv.setPass(pass);
				mjv.setQuNum(quNum);
				mjv.setPassAnswer(answer);
				mjv.setInfo(info);
				
				cd.insertMember(mjv);
				JOptionPane.showMessageDialog(jv, "������ �����մϴ�.");
			}else{
				JOptionPane.showMessageDialog(jv, "�Էµ� ��й�ȣ�� �ٸ��ϴ�.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(jv, "���̵� �ߺ��Ǿ��ų� ������ �ùٸ��� �ʽ��ϴ�.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==jv.getJbCancel()){
			jv.dispose();
		}//end if

		if(ae.getSource()==jv.getJbImage()){
			addImg();
		}//end if
		
		if(ae.getSource()==jv.getJbSignUp()){
			signUpMember();
		}//end if
	}
}
