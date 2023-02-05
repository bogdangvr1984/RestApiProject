package tests;

import static io.restassured.RestAssured.given;

import static org.testng.Assert.assertTrue;


import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathHomework1  {
	
	@Test
	public void getBooks () {
		
		Response resp = given().get("https://fakerestapi.azurewebsites.net/api/v1/Books").then().extract().response();
		
		JsonPath jsonPath = resp.jsonPath();
		
		List<String> pageCount = jsonPath.getList("findAll{it.pageCount >1000 & it.pageCount < 2000}");
		System.out.println(pageCount);
		System.out.println(pageCount.size());

		assertTrue(pageCount.size() == 9);

	}

}
