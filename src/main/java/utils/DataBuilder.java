package utils;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder {
	
	public static JSONObject buildTodo() {
		
		JSONObject bodyBuilder = new JSONObject();
		Faker faker = new Faker();
		
		bodyBuilder.put("title", faker.book().title());
		bodyBuilder.put("body", faker.address().fullAddress());
		
		return bodyBuilder;
	}
	
	public static JSONObject buildUser() {
		
		JSONObject bodyBuilder = new JSONObject();
        Faker faker = new Faker();
		
		bodyBuilder.put("name", faker.name().firstName());
		bodyBuilder.put("email", faker.internet().safeEmailAddress());
		bodyBuilder.put("age", faker.number().numberBetween(5, 130));
		bodyBuilder.put("gender", "m");
		
		return bodyBuilder;
		
	}
	
	public static JSONObject buildPassenger() {
		
		JSONObject bodyBuilder = new JSONObject();
		Faker faker = new Faker();
		
		bodyBuilder.put("name", faker.name().firstName());
		bodyBuilder.put("trips", 250);
		bodyBuilder.put("airline", 1981);
		
		return bodyBuilder;
		
	}
	
    public static JSONObject updatePassenger(String nume) {
		
		JSONObject bodyBuilder = new JSONObject();
		Faker faker = new Faker();
		
		bodyBuilder.put("name", faker.name().firstName());
		bodyBuilder.put("trips", 300);
		bodyBuilder.put("airline", 1);
		
		return bodyBuilder;
		
	}

}
