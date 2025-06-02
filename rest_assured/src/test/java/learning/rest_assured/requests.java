package learning.rest_assured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 * given() -> pre-requsitives has to be defined in given() method
				content type, set cookies, add auth, add param, set headers info etc..
 * when() -> request type has to be defined in when() method
 				get, post, put, delete
 * then() -> validation has to be defined in then() method
 				validate status code, extract response, extract header cookies and response body etc..
*/
public class requests {

	/* 
	 	Get: https://reqres.in/api/users?page=2
	 	Post: https://reqres.in/api/users
	 	 	 Body: {
					    "name": "morpheus",
					    "job": "leader"
					}
		Put: https://reqres.in/api/users/2
			 Body: {
				    	"name": "morpheus",
				    	"job": "zion resident"
					}
		Delete: https://reqres.in/api/users/2			 
	*/
	
	int id;
	@Test
	public void getuser()
	{
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")			// pass the get request url
			
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
	@Test
	public void createuser()
	{
		
		HashMap<String, String> map= new HashMap<String,String>();
		map.put("name", "Rahul");										// body params are defined using hashmap
		map.put("job", "Rider");
		
		id=given()
			.contentType("application/json")							// content.type has to be defined
			.body(map)													// hashmap object is passed inside body values
			.header("x-api-key", "reqres-free-v1")
			
		.when()
			.post("https://reqres.in/api/users")			// pass the post request url
			.jsonPath().getInt("id");
		
//		.then()
//			.statusCode(201)
//			.body("name",equalTo("Rahul"))
//			.log().all();
	}
	
	@Test(dependsOnMethods="createuser")
	public void udpate_user()
	{
		
		HashMap<String, String> map= new HashMap<String,String>();
		map.put("name", "Rahul");										// body params are defined using hashmap
		map.put("job", "Tester");
		
		given()
			.contentType("application/json")							// content.type has to be defined
			.body(map)													// hashmap object is passed inside body values
			.header("x-api-key", "reqres-free-v1")
			
		.when()
			.put("https://reqres.in/api/users/id")						// pass the put request url
			
			
		.then()
			.statusCode(200)
			.body("name",equalTo("Rahul"))
			.log().all();
	}

	@Test(dependsOnMethods="createuser")
	public void delete_user()
	{
				
		given()
			.contentType("application/json")							// content.type has to be defined
			.header("x-api-key", "reqres-free-v1")
			
		.when()
			.delete("https://reqres.in/api/users/id")						// pass the delete request url
			
		.then()
			.statusCode(204)
			.log().all();
	}
	
	
}
