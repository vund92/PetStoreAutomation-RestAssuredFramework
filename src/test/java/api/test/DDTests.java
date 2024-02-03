package api.test;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.RestUtils;
import api.endpoints.Routes;
import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
//	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class )
//	public void testPostuser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
//	{
//		
//		User userPayload=new User();
//		
//		userPayload.setId(Integer.parseInt(userID));
//		userPayload.setUsername(userName);
//		userPayload.setFirstName(fname);
//		userPayload.setLastName(lname);
//		userPayload.setEmail(useremail);
//		userPayload.setPassword(pwd);
//		userPayload.setPhone(ph);
//		
//		Response response=UserEndPoints.createUser(userPayload);
//		
//		String capturedCurl = UserEndPoints.restReqFilter.getCapturedCurl();
//	    System.out.println("Captured cURL: " + capturedCurl);
//		
//		Assert.assertEquals(response.getStatusCode(),200);
//			
//	}
	
//	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
//	public void testDeleteUserByName(String userName)
//	{
//			Response response=UserEndPoints.deleteUser(userName);
//			Assert.assertEquals(response.getStatusCode(),202);	
//	
//	}
	
	@Test(priority=3, dataProvider="Data", dataProviderClass=DataProviders.class )
	public void testPostuser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=RestUtils.createUser(Routes.post_url, userPayload, new HashMap<>());
		
//		String capturedCurl = RestUtils.restReqFilter.getCapturedCurl();
//	    System.out.println("Captured cURL: " + capturedCurl);
		
		Assert.assertEquals(response.getStatusCode(),200);
			
	}
	
	
	
}
