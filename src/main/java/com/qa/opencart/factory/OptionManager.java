package com.qa.opencart.factory;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager 
{

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	private static final Logger log = LogManager.getLogger(OptionManager.class);
public OptionManager(Properties prop)
{
	this.prop=prop;
}
    public ChromeOptions getChromeOptions()
    {
    	co=new ChromeOptions();
    	if(Boolean.parseBoolean(prop.getProperty("headless")))
    	{
    		//System.out.println("Running in HeadLess mode");
    		log.info("=== Running in chrome browser HeadLess Mode===");
    		co.addArguments("--headless");
    	}
    	if(Boolean.parseBoolean(prop.getProperty("incognito")))
    	{
    		//System.out.println("Running in incogito mode");
    		log.info("=== Running in chrome browser incogito Mode===");

    		co.addArguments("--incognito");
    	}
    return co;
    }
    public FirefoxOptions getFirefoxOptions()
    {
    	fo=new FirefoxOptions();
    	if(Boolean.parseBoolean(prop.getProperty("headless")))
    	{

    		//System.out.println("Running in HeadLess mode");
    		log.info("=== Running in Firefox browser  HeadLess Mode===");

    		fo.addArguments("--headless");
    	}
    	if(Boolean.parseBoolean(prop.getProperty("incognito")))
    	{
    		//System.out.println("Running in incogito mode");
    		log.info("=== Running in firefox browser incogito Mode===");
    		fo.addArguments("--incognito");
    	}
    return fo;
    }
    public EdgeOptions getEdgeOptions()
    {
    	eo=new EdgeOptions();
    	if(Boolean.parseBoolean(prop.getProperty("headless")))
    	{

//    		System.out.println("Running in HeadLess mode");
    		log.info("=== Running in edge HeadLess Mode===");

    		eo.addArguments("--headless");
    	}
    	if(Boolean.parseBoolean(prop.getProperty("incognito")))
    	{
    		//System.out.println("Running in incogito mode");
    		log.info("=== Running in edge browser incognito Mode===");

    		eo.addArguments("--inPrivate");
    	}
    return eo;
    }
}

