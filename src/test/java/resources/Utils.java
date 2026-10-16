package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req; // It will declare globally(static) firstly it will assign from first data and when second data come it will not null and reassign them.
	public  RequestSpecification requestSpecification() throws IOException {
		
		if(req==null) { // It helps to get proper log otherwise  second data set will replace first one 
		PrintStream log= new PrintStream(new FileOutputStream("logging.txt"));// Help to generate a File logging.txt to log all the request
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))// It help to log all the request
				.addFilter(ResponseLoggingFilter.logResponseTo(log)) // It help to log all the response log
				.setContentType(ContentType.JSON)
				.build(); // For request
		return req;
		}
		return req;
	}
	
	public static String getGlobalValue(String Key) throws IOException {
		
		Properties prop=new Properties(); // Predefined class in java help to read properties file value
		
		FileInputStream fis=new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\API_Framework\\src\\test\\java\\resources\\global.properties"); // Help to get file and pass to properties class.
		 prop.load(fis);
		return prop.getProperty(Key);
		 
		 
		
		
	}
	// It help to get any value of the key from the response body
	public String getJsonPath(Response response,String Key) { 
		String res=response.asString();
		JsonPath js=new JsonPath(res);
		return js.get(Key).toString();
	}

}
