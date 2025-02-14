package com.qa.opencart.pages;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.util.ElementUtil;

public class ProductDetailsPage 
{
	private WebDriver driver;
	private ElementUtil elementutil;
	private Map<String ,String > prodMap; 
	public ProductDetailsPage(WebDriver driver)
	{
		// TODO Auto-generated constructor stub
				this.driver=driver;
				elementutil=new ElementUtil(driver);
	}
	//Private locators 
	private By productHeader=By.tagName("h1");
	private By productImages= By.cssSelector("ul.thumbnails img");
	private By producMetaDetails=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceDetails=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	//public methods
	
	public String getProductHeader()
	{
		String Header=elementutil.doElementGetText(productHeader);
		System.out.println("Product header:====>" +Header);
		return Header;
	}
	public int getProductImagesCount()
	{
		int imagecount=elementutil.waitForElementsPresence(productImages, AppConstant.DEFAULT_TIME_OUT).size();	
		System.out.println( getProductHeader() +" image Count"+ imagecount);
		return imagecount;
	}
	//get the full Product information header,image count meta data price 
	
	public Map<String,String> getProductInfo()
	{
		//prodMap=new HashMap<String, String>();
		//prodMap=new LinkedHashMap<String, String>();
		prodMap = new TreeMap<String, String>();
		prodMap.put("Header", getProductHeader());
		prodMap.put("ImageCount", getProductImagesCount()+"");
		getProductMetaData();
		getProductPriceData();
		return prodMap;
		
	}
	private void getProductMetaData()
	{
		List<WebElement> metaList=elementutil.waitForElementsPresence(producMetaDetails, AppConstant.DEFAULT_TIME_OUT);
		for(WebElement e: metaList)
		{
			String Metatext=e.getText();
		String meta[]=	Metatext.split(":");
          String metaKey=meta[0].trim();
          String metaValue=meta[1].trim();
          prodMap.put(metaKey, metaValue);
		}
		
	}
    private void getProductPriceData()
    {
	List<WebElement> prodPricedata=elementutil.waitForElementsPresence(productPriceDetails, AppConstant.DEFAULT_TIME_OUT);
	String productPrice=prodPricedata.get(0).getText().trim();
	String productexPrice[]=prodPricedata.get(1).getText().split(":");
	String productExTax=productexPrice[1].trim();
	prodMap.put("Price", productPrice);
	prodMap.put("exTax", productExTax);
   }
	
}
