package com.fastcompany;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Init.AbstractPage;
import com.Init.Common;

public class HomeUiVerification extends AbstractPage{

	public HomeUiVerification(WebDriver driver) {
		super(driver);
	}

//	@FindBy(xpath="//section[@role='main' and position()=1]//section[@class='homepage-page']//parent::section[@role='main']/preceding::div[@class='masthead']//a//h2")
	@FindBy(xpath="//section[@class='homepage-page']//parent::section[@role='main']/preceding::div[@class='masthead']//a//h2")
	private WebElement eleLogo;

//	@FindBy(xpath="//section[@role='main' and position()=1]//section[normalize-space(@class='featured-feed featured-feed--homepage')]//ancestor::section[@class='homepage-page']//div[@class='homepage-desktop']//section//h2[@class='slug  column__slug']//a")
	@FindBy(xpath="//section[normalize-space(@class='featured-feed featured-feed--homepage')]//ancestor::section[@class='homepage-page']//div[@class='homepage-desktop']//section//h2[@class='slug  column__slug']//a")
	private WebElement eleNews;

	
	public boolean VerifyIfLogoPresent() {
		Common.waitForElementVisibility(driver, eleLogo);
		return Common.isElementDisplayed(eleLogo);

	}

	public boolean VerifyNavigationListItems() {
		Common.waitForElementVisibility(driver, eleNews);
		return Common.isElementDisplayed(eleNews);
	}

}
