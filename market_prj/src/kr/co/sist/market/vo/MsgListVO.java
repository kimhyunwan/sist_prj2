package kr.co.sist.market.vo;

public class MsgListVO {
	private String Id, Item, msgDate;
	private boolean flag;
	
	public MsgListVO(String id, String item, String msgDate, boolean flag) {
		super();
		Id = id;
		Item = item;
		this.msgDate = msgDate;
		this.flag = flag;
	}

	public MsgListVO() {
		super();
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getItem() {
		return Item;
	}

	public void setItem(String item) {
		Item = item;
	}

	public String getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(String msgDate) {
		this.msgDate = msgDate;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
