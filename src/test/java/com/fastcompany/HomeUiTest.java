package com.fastcompany;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Init.ITestStatus;
import com.Init.SeleniumInit;
import com.utility.Constants;

public class HomeUiTest extends SeleniumInit {
	@Test
	public void testHomeUi() {
		boolean isLogoDisplayed = homeUiVerification.VerifyIfLogoPresent();
		
		if (isLogoDisplayed) 
		{
			logStatus(ITestStatus.PASSED, Constants.SUCCESS_MESSAGE_LOGO);
		}
		else {
			logStatus(ITestStatus.FAILED, Constants.ERROR_MESSAGE_LOGO);
		}
//		Assert.assertTrue(isLogoDisplayed, Constants.ERROR_MESSAGE_LOGO);

		boolean isSideListDisplayed = homeUiVerification.VerifyNavigationListItems();
		
		if (isSideListDisplayed) 
		{
			logStatus(ITestStatus.PASSED, Constants.SUCCESS_MESSAGE_LIST_ITEMS);
		}
		else {
			logStatus(ITestStatus.FAILED, Constants.ERROR_MESSAGE_LIST_ITEMS);
		}
//		Assert.assertTrue(isSideListDisplayed, Constants.ERROR_MESSAGE_LIST_ITEMS);
	
	}
}
