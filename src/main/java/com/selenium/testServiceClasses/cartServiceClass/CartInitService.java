package com.selenium.testServiceClasses.cartServiceClass;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pageClasses.cartPageClass.CartInitial;

public class CartInitService extends CartInitial{

	public void validateCartInitData(WebDriver driver, ExtentTest test)  
	{
		HashMap<String, Object> cartInitMap = cartInitialData(driver, test);
		if(!cartInitMap.containsKey("EXCEPTION"))
		{
			if(((String)cartInitMap.get("TITLE")).equalsIgnoreCase("SHOPPING-CART SUMMARY"))
				test.log(LogStatus.PASS, "Pass(Actual/Expected :: "+(String)cartInitMap.get("TITLE")+"/"+"SHOPPING-CART SUMMARY)"); 
			else
				test.log(LogStatus.FAIL, "Fail(Actual/Expected :: "+(String)cartInitMap.get("TITLE")+"/"+"SHOPPING-CART SUMMARY)"); 

			if(((String)cartInitMap.get("BUYPROCEDURE")).equalsIgnoreCase("01. Summary :: 02. Sign in :: 03. Address :: 04. Shipping :: 05. Payment"))
				test.log(LogStatus.PASS, "Pass(Actual/Expected :: "+(String)cartInitMap.get("BUYPROCEDURE")+"/"+"01. Summary :: 02. Sign in :: 03. Address :: 04. Shipping :: 05. Payment)"); 
			else
				test.log(LogStatus.FAIL, "Fail(Actual/Expected :: "+(String)cartInitMap.get("BUYPROCEDURE")+"/"+"01. Summary :: 02. Sign in :: 03. Address :: 04. Shipping :: 05. Payment)"); 

			if(((String)cartInitMap.get("CARDSTATUS")).equalsIgnoreCase("Your shopping cart is empty."))
				test.log(LogStatus.PASS, "Pass(Actual/Expected :: "+(String)cartInitMap.get("CARDSTATUS")+"/"+"Your shopping cart is empty.)"); 
			else
				test.log(LogStatus.FAIL, "Fail(Actual/Expected :: "+(String)cartInitMap.get("CARDSTATUS")+"/"+"Your shopping cart is empty.)"); 
		}
		else
		{
			test.log(LogStatus.ERROR, "Error : "+(String)cartInitMap.get("EXCEPTION"));
		}
	}
}
