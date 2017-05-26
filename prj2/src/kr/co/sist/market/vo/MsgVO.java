package kr.co.sist.market.vo;

public class MsgVO {
	String sendId, msg, id, itemCode;
	
	//기본생성자
	public MsgVO() {
	}

	//인자있는생성자
	public MsgVO(String sendId, String msg, String id, String itemCode) {
		super();
		this.sendId = sendId;
		this.msg = msg;
		this.id = id;
		this.itemCode = itemCode;
	}

	//getter,setter
	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	@Override
	public String toString() {
		return "MsgVO [sendId=" + sendId + ", msg=" + msg + ", id=" + id + ", itemCode=" + itemCode + "]";
	}
	
}//class
