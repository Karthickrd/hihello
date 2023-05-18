package com.stepdefinition;



import org.junit.Assert;

import com.base.BaseClass;

import io.cucumber.java.en.Then;



public class CommonStep extends BaseClass {

	@Then("User verify the status code is {int}")
	public void user_verify_the_status_code_is(int expStausCode) {

		int actStatusCode = TC1_LoginStep.globalData.getStatusCode();

		Assert.assertEquals("Verify StatusCode", expStausCode, actStatusCode);

	}
}
