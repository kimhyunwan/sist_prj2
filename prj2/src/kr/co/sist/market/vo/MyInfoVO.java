package kr.co.sist.market.vo;

public class MyInfoVO {
	private String id, img;
	private int sellWait, buyWait, nonChkMsg;
	public MyInfoVO(String id, String img, int sellWait, int buyWait, int nonChkMsg) {
		this.id = id;
		this.img = img;
		this.sellWait = sellWait;
		this.buyWait = buyWait;
		this.nonChkMsg = nonChkMsg;
	}
	public MyInfoVO() {

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getSellWait() {
		return sellWait;
	}
	public void setSellWait(int sellWait) {
		this.sellWait = sellWait;
	}
	public int getBuyWait() {
		return buyWait;
	}
	public void setBuyWait(int buyWait) {
		this.buyWait = buyWait;
	}
	public int getNonChkMsg() {
		return nonChkMsg;
	}
	public void setNonChkMsg(int nonChkMsg) {
		this.nonChkMsg = nonChkMsg;
	}
	
	
}
