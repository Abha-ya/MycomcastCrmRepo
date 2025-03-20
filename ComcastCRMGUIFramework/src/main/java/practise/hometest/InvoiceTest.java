package practise.hometest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest1.BaseClass1;


public class InvoiceTest extends BaseClass1 {
	
	@Test(retryAnalyzer = com.comcast.crm.listnerutility.RetryListnerImp.class)
	public void activateSim() {
		System.out.println("Execute create invoice test");
		String actTitle = driver.getTitle();
		Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	
	}
	
	@Test
	public void createInvoiceWithContactTest() {
		System.out.println("Execute create invoice with contact test");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	}
	

}
