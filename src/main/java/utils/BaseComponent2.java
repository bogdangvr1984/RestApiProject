package utils;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BaseComponent2 {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public void setup() {
		
		requestSpec = new RequestSpecBuilder().
				//setBaseUri("https://keytrcrud.herokuapp.com/").
				setBaseUri("https://keytodorestapi.herokuapp.com/").
				//setBasePath("api/users").
				setBasePath("api/").
				setContentType(ContentType.JSON).
				addHeader("accept", "application/json").
				build();
		
		responseSpec = new ResponseSpecBuilder().
						expectStatusCode(200).
						build();
						
		
	}
	
	public static Response doPost(String body) {
		
		Response result = given().
				spec(requestSpec).
				body(body).
				when().
				post().
				then().
				//spec(responseSpec).
				statusCode(201).
				extract().
				response();
								
		return result;
		
	}
	
	public static Response doGetAll() {
		
		Response result = given().
				  spec(requestSpec).
				when().
				  get().
				then().
				  spec(responseSpec).
				  extract().
				  response();
								
		return result;
		
	}
	
	public static Response doGet(String id) {
		
		Response result = given().
				  spec(requestSpec).
				when().
				  get().
				then().
				  spec(responseSpec).
				  extract().
				  response();
								
		return result;
		
	}

}
