package db.ret.mng.service;

import java.util.List;

import db.ret.mng.bean.RetailManagerReqResponse;

/**
 * @author sainath jogdand This is service interface for shop. - Helps to add
 *         new shop details in memory. - Helps to get nearby shops.
 *
 */
public interface RetailShopService {

	/**
	 * Helps to add new shop details in the memory.
	 * 
	 * @param request
	 *            It contains the shop details which needs to add into the
	 *            memory.
	 * @return RetailManagerReqResponse It will returns shop details with
	 *         generated longitude and latitude.
	 * @throws Exception
	 *             throws an Exception.
	 */
	public RetailManagerReqResponse addShopDetails(RetailManagerReqResponse request) throws Exception;

	/**
	 * Helps to get nearby shops based on longitude and latitude.
	 * 
	 * @param longitude
	 *            client provided longitude.
	 * @param latitude
	 *            client provided latitude.
	 * @return List<RetailManagerReqResponse> Contains the list of nearby
	 *         location for the provided longitutde and latitude.
	 * @throws Exception
	 *             throws an Exception.
	 */
	public List<RetailManagerReqResponse> getNearByShop(double longitude, double latitude) throws Exception;

}
