package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent2;
import utils.DataBuilder;

public class BaseComponent2Example extends BaseComponent2 {
	
	String id;
	
	@Test(priority=1)
	public void getAllUsersTest() {
		
		Response result = doGetAll();
		System.out.println(result.asString());
	}

	@Test(priority=2)
	
	public void postAnUser() {
		Response result = doPost(DataBuilder.buildUser().toJSONString());
		id = result.jsonPath().getString("result._id");
		System.out.println(result.asString());
		
	}
	
	@Test(priority=3)
	public void getSingleUser( ) {
		Response result = doGet(id);
		System.out.println(result.asString());
	}
}
