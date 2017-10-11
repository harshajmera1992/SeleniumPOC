package com.selenium.testServiceClasses.tabWomenServiceClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pageClasses.womenSecPageClass.FilterCount;

public class FilterCountService extends FilterCount{

	public void validateCount(String actualCount, String expectedCount, ExtentTest testChild) 
	{
		if(actualCount.equals(expectedCount))
			testChild.log(LogStatus.PASS, "UI and expected count are equal");
		else
			testChild.log(LogStatus.FAIL, "UI and expected count are not equal");
	}
}
