package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.basepackage.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.constants.AppError;

public class LoginPageTest extends BaseTest
{

	@Test
	public void loginPageTitleTest()
	{
		String actualTitle=loginpage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstant.LOCAL_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
	}
	@Test
	public void loginPageUrl()
	{
		String url=loginpage.getLoginPageURL();
		Assert.assertTrue(url.contains(AppConstant.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND_ERROR);
	}
	@Test
	public void forgotLinkDisplay()
	{
		Assert.assertTrue(loginpage.isForgotPWDLinkDispaled(),AppError.ELEMENT_NOT_FOUND_ERROR);
	}
	@Test
	public void loginTest()
	{
		homepage= loginpage.doLogin(prop.getProperty("userID").trim(), prop.getProperty("PWD").trim());
		Assert.assertEquals(homepage.getHomePageTitle(), AppConstant.HOME_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
	}
	@Test(enabled = false)
	public void logoTest()
	{
	Assert.assertTrue(commonPage.isLogoDisplayed(),AppError.LOGO_NOT_FOUND_ERROR);
	}
	@DataProvider
	public Object[][] getFooterData()
	{
		return new Object[][] {
			{"About Us"},
			{"Contact Us"},
			{"Specials"},
			{"Order History"}
			
		};
	}
	@Test(dataProvider = "getFooterData",enabled = true)
	public void footerTest(String footerLink)
	{
		Assert.assertTrue(commonPage.checkFooterLink(footerLink));
	}
}
