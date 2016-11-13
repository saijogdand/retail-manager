package db.ret.mng.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import db.ret.mng.bean.RetailManagerReqResponse;
import db.ret.mng.service.RetailShopService;

/**
 * @author sainath jogdand This class is a rest controller for the retail
 *         manager application.
 *
 */
@RestController
@RequestMapping("/retailmanager/shop")
public class RetailRestController {

	// TODO logger needs to implement and need to add log message capturing at
	// method levels.

	
	/**
	 * shopService bean will helps to add, get near by locations.
	 */
	@Autowired
	private RetailShopService shopService;

	/**
	 * 
	 * A Customer can get a JSON payload from the shops endpoint containing
	 * details of the shop nearest to them: o shopName o shopAddress.number o
	 * shopAddress.postCode o shopLongitude o shopLatitude
	 *
	 * @param longitude
	 *            URL parameter which will accept longitude
	 * @param latitude
	 *            URL parameter which will accept latitude
	 * @return ResponseEntity Returns a JSON formatted response containing List
	 *         of RetailManagerReqResponse.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<RetailManagerReqResponse>> getNearyLocations(
			@RequestParam("customerLongitude") String longitude, @RequestParam("customerLatitude") String latitude) {

		List<RetailManagerReqResponse> response;
		try {
			response = shopService.getNearByShop(new Double(longitude),
					new Double(latitude));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Exception and error handling needs to here as well as status messages.
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Method will create new entry for the shop details in the memory.
	 * 
	 * @param shopAddRequest
	 *            It contains request JSON which will be input from customer to
	 *            add shop details.
	 * @return ResponseEntity It will return the response to the client with
	 *         generated longitude and latitude.
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<RetailManagerReqResponse> addShopDetails(@RequestBody RetailManagerReqResponse shopAddRequest) {

		try {
			RetailManagerReqResponse response = shopService.addShopDetails(shopAddRequest);
			return new ResponseEntity<RetailManagerReqResponse>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Exception and error handling needs to here as well as status messages.
		}
		return null;

	}

}
