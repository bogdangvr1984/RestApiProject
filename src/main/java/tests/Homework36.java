package tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent;
import utils.DataBuilder;

public class Homework36 extends BaseComponent{
	
String id;
String nume;
	
	@Test(priority = 1)
	public void postNewPassenger() {
		
		Response resp = doPostRequest("v1/passenger/", DataBuilder.buildPassenger().toJSONString(), 200);
		id = resp.jsonPath().getString("_id");
		nume = resp.jsonPath().getString("name");
	//	assertThat(DataBuilder.buildPassenger(name), is(equalTo("nume")));
		System.out.println(resp.asPrettyString());
	}
	
	@Test(priority = 2)
	public void getNewPassenger() {
		
		Response resp = doGetRequest("v1/passenger/"+id, 200);
		assertThat(resp.jsonPath().getString("airline.name[0]"), is(equalTo("Tarom")));
	}
	
	@Test(priority = 3)
	public void updateNewPassenger() {
		
		Response resp = doPutRequest("v1/passenger/"+id, DataBuilder.updatePassenger(nume).toJSONString(), 200);
		assertThat(resp.jsonPath().getString("message"), is(equalTo("Passenger data put successfully completed.")));		
	}
	
	@Test(priority = 4)
	public void getUpdatedPassenger() {
		
		Response resp = doGetRequest("v1/passenger/"+id, 200);
		assertThat(resp.jsonPath().getString("airline.name[0]"), is(equalTo("Quatar Airways")));
		assertThat(resp.jsonPath().getString("trips"), is(equalTo("300")));
		System.out.println(resp.asPrettyString());
	}
	
	@Test(priority = 5)
	public void deletePassenger() {
		
		Response resp = doDeleteRequest("v1/passenger/"+id, 200);
		assertThat(resp.jsonPath().getString("message"), is(equalTo("Passenger data deleted successfully.")));
	}

}
