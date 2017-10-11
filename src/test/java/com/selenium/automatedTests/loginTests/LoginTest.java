package com.selenium.automatedTests.loginTests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.reusableMethods.XlsReader;
import com.selenium.reusableMethods.Constants.CommonConstants;
import com.selenium.reusableMethods.Constants.SheetConstants;
import com.selenium.testServiceClasses.loginServiceClass.LoginService;

/**
 * This test class is used to check login functionality with valid and invalid
 * credentials
 *
 * @author HarshA
 */
public class LoginTest extends LoginService {
	/**
	 * Test method for login class
	 * 
	 * @throws Exception
	 */
	@Test
	public void validateLogin() throws Exception {
		WebDriver driver = null;
		ExtentReports extent = null;
		String testDataPath =  SheetConstants.TESTDATAPATH_LOGIN.getValue() + "//Login.xlsx";

		extent = new ExtentReports(SheetConstants.TESTRSLTPATH_LOGIN.getValue() + "//Login"+CommonConstants.CURRENT_TIME_STAMP+".html", true);
		ExtentTest test = extent.startTest("Login Functionality Test");

		XlsReader loginData = new XlsReader(testDataPath);
		loginData.createExcel(testDataPath, SheetConstants.TESTRSLTPATH_LOGIN.getValue() + "//LoginResult"+CommonConstants.CURRENT_TIME_STAMP+".xlsx");
		XlsReader loginRsltXcl = new XlsReader(SheetConstants.TESTRSLTPATH_LOGIN.getValue() + "//LoginResult"+CommonConstants.CURRENT_TIME_STAMP+".xlsx");
		String sheetName = "Login";
		int rowCount = loginRsltXcl.getRowCount(sheetName);

		String testScenario = "";
		String username = "";
		String password = "";
		ExtentTest testChild = null;

		driver = startDesktopDriver(driver, CommonConstants.BROWSER, CommonConstants.SITEURL);

		for (int row = 1; row <= rowCount; row++) {
			try {
				testScenario = loginRsltXcl.getCellData(sheetName, "TestScenario", row);
				username = loginRsltXcl.getCellData(sheetName, "Username", row);
				password = loginRsltXcl.getCellData(sheetName, "Password", row);

				if (testScenario.equals("Valid")) {
					testChild = extent.startTest("Login with Valid Credentials",
							"This is to test the login functionality of site with valid credentials.");
				} else {
					testChild = extent.startTest("Login with Invalid Credentials",
							"This is to test the login functionality of site with invalid credentials.");
				}

				testChild.log(LogStatus.INFO, "Site url opened in " + CommonConstants.BROWSER + " browser");
				testChild.log(LogStatus.INFO, "Logging in with user : " + username);
				String result = enterCredentials(driver, username, password, testChild, row);
				if (!result.equals("Error")) {
					testChild.log(LogStatus.INFO, "Logged in step performed");
					validateTest(driver, testScenario, username, password, testChild);
				}
			} catch (Exception e) {
				takeSnapShot(driver, SheetConstants.SCREENSHOT_PATH_LOGINTEST.getValue());
				testChild.log(LogStatus.ERROR, "Exception in iteration : " + row);
				testChild.log(LogStatus.ERROR, "Exception is : " + e.getLocalizedMessage());
			}
			test.appendChild(testChild);
		}
		quitDriver(driver);
		extent.endTest(test);
		extent.flush();
		extent.close();
	}
}
