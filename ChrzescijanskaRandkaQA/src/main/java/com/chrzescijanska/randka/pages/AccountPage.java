package com.chrzescijanska.randka.pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {
	WebDriver driver;
	

//@FindBy(className=("tiny button radius btn-logout"))
//private WebElement profileButton;
	
	public AccountPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

//public boolean getDisplayStatusOfprofileButton() {
	//boolean displayStatus = profileButton.isDisplayed();
	//return displayStatus;
}



