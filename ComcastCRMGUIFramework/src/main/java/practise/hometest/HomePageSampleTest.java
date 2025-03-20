package practise.hometest;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class HomePageSampleTest {
	
	@Test
	public void homePageTest(Method mtd) {
		
		Reporter.log(mtd.getName() + " Test Start");
		Reporter.log("step-1" , true);
		Reporter.log("step-2", true);
		Reporter.log("step-3", true);
		Reporter.log("step-4", true);
		Reporter.log("step-5", true);
		Reporter.log(mtd.getName() + " Test end");
		
	}
	
    @Test
    public void verifyLogoHomePage(Method mtd) {
    	Reporter.log(mtd.getName() + " Test Start");
    	Reporter.log("step-1", true);
    	Reporter.log("step-2", true);
    	Reporter.log("step-3", true);
    	Reporter.log("step-4", true);
    	Reporter.log("step-5", true);
    	Reporter.log(mtd.getName() + " Test end");
    	
    }
	
	

}
