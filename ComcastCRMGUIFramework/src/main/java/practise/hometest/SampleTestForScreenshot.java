package practise.hometest;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class SampleTestForScreenshot {
	@Test
	public void amazonTest() {
		
	//public void amazonTest(){
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		//step1 : create an object to an event firing web driver
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		
		//step2: Use getScreenshotAs method to get file type of screenshot
		File srcFile = edriver.getScreenShotAs(OutputType.FILE);
		
		//step3: Store screen on local driver
		FileUtils.copyFile(srcFile , new File("./screenshot/test1.png"));
		
	

		
		
	}

}
