package kr.co.sist.market.vo;

public class PhoneVO {
	private String id, phone, itemCode;
	
	public PhoneVO(String id, String phone, String itemCode) {
		this.id = id;
		this.phone = phone;
		this.itemCode = itemCode;
	}

	public PhoneVO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
}
