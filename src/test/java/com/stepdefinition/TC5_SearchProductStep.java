package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.manager.PayloadManager;
import com.pojo.product.SearchProductInput_Pojo;
import com.pojo.product.SearchProductOutput_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC5_SearchProductStep extends BaseClass {

	Response response;
	PayloadManager pm = new PayloadManager();

	@Given("User add header for searchProduct")
	public void user_add_header_for_search_product() {

		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

	}

	@When("User add request body for searchProduct {string}")
	public void user_add_request_body_for_search_product(String productName) {

		SearchProductInput_Pojo searchProductInput_Pojo = pm.getSearchPayload().searchProduct(productName);

		addBody(searchProductInput_Pojo);

	}

	@When("User send {string} request for searchProduct endpoint")
	public void user_send_request_for_search_product_endpoint(String reqType) {

		response = addReqType(reqType, Endpoints.SEARCHPRODUCT);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalData.setStatusCode(statusCode);
	}

	@Then("User Should verify the searchProduct response message matches {string}")
	public void user_should_verify_the_search_product_response_message_matches(String expSuccesMsg) {
		
		SearchProductOutput_Pojo searchProductOutput_Pojo = response.as(SearchProductOutput_Pojo.class);
		
		String actSuccessMessage = searchProductOutput_Pojo.getMessage();
		
		System.out.println("The Actual Message for searchProduct-->" + actSuccessMessage);
		Assert.assertEquals("Verify Search Product", "OK", expSuccesMsg);
		
	}

}
