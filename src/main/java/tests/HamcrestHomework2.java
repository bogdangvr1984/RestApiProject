package tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.CargoCapacityChecker.cargoCapacity;

public class HamcrestHomework2 {
	
	@Test
    public void hamcrestMatchersHomework2() {
		
	Response resp = given().get("https://swapi.dev/api/starships/3/").then().extract().response();
	System.out.println(resp.asPrettyString());
	
	JsonPath jsonPath = resp.jsonPath();
	
	String model = jsonPath.getString("model");
	int shipCapacity = jsonPath.getInt("cargo_capacity");
	Double speed = jsonPath.getDouble("max_atmosphering_speed");
	List<String> films = new ArrayList<>(jsonPath.getList("films"));
	List<String> pilots = new ArrayList<>(jsonPath.getList("pilots"));
	System.out.println(films);
	
	String[] filmsToSearch = {
			"https://swapi.dev/api/films/2/", 
	        "https://swapi.dev/api/films/6/", 
	        "https://swapi.dev/api/films/5/", 	
	};
	
	//assertThat(filmsToSearch, is(not(equalTo(films))));
	
	//assertThat(films, is(anyOf(contains("https://swapi.dev/api/films/2/"))));
	//assertThat(films, either(containsString("https://swapi.dev/api/films/2/")).or(containsString("https://swapi.dev/api/films/6/")));
	
	assertThat(speed, closeTo(1000, 30));
	
	assertThat(pilots, is(emptyCollectionOf(String.class)));
	
	assertThat(films, is(not(emptyCollectionOf(String.class))));
	
	assertThat(model, both(containsString("Imperial")).and(containsString("Destroyer")));
	
	assertThat(shipCapacity, is(cargoCapacity()));
	
	

	}

}