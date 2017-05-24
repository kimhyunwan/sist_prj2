package kr.co.sist.market.vo;

public class MsgListVO {
	private String id, item, msgDate, flag;
	
	public MsgListVO() {
	
	}

	public MsgListVO(String id, String item, String msgDate, String flag) {
		this.id = id;
		this.item = item;
		this.msgDate = msgDate;
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(String msgDate) {
		this.msgDate = msgDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
