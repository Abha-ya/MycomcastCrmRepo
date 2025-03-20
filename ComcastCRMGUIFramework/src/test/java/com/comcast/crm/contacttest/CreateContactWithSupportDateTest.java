package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;

public class CreateContactWithSupportDateTest {

	private static final CharSequence lastName = null;

	public static void main(String[] args) throws Throwable {
		/*Create object */
	       FileUtility fLib = new FileUtility();
	       ExcelUtility eLib = new ExcelUtility();
	       javaUtility jLib = new javaUtility();
		
			
			String BROWSER = fLib.getDataFromPropertiesFile("browser");
			String URL = fLib.getDataFromPropertiesFile("url");
			String USERNAME = fLib.getDataFromPropertiesFile("username");
			String PASSWORD = fLib.getDataFromPropertiesFile("password");;
				
			String OrgName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();	
				
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
				String startDate = jLib.getSystemDateYYYYDDMM();
				String endDate = jLib.getRequiredDateYYYYDDMM(30);
				
				
				
				
			    driver.findElement(By.name("lastname")).sendKeys(lastName);
			    driver.findElement(By.name("support_start_date")).clear();
			    driver.findElement(By.name("support_start_date")).sendKeys(startDate);
			    
			    driver.findElement(By.name("support_end_date")).clear();
			    driver.findElement(By.name("support_end_date")).sendKeys(endDate);
			    
			    
			    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			    
			    //verify Contact with support date expected result
			    String actstartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
			    if (actstartDate.equals(startDate)) {
			    	System.out.println(startDate + "is verified==PASS");
			    }else {
			    	System.out.println(startDate + "is not verified==FAIL");
			    }
			    
			    String actendDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
			    if (actendDate.equals(endDate)) {
			    	System.out.println(endDate + "is verified==PASS");
			    }else {
			    	System.out.println(endDate + "is not verified==FAIL");
			    }
			    
			      
			    //step 5: logout
			    driver.quit();

			}

		


	}


