package kr.co.sist.market.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ImageView extends JDialog {
	
	private JLabel jlImg;
	private static MainView mv;
	private ItemInfoView iiv;
	public ImageView(MainView mv){
		super(mv, "¿ÃπÃ¡ˆ");
//	      File file=new File(System.getProperty("user.dir")+"/src/kr/co/sist/market/img/market");
//	      ImageIcon item=new ImageIcon(file.toString());
//	      jlImg=new JLabel(item);
		ImageIcon item = new ImageIcon();
		jlImg=new JLabel(item);
		setLayout(new BorderLayout());
		add("Center", jlImg);
		
		
		setBounds(800, 200, 500, 700);
	    setBackground(Color.WHITE);
	    setResizable(false);
	    setVisible(true);
	}

	public static void main(String[] args){
		new ImageView(mv);
	}
}
