package com.selenium.moduleTestScripts;

import java.util.List;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.automatedTests.loginTests.LoginTest;
import com.selenium.reusableMethods.GenericMethods;
import com.selenium.reusableMethods.XlsReader;
import com.selenium.reusableMethods.Constants.CommonConstants;

/**
 * This class is used to store all test scripts corresponding to login module
 * 
 * @author HarshA
 */
public class LoginTestScript extends GenericMethods {
	/**
	 * This method contains login module script containing different login tests
	 * 
	 * @throws Exception
	 */
	@Test
	public void validateLogin() throws Exception {
		LoginTest login = new LoginTest();
		XlsReader sanityDriverSheetRslt = beforeDriverScriptTest();
		List<String> allTestsToRun = sanityDriverSheetRslt.testsToRun("Login");
		if (!allTestsToRun.isEmpty()) {
			for (int i = 0; i < allTestsToRun.size(); i++) {
				try {
					switch (allTestsToRun.get(i)) {
					case "LOGIN_TEST":
						System.out.println("LOGIN_TEST");
						login.validateLogin();
						break;
					default:
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		} else {
			ExtentReports extent = new ExtentReports(
					CommonConstants.CLASSPATH + "//test-output//moduleScriptResults//Login_NoTestCaseSelected.html", true);
			ExtentTest test = extent.startTest("Empty Cart Page Test");
			test.log(LogStatus.INFO, "This test is created when no testscript in checked in driver script");
			test.log(LogStatus.SKIP, "Skipping login driver script");
			extent.endTest(test);
			extent.close();
		}
		System.out.println("Login Test Script Completed !!!!");
	}
}