package kr.co.sist.market.vo;

public class MsgViewVO {
	String sendId, msg;
	
	//기본생성자
	public MsgViewVO() {
	}

	//인자있는생성자
	public MsgViewVO(String sendId, String msg) {
		this.sendId = sendId;
		this.msg = msg;
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
	
}//class
