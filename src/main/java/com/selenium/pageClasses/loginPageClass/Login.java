package com.selenium.pageClasses.loginPageClass;
import java.io.IOException;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pageObjects.loginPageObjects.LoginObjects;
import com.selenium.reusableMethods.GenericMethods;
import com.selenium.reusableMethods.Constants.SheetConstants;
/**
 * This is page class to maintain ui operations for login test
 * 
 * @author HarshA
 */
public class Login extends GenericMethods
{
	/**
	 * This method is used to enter credentials from excel on ui and click on submit button
	 *
	 * @param driver
	 * @param username
	 * @param password
	 * @param row 
	 * @param testChild 
	 * @return result
	 */
	public String enterCredentials(final WebDriver driver, final String username, final String password, final ExtentTest testChild, final int row)
	{
		String result = "";
		try {
			waitForElementTimeOut(driver, 10, By.className(LoginObjects.LOGIN.getValue()));
			driver.findElement(By.className(LoginObjects.LOGIN.getValue())).click();
			waitForElementTimeOut(driver, 10, By.id(LoginObjects.EMAIL.getValue()));
			driver.findElement(By.id(LoginObjects.EMAIL.getValue())).sendKeys(username);
			driver.findElement(By.id(LoginObjects.PASSWORD.getValue())).sendKeys(password);
			driver.findElement(By.id(LoginObjects.SUBMITBUTTON.getValue())).click();
		} catch (Exception e) {
			try {
				takeSnapShot(driver, SheetConstants.SCREENSHOT_PATH_LOGINTEST.getValue());
			} catch (IOException excptn) {
				excptn.printStackTrace();
			}
			testChild.log(LogStatus.ERROR, "Exception in iteration : "+row);
			testChild.log(LogStatus.ERROR, "Exception is : "+e.getMessage()); 
			result = "Error";
		}
		return result;
	}
	
	/**
	 * This method is used to capture output for invalid credentials input
	 * 
	 * @param driver
	 * @return heading
	 */
	public String getInvalidCredentialsOutput(final WebDriver driver)
	{
		String heading = "";
		try {
			waitForElementTimeOut(driver, 10, By.xpath(LoginObjects.HEADINGFIRSTPART.getValue()));
			String headingFirst = driver.findElement(By.xpath(LoginObjects.HEADINGFIRSTPART.getValue())).getText();
			String headingSecond = driver.findElement(By.xpath(LoginObjects.HEADINGSECONDPART.getValue())).getText();
			heading = headingFirst.trim() + "." + headingSecond.trim();
			return heading;
		} catch (Exception e) {
			try {
				takeSnapShot(driver, SheetConstants.SCREENSHOT_PATH_LOGINTEST.getValue());
			} catch (IOException excptn) {
				excptn.printStackTrace();
			}
			heading = "Invalid Credentials Heading Error";
			return heading;
		}
	}

	/**
	 * 
	 * This method is used to capture output on valid credentials input
	 *  
	 * @param driver
	 * @return
	 */
	public HashMap<String, Object> getValidCredentialsOutput(final WebDriver driver)
	{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {
			boolean accountIconDisplay = driver.findElement(By.xpath(LoginObjects.ACCOUNTICON.getValue())).isDisplayed();
			boolean accountIconEnable = driver.findElement(By.xpath(LoginObjects.ACCOUNTICON.getValue())).isEnabled();
			String logoutText = driver.findElement(By.xpath(LoginObjects.LOGOUT.getValue())).getText().trim();
			returnMap.put("IsAccountIconDisplayed", accountIconDisplay);
			returnMap.put("IsAccountIconEnabled", accountIconEnable);
			returnMap.put("LogoutText", logoutText);
		} catch (Exception e) {
			try {
				takeSnapShot(driver, SheetConstants.SCREENSHOT_PATH_LOGINTEST.getValue());
			} catch (IOException excptn) { 
				excptn.printStackTrace();
			}
			returnMap.put("Exception", e.getMessage());
		}
		return returnMap; 
	}
}
