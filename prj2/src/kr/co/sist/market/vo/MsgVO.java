package kr.co.sist.market.vo;

public class MsgVO {
	private String sendId, id, msg, itemCode;
	
	public MsgVO() {
	
	}

	public MsgVO(String sendId, String id, String msg, String itemCode) {
		this.sendId = sendId;
		this.id = id;
		this.msg = msg;
		this.itemCode = itemCode;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	
	
}
