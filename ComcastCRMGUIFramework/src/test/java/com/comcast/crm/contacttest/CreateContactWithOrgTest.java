package com.comcast.crm.contacttest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;

public class CreateContactWithOrgTest {
	
	public static void main(String[]args) throws Throwable
	{
		/*Create object */
	       FileUtility fLib = new FileUtility();
	       ExcelUtility eLib = new ExcelUtility();
	       javaUtility jLib = new javaUtility();
	       WebDriverUtility wLib = new WebDriverUtility();
		
			
			String BROWSER = fLib.getDataFromPropertiesFile("browser");
			String URL = fLib.getDataFromPropertiesFile("url");
			String USERNAME = fLib.getDataFromPropertiesFile("username");
			String PASSWORD = fLib.getDataFromPropertiesFile("password");
	
			
		//read test script data from excel file
		String OrgName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("contact", 7, 3);
		
		 WebDriver driver = null;
		 
		 if(BROWSER.equals("chrome")){
			driver = new ChromeDriver();
		 }else if(BROWSER.equals("firefox")){
			driver = new FirefoxDriver();
		 }else if(BROWSER.equals("edge")){
			driver = new EdgeDriver();
		 }else {
			driver = new ChromeDriver();
		 }
			
		 //step 1 :Login
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
        driver.findElement(By.id("SubmitButton")).click();
			  
		//step 2: navigate organization module
		driver.findElement(By.linkText("Organizations")).click();
		  
		//step 3: click on create organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			  
		//step 4: enter all the details and create new organization
	    driver.findElement(By.name("accountname")).sendKeys(OrgName);
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    
	    //verify Header message expected result
	    
	    String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    if (headerInfo.contains(PASSWORD)) {
	    	System.out.println(OrgName + "is created==PASS");
	    }else {
	    	System.out.println(OrgName + "is not created==FAIL");
	    }
	  	  
	    //step 5: navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		  
		//step 6: click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			  
		//step 7: enter all the details and create new contact
	    driver.findElement(By.name("lastname")).sendKeys(headerInfo);
	    driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
	    
	    //switch to child window
	    
	    wLib.switchNewBrowserTab(driver, "module=Accounts");
	   
	    driver.findElement(By.name("search_text")).sendKeys(OrgName);
	    driver.findElement(By.name("search")).click();
	    driver.findElement(By.xpath("//a[text()='"+OrgName+"']")).click();
	    
	    // switch to parent window
	    wLib.switchNewBrowserTab(driver, "Contacts&action");
	    
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    
	    //verify Header message expected result
	    headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    if (headerInfo.contains(headerInfo)) {
	    	System.out.println(headerInfo + " header is verified==PASS");
	    }else {
	    	System.out.println(headerInfo + "header is not verified==FAIL");
	    }
	    
	    //verify Header orgName info  expected result
	    String actOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
	    System.out.println(OrgName);
	    if (actOrgName.trim().equals(OrgName)) {
	    	System.out.println(OrgName + "is created==PASS");
	    }else {
	    	System.out.println(OrgName + "is not created==FAIL");
	    }	  
	    //step 5: logout
	    driver.quit();

	}
	  

	}


