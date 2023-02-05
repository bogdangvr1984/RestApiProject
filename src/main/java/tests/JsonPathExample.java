package tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent2;

public class JsonPathExample extends BaseComponent2 {
	
	@Test
	public void getAllUsers() {
		
		Response result = doGetAll();
		
		System.out.println(result.asString());
		assertTrue(result.asString().contains("Caesar"));
		
		JsonPath jsonPath = result.jsonPath();
		
		System.out.println(jsonPath.getString("users"));
		
		//get based on index in array
		System.out.println("get by index " +jsonPath.getString("user[1]"));
		
		//read size of the array
		System.out.println("get size of array " +jsonPath.getString("users.size()"));
		
		//get filed from object
		System.out.println("get field from index object " +jsonPath.getString("users[2].email"));
		System.out.println("get field from index object " +jsonPath.getString("users[2].name"));
		System.out.println("get field from index object " +jsonPath.getString("users[2].age"));
		System.out.println("get field from index object " +jsonPath.getString("users[2]._id"));
		
		//get all field values from all objects
		System.out.println("get all emails " +jsonPath.getString("users.email"));
		System.out.println("get all ids " +jsonPath.getString("users._id"));
		
		System.out.println("-------------------------------------------------------------------");
		
		System.out.println("get all genders :" +jsonPath.getString("users.gender"));
		
		List<String> allMales = jsonPath.getList("users.findAll{it.gender == 'm'}.gender");
		System.out.println(allMales.size());
		System.out.println(allMales);
		List<String> allFemales = jsonPath.getList("users.findAll{it.gender == 'f'}.gender");
		System.out.println(allFemales.size());
		System.out.println(allFemales);
		List<String> allFemalesEmails = jsonPath.getList("users.findAll{it.gender == 'f'}.email");
		System.out.println(allFemalesEmails);
		List<String> allFemalesIds = jsonPath.getList("users.findAll{it.gender == 'f'}._id");
		System.out.println(allFemalesIds);
		
		System.out.println("------------------------------");
		
		List<String> allFritz = jsonPath.getList("users.findAll{it.name == 'Fritz'}.age");
		System.out.println(allFritz);
		
		String FritzObject = jsonPath.getString("users.find{it.name == 'Fritz' & it.age == 22}");
		System.out.println(FritzObject);
		
		String FritzEmail = jsonPath.getString("users.find{it.name == 'Fritz' & it.age == 22}.email");
		System.out.println(FritzEmail);
		
		List<String> olderFritz = jsonPath.getList("users.findAll{it.name == 'Fritz' & it.age >68}");
		System.out.println(olderFritz);
		System.out.println(olderFritz.size());
		
		List<String> allNames = jsonPath.getList("users.findAll{it.name == 'Fritz' | it.name == 'Bogdan'}");
		System.out.println(allNames);
		System.out.println(allNames.size());
		
		List<String> ageLessThan = jsonPath.getList("users.findAll{it.age <45}");
		System.out.println(ageLessThan.size());

		System.out.println(ageLessThan);
		
	//	System.out.println(ageLessThan.get(0));
	}

}
