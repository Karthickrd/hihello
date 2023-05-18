package com.stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.global.GlobalData;
import com.pojo.login.Root;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC1_LoginStep extends BaseClass {

	Response response;

	// object of the Global Data pojo class
	static GlobalData globalData = new GlobalData();

	@Given("User add Header")
	public void user_add_header() {
		addHeader("accept", "application/json");
	}

	@When("User add basic authentication for login")
	public void user_add_basic_authentication_for_login() throws FileNotFoundException, IOException {
		addBaseAthu(getPropertyFileValue("username"), getPropertyFileValue("password"));
	}

	@When("User send {string} request for login endpoint")
	public void user_send_request_for_login_endpoint(String reqType) {
		response = addReqType(reqType, Endpoints.POSTMANBASICAUTH);
		int statusCode = getStatusCode(response);

		// to set the status code global by Global data pojo
		globalData.setStatusCode(statusCode);
	}

	@Then("User Should verify the login response body firstName present as {string} and get the logtoken saved")
	public void user_should_verify_the_login_response_body_first_name_present_as_and_get_the_logtoken_saved(
			String expFirstName) {

		Root root = response.as(Root.class);

		// to get the actual message
		String actFirstName = root.getData().getFirst_name();
		System.out.println("actFirstName-->" + actFirstName);
		System.out.println("expFirstName-->" + expFirstName);

		// To get the logtoken to authorize address..and declare it to the global level
		String logtoken = root.getData().getLogtoken();

		// to set the log token global by Global data pojo
		globalData.setLogtoken(logtoken);

		Assert.assertEquals("Verify First Name", expFirstName, actFirstName);

	}

}
