package kr.co.sist.market.vo;

public class ItemInfoVO {
	private String itemName, itemType, itemInfo, hiredate, image;
	private int price;
	
	public ItemInfoVO() {

	}
	
	public ItemInfoVO(String itemName, String itemType, String itemInfo, String hiredate, String image, int price) {
		this.itemName = itemName;
		this.itemType = itemType;
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
	
	public String getItemType() {
		return itemType;
	}
	
	public void setItemType(String itemType) {
		this.itemType = itemType;
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
