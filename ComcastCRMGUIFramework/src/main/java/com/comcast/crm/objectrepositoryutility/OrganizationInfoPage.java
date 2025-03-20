package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
WebDriver driver;
	
	public OrganizationInfoPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getHeadMsg() {
		return headMsg;
	}

	@FindBy(className ="dvHeaderText")
	private WebElement headMsg;

	public WebElement getPhoneInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
