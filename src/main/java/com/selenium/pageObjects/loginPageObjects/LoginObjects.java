package com.selenium.pageObjects.loginPageObjects;
/**
 * This enum contains locators for login class
 * 
 * @author HarshA
 */
public enum LoginObjects {
	//name locators
	LOGIN("login"),
	//id locators
	EMAIL("email"),
	PASSWORD("passwd"),
	SUBMITBUTTON("SubmitLogin"),
	//xpath locators
	HEADINGFIRSTPART("//div[@class='alert alert-danger']/p"),
	HEADINGSECONDPART("//div[@class='alert alert-danger']/ol/li"),
	ACCOUNTICON(".//*[@title ='View my customer account']"),
	LOGOUT(".//*[@class='logout']");
	//css locators

	private final String value;
	private LoginObjects(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value; 
	}
}
