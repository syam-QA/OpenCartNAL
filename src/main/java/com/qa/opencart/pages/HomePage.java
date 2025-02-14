package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.util.ElementUtil;

import java.util.List;
import java.util.ArrayList;


public class HomePage
{
	private WebDriver driver;
	private ElementUtil elementutil;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtil(driver);
	}
           
           //Private By Locatoer:
           private By logoutlink=By.linkText("Logout");
           private By Rheaders=By.xpath("//div//h2");
           private By search=By.name("search");
           private By sButton=By.xpath("//div[@id='search']/span[@class='input-group-btn']");
           
           //Public Page Action:
           public String getHomePageTitle()
       	{
       	 //String loginpagetitle=driver.getTitle();
       	 String title=elementutil.waitForTitleIs(AppConstant.HOME_PAGE_TITLE, AppConstant.DEFAULT_TIME_OUT);
       	 System.out.println("Login Page tiltle : " +title);
       	 return title ;
       	}
       	public String getHomePageURL()
       	{
       	//String loginPageURL=	driver.getCurrentUrl();
       	String url =elementutil.waitForURLContains(AppConstant.HOME_PAGE_URL_FRACTION, AppConstant.DEFAULT_TIME_OUT);
       	System.out.println("Login page URL : " +url);	
       	return url;
       	}
            
        public boolean isLogoutLinkExist()
        {
       return elementutil.doIsElementDisplayed(logoutlink);
        }
        public void dologout()
        {
        	if(isLogoutLinkExist())
        	{
        		elementutil.doClick(logoutlink);
        	}
        	//pending --working in prograss
        }
        public List<String> getHeaderlist()
        {
        	
        	List<WebElement> headerList = elementutil.waitForElementsVisible(Rheaders, AppConstant.DEFAULT_TIME_OUT);
        	List<String> headertValueList= new ArrayList<String>();
        	for(WebElement e:headerList)
        	{
        		String text=e.getText();
        		headertValueList.add(text);
        	}
          return headertValueList;
        }
        public SearcResultPage doSearch(String searchKey)
        {
        	System.out.println(searchKey);
        	WebElement serachEle =elementutil.waitForElementVisible(search, AppConstant.DEFAULT_TIME_OUT);
        	elementutil.doSendKeys(serachEle, searchKey);
        	elementutil.doClick(sButton);
        	//serachEle.sendKeys(searchKey);
        	//WebElement element =driver.findElement(search);
        	//element.clear();
        	//element.sendKeys(searchKey);
        	//driver.findElement(sButton).click();
        	return new SearcResultPage(driver);
        	
        }
           
           
           

	
}
