package com.selenium.pageClasses.womenSecPageClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.reusableMethods.GenericMethods;

public class FilterCount extends GenericMethods{

	public void goToWomenSec(WebDriver driver)
	{
		waitForElementTimeOut(driver, 10, By.className("sf-with-ul"));
		driver.findElement(By.className("sf-with-ul")).click();
	}

	public String countAfterFilter(WebDriver driver, String subFilter, ExtentTest testChild) 
	{
		String count = "";
		String exception = "";
		try {
			waitForElementTimeOut(driver, 10, By.xpath(".//a[contains(text(),'"+subFilter+"')]//ancestor::li//div"));
			driver.findElement(By.xpath(".//a[contains(text(),'"+subFilter+"')]//ancestor::li//div[@class='checker']")).click();
			testChild.log(LogStatus.INFO, "Selected subFilter :: "+subFilter); 
			Thread.sleep(2000);
			waitForElementTimeOut(driver, 10, By.className("heading-counter"));
			count = driver.findElement(By.className("heading-counter")).getText().split(" ")[2];
			testChild.log(LogStatus.INFO, "Count fetched from UI :: "+count); 
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			exception = "Issue in fetching count";
			testChild.log(LogStatus.ERROR, "Issue in fetching count"); 
			return exception;
		}
	}

	public void resetFilter(WebDriver driver) throws InterruptedException 
	{
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,0)", ""); 
		driver.findElement(By.className("icon-remove")).click();
	}
}
