package testcases;

import java.util.ArrayList;
import java.util.List;
//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utility.Constants;

//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.Keys;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Nidhi
 *
 */
public class TestKBSearches {

	static Properties prop = null;
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args)
			throws Exception {

		TestKBSearches kbs = new TestKBSearches();
		prop = new Properties();
		
		String appName = null;
		String channelName = null;
		
		System.setProperty("webdriver.chrome.driver", "/Users/Nidhi/Downloads/WebDriversEXEfiles/chromedriver");
		WebDriver driver = new ChromeDriver();

		// System.setProperty("webdriver.gecko.driver",
		// "/Users/Nidhi/Downloads/WebDriversEXEfiles/geckodriver");
		// WebDriver driver = new FirefoxDriver();

		try {
			
			if (args.length > 0) {
				System.out.println("Env Name: "+args[0]);
				System.out.println("Aisera App:  "+args[1]);
				System.out.println("Webchat Channel: "+args[2]);
				
				Constants.propertiesfileName = args[0] + ".properties";
				appName = args[1].toString(); //IT Helpdesk for dev1
				channelName = args[2].toString();	//Nik webchat 2 for dev1	
				
				System.out.println("Running test on " + args[0] + " environment and using credentials from " + Constants.propertiesfileName);


			}
			else
				System.out.println("Missing Command Line Arguments ");

			if(Constants.propertiesfileName!=null)
			{
				InputStream input = kbs.getClass().getClassLoader().getResourceAsStream(Constants.propertiesfileName);
			

			if (input == null) {
				System.out.println("Sorry, unable to find " + Constants.propertiesfileName);
				return;
			}

			prop.load(input);
			System.out.println("Opening URL " + prop.getProperty("url"));
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();

			driver.navigate().refresh();
			kbs.setTimeOutForElements(driver, 2000); // DO NOT CHANGE THIS NUMBER 2000
			// ISSUES :
			// 1. setTimeOuts is not working
			// 2. ByClassName is not working
			// 3. sometimes element not found.page load issue.

			/*
			 * TODO //1. Give variable names //2. run properties file as args
			 */

			driver.findElement(By.name("email")).clear();
			kbs.setTimeOutForElements(driver, 2000);

			kbs.getElementbyName(driver, "email");

			kbs.getElementbyName(driver, "password");

			// String loginBtnClass = "btn btn-primary btn-login";
			WebElement loginBtn = null;
			// getElementbyClass(driver,loginBtnClass,loginBtn);
			kbs.getElementbyXpath(driver, Constants.loginBtnXPath, loginBtn);

			// String agreeBtnClass = "btn btn-primary ok-button pull-right";
			WebElement agreeBtn = null;
			// getElementbyClass(driver,agreeBtnClass,agreeBtn);
			kbs.getElementbyXpath(driver, Constants.agreeBtnXPath, agreeBtn);

			WebElement pullDownManu = null;
			kbs.getElementbyXpath(driver, Constants.pullDownXpath, pullDownManu);

			WebElement aiserApp = null;
			kbs.getElementbyXpath(driver, Constants.aiserAppXpath, aiserApp);

			kbs.scrollDown(driver);
			kbs.setTimeOutForElements(driver, 1000);

			String viewDetailXpath =
					  "//div[contains(text(),'"+appName+"')]/ancestor::div[contains(@class,'grid-item-cell entity-card no-fields empty')]//child::span[contains(@class,'flaticon abeckicon icon-detail_view')]"; 
			String channelXpath = "//div[contains(text(), '"+channelName+"')]"; 
			
			WebElement viewDetail = null;
			kbs.getElementbyXpath(driver, viewDetailXpath, viewDetail);

			kbs.scrollDown(driver);
			kbs.setTimeOutForElements(driver, 1000);

			WebElement channel = null;
			kbs.getElementbyXpath(driver, channelXpath, channel);

			WebElement testBtn = null;
			// getElementbyClass(driver,testBtnClass,testBtn);
			kbs.getElementbyXpath(driver, Constants.testBtnXpath, testBtn);

			driver.switchTo().frame(0);

			Thread.sleep(2000);
			WebElement userName = null;
			kbs.getElementbyXpath(driver, Constants.userNameXpath, userName);

			// String continueBtnClass = "btn btn-plain continue-btn";
			WebElement continueBtn = null;
			// getElementbyClass(driver,continueBtnClass,continueBtn);
			kbs.getElementbyXpath(driver, Constants.continueBtnXpath, continueBtn);

			kbs.validateBotGreeting(driver);
			// file read , validate, next line ,validate ,

			kbs.validateFirstChatQuestion(driver);

			kbs.validateSecondChatQuestion(driver);

			kbs.validateYesButtonResponse(driver);

			driver.close();
			// kbs.startChat(driver);
			System.out.println("\n #####  Conversation UI Test Complete ###### ");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Missing Command Line Arguments ");
			e.printStackTrace();
			driver.close();
			throw e;
		} 
		catch (IOException e) {
			System.out.println("Unable to load proerpties file ");
			e.printStackTrace();
			driver.close();
			throw e;
			
		} 
		catch (Exception ex) {
			System.out.println("Exception caught ");
			ex.printStackTrace();
			driver.close();
			throw ex;
		}

	}

	public void getElementbyName(WebDriver driver, String name) {

		if (name.equalsIgnoreCase(Constants.email)) {
			WebElement email = driver.findElement(By.name("email"));
			highlightElement(driver, email);
			email.sendKeys(prop.getProperty("email"));
			setTimeOutForElements(driver, 2000);
		} else if (name.equalsIgnoreCase(Constants.password)) {
			WebElement password = driver.findElement(By.name("password"));
			highlightElement(driver, password);
			password.sendKeys(prop.getProperty("password"));
			setTimeOutForElements(driver, 2000);

		}

	}

	public void getElementbyClass(WebDriver driver, String btnClassName, WebElement btnName) {

		btnName = driver.findElement(By.className(btnClassName));
		highlightElement(driver, btnName);
		btnName.click();
		setTimeOutForElements(driver, 2000);

	}

	public void getElementbyXpath(WebDriver driver, String xPath, WebElement element) {

		setTimeOutForElements(driver, 4000);
		element = driver.findElement(By.xpath(xPath));
		highlightElement(driver, element);
		setTimeOutForElements(driver, 2000);

		if (xPath.equals(Constants.emailAddressXpath))
			element.sendKeys(prop.getProperty("chatUser"));
		else
			element.click();

		setTimeOutForElements(driver, 2000);

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

	public void validateBotGreeting(WebDriver driver) {

		List<WebElement> botUIGreeting = driver.findElements(By.xpath(Constants.greetingXpath));

		System.out.println("\n ##### TEST RESULTS ####\n");

		if (Constants.greeting1.equalsIgnoreCase(botUIGreeting.get(0).getText())) {
			System.out.println("Bot first Greeting Validation Passed");
			if (Constants.greeting2.equalsIgnoreCase(botUIGreeting.get(1).getText()))
				System.out.println("Bot second Greeting Validation Passed");
			else
				System.out.println("Bot second Greeting Validation Failed");

		} else
			System.out.println("Bot first Greeting Validation Failed");

	}

	public void startChat(WebDriver driver) throws FileNotFoundException, IOException {

		// read query from file and enter/input as chat question
		try {

			BufferedReader br = new BufferedReader(new FileReader(Constants.queryfilename));

			String line;

			while ((line = br.readLine()) != null) {
				System.out.println(" printing line from query file " + line);
				// for each line send the line as question and press enter
				WebElement questionElement = driver.findElement(By.xpath(Constants.typeQuestionXpath));
				highlightElement(driver, questionElement);
				questionElement.sendKeys("help");
				questionElement.sendKeys(Keys.ENTER);
				setTimeOutForElements(driver, 3000);
			}

			br.close();

		} catch (IOException e) {
			System.out.println("IO exception in inputChatQuestion - query.txt not found");
			e.printStackTrace();
		}
		// read chat response and write to a List
		// getChatOutput();
		// validate chat response compare response list with output list
		// validateChatReponse();

	}

	public void validateFirstChatQuestion(WebDriver driver) {

		String question1 = "help";

		WebElement questionElement = driver.findElement(By.xpath(Constants.typeQuestionXpath));
		highlightElement(driver, questionElement);
		questionElement.sendKeys(question1);
		questionElement.sendKeys(Keys.ENTER);
		setTimeOutForElements(driver, 3000);

		String expectedResponse = "Sure, I can help";

		setTimeOutForElements(driver, 3000);

		WebElement chatReponseElement = driver.findElement(By.xpath(Constants.chatReponseXpath));
		setTimeOutForElements(driver, 2000);
		highlightElement(driver, chatReponseElement);
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

		WebElement questionElement = driver.findElement(By.xpath(Constants.typeQuestionXpath));
		highlightElement(driver, questionElement);
		questionElement.sendKeys(question2);
		questionElement.sendKeys(Keys.ENTER);
		setTimeOutForElements(driver, 3000);

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
		
		setTimeOutForElements(driver, 2000);

		List<WebElement> chatReponseElements = driver.findElements(By.xpath(Constants.chatReponseXpath));

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

		WebElement questionElement = driver.findElement(By.xpath(Constants.yesButtonXpath));
		highlightElement(driver, questionElement);
		questionElement.click();

		setTimeOutForElements(driver, 2000);

		List<WebElement> chatQuestionElements = driver.findElements(By.xpath(Constants.chatQuestionXpath));

		List<String> chatQuestion = new ArrayList<String>();

		for (WebElement e : chatQuestionElements) {
			chatQuestion.add(e.getText());
		}

		/*
		 * System.out.println("chatQuestion size "+chatQuestion.size());
		 * System.out.println("chatQuestion  "+chatQuestion);
		 * 
		 * System.out.println("chatQuestion 2nd  "+chatQuestion.get(2));
		 */

		if (chatQuestion != null || !(chatQuestion.isEmpty())) {
			if (chatQuestion.get(2).equalsIgnoreCase("Yes")) {
				System.out.println("User Clicked Yes Button ");
				List<WebElement> chatReponseElements = driver.findElements(By.xpath(Constants.chatReponseXpath));
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
