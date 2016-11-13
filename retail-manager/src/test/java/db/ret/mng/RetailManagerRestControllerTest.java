package db.ret.mng;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import db.ret.mng.app.RetailManagerApplication;
import db.ret.mng.bean.Address;
import db.ret.mng.bean.RetailManagerReqResponse;

/**
 * Test class for rest controller.
 * @author sainath jogdand
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes=RetailManagerApplication.class)


public class RetailManagerRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	
	
	@Test
	public void addShopDetailTest() {
		Address shopAddress = new Address(1, "411041");
		RetailManagerReqResponse body = new RetailManagerReqResponse("Test shop", shopAddress);
		restTemplate.put("/retailmanager/shop", body,RetailManagerReqResponse.class);
		Assert.notNull(body, "Added new shop - Response received..");
		Assert.notNull(body.getLatitude(), "no latitude");
		Assert.notNull(body.getLongitude(),"no longitutde");
		
		
	}

	@Test
	public void getNearByShopTest() {
		List<RetailManagerReqResponse> body =  restTemplate.getForObject("/retailmanager/shop?customerLongitude=73.7823292&customerLatitude=18.5905064", List.class);
		Assert.notNull(body);
		
	}
	
	
    
    }

   
