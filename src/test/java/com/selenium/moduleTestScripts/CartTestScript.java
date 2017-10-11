package com.selenium.moduleTestScripts;
import java.util.List;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.automatedTests.cartPageTests.CartInitialTest;
import com.selenium.reusableMethods.GenericMethods;
import com.selenium.reusableMethods.XlsReader;
import com.selenium.reusableMethods.Constants.CommonConstants;

public class CartTestScript extends GenericMethods
{
	@Test
	public void validateCart() throws Exception
	{
		CartInitialTest cartInit = new CartInitialTest();

		XlsReader sanityDriverSheetRslt = beforeDriverScriptTest();
		List<String> allTestsToRun = sanityDriverSheetRslt.testsToRun("CartPage"); 
		if(!allTestsToRun.isEmpty())
		{
			for (int i = 0; i < allTestsToRun.size(); i++) 
			{
				try 
				{
					switch (allTestsToRun.get(i))  
					{
					case "CART_INIT":
						System.out.println("CART_INIT");
						cartInit.validateCartInitTest();
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
			ExtentReports extent = new ExtentReports(CommonConstants.CLASSPATH+"//moduleScriptResults//CartPage_NoTestCaseSelected.html",true);
			ExtentTest test = extent.startTest("Empty Cart Test");
			test.log(LogStatus.INFO, "This test is created when no testscript in checked in driver script");
			test.log(LogStatus.SKIP, "Skipping cart driver script"); 
			extent.endTest(test);
			extent.close();
		}

		System.out.println("Cart Page Test Script Completed !!!!");
	}
}