package com.pojo.address;

import java.util.ArrayList;

public class StateListOutput_Pojo {
	 private int status;
	    private String message;
	    private ArrayList<StateListDatum> data;
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
		public ArrayList<StateListDatum> getData() {
			return data;
		}
		public void setData(ArrayList<StateListDatum> data) {
			this.data = data;
		}
	    

}
