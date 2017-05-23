package kr.co.sist.market.vo;

public class SellerInfoVO {
	private String id, info, img;

	public SellerInfoVO(String id, String info, String img) {
		this.id = id;
		this.info = info;
		this.img = img;
	}

	public SellerInfoVO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
