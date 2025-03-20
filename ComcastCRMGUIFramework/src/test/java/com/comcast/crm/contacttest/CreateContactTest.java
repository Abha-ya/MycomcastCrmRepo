package com.comcast.crm.contacttest;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.basetest1.BaseClass1;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;

public class CreateContactTest extends BaseClass1 {

	public static void main(String[] args) throws Throwable {
		       /*Create object */
		       FileUtility fLib = new FileUtility();
		       ExcelUtility eLib = new ExcelUtility();
		       javaUtility jLib = new javaUtility();
			
				
				String BROWSER = fLib.getDataFromPropertiesFile("browser");
				String URL = fLib.getDataFromPropertiesFile("url");
				String USERNAME = fLib.getDataFromPropertiesFile("username");
				String PASSWORD = fLib.getDataFromPropertiesFile("password");
				
				String orgName = eLib.getDataFromExcel("Org", 1, 2) + jLib.getRandomNumber();
				
			    //read test script data from excel file
			    String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();
				
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
					  
				//step 2: navigate Contact module
				driver.findElement(By.linkText("Contacts")).click();
				  
				//step 3: click on create contact button
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
					  
				//step 4: enter all the details and create new contact
			    driver.findElement(By.name("lastname")).sendKeys(lastName);
			    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			    
			    //verify last name expected result
			    String actLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
			    if (actLastName.equals(lastName)) {
			    	System.out.println(lastName + "is verified==PASS");
			    }else {
			    	System.out.println(lastName + "is not verified==FAIL");
			    }
			    
			      
			    //step 5: logout
			    driver.quit();

			}

		


	}


