
package stepDefinations; // Match this exact package name to your folder name

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import resources.Utils;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace_Map;
import pojo.Location_Map;
import resources.APIResource_Enum;
import resources.TestDataBuild;
import io.cucumber.java.en.Then;

public class StepDefinations extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	static String place_id;// TO make it static because we need place ID when we run delete api otherwise it store null value.
	Response response;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload(String name, String Language, String Address) throws IOException {

		res = given().spec(requestSpecification()).body(data.addPlaceDataPayLoad(name,Language,Address));
	}

	@When("user calls {string} with post {string} request")
	public void user_calls_with_post_http_request(String resource,String method) {
		// Constructor will be called with value of resource which you pass
		APIResource_Enum resourceAPI=APIResource_Enum.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) {
		/*
		 response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
	*/
			response=res.when().post(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("GET")) {
			response=res.when().get(resourceAPI.getResource());
		}
	}
	

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
//		String resp = response.asString();
//		JsonPath js = new JsonPath(resp);
		
//		assertEquals(js.get(key).toString(), expectedValue);
		
		assertEquals(getJsonPath(response,keyValue),expectedValue);
	}
	
	

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id=getJsonPath(response,"place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		// resource-->Get place api-->"/maps/api/place/get/json"
		user_calls_with_post_http_request(resource, "GET");
		String actualName=getJsonPath(response, "name");
		assertEquals(actualName,expectedName);
	}
	
	

	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	 res=   given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}






	
	

	

}
