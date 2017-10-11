package com.selenium.reusableMethods;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.reusableMethods.Constants.CommonConstants;

/** 
 * This class contains generic methods used throughout the project 
 * 
 *@author HarshA
 */
public class GenericMethods {
	/**
	 * to open browser and navigate to site
	 * 
	 * @param driver
	 * @param driverOption
	 * @param siteURL
	 * @return driver
	 */
	public WebDriver startDesktopDriver(WebDriver driver, String driverOption, String siteURL) {
		if (driverOption.equalsIgnoreCase("firefox")) {
			System.out.println(CommonConstants.CLASSPATH);
			System.setProperty("webdriver.firefox.bin",
					CommonConstants.CLASSPATH + "//resources//browser//firefoxBrowser//Mozilla Firefox//firefox.exe");
			driver = new FirefoxDriver();
			try {
				driver.get(siteURL);
			} catch (Exception e) {
			}
		} else if (driverOption.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					CommonConstants.CLASSPATH + "//resources//drivers//chromeDriver//chromedriver.exe");
			driver = new ChromeDriver();
			try {
				driver.get(siteURL);
			} catch (Exception e) {
				// Now the alert appears.
			}
		}
		driver.manage().window().maximize();
		return driver;
	}
	/**
	 * explicit wait for Element visibility
	 * 
	 * @param driver
	 * @param timeOut
	 * @param idName
	 * @return isElementPresent(true/false)
	 */
	public boolean waitForElementTimeOut(WebDriver driver, int timeOut, By idName) {
		boolean isElementPresent = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(idName));
			isElementPresent = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isElementPresent;
	}
	/**
	 * implcit wait for Element visibility
	 * 
	 * @param driver
	 * @param timeToWaitInSec
	 */
	public void waitForElementImplicitly(WebDriver driver, int timeToWaitInSec) {
		driver.manage().timeouts().implicitlyWait(timeToWaitInSec, TimeUnit.SECONDS);
	}
	/**
	 * Close all the browsers opened by automation
	 * 
	 * @param driver
	 */
	public void quitDriver(WebDriver driver) {
		driver.quit();
	}
	/**
	 * this method returns driver script object for result driver script excel
	 * 
	 * @return
	 */
	public XlsReader beforeDriverScriptTest() {
		XlsReader sanityDriverSheetRslt = null;
		System.out.println("Before Test");
		String testCasesToExecute = CommonConstants.CLASSPATH + "//test-output//RegressionSuiteSheet.xlsx";
		sanityDriverSheetRslt = new XlsReader(testCasesToExecute);
		sanityDriverSheetRslt.createExcel(testCasesToExecute,
				CommonConstants.CLASSPATH + "//test-output//moduleScriptResults//RegressionSuiteResult.xlsx");
		return sanityDriverSheetRslt;
	}
	/**
	 * this method is used to capture screenshot for failed test scripts
	 * 
	 * @param webdriver
	 * @param fileWithPath
	 * @throws IOException
	 * @throws Exception
	 */
	public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileWithPath);
		FileUtils.copyFile(srcFile, destFile);
	}
}