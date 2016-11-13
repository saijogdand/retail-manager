package db.ret.mng.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.util.StringUtils;
import org.w3c.dom.Document;

import db.ret.mng.bean.RetailManagerReqResponse;

/**
 * This is the utility class, which will help to get the lat long values.
 * 
 * @author sainath jogdand
 * 
 */
public class GeoLocationByAddress {

	// TODO logger needs to implement and need to add log message capturing at
	// method levels.

	/**
	 * Google map api url.
	 */
	private static String GOOGLE_MAP_API_URL = "http://maps.googleapis.com/maps/api/geocode/xml?address={0}&sensor=true";
	/**
	 * api success code.
	 */
	private static int API_SUCCESS_CODE = 200;
	/**
	 * status for ok.
	 */
	private static String API_OK_STATUS = "OK";

	/**
	 * This utility to - Get langitude and latitude from google map api, based
	 * on postCode you can provide postal code as well as full address with
	 * postal code.
	 * 
	 * @param request
	 *            Shop details which contains postCode to requires to get
	 *            longitude and latitude from api.
	 * @return RetailManagerReqResponse Shop details with generated longitude
	 *         and latitutde.
	 * @throws Exception
	 *             throws an Exception.
	 */
	public static RetailManagerReqResponse getGeoPositions(RetailManagerReqResponse request) throws Exception {
		int apiResponseCd = 0;
		if (request == null) {
			throw new Exception("Shop details reuest doesn't have data");
		}

		if (request.getShopAddress() == null) {
			throw new Exception("Shop details reuest doesn't have shopAddress details");
		}

		if (StringUtils.isEmpty(request.getShopAddress().getPostCode())) {
			throw new Exception("Shop details reuest doesn't have shopAddress.postCode details");
		}

		String address = request.getShopAddress().getPostCode();
		String apiUrl = MessageFormat.format(GOOGLE_MAP_API_URL, URLEncoder.encode(address, "UTF-8"));
		URL url = new URL(apiUrl);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.connect();
		apiResponseCd = httpConnection.getResponseCode();

		if (apiResponseCd == API_SUCCESS_CODE) {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(httpConnection.getInputStream());
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression exprGeoResponse = xpath.compile("/GeocodeResponse/status");
			String status = (String) exprGeoResponse.evaluate(document, XPathConstants.STRING);
			if (API_OK_STATUS.equals(status)) {
				exprGeoResponse = xpath.compile("//geometry/location/lat");
				String latitude = (String) exprGeoResponse.evaluate(document, XPathConstants.STRING);
				exprGeoResponse = xpath.compile("//geometry/location/lng");
				String longitude = (String) exprGeoResponse.evaluate(document, XPathConstants.STRING);

				if (longitude != null) {
					request.setLongitude(new Double(longitude));
				}

				if (latitude != null) {
					request.setLatitude(new Double(latitude));
				}

				return request;
			} else {
				throw new Exception("Error from the API - response status: " + status);
			}
		}
		return request;
	}
}
