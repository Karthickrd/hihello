package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.manager.PayloadManager;
import com.pojo.address.CityListDaum;
import com.pojo.address.CityListInput_Pojo;
import com.pojo.address.CityListOutput_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class TC3_GetCityIdStep extends BaseClass {
	Response response;
	PayloadManager pm = new PayloadManager();
	
	@Given("User add headers to the CityList")
	public void user_add_headers_to_the_city_list() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		
	}
	@When("User add request body for City with saved stateid")
	public void user_add_request_body_for_city_with_saved_stateid() {
		String state_id = TC1_LoginStep.globalData.getStateId();
		
		CityListInput_Pojo cityListInput_Pojo = pm.getAddressPayload().getCityList(state_id);
		addBody(cityListInput_Pojo);
		
		
	}
	@When("User send {string} request for CityList endpoint")
	public void user_send_request_for_city_list_endpoint(String reqtype) {
		 response = addReqType(reqtype, Endpoints.CITYLIST);
		 int statuscode = getStatusCode(response);
		 TC1_LoginStep.globalData.setStatusCode(statuscode);
	        

		

	}
	@Then("User Should verify the CityList response message matches {string} and saved that id")
	public void user_should_verify_the_city_list_response_message_matches_and_saved_that_id(String expCityName) {
		
		  CityListOutput_Pojo cityListOutput_Pojo = response.as(CityListOutput_Pojo.class);
		  addBody(cityListOutput_Pojo);
	        

			
			ArrayList<CityListDaum> cityList = cityListOutput_Pojo.getData();

			for (CityListDaum eachCityList : cityList) {

				String cityName = eachCityList.getName();
				
				if (cityName.equals(expCityName)) {
				int	cityIdNum = eachCityList.getId();
				TC1_LoginStep.globalData.setCityIdNum(cityIdNum);
					System.out.println("City Id of Tenkasi-->" + cityIdNum);
					String cityId = String.valueOf(cityIdNum);
					TC1_LoginStep.globalData.setCityId(cityId);
					break;
				}
			}
			
			String actMessage =  cityListOutput_Pojo.getMessage();
			System.out.println("The actual Message for CityList--->"+ actMessage);
		    Assert.assertEquals("Verify OK", "OK" , actMessage);

	}




}
