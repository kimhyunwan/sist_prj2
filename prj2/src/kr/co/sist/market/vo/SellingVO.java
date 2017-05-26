package kr.co.sist.market.vo;

public class SellingVO {
	private String itemCode, phone, id, reqDate, itemName;
	
	public SellingVO() {
		
	}

	public SellingVO(String itemCode, String phone, String id, String reqDate, String itemName) {
		this.itemCode = itemCode;
		this.phone = phone;
		this.id = id;
		this.reqDate = reqDate;
		this.itemName = itemName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	@Override
	public String toString() {
		return "SellingVO [itemCode=" + itemCode + ", phone=" + phone + ", id=" + id + ", reqDate=" + reqDate
				+ ", itemName=" + itemName + "]";
	}

	
}
