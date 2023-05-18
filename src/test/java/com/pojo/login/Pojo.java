package com.pojo.login;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.manager.PayloadManager;
import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.AddUserAddress_Output_Pojo;
import com.pojo.address.CityListDaum;
import com.pojo.address.CityListInput_Pojo;
import com.pojo.address.CityListOutput_Pojo;
import com.pojo.address.DeleteAddressOutput_Pojo;
import com.pojo.address.DeleteUserAddress_Input_Pojo;
import com.pojo.address.GetUserAddressOutput_Pojo;
import com.pojo.address.StateListDatum;
import com.pojo.address.StateListOutput_Pojo;
import com.pojo.address.Update_User_Address_Input;
import com.pojo.address.Update_User_Address_Output;
import com.pojo.product.SearchProductInput_Pojo;
import com.pojo.product.SearchProductOutput_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Pojo extends BaseClass {
	String logtoken;
	String address_id;
	String state_id;
	int stateIdNum;
	int cityIdNum;
	PayloadManager plm = new PayloadManager() ;

	@Test(priority = 1)

	public void basicAuth() {
		addHeader("accept", "application/json");
		addBaseAthu("karthickmech496@gmail.com", "@KarthicK1996");
		Response response = addReqType("POST", Endpoints.POSTMANBASICAUTH);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Assert.assertEquals(statusCode, 200, "Verify statuscode");
		Root as = response.as(Root.class);
		String first_name = as.getData().getFirst_name();
		System.out.println(first_name);
		logtoken = as.getData().getLogtoken();
		Assert.assertEquals(first_name, "Karthick", "Verify first name");

	}

	@Test(priority = 4)

	public void addUserAddress() {

		// 1.Headers
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		// 2.payload
//		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo("Karthick", "Rajendran",
//				"7305055527", "HappyStay", 105, 765, 100, "600097", "Thoraippakkam", "Work");
		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = plm.getAddressPayload().addUserAddress_Input_Pojo("Karthick", "Rajendran",
				"7305055527", "HappyStay", 105, 765, 100, "600097", "Thoraippakkam", "Work");
		

		addBody(addUserAddress_Input_Pojo);

		// 3.req type

		Response response = addReqType("POST", Endpoints.ADDUSERADDRESS);

		int actStatusCode = getStatusCode(response);
		System.out.println(actStatusCode);

		Assert.assertEquals(actStatusCode, 200, "Verify status code");

		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);

		String actMessage = addUserAddress_Output_Pojo.getMessage();

		Assert.assertEquals(actMessage, "Address added successfully", "Verify Address added successfully");
		int addressIdNum = addUserAddress_Output_Pojo.getAddress_id();
		address_id = String.valueOf(addressIdNum);

	}

	@Test(priority = 5)

	public void updateUserAddress() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		// pay load
