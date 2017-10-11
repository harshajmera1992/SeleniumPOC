package com.selenium.pageClasses.cartPageClass;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.reusableMethods.GenericMethods;


public class CartInitial extends GenericMethods{

	public HashMap<String, Object> cartInitialData(WebDriver driver, ExtentTest test) 
	{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		try {
			driver.findElement(By.xpath(".//*[@class='shopping_cart']/a")).click();
			test.log(LogStatus.INFO, "Clicked on Cart(Empty) link");
			waitForElementTimeOut(driver, 10, By.xpath(".//*[@id='cart_title']"));
			String title = driver.findElement(By.xpath(".//*[@id='cart_title']")).getText();
			String buyProcedure = "";
			String buyProcSteps = "";
			for(int i=1; i<=5; i++)
			{
				buyProcSteps = driver.findElement(By.xpath(".//*[@id='order_step']/li["+i+"]/span")).getText();
				buyProcedure = buyProcedure + " :: " + buyProcSteps;
			}
			buyProcedure = buyProcedure.replaceFirst("::", "").trim();
			String cartStatus = driver.findElement(By.xpath(".//*[@id='center_column']/p")).getText();

			returnMap.put("TITLE", title);
			returnMap.put("BUYPROCEDURE", buyProcedure);
			returnMap.put("CARDSTATUS", cartStatus);
		}
		catch(Exception e)
		{
			returnMap.put("EXCEPTION", e.getLocalizedMessage()); 
		}

		return returnMap;
	}
}
