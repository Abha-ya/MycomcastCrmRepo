package practise.hometest;

import org.testng.annotations.Test;

import com.comcast.crm.basetest1.BaseClass1;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * test class for contact module
 * @author ABHAYA SAWANT
 *
 */
public class SearchContactTest extends BaseClass1{
	@Test
	public void searchcontactTest() {
		/**
		 * Scenario : login()==> navigateContact==>cretaecontact()==verify
		 */
		
		/*step 1 : login to app*/
		LoginPage lp = new LoginPage(driver);
		lp.LoginToapp("url", "username", "pass");
		
	
		
	}
	

}
