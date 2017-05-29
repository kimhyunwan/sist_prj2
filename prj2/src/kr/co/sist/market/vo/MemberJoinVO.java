package kr.co.sist.market.vo;

public class MemberJoinVO {
	private String id, ssn, name, pass, passAnswer, image, info;
	private int quNum;
	
	public MemberJoinVO() {

	}

	public MemberJoinVO(String id, String name, String pass, String passAnswer, String image, String info, int quNum, String ssn) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.passAnswer = passAnswer;
		this.image = image;
		this.info = info;
		this.quNum = quNum;
		this.ssn = ssn;
	}

	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
