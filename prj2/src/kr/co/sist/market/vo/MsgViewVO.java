package kr.co.sist.market.vo;

public class MsgViewVO {
	String sendId, msg;
	
	//�⺻������
	public MsgViewVO() {
	}

	//�����ִ»�����
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
