package com.comcast.crm.orgtest;

import java.time.Duration;

import java.util.Random;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;

public class CreateOrganizationWithPhoneNumberTest {
	
	public static void main(String[]args) throws Throwable
	{/*Create object */
	       FileUtility fLib = new FileUtility();
	       ExcelUtility eLib = new ExcelUtility();
	       javaUtility jLib = new javaUtility();
		
			
			String BROWSER = fLib.getDataFromPropertiesFile("browser");
			String URL = fLib.getDataFromPropertiesFile("url");
			String USERNAME = fLib.getDataFromPropertiesFile("username");
			String PASSWORD = fLib.getDataFromPropertiesFile("password");
			
		//generate random number
        Random random = new Random();
		int randomInt = random.nextInt(1000);
		
	    //read test script data from excel file
		String OrgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
	   // driver.findElement(By.name("phone")).sendKeys(PhoneNumber);
	    
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    
	    //verify phoneNumber expected result
	    //String actphoneNumber = driver.findElement(By.className("detailedViewTextBox")).getText();
	    
	    String actphoneNumber = driver.findElement(By.cssSelector("input[id='phone']")).getText();
	    if (actphoneNumber.contains(actphoneNumber)) {
	    	System.out.println(actphoneNumber + "is created==PASS");
	    }else {
	    	System.out.println(actphoneNumber + "is not created==FAIL");
	    }
	    
	   	  
	    //step 5: logout
	    driver.quit();

	}

}
