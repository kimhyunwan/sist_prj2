package kr.co.sist.market.vo;

public class MsgViewVO {
	private String id, msg;
	
	public MsgViewVO() {
		
	}

	public MsgViewVO(String id, String msg) {
		this.id = id;
		this.msg = msg;
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
	
	
}
