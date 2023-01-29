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

public class HamcrestMatchersExample {
	
	@Test
	public void hamcrestMatchers() {
		
		Response resp = given().get("https://swapi.dev/api/planets/1/").then().extract().response();
		System.out.println(resp.asString());
		
		JsonPath jsonPath = resp.jsonPath();
		
		String nume = jsonPath.getString("name");
		
		assertEquals(nume, "Tatooine");
		assertThat(nume, equalTo("Tatooine"));
		assertThat(nume, is(equalTo("Tatooine")));
		assertThat(nume, is("Tatooine"));
		//is(T value) fie is(Matcher<T>)
		
		//not(T value) fie is(Matcher<T>)
		assertThat(nume, is(not("Pamant")));
		assertThat(nume, is(not(equalTo("Pamant"))));
		assertThat(nume, is(not(instanceOf(Integer.class))));
		
		//starts with -- startsWithIgnoringCase
		assertThat(resp.asString(), startsWith("{\"name"));
		assertThat(resp.asString(), startsWithIgnoringCase("{\"NAME"));
		
		//endsWith -- endWithIgnoringCase
		assertThat(resp.asString(), endsWith("api/planets/1/\"}"));
		assertThat(resp.asString(), endsWithIgnoringCase("aPi/pLAnEtS/1/\"}"));
		
		//contains -- containsIgnoringCase
		assertThat(jsonPath.getString("created"), containsString(":"));
		assertThat(nume, containsStringIgnoringCase("tooin"));
		
		//collection
		List<String> films = new ArrayList<>(jsonPath.getList("films"));
		System.out.println(films);
		
		assertThat(films, contains(
				"https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));
		
		assertThat(films, containsInAnyOrder(
				"https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));
		
		assertThat(films, contains(
								startsWith("https"),
								endsWith("3/"),
								equalTo("https://swapi.dev/api/films/4/"),
								startsWith("https://swapi.dev"),
								endsWith("films/6/")
				));
		
		assertThat(films, hasItem("https://swapi.dev/api/films/6/"));
		assertThat(films, hasItems(
					"https://swapi.dev/api/films/1/",
					"https://swapi.dev/api/films/4/",
					"https://swapi.dev/api/films/6/"));
		
		assertThat(films, hasItem(startsWith("https:")));
		assertThat(films, hasItems(
				endsWith("/6/"),
				startsWith("ht"),
				endsWith("/3/")));
		
		assertThat(films, hasSize(5));
		assertThat(films, hasSize(lessThanOrEqualTo(10)));
		assertThat(10, is(lessThanOrEqualTo(12)));
		
		assertThat(films, hasSize(greaterThan(3)));
		
		assertThat(films.get(0), containsString("1"));
		assertThat(films, hasToString(containsString("1")));
		
		assertThat(films, both(hasSize(lessThan(10))).and(hasToString(containsString("dev"))));
		
		films.clear();
		
		assertThat(films, is(empty()));
		assertThat(films, is(emptyCollectionOf(String.class)));
		
		String[] myarr = {jsonPath.getString("rotation_period"), jsonPath.getString("orbital_period")};
		System.out.println(myarr[0]);
		System.out.println(myarr[1]);
		System.out.println(myarr.length);
		
		//assertThat(myarr, is(not(emptyArray())));
		
		myarr = null;
		assertThat(myarr, is(nullValue()));
		
		String[] array = {
				"https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"		
		};
		
		List<String> films2 = new ArrayList<>(jsonPath.getList("films"));		
		assertThat(array, equalTo(films2.toArray()));
		
		assertThat(array, arrayContaining(
				"https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"
		        ));
		
		//stringContainsInOrder
		assertThat(resp.asString(), stringContainsInOrder("rotation_period", "diameter"));
		
		//and
		assertThat(nume, both(containsString("T")).and(containsString("oo")));
		//or
		assertThat(nume, either(is("Tatooine")).or(is("Tatooine3")).or(is("Tatooine7")));
		
		String population = jsonPath.getString("population");
		String diameter = jsonPath.getString("diameter");
		String gravity = jsonPath.getString("gravity");
		
		
		assertThat(population, is(numbersOnly()));
		assertThat(gravity, is(not(numbersOnly())));
		
		}
		
	}
