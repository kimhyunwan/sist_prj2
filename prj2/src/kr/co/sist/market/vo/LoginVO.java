package kr.co.sist.market.vo;

public class LoginVO {
	String id,pass;
	
	//기본생성자
	public LoginVO() {
	}
	
	//인자있는 생성자
	public LoginVO(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
	}

	//getter,setter
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
	
}//class
