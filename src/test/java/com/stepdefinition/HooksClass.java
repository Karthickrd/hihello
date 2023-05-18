package com.stepdefinition;


import com.base.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;


public class HooksClass extends BaseClass {
	
	@After
	public void afterScenario(Scenario scenario) {
		String resBodyAsPrettyString= getResBodyAsPrettyString(response);
		scenario.log(resBodyAsPrettyString);

	}

}
