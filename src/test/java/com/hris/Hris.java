package com.hris;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Hris {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		Hris.hrislogin("chrome", "https://hris.qainfotech.com/login.php");
		Hris.enterusername("aakashchoudhary");
		Hris.enterpassword("Cengage@3");
		Hris.hrissignin();
		Hris.hrismenu();
		Hris.hristimesheet();
		Hris.hrispunchdetails();
	}

	public static WebDriver getdriver(String browser) {
		switch (browser) {
		case "chrome":
			System.setProperty("ChromeDriver", "C:\\Users\\Admin\\MT_Workspace\\hris\\chromedriver.exe");
			return new ChromeDriver();
		case "firefox":
			return getdriver("firefox");
		}

		return new ChromeDriver();

	}

	public static void hrislogin(String browser, String URL) {
		getdriver(browser);
		driver.get(URL);
		driver.manage().window().maximize();
	}

	public static void enterusername(String username) {
		WebElement username_hris = element("xpath", "//input[@id='txtUserName']");
		sendKeys(username_hris, username);
	}

	public static void enterpassword(String password) {
		WebElement password_hris = element("xpath", "//input[@id='txtPassword']");
		sendKeys(password_hris, password);
	}

	public static void hrissignin() {
		WebElement signin_hris = element("xpath", "//input[@value='Sign In']");
		click(signin_hris);
	}

	public static void hrismenu() {
		WebElement menu_hris = element("xpath", "//a[@id='hamburger']");
		click(menu_hris);

	}

	public static void hristimesheet() {

		WebElement time_hris = element("xpath", "//a[@title='Time']");
		click(time_hris);
	}

	public static void hrispunchdetails() {
		try {
			WebElement punches_hris = element("xpath", "(//h1[contains(text(),'" + currentdate() + "')]/..//b)[2]");
			String val = punches_hris.getAttribute("innerText");
			System.out.println(val);
		} catch (NoSuchElementException e) {
			System.out.println("Exception details:" + e);
			System.out.println("No Punches Found Till Now");

		}

		System.out.println("Rest of the code..");
	}

	public static WebElement element(String locatortype, String locator) {
		By ele;
		switch (locatortype) {
		case "xpath":
			ele = By.xpath(locator);
			break;
		case "css":
			ele = By.cssSelector(locator);
			break;
		case "id":
			ele = By.id(locator);
			break;
		case "class":
			ele = By.className(locator);
			break;

		}

		return driver.findElement(By.xpath(locator));

	}

	public static void click(WebElement element) {
		element.click();

	}

	public static void sendKeys(WebElement element, String x) {
		element.sendKeys(x);
		System.out.println("passing string:" + x);
	}

	public static String currentdate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		String date = dtf.format(now);
		return date;
	}

}
