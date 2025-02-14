package com.qa.opencart.factory;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.excepection.FrameWorkExcepection;

public class DriverFactory
{
	WebDriver driver;
	Properties prop;
	OptionManager optionManager;
	public static String highlight;
	
	//browser launch feature with switch case 
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	public WebDriver initBrowser(Properties prop)
	{
		String Browsertype= prop.getProperty("browser");
		System.out.println("Browser type is :" +Browsertype);
		highlight=prop.getProperty("highlight");
		optionManager = new OptionManager(prop);
		switch(Browsertype.trim().toLowerCase())
		{
		case "chrome":
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			//driver=new ChromeDriver(optionManager.getChromeOptions());
			break;

		case "firefox":
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			//driver=new FirefoxDriver(optionManager.getFirefoxOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
			//driver=new EdgeDriver(optionManager.getEdgeOptions());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			
			//driver=new SafariDriver();
			break;
		default:
			System.out.println("PLease enter a proper Browser type ");
			throw new FrameWorkExcepection("invalid browser type");
		}
	getDriver().manage().deleteAllCookies();
	getDriver().manage().window().maximize();
	getDriver().get(prop.getProperty("url").trim());
	return getDriver();
	}
	/**
	 * get griver using the threadlocal 
	 * */
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	//This Method is used to init the properties from .properties file
	 public Properties initProp()
	 {
		 prop = new Properties();
		 String envName =System.getProperty("env");
		 System.out.println("Running the test suiton env : " +envName);
		 FileInputStream ip= null;
		 
		 try {
		 if(envName==null)
		 {
			 System.out.println("NO env is paased ,hence running the test suit on the qa env");
			 ip= new FileInputStream(AppConstant.CONIF_QA_PROP_FILE_PATH);
		 }
		 else 
		  {
		switch(envName.trim().toLowerCase())
		       {
		case "qa":
			ip= new FileInputStream(AppConstant.CONIF_QA_PROP_FILE_PATH);
			break;
		case "dev":
			ip= new FileInputStream(AppConstant.CONIF_DEV_PROP_FILE_PATH);
			break;
		case "prod":
			ip= new FileInputStream(AppConstant.CONIF_PROD_FILE_PATH);
		break;
		
			default:
				throw new FrameWorkExcepection("=== invalid env provide a valid env");
		
		          }
		 }
		 prop.load(ip);
		 }
		 catch(FileNotFoundException e)
		 {
			 e.printStackTrace();
		 } catch (IOException e) {
			
			e.printStackTrace();
		}
			 
			 
				return prop;
		 }
	 } 

