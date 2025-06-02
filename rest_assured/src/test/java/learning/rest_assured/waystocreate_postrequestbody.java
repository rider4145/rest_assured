package learning.rest_assured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class waystocreate_postrequestbody {

/*	
 *	Different many ways to create request body
 *	1. Hashmap
 *	2. using org.json library
 *	3. using POJO(plain object java object) class -> we need to create a separate class
 *	4. using external json file
*/
	
	@Test
	public void hashmap()
	{
	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "Ajith");
		map.put("location", "Chennai");
		map.put("phone", "9876543210");
		
		String courseArr[] = {"C","C++"};
		
		map.put("courses", "coureArr");
		
		given()
			.contentType("application/json")
			.body(map)
			
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name",equalTo("Ajith"))
			.body("location", equalTo("Chennai"))
			.body("phone", equalTo("9876543210"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type", "application/json; chartset=utf-8")
			.log().all();
	}
	
	@Test(dependsOnMethods="hashmap")
	public void delete_user()
	{
	
		given()
			.contentType("application/json")
		
		.when()
			.delete("http://localhost:3000/students/4")
			
		.then()
			.statusCode(200);
	}
	
}
