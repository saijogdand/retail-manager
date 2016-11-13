package db.ret.mng.bean;

import java.io.Serializable;

/**
 * Bean for Request body and Response body
 * as well as Dataset.
 * @author sainath jogdand
 *
 */
public class RetailManagerReqResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1542759825082513043L;
	private String shopName;
	private Address shopAddress;

	private double longitude;
	private double latitude;

	public RetailManagerReqResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RetailManagerReqResponse(String shopName, Address shopAddress) {
		super();
		this.shopName = shopName;
		this.shopAddress = shopAddress;
	}

	public RetailManagerReqResponse(String shopName, Address shopAddress, double longitude, double latitude) {
		super();
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Address getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(Address shopAddress) {
		this.shopAddress = shopAddress;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((shopAddress == null) ? 0 : shopAddress.hashCode());
		result = prime * result + ((shopName == null) ? 0 : shopName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RetailManagerReqResponse other = (RetailManagerReqResponse) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (shopAddress == null) {
			if (other.shopAddress != null)
				return false;
		} else if (!shopAddress.equals(other.shopAddress))
			return false;
		if (shopName == null) {
			if (other.shopName != null)
				return false;
		} else if (!shopName.equals(other.shopName))
			return false;
		return true;
	}

}
