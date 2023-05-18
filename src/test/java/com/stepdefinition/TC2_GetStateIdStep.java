package com.stepdefinition;

import java.util.ArrayList;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.address.StateListDatum;
import com.pojo.address.StateListOutput_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;


public class TC2_GetStateIdStep extends BaseClass {
	Response response;

	@Given("User and headers for to StateList")
	public void user_and_headers_for_to_state_list() {
		addHeader("accept", "application/json");
	}

	@When("User send {string} request for StateList endpoint")
	public void user_send_request_for_state_list_endpoint(String reqType) {
		response = addReqType(reqType, Endpoints.STATELIST);
		int statuscode = getStatusCode(response);
		TC1_LoginStep.globalData.setStatusCode(statuscode);
	}

	@Then("User verify the StateList response message matches {string} and saved that id")
	public void user_verify_the_state_list_response_message_matches_and_saved_that_id(String expstatename) {
		
		StateListOutput_Pojo stateListOutput_Pojo = response.as(StateListOutput_Pojo.class);
		
		ArrayList<StateListDatum> stateList = stateListOutput_Pojo.getData();
	
		for (StateListDatum eachStateList : stateList) {
			String stateName = eachStateList.getName();

			if (stateName.equals(expstatename)) {
				int stateIdNum = eachStateList.getId();
				String state_id = String.valueOf(stateIdNum);
				System.out.println("The State id of Tamil Nadu-->" + stateIdNum);

				TC1_LoginStep.globalData.setStateIdNum(stateIdNum);
				TC1_LoginStep.globalData.setStateId(state_id);
				break;
			}
			String actMessage = stateListOutput_Pojo.getMessage();
			Assert.assertEquals("Verify OK", "OK", actMessage);

		}
	}

}
