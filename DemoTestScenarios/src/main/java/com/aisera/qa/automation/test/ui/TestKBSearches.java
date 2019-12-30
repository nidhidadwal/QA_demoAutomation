package com.aisera.qa.automation.test.ui;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
//import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aisera.qa.automation.test.utils.SeleniumUtilities;
import com.aisera.qa.automation.test.common.Constants;
import com.aisera.qa.automation.test.common.StartValidateWebchat;

import org.apache.logging.log4j.*;


/**
 * @author Nidhi
 *
 */
public class TestKBSearches {

	static Properties prop = null;
	static StartValidateWebchat webchat = null;
	static SeleniumUtilities selutils = null;
	private static final Logger logger = LogManager.getLogger(TestKBSearches.class.getName());
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args)
			throws Exception {

		TestKBSearches kbs = new TestKBSearches();
		webchat = new StartValidateWebchat();
		selutils = new SeleniumUtilities();
		
		prop = new Properties();
		
		String envName =null;
		String appName = null;
		String channelName = null;
		
		System.setProperty("webdriver.chrome.driver", "/Users/Nidhi/Downloads/WebDriversEXEfiles/chromedriver");
		WebDriver driver = new ChromeDriver();

		// System.setProperty("webdriver.gecko.driver",
		// "/Users/Nidhi/Downloads/WebDriversEXEfiles/geckodriver");
		// WebDriver driver = new FirefoxDriver();

		try {
			
			 envName = System.getProperties().getProperty("EnvName");
			 appName = System.getProperties().getProperty("AppName"); // IT Helpdesk for dev1
			 channelName = System.getProperties().getProperty("ChannelName"); // Nik webchat 2 for dev1

			 if(envName!=null)
				 envName = envName.replace("%20", " ");
			 
			 if(appName!=null)
				 appName = appName.replace("%20", " ");
			 
			 if(channelName!=null)
				 channelName = channelName.replace("%20", " ");
			
			logger.debug("Env Name: " + envName);
			logger.debug("Aisera App:  " + appName);
			logger.debug("Webchat Channel: " + channelName);

			Constants.PROPERTIES_FILE_NAME = envName+".properties";
			
			logger.debug("Running test using " + Constants.PROPERTIES_FILE_NAME + " and using credentials from "
					+ Constants.PROPERTIES_FILE_NAME);
			
				
			if(Constants.PROPERTIES_FILE_NAME!=null)
			{
				InputStream input = kbs.getClass().getClassLoader().getResourceAsStream(Constants.PROPERTIES_FILE_NAME);
			

			if (input == null) {
				logger.error("Sorry, unable to find " + Constants.PROPERTIES_FILE_NAME);
				driver.close();
				driver.quit();
				return;
			}

			prop.load(input);
			logger.debug("Opening URL " + prop.getProperty("url"));
			driver.get(prop.getProperty("url"));
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			driver.manage().window().maximize();

			driver.navigate().refresh();
			selutils.setTimeOutForElements(driver, 2000); // DO NOT CHANGE THIS NUMBER 2000
			// ISSUES :
			// 1. setTimeOuts is not working

			driver.findElement(By.name("email")).clear();
			selutils.setTimeOutForElements(driver, 1000);

			selutils.getElementbyName(driver, "email", prop);

			selutils.getElementbyName(driver, "password", prop);

			WebElement loginBtn = null;
			selutils.getElementbyXpath(driver, Constants.LOGIN_BTN_XPATH, loginBtn, prop);

			WebElement agreeBtn = null;
			selutils.getElementbyXpath(driver, Constants.AGREE_BTN_XPATH, agreeBtn, prop);

			WebElement pullDownManu = null;
			selutils.getElementbyXpath(driver, Constants.ADMIN_MENU_XPATH, pullDownManu, prop);

			WebElement aiserApp = null;
			selutils.getElementbyXpath(driver, Constants.AISERA_APP_XPATH, aiserApp, prop);

			selutils.scrollDown(driver);
			selutils.setTimeOutForElements(driver, 1000);

			String viewDetailXpath =
					  "//div[contains(text(),'"+appName+"')]/ancestor::div[contains(@class,'grid-item-cell entity-card no-fields empty')]//child::span[contains(@class,'flaticon abeckicon icon-detail_view')]"; 
			String channelXpath = "//div[contains(text(), '"+channelName+"')]"; 
			
			WebElement viewDetail = null;
			selutils.getElementbyXpath(driver, viewDetailXpath, viewDetail, prop);

			selutils.scrollDown(driver);
			selutils.setTimeOutForElements(driver, 1000);

			WebElement channel = null;
			selutils.getElementbyXpath(driver, channelXpath, channel, prop);

			WebElement testBtn = null;
			selutils.getElementbyXpath(driver, Constants.TEST_BTN_XPATH, testBtn, prop);

			driver.switchTo().frame(0);

			Thread.sleep(2000);
			WebElement userName = null;
			selutils.getElementbyXpath(driver, Constants.USER_NAME_XPATH, userName, prop);

			WebElement continueBtn = null;
			selutils.getElementbyXpath(driver, Constants.CONTINUE_BTN_XPATH, continueBtn, prop);

			kbs.validateBotGreeting(driver);
			// file read , validate, next line ,validate ,

			kbs.validateFirstChatQuestion(driver);

			kbs.validateSecondChatQuestion(driver);

			kbs.validateYesButtonResponse(driver);
			
			//webchat.startWebchat(driver);
			
			driver.close();
			
			logger.info("\n #####  Conversation UI Test Complete ###### ");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error("Missing Command Line Arguments ",e);
			e.printStackTrace();
			driver.close();
			driver.quit();
			throw e;
		} 
		catch (IOException e) {
			logger.error("Unable to load properties file ",e);
			e.printStackTrace();
			driver.close();
			driver.quit();
			throw e;
			
		} 
		catch (Exception ex) {
			logger.error("Exception caught ",ex);
			ex.printStackTrace();
			driver.close();
			driver.quit();
			throw ex;
		}

	}

	public void validateBotGreeting(WebDriver driver) {

		List<WebElement> botUIGreeting = driver.findElements(By.xpath(Constants.GREETING_XPATH));

		logger.info("\n ##### TEST RESULTS ####\n");
		
		if(!botUIGreeting.isEmpty()) {
			if (Constants.GREETING1.equalsIgnoreCase(botUIGreeting.get(0).getText())) {
				logger.info("Bot first Greeting Validation Passed");
				if (Constants.GREETING2.equalsIgnoreCase(botUIGreeting.get(1).getText()))
					logger.info("Bot second Greeting Validation Passed");
				else
					logger.info("Bot second Greeting Validation Failed");

			} else
				logger.info("Bot first Greeting Validation Failed");
		}
		else
			logger.info(" Could not validate as botUIGreeting size is "+botUIGreeting.size());
		

	}

	
	public void validateFirstChatQuestion(WebDriver driver) {

		String question1 = "help";

		WebElement questionElement = driver.findElement(By.xpath(Constants.TYPE_QUESTION_XPATH));
		selutils.highlightElement(driver, questionElement);
		questionElement.sendKeys(question1);
		questionElement.sendKeys(Keys.ENTER);
		selutils.setTimeOutForElements(driver, 1000);

		String expectedResponse = "Sure, I can help";

		selutils.setTimeOutForElements(driver, 1000);

		WebElement chatReponseElement = driver.findElement(By.xpath(Constants.CHAT_RESPONSE_XPATH));
		selutils.setTimeOutForElements(driver, 1000);
		selutils.highlightElement(driver, chatReponseElement);
		String chatResponse = chatReponseElement.getText();

		logger.info("\n*** Query - 'help' ****");
		if (expectedResponse.equalsIgnoreCase(chatResponse))
			logger.info("Bot response: '" + chatResponse + "' matches expected response: '" + expectedResponse
					+ "'\n Validation Passed");
		else
			logger.info("Bot response:  '" + chatResponse + "' did not match the expected response: '"
					+ expectedResponse + "'\n Validation Failed");

	}

	public void validateSecondChatQuestion(WebDriver driver) {
		String question2 = "conf call";

		WebElement questionElement = driver.findElement(By.xpath(Constants.TYPE_QUESTION_XPATH));
		selutils.highlightElement(driver, questionElement);
		questionElement.sendKeys(question2);
		questionElement.sendKeys(Keys.ENTER);
		selutils.setTimeOutForElements(driver, 1000);

		List<String> expectedResponse = new ArrayList<String>();

		expectedResponse.add("I found a relevant article.");
		expectedResponse.add("I think this article might help!");
		expectedResponse.add(
				"If none of the above reasons apply to you, and you still can't make or receive FaceTime calls, follow these steps:");
		expectedResponse.add("Did this help?");
		expectedResponse.add("Was I helpful?");
		expectedResponse.add("YesNoCreate Ticket");
		expectedResponse.add("YesNo");
		expectedResponse.add("Here is something I found");
		expectedResponse.add("Did this answer your question?");
		expectedResponse.add("YesNoShow More");
		
		selutils.setTimeOutForElements(driver, 1000);

		List<WebElement> chatReponseElements = driver.findElements(By.xpath(Constants.CHAT_RESPONSE_XPATH));

		List<String> chatResponse = new ArrayList<String>();

		for (WebElement e : chatReponseElements) {
			chatResponse.add(e.getText());
		}
		chatResponse.remove(0);

		logger.info("\n*** Query - 'conf call' ****");

		if (expectedResponse != null && chatResponse != null) {
			if (expectedResponse.containsAll(chatResponse))
				logger.info("Bot response for 'conf call' matches expected response, Validation Passed");
			else {
				logger.info("Bot response did not match expected response, Validation Failed ");
				logger.info("Bot response : " + chatResponse);
				logger.info("Expected response : " + expectedResponse);

			}
		}

	}

	public void validateYesButtonResponse(WebDriver driver) {

		WebElement questionElement = driver.findElement(By.xpath(Constants.YES_BTN_XPATH));
		selutils.highlightElement(driver, questionElement);
		questionElement.click();

		selutils.setTimeOutForElements(driver, 1500);

		List<WebElement> chatQuestionElements = driver.findElements(By.xpath(Constants.CHAT_QUESTION_XPATH));

		List<String> chatQuestion = new ArrayList<String>();

		for (WebElement e : chatQuestionElements) {
			chatQuestion.add(e.getText());
		}

		if (chatQuestion != null || !(chatQuestion.isEmpty())) {
			if (chatQuestion.get(2).equalsIgnoreCase("Yes")) {
				logger.info("User Clicked Yes Button ");
				
				List<WebElement> chatReponseElements = driver.findElements(By.xpath(Constants.CHAT_RESPONSE_XPATH));
				List<String> chatResponse = new ArrayList<String>();

				for (WebElement e : chatReponseElements) {
					chatResponse.add(e.getText());
				}
				if (chatResponse.get(5).equalsIgnoreCase("Thank you for your feedback."))
					logger.info(
							"Bot response: " + chatResponse.get(5) + " matches expected response. Validation Passed");

			}
		}

	}

}
