package tests;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import java.io.File;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Homework35 {
	
	
	@Test
	public void postTest() {
		
		File fisier = new File("homework35.json");
		
		
		Response resp = 
			given()
				.header("Content-Type", "application/json")
				.body(fisier)
			.when()			
				.post("https://fakerestapi.azurewebsites.net/api/v1/Books")
			.then()
			    .statusCode(200)
			    .extract().response();
		
		System.out.println(resp.asPrettyString());
		String id = resp.jsonPath().getString("id");
		System.out.println(id);
		assertThat(id, is(equalTo("77")));
	}

}
