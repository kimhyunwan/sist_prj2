package kr.co.sist.market.evt;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
		String ssnFront=jv.getJtfSsn().getText().trim();
		String tempFile=file.getName();
		String name=jv.getJtfName().getText().trim();
		String ssn=ssnFront+ssnBack;
		String id=jv.getJtfId().getText().trim();
		String pass=new String(jv.getJpwPass().getPassword()).trim();
		
		String passChk=new String(jv.getJpwPassChk().getPassword()).trim();
		int quNum=jv.getJcbQuest().getSelectedIndex()+1;
		String answer=jv.getJtfAnswer().getText().trim();
		String info=jv.getJtaIntro().getText().trim();
		
		MemberJoinVO mjv=new MemberJoinVO();
		cd=CustomerDAO.getInstance();
		
		try {
			if(cd.selectDoubleId(id)){
				JOptionPane.showMessageDialog(jv, "�ߺ��� ���̵��Դϴ�. �ٸ� ���̵�� ������ּ���");
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(!pass.equals(passChk)){
			JOptionPane.showMessageDialog(jv, "��й�ȣȮ���� ����� ���� �ʽ��ϴ�.\n��й�ȣ�� ��й�ȣ Ȯ��â�� �Է°��� ������ Ȯ�����ּ���");
			return;
		}
		
		if(ssnFront.length()!=6||ssnBack.length()!=7){
			JOptionPane.showMessageDialog(jv, "�ֹι�ȣ�� �ùٸ��� �Է����ּ���");
			return;
		}
		
		if(!file.getParent().equals(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/customer")){
	         try {
	        	 //���� Ȯ���� �߶󳻱�
	        	 int pointposition=file.getName().lastIndexOf('.');
	        	 String tailName=file.getName().substring(pointposition);
	            FileInputStream fis=new FileInputStream(file);
	            FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/customer/"+id+tailName);
	            tempFile=id+tailName;
	            byte[] temp=new byte[512];
	            
	            int readData=0;
	            while((readData=fis.read(temp))!=-1){
	               fos.write(temp, 0, readData);
	            }//end while
	            
	            fos.flush();
	            
	            if(fis!=null){
	               fis.close();
	            }//end if
	            
	            if(fos!=null){
	               fos.close();
	            }//end if
	            
	         } catch (FileNotFoundException e) {
	            e.printStackTrace();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	         
	      }//end if
		
		
		try {
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
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(jv, "�Է� ������ �ùٸ��� �ʽ��ϴ�.");
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
			int conf=JOptionPane.showConfirmDialog(jv, "�����Ͻðڽ��ϱ�?");
			
			switch(conf){
			case JOptionPane.OK_OPTION:
				signUpMember();
			}
			
		}//end if
	}
}
