package com.runnerclass;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClass;
import com.report.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(tags = " @Login or  @State or @City or @Address or @Search", plugin = { "pretty",
		"json:target\\index.json" }, monochrome = true, publish = true, stepNotifications = true, snippets = SnippetType.CAMELCASE, dryRun = false, features = "src\\test\\resources", glue = "com.stepdefinition")
public class TestRunnerClass extends BaseClass {

	@AfterClass
	public static void afterExecution() throws FileNotFoundException, IOException {
		Reporting.generateOMRBranchJVMReport(getProjectPath() + getPropertyFileValue("jsonpath"));
	}

}
