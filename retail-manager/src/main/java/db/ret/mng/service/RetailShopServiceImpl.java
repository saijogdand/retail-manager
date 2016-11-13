package db.ret.mng.service;

import java.util.List;

import org.springframework.stereotype.Component;

import db.ret.mng.bean.RetailManagerReqResponse;
import db.ret.mng.util.GeoLocationByAddress;
import db.ret.mng.util.ShopDataSetHelper;

/**
 * @author sainath jogdand, This is service impl class for shop. - Helps to add
 *         new shop details in memory. - Helps to get nearby shops.
 *
 */
@Component
public class RetailShopServiceImpl implements RetailShopService {

	// TODO logger needs to implement and need to add log message capturing at
	// method levels.

	/*
	 * (non-Javadoc)
	 * 
	 * @see db.ret.mng.service.RetailShopService#addShopDetails(db.ret.mng.bean.
	 * RetailManagerReqResponse)
	 */
	@Override
	public RetailManagerReqResponse addShopDetails(RetailManagerReqResponse request) throws Exception {
		request = GeoLocationByAddress.getGeoPositions(request);
		ShopDataSetHelper.addDataSet(request);
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see db.ret.mng.service.RetailShopService#getNearByShop(double, double)
	 */
	@Override
	public List<RetailManagerReqResponse> getNearByShop(double longitude, double latitude) throws Exception {
		return ShopDataSetHelper.findNearByShopDetails(longitude, latitude);
	}

}
