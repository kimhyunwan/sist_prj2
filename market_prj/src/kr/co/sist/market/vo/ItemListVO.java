package kr.co.sist.market.vo;

public class ItemListVO {
	private String itemName, itemCode, itemInfo, hiredate, image;
	private int price;
	
	public ItemListVO() {
		
	}
	
	public ItemListVO(String itemName, String itemCode, String itemInfo, String hiredate, String image, int price) {
		this.itemName = itemName;
		this.itemCode = itemCode;
		this.itemInfo = itemInfo;
		this.hiredate = hiredate;
		this.image = image;
		this.price = price;
	}
	
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
	
	
}
