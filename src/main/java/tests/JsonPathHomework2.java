package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent2;
import utils.DataBuilder;

public class JsonPathHomework2 extends BaseComponent2{
	
	String id;
	
    @Test(priority=1)
	public void postToDo() {
		Response result = doPost( DataBuilder.buildTodo().toJSONString());
		id = result.jsonPath().getString("id");
		System.out.println(result.asString());
		
	}

}
