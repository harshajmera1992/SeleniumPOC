package com.selenium.reusableMethods.Constants;


/**
 *This enum contains relative paths for different files
 *
 * @author HarshA
 */
public enum SheetConstants {

	TESTDATAPATH(CommonConstants.CLASSPATH + "//test-output//testData"),
	TESTRESULTSPATH(CommonConstants.CLASSPATH + "//test-output//testResults"),
	TESTDATAPATH_LOGIN(SheetConstants.TESTDATAPATH.getValue() + "//loginTestData"),
	TESTRSLTPATH_LOGIN(SheetConstants.TESTRESULTSPATH.getValue() + "//loginTestResult"),
	SCREENSHOT_PATH_LOGINTEST(SheetConstants.TESTRSLTPATH_LOGIN.getValue() + "//loginTestResult//screenshot//Login//failureScrnShot.png"); 
	
	private final String value;
	private SheetConstants(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value; 
	}
	
}
