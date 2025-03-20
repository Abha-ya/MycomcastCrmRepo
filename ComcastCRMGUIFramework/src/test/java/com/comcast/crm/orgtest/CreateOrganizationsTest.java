package com.comcast.crm.orgtest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest1.BaseClass1;
import com.comcast.crm.listnerutility.ListImpClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationsTest extends BaseClass1 {
	
	@Test(groups = "smokeTest")
	public void createOrganizationTest() throws Throwable {
	
		ListImpClass.test.log(Status.INFO, "read dat afrom excel");
		
	    //read test script data from excel file
		String OrgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
			  
		//step 2: navigate organization module
		ListImpClass.test.log(Status.INFO, "Navigate to org page");
		HomePage hp = new HomePage(driver);
	    hp.getOrgLink().click();
	    
		//step 3: click on create organization button
	    ListImpClass.test.log(Status.INFO, "Navigate to create org page");
	    OrganizationsPage cnp = new OrganizationsPage(driver);
	    cnp.getCreatNewOrgBtn().click();
			  
		//step 4: enter all the details and create new organization
	    CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
	    ListImpClass.test.log(Status.INFO, "Create a new org");
	    cnop.createOrg(OrgName);
	    ListImpClass.test.log(Status.INFO, OrgName + "====>Create a new org");
	   
	    
	    //verify Header message expected result
	    OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	    String actOrgName = oip.getHeadMsg().getText();
	    Assert.assertEquals(true, actOrgName.contains(OrgName));
	   
	}
	
	/*
	@Test
	public void CreateOrgTest() throws Throwable {
		
//		driver.findElement(By.linkText("Organizations")).click();
		 //read test script data from excel file
		String OrgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);
			  
		//step 2: navigate organization module
	  
		HomePage hp = new HomePage(driver);
	    hp.getOrgLink().click();
	    
		//step 3: click on create organization button
	    
	    OrganizationsPage cp = new OrganizationsPage(driver);
	    cp.getCreatNewOrgBtn().click();
			  
		//step 4: enter all the details and create new organization
	    CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
	   cnop.createOrgPhoneNumber(OrgName , phoneNumber);
	   
	    
	    //verify Header message expected result
	    OrganizationInfoPage oip = new OrganizationInfoPage(driver);
	    String actOrgName = oip.getHeadMsg().getText();
	    Assert.assertEquals(true, actOrgName.contains(OrgName));
	    String actPhoneNumber = oip.getPhoneInfo().getText();
	    Assert.assertEquals(actPhoneNumber, phoneNumber);
		
		
	}
	*/

}
