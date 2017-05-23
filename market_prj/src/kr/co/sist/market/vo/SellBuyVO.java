package kr.co.sist.market.vo;

public class SellBuyVO {
	private String id, itemCode, itemName, tradeDate;

	public SellBuyVO(String id, String itemCode, String itemName, String tradeDate) {
		super();
		this.id = id;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.tradeDate = tradeDate;
	}
	
	public SellBuyVO() {
		super();
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	
	

}
