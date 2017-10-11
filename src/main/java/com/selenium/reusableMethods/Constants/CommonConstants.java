package com.selenium.reusableMethods.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface CommonConstants{
	public static final String CLASSPATH = System.getProperty("user.dir");
	public static final String TESTDATAPATH = CommonConstants.CLASSPATH + "//test-output//testData";
	public static final String TESTRESULTSPATH = CommonConstants.CLASSPATH + "//test-output//testResults";
	public static final String BROWSER = "chrome";
	public static final String SITEURL = "http://automationpractice.com/index.php"; 
	public static final String CARTINITSCRNSHOTPATH = TESTRESULTSPATH + "//loginTestResult//screenshot//CartInitial//failureScrnShot.png";
	public static final String FILTERCNTSCRNSHOTPATH = TESTRESULTSPATH + "//loginTestResult//screenshot//FilterCount//failureScrnShot.png";
	public static final String TABWOMENSCRNSHOTPATH = TESTRESULTSPATH + "//loginTestResult//screenshot//TabWomenLink//failureScrnShot.png";
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh-mm-ss a");
	public static String CURRENT_TIME_STAMP = "_"+sdf.format(date);
}
