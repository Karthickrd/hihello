package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.manager.PayloadManager;
import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.AddUserAddress_Output_Pojo;
import com.pojo.address.DeleteAddressOutput_Pojo;
import com.pojo.address.DeleteUserAddress_Input_Pojo;
import com.pojo.address.GetUserAddressOutput_Pojo;
import com.pojo.address.Update_User_Address_Input;
import com.pojo.address.Update_User_Address_Output;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC4_AddressStep extends BaseClass {
	PayloadManager pm = new PayloadManager();
	Response response;

	@Given("User add header and bearer authurization for accesing address endpoints")
	public void user_add_header_and_bearer_authurization_for_accesing_address_endpoints() {
		String logtoken = TC1_LoginStep.globalData.getLogtoken();
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

	}

	@When("User add request body for add new address {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void user_add_request_body_for_add_new_address_and(String first_name, String last_name, String mobile,
			String apartment, String stateId, String cityId, String country, String zipcode, String address,
			String addressType) {

		Integer countryIdNum = Integer.valueOf(country);
		int stateIdNum = TC1_LoginStep.globalData.getStateIdNum();
		int cityIdNum = TC1_LoginStep.globalData.getCityIdNum();

		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = pm.getAddressPayload().addUserAddress_Input_Pojo(
				first_name, last_name, mobile, apartment, stateIdNum, cityIdNum, countryIdNum, zipcode, address,
				addressType);
		addBody(addUserAddress_Input_Pojo);

	}

	@When("User send {string} request for addUserAddress endpoint")
	public void user_send_request_for_add_user_address_endpoint(String reqType) {
		response = addReqType(reqType, Endpoints.ADDUSERADDRESS);

		int actStatusCode = getStatusCode(response);
		System.out.println(actStatusCode);
		TC1_LoginStep.globalData.setStatusCode(actStatusCode);

	}

	@Then("User verify the addUserAddress response message matches {string} and save the address Id")
	public void user_verify_the_add_user_address_response_message_matches_and_save_the_address_id(
			String expAddSucessMessage) {

		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);

		int addressIdNum = addUserAddress_Output_Pojo.getAddress_id();
		String address_id = String.valueOf(addressIdNum);
		System.out.println(address_id);
		TC1_LoginStep.globalData.setAddress_id(address_id);
		String actMessage = addUserAddress_Output_Pojo.getMessage();

		Assert.assertEquals("Address added successfully", expAddSucessMessage, actMessage);

	}

	@When("User add request body for update user address with saved addressId {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void user_add_request_body_for_update_user_address_with_saved_address_id_and(String first_name,
			String last_name, String mobile, String apartment, String stateId, String cityId, String country,
			String zipcode, String address, String addressType) {

		Integer countryIdNum = Integer.valueOf(country);
		int stateIdNum = TC1_LoginStep.globalData.getStateIdNum();
		int cityIdNum = TC1_LoginStep.globalData.getCityIdNum();
		String address_id = TC1_LoginStep.globalData.getAddress_id();

		Update_User_Address_Input updateUserAddress = pm.getAddressPayload().updateUserAddress(address_id, first_name,
				last_name, mobile, apartment, stateIdNum, cityIdNum, countryIdNum, zipcode, address, addressType);
		addBody(updateUserAddress);
	}

	@When("User send {string} request for updateUserAddress endpoint")
	public void user_send_request_for_update_user_address_endpoint(String reqType) {

		response = addReqType(reqType, Endpoints.UPDATEUSERADDRESS);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globalData.setStatusCode(actStatusCode);

	}

	@Then("User verify the updateUserAddress response message matches {string}")
	public void user_verify_the_update_user_address_response_message_matches(String expAddSucessMessage) {
		Update_User_Address_Output update_User_Address_Output = response.as(Update_User_Address_Output.class);
		String actmessage = update_User_Address_Output.getMessage();
		System.out.println(actmessage);
		Assert.assertEquals("Verify Address updated successfully", expAddSucessMessage, actmessage);

	}

	@Given("User add header and bearer authorization for accessing getUserAddress endpoint")
	public void user_add_header_and_bearer_authorization_for_accessing_get_user_address_endpoint() {
		String logtoken = TC1_LoginStep.globalData.getLogtoken();
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

	}

	@Given("User send {string} request for getUserAddress endpoint")
	public void user_send_request_for_get_user_address_endpoint(String reqType) {

		response = addReqType(reqType, Endpoints.GETUSERADDRESS);
		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globalData.setStatusCode(actStatusCode);

	}

	@Then("User Should verify the getUserAddress response message matches {string}")
	public void user_should_verify_the_get_user_address_response_message_matches(String expSuccessMessage) {

		GetUserAddressOutput_Pojo getUserAddressOutput_Pojo = response.as(GetUserAddressOutput_Pojo.class);
		String actmessage = getUserAddressOutput_Pojo.getMessage();
		Assert.assertEquals("Verify get message", expSuccessMessage, actmessage);

	}

	@When("User add request body for delete user address with saved addressId")
	public void user_add_request_body_for_delete_user_address_with_saved_address_id() {
		String address_id = TC1_LoginStep.globalData.getAddress_id();
		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = pm.getAddressPayload()
				.deleteUserAddress(address_id);
		addBody(deleteUserAddress_Input_Pojo);

	}

	@When("User send {string} request for deleteUserAddress endpoint")
	public void user_send_request_for_delete_user_address_endpoint(String reqType) {
		response = addReqType(reqType, Endpoints.DELETEADDRESS);

		int actStatusCode = getStatusCode(response);
		TC1_LoginStep.globalData.setStatusCode(actStatusCode);

	}

	@Then("User Should verify the deleteUserAddress response message matches {string}")
	public void user_should_verify_the_delete_user_address_response_message_matches(String expdeleteSuccessMessage) {
		DeleteAddressOutput_Pojo deleteAddressOutput_Pojo = response.as(DeleteAddressOutput_Pojo.class);

		String actMessage = deleteAddressOutput_Pojo.getMessage();
		System.out.println("The Actual Message for deleteUserAddress" + actMessage);
		Assert.assertEquals("Verify get message", expdeleteSuccessMessage, actMessage);

	}

}
