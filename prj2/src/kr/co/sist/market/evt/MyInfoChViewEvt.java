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

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import kr.co.sist.market.dao.CustomerDAO;
import kr.co.sist.market.view.MyInfoChView;
import kr.co.sist.market.vo.ChangeVO;

public class MyInfoChViewEvt extends WindowAdapter implements ActionListener {
	private MyInfoChView micv;
	private CustomerDAO cd;
	private LoginViewEvt lve;
	
	public MyInfoChViewEvt(MyInfoChView micv){
		this.micv=micv;
	}//MyInfoChViewEvt
	
	private void changeInfo(){
		ImageIcon icon=(ImageIcon)micv.getJlItemImage().getIcon();
		
		File file=new File(icon.toString());
		String pass=new String(micv.getJtfPass().getPassword()).trim();
		String passChk=new String(micv.getJtfPassChk().getPassword()).trim();
		String tempFile=file.getName();
		int quNum=micv.getJcbQuest().getSelectedIndex();
		String answer=micv.getJtfAnswer().getText().trim();
		String info=micv.getJtaIntro().getText().trim();
		
		if(!pass.equals(passChk)){
			JOptionPane.showMessageDialog(micv, "��й�ȣ�� Ȯ�����ּ���");
			return;
		}
		
		if(!file.getParent().equals(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/customer")){
	         try {
	        	 //���� Ȯ���� �߶󳻱�
	        	int pointposition=file.getName().lastIndexOf('.');
	         	String tailName=file.getName().substring(pointposition);
	         	tempFile=lve.id+tailName;
	         	FileInputStream fis=new FileInputStream(file);
	         	FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/customer/"+tempFile);
	            
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
		
		ChangeVO cv=new ChangeVO();
		cd=CustomerDAO.getInstance();
		
		try{
			cv.setName("����");
			cv.setImage(tempFile);
			cv.setPass(pass);
			cv.setQuNum(quNum);
			cv.setAnswer(answer);
			cv.setInfo(info);
			cv.setId("dongha");
			
			cd.updateMember(cv);
			JOptionPane.showMessageDialog(micv, "ȸ�������� �����Ǿ����ϴ�.");
			micv.dispose();
		} catch(SQLException se){
			JOptionPane.showMessageDialog(micv, "���������� �ùٸ��� �ʽ��ϴ�.");
		}
	}//changeInfo
	
	private void addImg(){
		FileDialog fdImg=new FileDialog(micv, "�����ʻ��� ����", FileDialog.LOAD);
		fdImg.setVisible(true);
		
		String path=fdImg.getDirectory();
		String file=fdImg.getFile();
		if(file !=null){
			String validFile="jpg, gif, png, bmp";
			if(!validFile.contains(file.substring(file.lastIndexOf(".")+1))){
				JOptionPane.showMessageDialog(micv, "�����Ͻ� ������ �̹����� �ƴմϴ�.");
				return;
			}//end if
			
			ImageIcon temp=new ImageIcon(path+file);
			micv.getJlItemImage().setIcon(temp);
		}//end if
	}//addImg
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==micv.getJbCancel()){
			micv.dispose();
		}//end if
		
		if(ae.getSource()==micv.getJbChange()){
			changeInfo();
		}
		
		if(ae.getSource()==micv.getJbImage()){
			addImg();
		}
	}
	
}
