package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.basepackage.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.util.ExcelUtil;

public class ProductInfoPageTest extends BaseTest
{
	@BeforeClass
	public void produectInfoPage()

	{
	homepage=	loginpage.doLogin("opencartpra@open.com", "Selenium@12345");
	}
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][]
				{
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
				};
		
	}
	@Test(dataProvider="getProductData")
	public void productSearchTest(String searchkey,String productname)
	{
		searchresult=homepage.doSearch(searchkey);
		productdetails=searchresult.selectProduct(productname);
		String actualProduectHeader=productdetails.getProductHeader();
		Assert.assertEquals(actualProduectHeader, productname);
		}
	@DataProvider
	public Object[][] getProductImageData()
	{
		return new Object[][]
				{
			{"macbook","MacBook Pro",4},
			{"macbook","MacBook Air",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}
				};
		
	}
	@DataProvider
	public Object[][] getProductImageSheetData()
	{
		Object productData[][]=ExcelUtil.getTestData(AppConstant.PRODUCT_SHEET_NAME);
		return productData;
		
	}
	@Test(dataProvider = "getProductImageSheetData")
	public void productImageCount( String searchkey,String productname,String expimagecount)
	{
		searchresult=homepage.doSearch(searchkey);
		productdetails=searchresult.selectProduct(productname);
		int actualProduectImageCount=productdetails.getProductImagesCount();
		Assert.assertEquals(actualProduectImageCount, Integer.parseInt(expimagecount));
		}
	
	@Test
	public void productInfoTest()
	{
		searchresult=homepage.doSearch("macBook");
		productdetails=searchresult.selectProduct("MacBook Pro");
		Map<String,String>productInfoMap= productdetails.getProductInfo();
	    productInfoMap.forEach((k,v) ->  System.out.println(k+":"+v));
	    
	    SoftAssert softassert=new SoftAssert();
	    
	    softassert.assertEquals(productInfoMap.get("Brand"), "Apple");
	    softassert.assertEquals(productInfoMap.get("Availability"), "In Stock");
	    softassert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
	    softassert.assertEquals(productInfoMap.get("Reward Points"), "800");
	    softassert.assertEquals(productInfoMap.get("Price"), "$2,000.00");
	    softassert.assertEquals(productInfoMap.get("exTax"), "$2,000.00");
	    softassert.assertAll();
	}
}
