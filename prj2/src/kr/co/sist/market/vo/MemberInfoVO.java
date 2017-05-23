package kr.co.sist.market.vo;

public class MemberInfoVO {
	private String id, pass, passAnswer, image, info;
	private int quNum;
	
	public MemberInfoVO() {
	
	}
	public MemberInfoVO(String id, String pass, String passAnswer, String image, String info, int quNum) {
		this.id = id;
		this.pass = pass;
		this.passAnswer = passAnswer;
		this.image = image;
		this.info = info;
		this.quNum = quNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPassAnswer() {
		return passAnswer;
	}
	public void setPassAnswer(String passAnswer) {
		this.passAnswer = passAnswer;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getQuNum() {
		return quNum;
	}
	public void setQuNum(int quNum) {
		this.quNum = quNum;
	}
	
	
}
