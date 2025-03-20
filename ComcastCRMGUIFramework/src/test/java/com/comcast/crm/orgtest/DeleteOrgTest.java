package com.comcast.crm.orgtest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.webdriverutility.javaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {
	
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
		String OrgName = eLib.getDataFromExcel("org", 10, 2) + jLib.getRandomNumber();
		 
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
		
		LoginPage lp = new LoginPage(driver);
		
	    lp.LoginToapp(USERNAME, PASSWORD, OrgName);
		
			  
		//step 2: navigate organization module
	    
	    HomePage hp = new HomePage(driver);
	    hp.getOrgLink().click();
	    
		
		  
		//step 3: click on create organization button
	    OrganizationsPage cnp = new OrganizationsPage(driver);
	    cnp.getCreatNewOrgBtn().click();
		
			  
		//step 4: enter all the details and create new organization
	    CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
	    cnop.createOrg(OrgName);
	   
	    
	    //verify Header message expected result
	    OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	    String actOrgName = oip.getHeadMsg().getText();
	    if(actOrgName.contains(OrgName)) {
	    	System.out.println(OrgName + "name is verified==PASS");
	    }
	    else {
	    	System.out.println(OrgName + "name is not verified==FAIL");
	    }
	    
	    
	    
	    //go back to organizations  page
	   
	    hp.getOrgLink().click();
	    
	    // search for organizations
	    cnp.getSearchEdt().sendKeys(OrgName);
	    wLib.select(cnp.getSearchDD(), "Organization Name");
	    cnp.getSearchBtn().click(); 
	    
	    // in dynamic web table select and delete org
	    driver.findElement(By.xpath("//a[text()='"+OrgName+"']/../../td[8]/a[text()='del']")).click();
	    
	
	    //step 5: logout
	    hp.logout();
	    
	    driver.quit();

	

	}
}
