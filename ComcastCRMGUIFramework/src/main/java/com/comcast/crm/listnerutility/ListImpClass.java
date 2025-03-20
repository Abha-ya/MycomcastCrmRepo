package com.comcast.crm.listnerutility;
import java.util.Date;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest1.BaseClass1;

public class ListImpClass implements ITestListener , ISuiteListener{
	
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;
	
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");

		//Spark report config
		spark = new ExtentSparkReporter("./AdvanceReport/report.html");
        spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//add Env information and create test
		report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-10");
		
	}
	
	public void onFinish(ISuite suite) {
		System.out.println("Report backUp");
		report.flush();
		
	}
	public void onTestStart(ITestResult result) {
		System.out.println("===="+result.getMethod().getMethodName()+"===START====");
	    test = report.createTest(result.getMethod().getMethodName());
	    test.log(Status.INFO,result.getMethod().getMethodName()+"==> STARTED <===" );
		
	}
	public void onTestSuccess(ITestResult result) {
		System.out.println("====<"+result.getMethod().getMethodName()+">===END====");
		test.log(Status.PASS,result.getMethod().getMethodName()+"==> COMPLETED <===" );
		
	}
	public void onTestFailure(ITestResult result) {
		String testName =result.getMethod().getMethodName();
		
		TakesScreenshot ts = (TakesScreenshot) BaseClass1.sdriver;
	    String filePath = ts.getScreenshotAs(OutputType.BASE64);
	    
	    //File dest = new File("./screenshot/"+testName+"listSC.png");
	    String time= new Date().toString().replace(" ", "_").replace(":", "_");
	    
	    test.addScreenCaptureFromBase64String(filePath,testName+"_"+time);
	    test.log(Status.FAIL,result.getMethod().getMethodName()+"==> FAILED <===" );
	   
	    
		
		
	}
	
	
	

}
