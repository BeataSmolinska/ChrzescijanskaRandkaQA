package chrzescijanksaRandka.testScenarios;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import chrzescijanskaRandka.base.Base;
import pl.chrzescijanskaRandka.pages.AccountPage;
import pl.chrzescijanskaRandka.pages.HomePage;
import pl.chrzescijanskaRandka.pages.LoginPage;
import pl.chrzescijanskaRandka.utils.Utils;

public class LoginTest extends Base {
	LoginPage loginPage;
	AccountPage accountPage;
	HomePage homePage;

	LoginTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.naviageToLoginPage();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

	@Test(priority = 1)
	public void verifyLoggingIntoTheApplicationUsingValidCredentials() {

		loginPage.clickOnFirstLoginButton();
		loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfprofileButton(), "profileButton is not displated");
	}

	@Test(priority = 2)
	public void verifyLoggingIntoTheApplicationUsingInvalidCredentials() {
		loginPage.login(Utils.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		loginPage.waitForTextInEmailPasswordNotMatchingWarningElement();
		loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();

		Assert.assertTrue(
				loginPage.retrieveEmailPasswordNotMatchingWarningMessageText()
						.contains(dataProp.getProperty("emailPasswordNoMatchWarning")),
				"Expected Warning message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoggingIntoTheApplicationUsingKeyboardKeysTabAndEnter() {

		loginPage.loginUsingTabAndEnter(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfprofileButton(), "profileButton is not display");
	}

	@Test(priority = 4)
	public void VerifyLoggingIntoTheApplicationWithoutProvidingAnyCredentials() {
		loginPage.clickOnFirstLoginButton();
		loginPage.clickOnSecondaryLoginButton();
		Assert.assertTrue(loginPage.getDisplayStatusOfsecondaryLoginButton(), "secondaryLoginButton is not display");

	}

	@Test(priority = 5)
	public void VerifyLoggingOutOfTheApplication() {

		loginPage.clickOnFirstLoginButton();
		loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
		AccountPage accountPage = new AccountPage(driver);
		accountPage.clickOnlogOutButton();
		HomePage homePage = new HomePage(driver);
		Assert.assertTrue(homePage.getDisplayStatusOfzalogujButton(), "zalogujButton is not display ");

	}
}
