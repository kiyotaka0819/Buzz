package model;

import java.io.Serializable;

public class ShopInfo implements Serializable {

	private String shopName;
	private String shopAddress;
	private String shopURL;
	private String shopTEL;
	
	public ShopInfo(String shopName,String shopAddress, String shopURL, String shopTEL) {
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.shopURL = shopURL;
		this.shopTEL = shopTEL;
	}
	public String getShopName() {
		return shopName;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public String getShopURL() {
		return shopURL;
	}
	public String getShopTEL() {
		return shopTEL;
	}
	/*プッシュテスト*/
}

