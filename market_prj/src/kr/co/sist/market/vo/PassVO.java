package kr.co.sist.market.vo;

public class PassVO {
	private String name, ssn, id, passAnswer;
	private int quNum;
	
	public PassVO(String name, String ssn, String id, String passAnswer, int quNum) {
		super();
		this.name = name;
		this.ssn = ssn;
		this.id = id;
		this.passAnswer = passAnswer;
		this.quNum = quNum;
	}

	public PassVO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPassAnswer() {
		return passAnswer;
	}

	public void setPassAnswer(String passAnswer) {
		this.passAnswer = passAnswer;
	}

	public int getQuNum() {
		return quNum;
	}

	public void setQuNum(int quNum) {
		this.quNum = quNum;
	}

}
