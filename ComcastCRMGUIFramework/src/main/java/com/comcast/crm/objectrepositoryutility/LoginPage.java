package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

/**
 * 
 * @author ABHAYA SAWANT
 * 
 * Contains Login page elements and business library like login()
 *
 */

public class LoginPage extends WebDriverUtility {// Rule 1- Create separate java class
                        // Rule 2- Object creation
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;
	                    // Rule 3 - Object initialization
	                    //Rule 4: Object Encapsulation  
	
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}
	
/**
 * Login to application based on username password url arguments	
 * @param url
 * @param username
 * @param password
 */
	
	//Rule 5: provide action
	public void LoginToapp( String url, String username , String password)  
	{
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginbtn.click();
	}
	              

	
	
	

}
