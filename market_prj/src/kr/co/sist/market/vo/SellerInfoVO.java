package kr.co.sist.market.vo;

public class SellerInfoVO {
	private String Id, info, img;

	public SellerInfoVO(String id, String info, String img) {
		super();
		Id = id;
		this.info = info;
		this.img = img;
	}

	public SellerInfoVO() {
		super();
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
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
