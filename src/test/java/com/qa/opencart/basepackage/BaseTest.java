package com.qa.opencart.basepackage;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.Listeners;
import org.testng.annotations.AfterTest;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
//import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.CommonPages;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDetailsPage;
import com.qa.opencart.pages.SearcResultPage;

//@Listeners(ChainTestListener.class)
public class BaseTest 
{
	WebDriver driver;
	DriverFactory df;
	
	protected Properties prop;
	
	protected CommonPages commonPage;
	protected LoginPage loginpage;
	protected HomePage homepage;
	protected SearcResultPage searchresult;
	protected ProductDetailsPage productdetails;
	@BeforeTest
	public void setup()
	{    
		df=new DriverFactory();
		prop =df.initProp();
		driver=df.initBrowser(prop);
		loginpage =new LoginPage(driver);
		commonPage =new CommonPages(driver);
		
		ChainPluginService.getInstance().addSystemInfo("Owner#", "SyamKuamr");
		ChainPluginService.getInstance().addSystemInfo("HeadLess#", prop.getProperty("headless"));
		ChainPluginService.getInstance().addSystemInfo("Incognito", prop.getProperty("incognito"));
		//homepage=new HomePage(driver);
	}
	@AfterMethod
	public void attchScrrenshot(ITestResult result)
	{
		if(!result.isSuccess())
		{
			System.out.println();
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
		
	}
	@AfterTest
	public void tearDoown()	{
		driver.quit();
	}
}
