/**
 * 
 */
package qa.automation.demos;

import java.util.List;
//import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.Keys;


/**
 * @author Nidhi
 *
 */
public class basicDemoScriptTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws NoSuchElementException{
		
		System.setProperty("webdriver.chrome.driver", "/Users/Nidhi/Downloads/WebDriversEXEfiles/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		/*System.setProperty("webdriver.gecko.driver", "/Users/Nidhi/Downloads/WebDriversEXEfiles/geckodriver");
		WebDriver driver = new FirefoxDriver();*/

		driver.get("https://login.dev1.aisera.net/");
		driver.manage().window().maximize();
		
		try{
					
			  driver.navigate().refresh(); 
			  setTimeOutForElements(driver,1000);
			  // ISSUE : 
			  //1. setTimeoUts is not working
			  //2. ByClassName is not working
			  
			  //TO DO :
			  //1. Give variable names
			  
			  driver.findElement(By.name("email")).clear();
			  setTimeOutForElements(driver,2000);
			  
			  getElementbyName(driver,"email");
			  
			  getElementbyName(driver,"password");
			  
			  //String loginBtnClass = "btn btn-primary btn-login"; 
			  String loginBtnXPath = "//button[@class='btn btn-primary btn-login']"; 
			  WebElement loginBtn = null; 
			  //getElementbyClass(driver,loginBtnClass,loginBtn);
			  getElementbyXpath(driver,loginBtnXPath,loginBtn);
			  
			  //String agreeBtnClass = "btn btn-primary ok-button pull-right"; 
			  String agreeBtnXPath = "//button[@class='btn btn-primary ok-button pull-right']"; 
			  WebElement agreeBtn = null; 
			  //getElementbyClass(driver,agreeBtnClass,agreeBtn);
			  getElementbyXpath(driver,agreeBtnXPath,agreeBtn);

			
			  String pullDownXpath =
			  "//span[@class='flaticon flaticon-zplex flaticon-chevron-down']"; 
			  WebElement pullDownManu = null; 
			  getElementbyXpath(driver,pullDownXpath,pullDownManu);
			  
			  String aiserAppXpath = "//div[contains(text(), 'Aisera Apps')]"; 
			  WebElement aiserApp = null; 
			  getElementbyXpath(driver,aiserAppXpath,aiserApp);
			  
			  scrollDown(driver); 
			  setTimeOutForElements(driver,1000);
			  
			  String viewDetailXpath =
			  "//div[contains(text(),'IT Helpdesk')]/ancestor::div[contains(@class,'grid-item-cell entity-card no-fields empty')]//child::span[contains(@class,'flaticon abeckicon icon-detail_view')]"
			  ; 
			  WebElement viewDetail = null;
			  getElementbyXpath(driver,viewDetailXpath,viewDetail);
			  
			  scrollDown(driver); 
			  setTimeOutForElements(driver,1000);
			  
			  String channelXpath = "//div[contains(text(), 'Nik webchat 2')]"; 
			  WebElement channel = null; 
			  getElementbyXpath(driver,channelXpath,channel);
			  
			  //String testBtnClass = "btn btn-plain test-channel multi-item-vspace";
			  String testBtnXpath = "//button[@class='btn btn-plain test-channel multi-item-vspace']";
			  WebElement testBtn = null; 
			  //getElementbyClass(driver,testBtnClass,testBtn);
			  getElementbyXpath(driver,testBtnXpath,testBtn);

			  driver.switchTo().frame(0);
			
			  String userNameXpath = "//input[@placeholder='Enter email address']";
			  WebElement userName = null; 
			  getElementbyXpath(driver,userNameXpath,userName);
			 
			  
			  //String continueBtnClass = "btn btn-plain continue-btn"; 
			  String continueBtnXpath = "//button[@class='btn btn-plain continue-btn']";
			  WebElement continueBtn = null; 
			  //getElementbyClass(driver,continueBtnClass,continueBtn);
			  getElementbyXpath(driver,continueBtnXpath,continueBtn);

			  validateBotGreeting(driver);
			  // file read , validate, next line ,validate , 
			  
			  validateFirstChatQuestion(driver);
			  
			  validateSecondChatQuestion(driver);
			  
			  driver.close(); 
			  System.out.println("Conversation UI Test Passed ");
			 
		}
		catch(Exception e) {
			System.out.println("Exception caught ");
			e.printStackTrace();	
			driver.close();
		}
		
	}
	
