package kr.co.sist.market.vo;

public class MemberInfoVO {
	String id, pass, pass_answer, image, info;
	int qu_num;
	
	//기본생성자
	public MemberInfoVO() {
	}

	//인자있는생성자
	public MemberInfoVO(String id, String pass, String pass_answer, String image, String info, int qu_num) {
		super();
		this.id = id;
		this.pass = pass;
		this.pass_answer = pass_answer;
		this.image = image;
		this.info = info;
		this.qu_num = qu_num;
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

	public String getPass_answer() {
		return pass_answer;
	}

	public void setPass_answer(String pass_answer) {
		this.pass_answer = pass_answer;
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

	public int getQu_num() {
		return qu_num;
	}

	public void setQu_num(int qu_num) {
		this.qu_num = qu_num;
	}
	
	
	
	
}//class
