package com.aisera.qa.automation.test.ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aisera.qa.automation.test.utils.SeleniumUtilities;
import com.aisera.qa.automation.test.common.Constants;

public class TestSFApp {

	static Properties prop = null;
	static SeleniumUtilities selutils = null;
	
	public static void main(String[] args) throws Exception {
		TestSFApp sf = new TestSFApp();
		selutils = new SeleniumUtilities();
		prop = new Properties();

		String appName = null;
		String channelName = null;

		System.setProperty("webdriver.chrome.driver", "/Users/Nidhi/Downloads/WebDriversEXEfiles/chromedriver");
		WebDriver driver = new ChromeDriver();

		try {

			if (args.length > 0) {
				System.out.println("Env Name: " + args[0]);
				System.out.println("Aisera App:  " + args[1]);
				System.out.println("Webchat Channel: " + args[2]);

				Constants.PROPERTIES_FILE_NAME = args[0] + ".properties";
				appName = args[1].toString(); // IT Helpdesk for dev1
				channelName = args[2].toString(); // Nik webchat 2 for dev1

				System.out.println("Running test on " + args[0] + " environment and using credentials from "
						+ Constants.PROPERTIES_FILE_NAME);

			} else
				System.out.println("Missing Command Line Arguments ");

			if (Constants.PROPERTIES_FILE_NAME != null) {
				InputStream input = sf.getClass().getClassLoader().getResourceAsStream(Constants.PROPERTIES_FILE_NAME);

				if (input == null) {
					System.out.println("Sorry, unable to find " + Constants.PROPERTIES_FILE_NAME);
					return;
				}

				prop.load(input);
				System.out.println("Opening URL " + prop.getProperty("url"));
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

				String viewDetailXpath = "//div[contains(text(),'" + appName
						+ "')]/ancestor::div[contains(@class,'grid-item-cell entity-card no-fields empty')]//child::span[contains(@class,'flaticon abeckicon icon-detail_view')]";
				String channelXpath = "//div[contains(text(), '" + channelName + "')]";

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

				sf.validateBotGreeting(driver);
				// file read , validate, next line ,validate ,

				sf.validateFirstChatQuestion(driver);

				sf.validateSecondChatQuestion(driver);

				sf.validateYesButtonResponse(driver);

				// kbs.startChat(driver);

				driver.close();

				System.out.println("\n #####  Conversation UI Test Complete ###### ");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Missing Command Line Arguments ");
			e.printStackTrace();
			driver.close();
			throw e;
		} catch (IOException e) {
			System.out.println("Unable to load proerpties file ");
			e.printStackTrace();
			driver.close();
			throw e;

		} catch (Exception ex) {
			System.out.println("Exception caught ");
			ex.printStackTrace();
			driver.close();
			throw ex;
		}
	}

	

	public void validateBotGreeting(WebDriver driver) {

		List<WebElement> botUIGreeting = driver.findElements(By.xpath(Constants.GREETING_XPATH));

		System.out.println("\n ##### TEST RESULTS ####\n");
		if (!botUIGreeting.isEmpty()) {
			if (Constants.GREETING1.equalsIgnoreCase(botUIGreeting.get(0).getText())) {
				System.out.println("Bot first Greeting Validation Passed");
				if (Constants.GREETING2.equalsIgnoreCase(botUIGreeting.get(1).getText()))
					System.out.println("Bot second Greeting Validation Passed");
				else
					System.out.println("Bot second Greeting Validation Failed");

			} else
				System.out.println("Bot first Greeting Validation Failed");
		} else
			System.out.println(" Could not validate as botUIGreeting size is " + botUIGreeting.size());

	}

	public void startChat(WebDriver driver) throws FileNotFoundException, IOException {

		// 1. read each line from file and enter/input as chat question / query
		// 2. receive the output for this line / query
		// 3. validate this query
		try {

			BufferedReader br = new BufferedReader(new FileReader(Constants.QUERY_FILE_NAME));

			String line;

			while ((line = br.readLine()) != null) {

				System.out.println(" printing line from query file " + line);

				// for each line send the line as question and press enter
				WebElement questionElement = driver.findElement(By.xpath(Constants.TYPE_QUESTION_XPATH));
				selutils.highlightElement(driver, questionElement);

				System.out.println(" sending " + line + " as a query ");

				questionElement.sendKeys(line);
				questionElement.sendKeys(Keys.ENTER);
				selutils.setTimeOutForElements(driver, 3000);

				// receive the output for this line / query
				List<WebElement> chatReponseElements = driver.findElements(By.xpath(Constants.CHAT_RESPONSE_XPATH));
				List<String> chatResponse = new ArrayList<String>();

				if (!chatReponseElements.isEmpty()) {
					for (WebElement e : chatReponseElements) {
						chatResponse.add(e.getText());
					}
				}

				// read output as a list

				System.out.println("Received following response for this query " + chatResponse);
			}

			br.close();

		} catch (IOException e) {
			System.out.println("IO exception in inputChatQuestion - query.txt not found");
			e.printStackTrace();
		}
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

		System.out.println("\n*** Query - 'help' ****");
		if (expectedResponse.equalsIgnoreCase(chatResponse))
			System.out.println("Bot response: '" + chatResponse + "' matches expected response: '" + expectedResponse
					+ "'\n Validation Passed");
		else
			System.out.println("Bot response:  '" + chatResponse + "' did not match the expected response: '"
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

		System.out.println("\n*** Query - 'conf call' ****");

		if (expectedResponse != null && chatResponse != null) {
			if (expectedResponse.containsAll(chatResponse))
				System.out.println("Bot response for 'conf call' matches expected response, Validation Passed");
			else {
				System.out.println("Bot response did not match expected response, Validation Failed ");
				System.out.println("Bot response : " + chatResponse);
				System.out.println("Expected response : " + expectedResponse);

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
				System.out.println("User Clicked Yes Button ");
				List<WebElement> chatReponseElements = driver.findElements(By.xpath(Constants.CHAT_RESPONSE_XPATH));
				List<String> chatResponse = new ArrayList<String>();

				for (WebElement e : chatReponseElements) {
					chatResponse.add(e.getText());
				}
				if (chatResponse.get(5).equalsIgnoreCase("Thank you for your feedback."))
					System.out.println(
							"Bot response: " + chatResponse.get(5) + " matches expected response. Validation Passed");

			}
		}

	}

}