	public static void getElementbyName(WebDriver driver,String name){
		
		if(name.equalsIgnoreCase("email")) {
			WebElement email = driver.findElement(By.name("email"));
			highlightElement(driver,email);
			email.sendKeys("admin@aisera.com");
			setTimeOutForElements(driver,2000);
		}
		else if(name.equalsIgnoreCase("password")) {
			WebElement password = driver.findElement(By.name("password"));		
			highlightElement(driver,password);
			password.sendKeys("!sTiKLwez8DBPJ6KHeWB");
			setTimeOutForElements(driver,2000);

		}
		
	}
	
	public static void getElementbyClass(WebDriver driver , String btnClassName, WebElement btnName) {
				
		btnName = driver.findElement(By.className(btnClassName));
		highlightElement(driver,btnName);
		btnName.click();
		setTimeOutForElements(driver,2000);
		
	}
	
	public static void getElementbyXpath(WebDriver driver, String xPath , WebElement element) {
		
		element = driver.findElement(By.xpath(xPath));
		highlightElement(driver,element);
		setTimeOutForElements(driver,2000);

		if(xPath.equals("//input[@placeholder='Enter email address']"))
			element.sendKeys("QA_testing@gmail.com");
		else 
			element.click();
		
		setTimeOutForElements(driver,2000);
		
	}
	public static void highlightElement(WebDriver driver, WebElement element) {
		
		String jsStyle = "'3px solid red'";
	    JavascriptExecutor jse = (JavascriptExecutor) driver;
	    jse.executeScript("arguments[0].style.border=" + jsStyle, element);
		
	}
	
	public static void scrollDown(WebDriver driver)
	{
		JavascriptExecutor jsx = (JavascriptExecutor)driver;
		jsx.executeScript("window.scrollBy(0,450)", "");
	}
   
	public static void setTimeOutForElements(WebDriver driver,int timeout) {
		   try{
			   Thread.sleep(timeout);
		   }
		   catch(Exception e) {
			   System.out.println(" In setTimeOutForElements exception");

			   e.printStackTrace();
		   }
		   //driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.MILLISECONDS);
	   }
	
	
	public static void validateBotGreeting(WebDriver driver){
		
		List<WebElement> botUIGreeting = driver.findElements(By.xpath("//div[@class='answer-content']//child::div"));
		
		String greeting1 = "I can answer questions about Tickets, Application provisioning, Group management and other IT related topics.";
		String greeting2 = "I am a conversational AI Bot. How can I help you?";
		
		if(greeting1.equalsIgnoreCase(botUIGreeting.get(0).getText())) {
			System.out.println("Bot first Greeting Validation Passed");
			if(greeting2.equalsIgnoreCase(botUIGreeting.get(1).getText()))
				System.out.println("Bot second Greeting Validation Passed");
			else
				System.out.println("Bot second Greeting Validation Failed");

		}
		else
			System.out.println("Bot first Greeting Validation Failed");

	 }
	public static void validateFirstChatQuestion(WebDriver driver) {
		
		String question1 = "help";
		
		WebElement questionElement = driver.findElement(By.xpath("//input[@placeholder='Type your question here']"));
		highlightElement(driver,questionElement);
		questionElement.sendKeys(question1);
		questionElement.sendKeys(Keys.ENTER);
		setTimeOutForElements(driver,3000);

		String expectedResponse = "Sure, I can help";
		
		setTimeOutForElements(driver,3000);
		
		WebElement chatReponseElement = driver.findElement(By.xpath("//div[@class='answer-content']//p"));
		setTimeOutForElements(driver,2000);
		highlightElement(driver,chatReponseElement);
		String chatReponse = chatReponseElement.getText();
		
		if(expectedResponse.equalsIgnoreCase(chatReponse))
			System.out.println("Bot first response to Query - 'help' Passed");
		else
			System.out.println("Bot first response to Query - 'help' Failed");

	}
	  
	public static void validateSecondChatQuestion(WebDriver driver) {
		String question2 = "conf call";
		
		WebElement questionElement = driver.findElement(By.xpath("//input[@placeholder='Type your question here']"));
		highlightElement(driver,questionElement);
		questionElement.sendKeys(question2);
		questionElement.sendKeys(Keys.ENTER);
		setTimeOutForElements(driver,3000);

		String expectedResponse = "I think this article might help!";
		setTimeOutForElements(driver,3000);
		
		WebElement chatReponseElement = driver.findElement(By.xpath("//div[@class='answer-content']//p"));
		
		setTimeOutForElements(driver,3000);
		
	}
	

}
