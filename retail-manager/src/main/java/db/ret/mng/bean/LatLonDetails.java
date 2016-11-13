package db.ret.mng.bean;

import java.io.Serializable;

/**
 * @author sainath jogdand
 *
 *         It will hold latitude and longitude details to get nearby details.
 */
public class LatLonDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double longitude;
	private double latitude;

	public LatLonDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param latitude
	 *            initialize latitude point.
	 * @param longitude
	 *            initialize longitude point.
	 */
	public LatLonDetails(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
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
		LatLonDetails other = (LatLonDetails) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

}
