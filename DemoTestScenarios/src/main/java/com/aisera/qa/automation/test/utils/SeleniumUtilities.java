package com.aisera.qa.automation.test.utils;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aisera.qa.automation.test.common.*;

public class SeleniumUtilities {
	
	public void getElementbyName(WebDriver driver, String name, Properties prop) {

		if (name.equalsIgnoreCase(Constants.USER_EMAIL)) {
			WebElement email = driver.findElement(By.name("email"));
			highlightElement(driver, email);
			email.sendKeys(prop.getProperty("email"));
			setTimeOutForElements(driver, 1500);
		} else if (name.equalsIgnoreCase(Constants.PASSWORD)) {
			WebElement password = driver.findElement(By.name("password"));
			highlightElement(driver, password);
			password.sendKeys(prop.getProperty("password"));
			setTimeOutForElements(driver, 1500);

		}

	}

	public void getElementbyClass(WebDriver driver, String btnClassName, WebElement btnName) {

		btnName = driver.findElement(By.className(btnClassName));
		highlightElement(driver, btnName);
		btnName.click();
		setTimeOutForElements(driver, 1500);

	}

	public void getElementbyXpath(WebDriver driver, String xPath, WebElement element, Properties prop) {

		setTimeOutForElements(driver, 1500);
		element = driver.findElement(By.xpath(xPath));
		highlightElement(driver, element);
		setTimeOutForElements(driver, 1000);

		if (xPath.equals(Constants.EMAIL_ADDRESS_XPATH))
			element.sendKeys(prop.getProperty("chatUser"));
		else
			element.click();

		setTimeOutForElements(driver, 1000);

	}
	public void highlightElement(WebDriver driver, WebElement element) {

		String jsStyle = "'3px solid red'";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.border=" + jsStyle, element);

	}

	public void scrollDown(WebDriver driver) {
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("window.scrollBy(0,450)", "");
	}

	public void setTimeOutForElements(WebDriver driver, int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (Exception e) {
			System.out.println(" In setTimeOutForElements exception");
			e.printStackTrace();
		}
		// driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.MILLISECONDS);
	}

}
