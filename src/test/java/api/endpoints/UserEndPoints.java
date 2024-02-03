package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import api.utilities.helpers.RestReqFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


//UserEndPints.java
// Created for perform Create, Read, Update, Delete requests t the user API.

public class UserEndPoints {
	
	public static RestReqFilter restReqFilter;
	
	public static Response createUser(User payload)
	{
		restReqFilter = new RestReqFilter();
		
		Response response=given()
				.filter(restReqFilter)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
			
		return response;
	}
	
	public static Response readUser(String userName)
	{
		restReqFilter = new RestReqFilter();
		
		Response response=given()
						.pathParam("username",userName)
						.filter(restReqFilter)
		.when()
			.get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		restReqFilter = new RestReqFilter();
		
		Response response=given()
				.filter(restReqFilter)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Routes.update_url);
			
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		restReqFilter = new RestReqFilter();
		
		Response response=given()
						.pathParam("username",userName)
						.filter(restReqFilter)
		.when()
			.delete(Routes.delete_url);
			
		return response;
	}
		
}
