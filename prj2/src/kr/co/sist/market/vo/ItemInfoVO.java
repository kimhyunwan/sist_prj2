package kr.co.sist.market.vo;

public class ItemInfoVO {
	private String itemName, itemInfo, image, id;
	private int itemType, price;
	
	public ItemInfoVO() {
		
	}
	
	public ItemInfoVO(String itemName, String itemInfo, String image, String id, int itemType, int price) {
		this.itemName = itemName;
		this.itemInfo = itemInfo;
		this.image = image;
		this.id = id;
		this.itemType = itemType;
		this.price = price;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemInfo() {
		return itemInfo;
	}
	
	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
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