//		Update_User_Address_Input update_User_Address_Input = new Update_User_Address_Input(address_id, "Karthick",
//				"Rajendran", "7305055527", "HappyStay", 105, 765, 100, "600097", "Thoraippakkam", "Work");
		Update_User_Address_Input updateUserAddress = plm.getAddressPayload().updateUserAddress(address_id, "Karthick",
				"Rajendran", "7305055527", "HappyStay", 105, 765, 100, "600097", "Thoraippakkam", "Work");

		addBody(updateUserAddress);

		Response response = addReqType("PUT", Endpoints.UPDATEUSERADDRESS);
		int actStatusCode = getStatusCode(response);
		System.out.println(actStatusCode);

		Assert.assertEquals(actStatusCode, 200, "Verify status code");

		Update_User_Address_Output update_User_Address_Output = response.as(Update_User_Address_Output.class);
		String actmessage = update_User_Address_Output.getMessage();
		Assert.assertEquals(actmessage, "Address updated successfully", "Verify Address updated successfully");

	}

	@Test(priority = 6)

	public void getUserAddress() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		Response response = addReqType("GET", Endpoints.GETUSERADDRESS);
		int actStatusCode = getStatusCode(response);
		System.out.println(actStatusCode);

		Assert.assertEquals(actStatusCode, 200, "Verify status code");

		GetUserAddressOutput_Pojo getUserAddressOutput_Pojo = response.as(GetUserAddressOutput_Pojo.class);
		String actmessage = getUserAddressOutput_Pojo.getMessage();
		Assert.assertEquals(actmessage, "OK", "Verify get Address");

	}

	@Test(priority = 7)
	public void deleteUserAddress() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		// 2 pay load
	//	DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = new DeleteUserAddress_Input_Pojo(address_id);
		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = plm.getAddressPayload().deleteUserAddress(address_id);
		addBody(deleteUserAddress_Input_Pojo);

		// 3.reqtype

		Response response = addReqType("DELETE", Endpoints.DELETEADDRESS);

		int actStatusCode = getStatusCode(response);
		System.out.println("The Actual Status Code for deleteUserAddress-->" + actStatusCode);
		Assert.assertEquals(actStatusCode, 200, "Verify Status Code");

		DeleteAddressOutput_Pojo deleteAddressOutput_Pojo = response.as(DeleteAddressOutput_Pojo.class);

		String actMessage = deleteAddressOutput_Pojo.getMessage();
		System.out.println("The Actual Message for deleteUserAddress" + actMessage);
		Assert.assertEquals(actMessage, "Address deleted successfully", "Verify Address deleted successfully");
	}

	@Test(priority = 8)
	public void searchProduct() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);

		// 2.payload
	//	SearchProductInput_Pojo searchProductInput_Pojo = new SearchProductInput_Pojo("nuts");
		SearchProductInput_Pojo searchProductInput_Pojo = plm.getSearchPayload().searchProduct("nuts");
		addBody(searchProductInput_Pojo);

		// 3.reqtype
		Response response = addReqType("POST", Endpoints.SEARCHPRODUCT);
		int actStatusCode = getStatusCode(response);
		System.out.println("The Actual Status Code for searchProduct" + actStatusCode);
		Assert.assertEquals(actStatusCode, 200, "Verify Status Code");

		SearchProductOutput_Pojo searchProductOutput_Pojo = response.as(SearchProductOutput_Pojo.class);
		String actMessage = searchProductOutput_Pojo.getMessage();
		System.out.println("The Actual Message for searchProduct" + actMessage);
		Assert.assertEquals(actMessage, "OK", "Verify Search Product");
	}

	@Test(priority = 2)

	public void getStateList() {

		// 1.Headers
		addHeader("accept", "application/json");

		// 2.reqType
		Response response = addReqType("GET", Endpoints.STATELIST);
		int actStatusCode = getStatusCode(response);
		System.out.println("The Actual Status Code for getStateList-->" + actStatusCode);
		Assert.assertEquals(actStatusCode, 200, "Verify Status Code");

		StateListOutput_Pojo stateListOutput_Pojo = response.as(StateListOutput_Pojo.class);
		// to get the state list
		ArrayList<StateListDatum> stateList = stateListOutput_Pojo.getData();
		for (StateListDatum eachStateList : stateList) {
			String stateName = eachStateList.getName();
			// to get State Id for Tamil Nadu-->this id is used to pass in addUserAddress
			if (stateName.equals("Tamil Nadu")) {
				stateIdNum = eachStateList.getId();
				state_id = String.valueOf(stateIdNum);
				System.out.println("The State id of Tamil Nadu-->" + stateIdNum);
				break;
			}
		}
		// to get the actual message
		String actMessage = stateListOutput_Pojo.getMessage();
		System.out.println("The Actual message for getStateList-->" + actMessage);
		Assert.assertEquals(actMessage, "OK", "Verify OK");
	}

	@Test(priority = 3)

	private void getCityList() {

		// 1.Headers
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");

		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		addHeaders(headers);
		
		// 2.pay load

	//	CityListInput_Pojo cityList_Input_Pojo = new CityListInput_Pojo(state_id);
		CityListInput_Pojo cityList_Input_Pojo = plm.getAddressPayload().getCityList(state_id);
    	addBody(cityList_Input_Pojo);

				// 3.reqtype

		Response response = addReqType("POST", Endpoints.CITYLIST);

		int actStatusCode = getStatusCode(response);
		System.out.println("The Actual Status Code for getCityList-->" + actStatusCode);
        Assert.assertEquals(actStatusCode, 200, "Verify Status Code");
        
        CityListOutput_Pojo cityListOutput_Pojo = response.as(CityListOutput_Pojo.class);
        

		// to get the city list
		ArrayList<CityListDaum> cityList = cityListOutput_Pojo.getData();

		for (CityListDaum eachCityList : cityList) {

			String cityName = eachCityList.getName();
			// to get city Id for Tenkasi-->this id is used to pass in addUserAddress
			if (cityName.equals("Tenkasi")) {
				cityIdNum = eachCityList.getId();
				System.out.println("City Id of Tenkasi-->" + cityIdNum);
				break;
			}
		}
		
	}
	
}
