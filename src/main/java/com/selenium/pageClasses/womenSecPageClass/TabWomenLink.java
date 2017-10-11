package com.selenium.pageClasses.womenSecPageClass;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.selenium.reusableMethods.GenericMethods;

public class TabWomenLink extends GenericMethods{

	public HashMap<String, Object> textAfterLinkClick(WebDriver driver)
	{
		HashMap<String,Object> returnMap = new HashMap<String, Object>();
		String ysfText,filterText,superText,subText = "";
		waitForElementTimeOut(driver, 10, By.className("sf-with-ul"));
		driver.findElement(By.className("sf-with-ul")).click();
		waitForElementTimeOut(driver, 10, By.xpath(".//*[contains(@class,'breadcrumb')]"));
		ysfText = driver.findElement(By.xpath(".//*[contains(@class,'breadcrumb')]")).getText().replace(">","").trim();
		filterText = driver.findElement(By.className("title_block")).getText().trim();
		superText = driver.findElement(By.className("category-name")).getText().trim();
		subText = driver.findElement(By.className("cat-name")).getText().trim();
		
		returnMap.put("YSFTEXT", ysfText);
		returnMap.put("FILTERTEXT", filterText);
		returnMap.put("SUPERTEXT", superText);
		returnMap.put("SUBTEXT", subText);
		return returnMap;
	}
}
