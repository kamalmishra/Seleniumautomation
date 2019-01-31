package com.opus.Testcase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.opus.Testbase.seleniumLayer;

public class newuserScreen {

	seleniumLayer selenium = new seleniumLayer();

	@BeforeTest
	private void initializeConfigData() {
		selenium.initizlizePropertyFile();
	}

	@Test(priority = 1)
	private void verifyNewUserScreen() {
		selenium.openBrowser("browser");
		selenium.loadUrl("url");
		selenium.returnElement("Userslink_xpath").click();
		selenium.returnElement("Newuserbutton_xpath").click();
		selenium.returnElement("Username_xpath").sendKeys("Mohan");
		selenium.returnElement("Password_xpath").sendKeys("kamal123");
		selenium.returnElement("Email_xpath").sendKeys("Mohan@gmail.com");
		selenium.returnElement("Createuserbutton_xpath").click();
	}

	@Test(priority = 2)
	private void verifyUsersFilters() {

		selenium.loadUrl("url");
		selenium.returnElement("Userslink_xpath").click();
		selenium.returnElement("Usernamefilter_xpath").sendKeys("Contains");
		selenium.returnElement("Usernameinputbox_xpath").sendKeys("Mohan");
		selenium.returnElement("Emailfilter_xpath").sendKeys("Contains");
		selenium.returnElement("Emailinputbox_xpath").sendKeys("Mohan@gmail.com");
		selenium.returnElement("Fromdatepicker_xpath").click();
		selenium.returnElement("Fromdatepicker_xpath").sendKeys("2019-01-29");
		selenium.returnElement("Todatepicker_xpath").click();
		selenium.returnElement("Todatepicker_xpath").sendKeys("2019-03-29");
		selenium.returnElement("Filterbutton_xpath").click();
		Assert.assertNotNull(selenium.returnElement("Listuser_xpath"));
	}

	@AfterTest()
	private void closeBrowser() {
		selenium.closeDriver();
	}

}
