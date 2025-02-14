package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.util.ElementUtil;

public class CommonPages
{
private WebDriver driver;
private ElementUtil eleUtil;
public CommonPages(WebDriver driver)
{
	this.driver=driver;
	eleUtil=new ElementUtil(driver);
}
private By Logo=By.className("img-responsive");
private By footer =By.xpath("//footer//a");

public boolean isLogoDisplayed()
{
	return eleUtil.doIsElementDisplayed(Logo);
}

public List<String> getFooters()
{
	List<WebElement> foosterList=eleUtil.waitForElementsPresence(footer, AppConstant.DEFAULT_TIME_OUT);
System.out.println("Total number of list in the footer" +foosterList.size());
List<String> footers= new ArrayList<String>();
for(WebElement e:foosterList)
{
	String text=e.getText();
	footers.add(text);
}
return footers;
}
public boolean checkFooterLink(String footerLink)
{
	return getFooters().contains(footerLink);
	
}
}
