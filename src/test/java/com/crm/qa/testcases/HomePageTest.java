package com.crm.qa.testcases;

import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest(){
		super();//using this it will not throw null pointer exception
	}
	
	// test cases should be independent, so always use login and logout for each test case
	
	@BeforeMethod
	public void setUp(){
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		//test
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(){		
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO","Home Page Title Not Matched");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest(){
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest(){
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	
	@AfterMethod(enabled=true)
	public void tearDown(){
		driver.quit();
	}
	
}
