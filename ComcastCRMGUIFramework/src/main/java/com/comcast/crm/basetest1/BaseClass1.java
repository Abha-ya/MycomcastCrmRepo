package com.comcast.crm.basetest1;

import java.sql.SQLException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beust.jcommander.Parameter;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
 
public class BaseClass1 {
	
	public static WebDriver sdriver=null;
	public WebDriver driver = null;
	public ExtentSparkReporter spark;


	public DatabaseUtility dbLib = new DatabaseUtility();
	public FileUtility fLib = new FileUtility();
	public static ExcelUtility eLib = new ExcelUtility();
	public static javaUtility jLib = new javaUtility();
	
	
	
	
	@BeforeSuite(groups= {"Smoke test" , "Regression Test"})
	public void configBS() {
		System.out.println("===Connect to DB , Report Config");
		dbLib.getDbconnection();
		
		
	}
	//@Parameters ("BROWSER")
	@BeforeClass
	public void configBC() throws Throwable {
		System.out.println("==Launch Browser==");
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		
		 
		 
		 if(BROWSER.equals("chrome")){
			driver = new ChromeDriver();
		 }else if(BROWSER.equals("firefox")){
			driver = new FirefoxDriver();
		 }else if(BROWSER.equals("edge")){
			driver = new EdgeDriver();
		 }else {
			driver = new ChromeDriver(); 
		 }
		 sdriver = driver;
	}
		
	
	
	@BeforeMethod
	public void configBM() throws Throwable {
		System.out.println("=Login=");
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.LoginToapp(URL, USERNAME, PASSWORD);
		
		
	}
	
	
	
	@AfterMethod
	public void configAM() {
		System.out.println("=LogOut=");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass
	public void configAC() {
		System.out.println("==Close the Browser==");
		driver.quit();
		
		
	}
	@AfterSuite
	public void configAS() throws SQLException
	{
		System.out.println("==close Db , Report BackUp");
		dbLib.closeDbconnection();
		
	}

}
