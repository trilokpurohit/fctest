package com.utility;

import org.openqa.selenium.WebDriver;

public class Commonverification {
	public static Boolean VerifyTitle(WebDriver p_Driver, String p_Value) {
		return p_Driver.getTitle().equalsIgnoreCase(p_Value);
	}
	
}
