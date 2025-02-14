package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.util.ElementUtil;
public class SearcResultPage 
{
private WebDriver driver;
private ElementUtil elementutil;

public SearcResultPage(WebDriver driver)
{
	this.driver=driver;
	elementutil=new ElementUtil(driver);
	
}
//Private Locator
private By searchResultList=By.xpath("//div[@class='product-thumb']");

//Public Search methods

public int searchResultCount()
{
	int resultCount= elementutil.waitForElementsVisible(searchResultList, AppConstant.DEFAULT_TIME_OUT).size();
   System.out.println(resultCount);
    return resultCount;
	
    //return driver.findElements(searchResultList).size();
}

public ProductDetailsPage selectProduct(String productName)
{
	//driver.findElement(By.linkText(productName)).click();
	//System.out.println("product name :" + productName);
	elementutil.doClick(By.linkText(productName));
	return new ProductDetailsPage(driver);
}

}
