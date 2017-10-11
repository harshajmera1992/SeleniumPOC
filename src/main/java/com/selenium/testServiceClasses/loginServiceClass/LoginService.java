package com.selenium.testServiceClasses.loginServiceClass;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pageClasses.loginPageClass.Login;

/**
 *This is a service class for login test containing all validations
 *  
 * @author HarshA
 */
public class LoginService extends Login {

	/**
	 * This method is implemented to validate pass or fail for login test
	 * 
	 * @param driver
	 * @param testScenario
	 * @param username
	 * @param password
	 * @param testChild
	 */
	public void validateTest(final WebDriver driver, final String testScenario, final String username, final String password,
			final ExtentTest testChild) {
		String resultValidation = "";
		if (testScenario.equalsIgnoreCase("Valid"))
			resultValidation = this.validateValidCredentials(driver);
		else
			resultValidation = this.validateInvalidCredentials(driver);

		if (resultValidation.contains("Pass"))
			testChild.log(LogStatus.PASS, resultValidation);
		else if (resultValidation.contains("Fail"))
			testChild.log(LogStatus.FAIL, resultValidation);
		else
			testChild.log(LogStatus.ERROR, resultValidation);
	}

	/**
	 * validate for invalid credentials(user should not be able to log in and error
	 * message should be proper)
	 * 
	 * @param driver
	 * @return result(Pass or fail) 
	 */
	public String validateInvalidCredentials(final WebDriver driver) {
		String result = "";
		String rsltntHeading = getInvalidCredentialsOutput(driver);
		if (rsltntHeading.equalsIgnoreCase("There is 1 error.Authentication failed."))
			result = "Pass :: For incorrect credentials";
		else
			result = "Fail :: For incorrect credentials. Result is :: " + rsltntHeading;
		return result;
	}

	/**
	 * validate for valid credentials(user should be able to log in)
	 * 
	 * @param driver
	 * @return result(Pass or fail) 
	 */
	public String validateValidCredentials(final WebDriver driver) {
		String result = "";
		HashMap<String, Object> resultMap = getValidCredentialsOutput(driver);
		if (!(boolean) resultMap.containsKey("Exception")) {
			if (((Boolean) resultMap.get("IsAccountIconDisplayed")) && ((Boolean) resultMap.get("IsAccountIconEnabled"))
					&& ((String) resultMap.get("LogoutText")).trim().equalsIgnoreCase("Sign out"))
				result = "Pass. Login Successful with valid Credentials";
			else
				result = "Fail. Login Unsuccessful";
		} else
			result = "Error :: " + (String) resultMap.get("Exception");

		return result;
	}
}
