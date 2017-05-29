package kr.co.sist.market.vo;

public class ChangeVO {
	
	private String id, name, image, pass, answer, info;
	private int quNum;
	
	public ChangeVO() {
	
	}
	
	public ChangeVO(String id, String name, String image, String pass, String answer, String info, int quNum) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.pass = pass;
		this.answer = answer;
		this.info = info;
		this.quNum = quNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
