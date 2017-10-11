package com.selenium.automatedTests.tabWomenTests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.reusableMethods.GenericMethods;
import com.selenium.reusableMethods.XlsReader;
import com.selenium.reusableMethods.Constants.CommonConstants;
import com.selenium.testServiceClasses.tabWomenServiceClass.FilterCountService;

public class FilterCountTest extends FilterCountService {

	@Test
	public void validateFilterCount() {
		GenericMethods gm = new GenericMethods();
		WebDriver driver = null;
		ExtentReports extent = null;
		String testDataPath = CommonConstants.TESTDATAPATH + "//tabWomenProductTestData//FilterProductCount.xlsx";
		extent = new ExtentReports(
				CommonConstants.TESTRESULTSPATH + "//tabWomenProductResult//FilterProductCountResult.html", true);
		ExtentTest test = extent.startTest("Women Dresses Filter Count Test");
		XlsReader testData = new XlsReader(testDataPath);
		testData.createExcel(testDataPath,
				CommonConstants.TESTRESULTSPATH + "//tabWomenProductResult//FilterProductCountResult.xlsx");
		XlsReader resultExcel = new XlsReader(
				CommonConstants.TESTRESULTSPATH + "//tabWomenProductResult//FilterProductCountResult.xlsx");
		String sheetName = "FilterProduct";
		int rowCount = resultExcel.getRowCount(sheetName);
		String filter = "";
		String subFilter = "";
		String expectedCount = "";
		ExtentTest testChild = null;
		driver = gm.startDesktopDriver(driver, CommonConstants.BROWSER, CommonConstants.SITEURL);
		goToWomenSec(driver);
		for (int row = 1; row <= rowCount; row++) {
			try {
				filter = resultExcel.getCellData(sheetName, "Filter", row);
				subFilter = resultExcel.getCellData(sheetName, "Sub Filter", row);
				expectedCount = resultExcel.getCellData(sheetName, "Expected Count", row);

				if (filter.equals("Compositions"))
					testChild = extent.startTest("Compositions Section Test",
							"This is to test count after filter for compositions section");
				else
					testChild = extent.startTest("Styles Section Test",
							"This is to test count after filter for styles section");

				testChild.log(LogStatus.INFO, "Site url opened in " + CommonConstants.BROWSER + " browser");
				testChild.log(LogStatus.INFO, "Validating count for sub filter : " + subFilter);
				String result = countAfterFilter(driver, subFilter, testChild);

				if (!result.equals("Issue in fetching count")) {
					testChild.log(LogStatus.INFO, "Expected count : " + expectedCount);
					testChild.log(LogStatus.INFO, "Reseting filter");
					resetFilter(driver);
					validateCount(result, expectedCount, testChild);
				}

			} catch (Exception e) {
				testChild.log(LogStatus.INFO, "Exception :: " + e.getMessage());
			}
			test.appendChild(testChild);
		}
		gm.quitDriver(driver);
		extent.endTest(test);
		extent.flush();
		extent.close();
	}
}
