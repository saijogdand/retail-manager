package db.ret.mng.util;

import java.util.ArrayList;
import java.util.List;

import db.ret.mng.bean.LatLonDetails;
import db.ret.mng.bean.RetailManagerReqResponse;

/**
 * @author sainath jogdand This is utility help to add shop details in the
 *         memory and to get nearby locations.
 *
 */
public class ShopDataSetHelper {
	// TODO logger needs to implement and need to add log message capturing at
	// method levels.

	/**
	 * per nuutical mile per earch circle angle.
	 */
	private static double PER_NAUTICAL_MILE = 1.15077945;
	/**
	 * shop details data set which will hold only 100 records.
	 */
	private static List<RetailManagerReqResponse> dataset = new ArrayList<RetailManagerReqResponse>();

	/**
	 * To add shop details in the dataset.
	 * 
	 * @param req
	 *            Contains shop details information.
	 * @throws Exception
	 *             throws an Exception.
	 */
	public static void addDataSet(RetailManagerReqResponse req) throws Exception {
		if (dataset.size() >= 100) {
			throw new Exception("Dataset limit is 100 records only.");

		} else {
			dataset.add(req);
		}
	}

	/**
	 * To get nearby 1 mile in circle shops based on longitude and latitude.
	 * 
	 * @param longitude
	 *            input longitutde.
	 * @param latitude
	 *            input latitude.
	 * @return List<RetailManagerReqResponse> list of nearby locations.
	 * @throws Exception
	 *             throws an Exception.
	 */
	public static List<RetailManagerReqResponse> findNearByShopDetails(double longitude, double latitude)
			throws Exception {
		List<RetailManagerReqResponse> nearByShops = new ArrayList<RetailManagerReqResponse>();
		LatLonDetails input = new LatLonDetails(latitude, longitude);
		double actualDistanceInMiles = 0;
		for (RetailManagerReqResponse shopDetail : dataset) {
			LatLonDetails fromDataSet = new LatLonDetails(shopDetail.getLatitude(), shopDetail.getLongitude());
			actualDistanceInMiles = distanceBetweenLocations(input, fromDataSet);

			// will add the shops which are nearby 1 miles in circle.
			if (actualDistanceInMiles <= 1) {
				nearByShops.add(shopDetail);
			}
		}

		return nearByShops;
	}

	/**
	 * To get actual distance between two locations in miles.
	 * 
	 * @param input
	 *            input latitutde and longitude points.
	 * @param fromDataSet
	 *            from the data set latitude and longitude points.
	 * @return double returns actual distance between two point in miles.
	 */
	private static double distanceBetweenLocations(LatLonDetails input, LatLonDetails fromDataSet) {

		double inputLat = Math.toRadians(input.getLatitude());
		double inputLong = Math.toRadians(input.getLongitude());
		double dataSetLat = Math.toRadians(fromDataSet.getLatitude());
		double dataSetLong = Math.toRadians(fromDataSet.getLongitude());

		// circle distance in radians, using law of cosines formula
		double geoAngle = Math.acos(Math.sin(inputLat) * Math.sin(dataSetLat)
				+ Math.cos(inputLat) * Math.cos(dataSetLat) * Math.cos(inputLong - dataSetLong));

		// each degree on Earth is 60 nautical miles
		double miles = 60 * Math.toDegrees(geoAngle);
		double actualDistance = PER_NAUTICAL_MILE * miles;
		return actualDistance;
	}

}
