package tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;


import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.NumberChecker.numbersOnly;


public class HamcrestHomework1 {
	
	
	@Test
    public void hamcrestMatchersHomework1() {
		
	Response resp = given().get("https://swapi.dev/api/people/1/").then().extract().response();
	System.out.println(resp.asPrettyString());
	
	JsonPath jsonPath = resp.jsonPath();
	
	String name = jsonPath.getString("name");
	int height = jsonPath.getInt("height");
	int mass = jsonPath.getInt("mass");
	String hair = jsonPath.getString("hair_color");
	String skin = jsonPath.getString("skin_color");
	String eye = jsonPath.getString("eye_color");
	String[] features = {hair, skin, eye};
	String[] attributes = {"blond", "fair", "blue"};
	String birth = jsonPath.getString("birth_year");
	List<String> species = new ArrayList<>(jsonPath.getList("species"));
	List<String> vehicles = new ArrayList<>(jsonPath.getList("vehicles"));
	List<String> starships = new ArrayList<>(jsonPath.getList("starships"));
	
	assertThat(name, is("Luke Skywalker"));
	
	assertThat(height, is(greaterThan(171)));
	
	assertThat(mass, is(lessThan(80)));
	
	assertThat(features, is(equalTo(attributes)));
	
	assertThat(birth, is(not(numbersOnly())));
	
	assertThat(species, is(emptyCollectionOf(String.class)));
	
	assertThat(vehicles.size(), equalTo(starships.size()));
	
	assertThat(vehicles, is(not(equalTo(starships))));
		
				
	}

}
