package com.pojo.address;

import java.util.ArrayList;

public class GetUserAddressOutput_Pojo {
	private int status;
	private String message;
	private ArrayList<GetUserAddressOutput_PojoData> data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<GetUserAddressOutput_PojoData> getData() {
		return data;
	}
	public void setData(ArrayList<GetUserAddressOutput_PojoData> data) {
		this.data = data;
	}
	
	
	
	
}
