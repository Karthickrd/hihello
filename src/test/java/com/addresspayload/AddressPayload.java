package com.addresspayload;

import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.CityListInput_Pojo;
import com.pojo.address.DeleteUserAddress_Input_Pojo;
import com.pojo.address.Update_User_Address_Input;

public class AddressPayload {
	public AddUserAddress_Input_Pojo addUserAddress_Input_Pojo (String first_name , String last_name , String mobile , String apartment , int stateId, int cityId,int country,String zipcode,String address,String addressType) {
		AddUserAddress_Input_Pojo address_Input_Pojo = new AddUserAddress_Input_Pojo(first_name, last_name, mobile, apartment, stateId, cityId, country, zipcode, address, addressType);
		
		return address_Input_Pojo;
	}
	public Update_User_Address_Input updateUserAddress (String addressId,String first_name , String last_name , String mobile , String apartment , int stateId, int cityId,int country,String zipcode,String address,String addressType) {
		Update_User_Address_Input  update_User_Address_Input = new Update_User_Address_Input(addressId, first_name, last_name, mobile, apartment, stateId, cityId, country, zipcode, address, addressType);
		return update_User_Address_Input;
	}
	public DeleteUserAddress_Input_Pojo deleteUserAddress(String addressId) {
		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = new DeleteUserAddress_Input_Pojo(addressId);
		return deleteUserAddress_Input_Pojo;
	}

	public CityListInput_Pojo getCityList(String state_id) {

		CityListInput_Pojo cityListInput_Pojo = new CityListInput_Pojo(state_id);

		return cityListInput_Pojo ;
	}

}
