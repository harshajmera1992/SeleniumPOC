package com.selenium.automatedTests.cartPageTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.reusableMethods.GenericMethods;
import com.selenium.reusableMethods.Constants.CommonConstants;
import com.selenium.testServiceClasses.cartServiceClass.CartInitService;


public class CartInitialTest extends CartInitService {

	@Test
	public void validateCartInitTest() {
		GenericMethods gm = new GenericMethods();
		WebDriver driver = null;
		ExtentReports extent = null;

		extent = new ExtentReports(CommonConstants.TESTRESULTSPATH+"//cartPageTests//EmptyCart.html",true);
		ExtentTest test = extent.startTest("Empty Cart Page Test", "This test validates empty cart page elements");

		driver = gm.startDesktopDriver(driver, CommonConstants.BROWSER, CommonConstants.SITEURL);
		test.log(LogStatus.INFO, "Site url opened in "+CommonConstants.BROWSER+" browser");

		validateCartInitData(driver, test); 

		gm.quitDriver(driver); 

		extent.endTest(test);
		extent.flush();
		extent.close();
	}
}