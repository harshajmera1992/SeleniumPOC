package com.selenium.moduleTestScripts;
import java.util.List;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.automatedTests.tabWomenTests.FilterCountTest;
import com.selenium.automatedTests.tabWomenTests.TabWomenLinkTest;
import com.selenium.reusableMethods.GenericMethods;
import com.selenium.reusableMethods.XlsReader;
import com.selenium.reusableMethods.Constants.CommonConstants;

public class TabWomenScript extends GenericMethods
{
	@Test
	public void validateTabWomen() throws Exception
	{
		FilterCountTest filterCount = new FilterCountTest();
		TabWomenLinkTest tabWomenLink = new TabWomenLinkTest();

		XlsReader sanityDriverSheetRslt = beforeDriverScriptTest();
		List<String> allTestsToRun = sanityDriverSheetRslt.testsToRun("TabWomen"); 
		if(!allTestsToRun.isEmpty())
		{
			for (int i = 0; i < allTestsToRun.size(); i++) 
			{
				try 
				{
					switch (allTestsToRun.get(i))  
					{
					case "FILTER_COUNT_TEST":
						System.out.println("FILTER_COUNT_TEST");
						filterCount.validateFilterCount();
						break;
					case "TAB_WOMEN_LINK_TEST":
						System.out.println("TAB_WOMEN_LINK_TEST");
						tabWomenLink.validateTabWomenLink();
						break;
					default:
						break;
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					System.out.println(e.getMessage());
				}    
			}
		}
		else
		{
			ExtentReports extent = new ExtentReports(CommonConstants.CLASSPATH+"//moduleScriptResults//TabWomen_NoTestSelected.html",true);
			ExtentTest test = extent.startTest("Tab women Test");
			test.log(LogStatus.INFO, "This test is created when no testscript in checked in driver script");
			test.log(LogStatus.SKIP, "Skipping tab women driver script"); 
			extent.endTest(test);
			extent.close();
		}

		System.out.println("Tab women Test Script Completed !!!!");
	}
}