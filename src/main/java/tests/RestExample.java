package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestExample {
	
	@Test
	public void restExampleTest() {
		
		JSONObject requestPayLoad = new JSONObject();
		requestPayLoad.put("title", "JSON Object Title");
		requestPayLoad.put("body", "JSON Object body");
		
		File fisier = new File("data.json");
		
		Response response = 
			given()
				.header("Content-Type", "application/json")
				.header("accept", "application/json")
				.body(requestPayLoad.toJSONString())
			.when()
			     //ex 1 --> direct in body as String
				//.body("{\"title\":\"Test rest assured\",\"body\":\"Rest assured call\"}")
			    //ex 2 --> Body as JsonObject
			    //ex 3 --> body as file
			.body(fisier)
				.post("https://keytodorestapi.herokuapp.com/api/save")
			.then()
			    .assertThat().statusCode(200)
			    .extract().response();
		
		System.out.println(response.asPrettyString());
		System.out.println(response.asString());
		String info = response.jsonPath().getString("info");
		System.out.println(info);
		assertEquals(info, "Todo saved! Nice job!");
			
	}
	

}
