package com.selenium.testServiceClasses.tabWomenServiceClass;
import java.util.HashMap;
import java.util.Map.Entry;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pageClasses.womenSecPageClass.TabWomenLink;

public class TabWomenService extends TabWomenLink{
	
	public void validateText(WebDriver driver, ExtentTest test) 
	{
		HashMap<String, Object> returnMap = textAfterLinkClick(driver);
		for(Entry<String, Object> returnValues : returnMap.entrySet())
		{
			if(((String)returnValues.getKey()).equals("YSFTEXT") || ((String)returnValues.getKey()).equals("SUPERTEXT"))
			{
				if(((String)returnValues.getValue()).equals("Women") || ((String)returnValues.getValue()).equals("Women"))
					test.log(LogStatus.PASS, ((String)returnValues.getKey())+" passed");
				else
					test.log(LogStatus.FAIL, ((String)returnValues.getKey())+" failed");
			}
			else 
			{
				if(((String)returnValues.getValue()).equals("WOMEN") || ((String)returnValues.getValue()).equals("WOMEN"))
					test.log(LogStatus.PASS, ((String)returnValues.getKey())+" passed");
				else
					test.log(LogStatus.FAIL, ((String)returnValues.getKey())+" failed");
			}
		}
	}

}
