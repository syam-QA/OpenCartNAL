package com.qa.opencart.test;

import java.util.List;

//import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.qa.opencart.basepackage.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.constants.AppError;

public class HomePageTest extends BaseTest
{
	@BeforeClass
	
	public void homePageSetUP()
	{
		homepage= loginpage.doLogin(prop.getProperty("userID").trim(), prop.getProperty("PWD").trim());
	}
@Test
public void homePageTitile()
{

	Assert.assertEquals(homepage.getHomePageTitle(), AppConstant.HOME_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
}

@Test
public void homePageURL()
{
	Assert.assertTrue(homepage.getHomePageURL().contains(AppConstant.HOME_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND_ERROR);
}
@Test
public void logoutLinkExist()
{
	Assert.assertTrue(homepage.isLogoutLinkExist(),AppError.ELEMENT_NOT_FOUND_ERROR);
}
@Test
public void headerTest()
{
	List<String> actualHeaders =homepage.getHeaderlist();
//	String header=actualHeaders.get(i).getText();
	System.out.println(actualHeaders);
//	String [] expectedHeader= {"My Account","My Affiliate Account","Newsletter"};
////	Assert the header
// //Assert the page headers
//   Assert.assertEquals(actualHeaders.size(), expectedHeader.length);   
//    for (int i = 0; i < actualHeaders.size(); i++) 
//    {\
//    	String header=actualHeaders.get(i).getText();
//        Assert.assertEquals(null, expectedHeader[i]);
//
//      }
}
@DataProvider
public Object[][] getSearchtData()
{
	return new Object[][]
			{
		{"MacBook",3},
		{"iMac",1},
		{ "Samsung",2},
		{"canon",1},
		{"airtel",0}
			};
			
}
@Test(dataProvider = "getSearchtData")
public void searchTest(String searchkey,int resultCount)
{
	searchresult= homepage.doSearch(searchkey);
	Assert.assertEquals(searchresult.searchResultCount(), resultCount);
}

}
