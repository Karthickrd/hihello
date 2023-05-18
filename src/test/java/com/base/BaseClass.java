package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	Header headers;
	RequestSpecification reqSpec;
	public static Response response;

	public void addHeader(String Key, String Value) {
		reqSpec = RestAssured.given().header(Key, Value);

	}

	public void addBaseAthu(String username, String Password) {
		reqSpec = reqSpec.auth().preemptive().basic(username, Password);

	}

	public void addPathParam(String Key, String Value) {
		reqSpec = reqSpec.pathParam(Key, Value);
	}

	public void addQueryParam(String Key, String Value) {
		reqSpec = reqSpec.queryParam(Key, Value);

	}

	public void addBody(String Body) {
		reqSpec = reqSpec.body(Body);

	}

	public Response addReqType(String type, String endpoint) {
		switch (type) {
		case "GET":
			response = reqSpec.log().all().get(endpoint);

			break;

		case "POST":
			response = reqSpec.log().all().post(endpoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endpoint);
			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endpoint);
			break;
		case "PATCH":
			response = reqSpec.log().all().patch(endpoint);
			break;

		default:
			break;
		}
		return response;

	}

	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;

	}

	public String getResBodyAsString(Response response) {
		String asString = response.asString();
		return asString;

	}

	public String getResBodyAsPrettyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;
	}

	public void addHeaders(Headers headers) {
		reqSpec = RestAssured.given().headers(headers);
	}

	public void addBody(Object body) {
		reqSpec = reqSpec.body(body);
	}

	public static String getProjectPath() {
		String path = System.getProperty("user.dir");
		return path;
	}

	public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();

		properties.load(new FileInputStream(getProjectPath() + "\\Config\\config.properties"));
		String property = properties.getProperty(key);

		return property;
	}

}
