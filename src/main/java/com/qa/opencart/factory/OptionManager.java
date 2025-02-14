package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager 
{

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

public OptionManager(Properties prop)
{
	this.prop=prop;
}
    public ChromeOptions getChromeOptions()
    {
    	co=new ChromeOptions();
    	if(Boolean.parseBoolean(prop.getProperty("headless")))
    	{
    		System.out.println("Running in HeadLess mode");
    		co.addArguments("--headless");
    	}
    	if(Boolean.parseBoolean(prop.getProperty("incognito")))
    	{
    		System.out.println("Running in incogito mode");
    		co.addArguments("--incognito");
    	}
    return co;
    }
    public FirefoxOptions getFirefoxOptions()
    {
    	fo=new FirefoxOptions();
    	if(Boolean.parseBoolean(prop.getProperty("headless")))
    	{

    		System.out.println("Running in HeadLess mode");
    		fo.addArguments("--headless");
    	}
    	if(Boolean.parseBoolean(prop.getProperty("incognito")))
    	{
    		System.out.println("Running in incogito mode");
    		fo.addArguments("--incognito");
    	}
    return fo;
    }
    public EdgeOptions getEdgeOptions()
    {
    	eo=new EdgeOptions();
    	if(Boolean.parseBoolean(prop.getProperty("headless")))
    	{

    		System.out.println("Running in HeadLess mode");
    		eo.addArguments("--headless");
    	}
    	if(Boolean.parseBoolean(prop.getProperty("incognito")))
    	{
    		System.out.println("Running in incogito mode");
    		eo.addArguments("--inPrivate");
    	}
    return eo;
    }
}

