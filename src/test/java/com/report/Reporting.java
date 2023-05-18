package com.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import com.base.BaseClass;

public class Reporting extends BaseClass {
	public static void generateOMRBranchJVMReport(String jsonFile) throws FileNotFoundException, IOException {

		
		File reportLoc = new File(getProjectPath() + getPropertyFileValue("jvmpath"));

		// File reportLoc = new File(getProjectPath() + "\\target");
		// File reportLoc = new File(System.getProperty("user.dir") + "\\target");

		Configuration con = new Configuration(reportLoc, "OMR Branch Grocery API Automation");
		con.addClassifications("platform", "Windows 10 pro");
		con.addClassifications("sprint", "23");
		con.addClassifications("Author", "Karthick");

		List<String> li = new ArrayList<String>();

		li.add(jsonFile);

		ReportBuilder builder = new ReportBuilder(li, con);
		builder.generateReports();

	}

}
