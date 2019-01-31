package com.opus.Testbase;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class seleniumLayer {
	Properties Pro;
	Properties OR;
	WebDriver driver;
	FileInputStream Fis = null;

	public void initizlizePropertyFile() {
		if (Pro == null) {
			try {

				Pro = new Properties();
				Fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\java\\resources\\project.properties");
				Pro.load(Fis);

				OR = new Properties();
				Fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\java\\resources\\or.properties");
				OR.load(Fis);

			} catch (Throwable t) {
				System.out.println("Error occurred while loading property files " + t.fillInStackTrace());
			}
		}
	}

	public void openBrowser(String browserType) {

		String btype = Pro.getProperty(browserType);

		if (btype.equals("Mozilla")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\java\\drivers\\geckodriver.exe");

			driver = new FirefoxDriver();

		} else if (btype.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\java\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (btype.equals("IE")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\test\\java\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else {
			System.out.println("Failed to match driver configuration ");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public void loadUrl(String testurl) {
		driver.get(Pro.getProperty(testurl));
	}

	public WebElement returnElement(String ElementKey) {

		WebElement e = null;
		WebDriverWait wait = null;

		try {
			if (ElementKey.endsWith("_id")) {
				wait = new WebDriverWait(driver, 30);
				e = wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.getProperty(ElementKey))));
			} else if (ElementKey.endsWith("_xpath")) {
				wait = new WebDriverWait(driver, 30);
				e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(ElementKey))));
			} else if (ElementKey.endsWith("_name")) {
				wait = new WebDriverWait(driver, 30);
				e = wait.until(ExpectedConditions.elementToBeClickable(By.name(OR.getProperty(ElementKey))));
			} else {
				System.out.println("Locator not correct [Get Element ] - " + OR.getProperty(ElementKey));
			}
		} catch (Exception er) {
			System.out.println("Failed to get element [getelement] " + OR.getProperty(ElementKey));
		}
		return e;
	}
	public void closeDriver() {
		if(!(driver==null)) {
			driver.close();
		}
	}
}
