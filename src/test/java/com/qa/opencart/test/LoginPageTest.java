package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.basepackage.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.constants.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Epic("Epiv 100 :Desgin login page")
@Story("Open cart login page")
@Feature("Feature 50 :Login Page")
@Owner("SyamKUmar")
public class LoginPageTest extends BaseTest
{

	@Description("Checking the login page title   ")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest()
	{
	
		
		ChainTestListener.log("Verifying the Loginpage Title");
		String actualTitle=loginpage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstant.LOCAL_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
	}
	@Description("Checking the login page URL   ")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageUrl()
	{
		String url=loginpage.getLoginPageURL();
		Assert.assertTrue(url.contains(AppConstant.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND_ERROR);
	}
	@Description("Checking the Forgot Password link  ")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotLinkDisplay()
	{
		Assert.assertTrue(loginpage.isForgotPWDLinkDispaled(),AppError.ELEMENT_NOT_FOUND_ERROR);
	}
	@Description("Checking the user is able with rightcredentials  ")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest()
	{
		homepage= loginpage.doLogin(prop.getProperty("userID").trim(), prop.getProperty("PWD").trim());
		Assert.assertEquals(homepage.getHomePageTitle(), AppConstant.HOME_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
	}
	@Description("Checking the Company Logo  ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(enabled = true)
	public void logoTest()
	{
	Assert.assertTrue(commonPage.isLogoDisplayed(),AppError.LOGO_NOT_FOUND_ERROR);
	}
	@DataProvider
	public Object[][] getFooterData()
	{System.out.println();
		return new Object[][] {
			{"About Us"},
			{"Contact Us"},
			{"Specials"},
			{"Order History"}
			
		};
	}
	@Description("Checking the footer   ")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "getFooterData",enabled = true)
	public void footerTest(String footerLink)
	{
		Assert.assertTrue(commonPage.checkFooterLink(footerLink));
	}
}
