package kr.co.sist.market.vo;

public class ItemInfoVO {
	String itemName, itemCode, itemInfo, hiredate, image;
	int price;
	
	//기본생성자
	public ItemInfoVO() {
	}
	
	//인자있는생성자
	public ItemInfoVO(String itemName, String itemCode, String itemInfo, String hiredate, String image, int price) {
		super();
		this.itemName = itemName;
		this.itemCode = itemCode;
		this.itemInfo = itemInfo;
		this.hiredate = hiredate;
		this.image = image;
		this.price = price;
	}
	
	//getter,setter
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemInfo() {
		return itemInfo;
	}
	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}//class
