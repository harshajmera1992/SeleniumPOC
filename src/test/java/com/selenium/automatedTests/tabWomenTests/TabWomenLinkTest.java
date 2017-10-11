package com.selenium.automatedTests.tabWomenTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.reusableMethods.GenericMethods;
import com.selenium.reusableMethods.Constants.CommonConstants;
import com.selenium.testServiceClasses.tabWomenServiceClass.TabWomenService;

public class TabWomenLinkTest extends TabWomenService{
	
	@Test
	public void validateTabWomenLink() {
		GenericMethods gm = new GenericMethods();
		WebDriver driver = null;
		ExtentReports extent = null;
		ExtentTest test = null;
		try{
		extent = new ExtentReports(CommonConstants.TESTRESULTSPATH+"//tabWomenProductResult//TabWomenLink.html",true);
		test = extent.startTest("Tab Women Link Test", "This test validates women link tab on home page");

		driver = gm.startDesktopDriver(driver, CommonConstants.BROWSER, CommonConstants.SITEURL);
		test.log(LogStatus.INFO, "Site url opened in "+CommonConstants.BROWSER+" browser");
		
		validateText(driver, test); 
		}
		catch(Exception e)
		{
			test.log(LogStatus.ERROR,"Exception is : "+e.getMessage());
		}
		quitDriver(driver); 
		extent.endTest(test);
		extent.close();
	}
}
