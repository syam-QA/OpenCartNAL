package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;
public class LoginPage 
{

	private WebDriver driver;
	private ElementUtil elementutil;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtil(driver);
	}
	//Private By locater for the element in login page
	
	private By UserId=By.xpath("//input[@id='input-email']");
	private By pwd=By.id("input-password");
	private By loginButton=By.xpath("//input[@type='submit']");
	private By forgotpwdLink=By.linkText("Forgotten Password");
	
	//public methods used for the validation
	@Step("getLoginPageTitle")
	public String getLoginPageTitle()
	{
	 //String loginpagetitle=driver.getTitle();
	 String title=elementutil.waitForTitleIs(AppConstant.LOCAL_PAGE_TITLE, AppConstant.DEFAULT_TIME_OUT);
	 System.out.println("Login Page tiltle : " +title);
	 ChainTestListener.log("Login Page tiltle : " +title);
	 return title ;
	}
	@Step(" get login in Page URl")
	public String getLoginPageURL()
	{
	//String loginPageURL=	driver.getCurrentUrl();
	String url =elementutil.waitForURLContains(AppConstant.LOGIN_PAGE_URL_FRACTION, AppConstant.DEFAULT_TIME_OUT);
	System.out.println("Login page URL : " +url);	
	return url;
	}
	@Step("Forgot PED Link Displayed ")
	public boolean isForgotPWDLinkDispaled()
	{
		return elementutil.doIsElementDisplayed(forgotpwdLink);
		//it will return true or false by check the forgot password link is displayed or not
		//return driver.findElement(forgotpwdLink).isDisplayed();
	}
	@Step(" Login with username:{0} and password : {1}")
	public HomePage doLogin(String userID,String PWD)
	{
		//driver.findElement(UserId).sendKeys(userID);
		elementutil.waitForElementVisible(UserId, AppConstant.SHORT_TIME_OUT).sendKeys(userID);
		//driver.findElement(pwd).sendKeys(PWD);
		elementutil.doSendKeys(pwd,PWD);
		//driver.findElement(loginButton).click();
		elementutil.doClick(loginButton);
		String accountTitle=driver.getTitle();
		System.out.println(accountTitle);
		return new HomePage(driver);
	}
	
	
	
}
