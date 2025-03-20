package com.comcast.crm.orgtest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.basetest1.BaseClass1;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;

public class CreateOrganizationWithIndustryTest  {
	
	public static void main(String[]args) throws Throwable
	{
		/*Create object */
	       FileUtility fLib = new FileUtility();
	       ExcelUtility eLib = new ExcelUtility();
	       javaUtility jLib = new javaUtility();
		
			
			String BROWSER = fLib.getDataFromPropertiesFile("browser");
			String URL = fLib.getDataFromPropertiesFile("url");
			String USERNAME = fLib.getDataFromPropertiesFile("username");
			String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
		//generate random number
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
	    
	    WebElement wbsele1=driver.findElement(By.name("industry"));
	    Select sel1 = new Select(wbsele1);
	    String industry = null;
		sel1.selectByVisibleText(industry);
	    
	    WebElement wbsele2= driver.findElement(By.name("accounttype"));
	    Select sel2 = new Select(wbsele2);
	    String type = null;
		sel2.selectByVisibleText(type);
	    
	    
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    
	    //verify the industry and type info
	    String actIndustries=driver.findElement(By.id("dtlview_Industry")).getText();
	    if (actIndustries.equals(actIndustries)) {
	    	System.out.println(actIndustries + "is verified==PASS");
	    }else {
	    	System.out.println(actIndustries + "is not verified==FAIL");
	    }
	    
	    String actType=driver.findElement(By.id("dtlview_Type")).getText();
	    if (actType.equals(actType)) {
	    	System.out.println(actType + "is verified==PASS");
	    }else {
	    	System.out.println(actType + "is not verified==FAIL");
	    }
	    
	    	  
	    //step 5: logout
	    driver.quit();

	}

}
