package model;

import java.io.Serializable;

public class ShopInfo implements Serializable {

	private String shopName;
	private String shopURL;
	private String shopAddress;
	private String shopTEL;
	
	public ShopInfo() {
	}
	
	public ShopInfo(String shopName,String shopURL, String shopAddress, String shopTEL) {
		this.shopName = shopName;
		this.shopURL = shopURL;
		this.shopAddress = shopAddress;
		this.shopTEL = shopTEL;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopURL() {
		return shopURL;
	}
	public void setShopURL(String shopURL) {
		this.shopURL = shopURL;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopTEL() {
		return shopTEL;
	}
	public void setShopTEL(String shopTEL) {
		this.shopTEL = shopTEL;
	}
}

