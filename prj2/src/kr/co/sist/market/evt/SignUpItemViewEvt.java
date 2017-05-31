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
import kr.co.sist.market.dao.MarketDAO;
import kr.co.sist.market.view.SignUpItemView;
import kr.co.sist.market.vo.ItemInfoVO;

public class SignUpItemViewEvt extends WindowAdapter implements ActionListener {
	private SignUpItemView suiv;
	private MarketDAO md;

	public SignUpItemViewEvt(SignUpItemView suiv) {
		this.suiv = suiv;
		md=MarketDAO.getInstance();
		
		DefaultComboBoxModel<String> dcbm=suiv.getDcbmTp();
		
		try {
			List<String> listTp=md.selectItemType();
			for(String tp : listTp){
				dcbm.addElement(tp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// SignUpItemViewEvt

	private void addImg() {
		FileDialog fdImg = new FileDialog(suiv, "물품사진 선택", FileDialog.LOAD);
		fdImg.setVisible(true);

		String path = fdImg.getDirectory();
		String file = fdImg.getFile();
		if (file != null) {
			String validFile = "jpg, gif, png, bmp";
			if (!validFile.contains(file.substring(file.lastIndexOf(".") + 1))) {
				JOptionPane.showMessageDialog(suiv, "선택하신 파일은 이미지가 아닙니다.");
				return;
			} // end if

			ImageIcon temp = new ImageIcon(path + file);
			suiv.getJlimg().setIcon(temp);
		} // end if
	}// addImg

	private void SignUpItem() {
		ImageIcon icon = (ImageIcon) suiv.getJlimg().getIcon();

		try {
			File file = new File(icon.toString());
			String tempFile = file.getName();
			String itemName = suiv.getJtfItemName().getText().trim();
			int typeNum = suiv.getJcbType().getSelectedIndex();
			int price = Integer.parseInt(suiv.getJtfPrice().getText().trim());
			String itemInfo = suiv.getJtaItemInfo().getText().trim();

			if (!file.getParent().equals(System.getProperty("user.dir")+"src/kr/co/sist/market/img/market")) {
				try {
					// 원본 파일 복붙
					FileInputStream fis = new FileInputStream(file);
					FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"src/kr/co/sist/market/img/market/" + file.getName());

					byte[] temp = new byte[512];

					int readData = 0;
					while ((readData = fis.read(temp)) != -1) {
						fos.write(temp, 0, readData);
					} // end while

					fos.flush();

					if (fis != null) {
						fis.close();
					} // end if

					if (fos != null) {
						fos.close();
					} // end if

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // end if

			ItemInfoVO iiv = new ItemInfoVO();
			CustomerDAO cd = CustomerDAO.getInstance();

			iiv.setItemName(itemName);
			iiv.setItemType(typeNum);
			iiv.setPrice(price);
			iiv.setImage(tempFile);
			iiv.setItemInfo(itemInfo);

			cd.insertItem(iiv);
			JOptionPane.showMessageDialog(suiv, "판매 물품이 등록되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(suiv, "올바른 가격을 입력해 주세요");
		}
	}// SignUpItem

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == suiv.getJbCancel()) {
			suiv.dispose();
		} // end if

		if (ae.getSource() == suiv.getJbImage()) {
			addImg();
		} // end if

		if (ae.getSource() == suiv.getJbSignUp()) {
			SignUpItem();
		} // end if
	}

}
