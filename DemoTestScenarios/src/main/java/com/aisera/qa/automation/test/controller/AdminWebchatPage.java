package com.aisera.qa.automation.test.controller;

import com.aisera.qa.automation.test.common.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import com.aisera.qa.automation.test.utils.SeleniumUtilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class AdminWebchatPage implements AdminWebchat {

    private final WebDriver driver;
    static SeleniumUtilities selUtils = null;
    static Properties prop = null;

    private static final Logger logger = LogManager.getLogger(AdminWebchatPage.class.getName());

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = Constants.LOGIN_BTN_XPATH)
    private WebElement loginBtn;

    @FindBy(xpath = Constants.AGREE_BTN_XPATH)
    private WebElement agreeBtn;

    @FindBy(xpath = Constants.ADMIN_MENU_XPATH)
    private WebElement adminMenu;

    @FindBy(xpath = Constants.AISERA_APP_XPATH)
    private WebElement aiseraApp;

    @FindBy(xpath = Constants.TEST_BTN_XPATH)
    private WebElement testBtn;

    @FindBy(xpath = Constants.USER_NAME_XPATH)
    private WebElement chatUserEmail;

    @FindBy(xpath = Constants.CONTINUE_BTN_XPATH)
    private WebElement continueBtn;

    public AdminWebchatPage(WebDriver driver) {
        this.driver = driver;
        selUtils = new SeleniumUtilities();
        prop = new Properties();
    }

    public void clickBtn(WebElement elementToBeClicked){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(elementToBeClicked));
        wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class); elementToBeClicked.click();
    }
    @Override
    public void setUpTestArguments(String envName, String appName, String channelName) throws Exception {


            if (envName != null)
                envName = envName.replace("%20", " ");

            if (appName != null)
                appName = appName.replace("%20", " ");

            if (channelName != null)
                channelName = channelName.replace("%20", " ");

            logger.debug("Env Name: " + envName+"  Aisera App:  " + appName+"  Webchat Channel: " + channelName);

            Constants.PROPERTIES_FILE_NAME = envName + ".properties";

            logger.debug("Running test using " + Constants.PROPERTIES_FILE_NAME + " and using credentials from "
                    + Constants.PROPERTIES_FILE_NAME);

            if (Constants.PROPERTIES_FILE_NAME != null) {
                InputStream input = this.getClass().getClassLoader().getResourceAsStream(Constants.PROPERTIES_FILE_NAME);


                if (input == null) {
                    logger.error("Sorry, unable to find " + Constants.PROPERTIES_FILE_NAME);
                    return;
                }

                prop.load(input);
                logger.debug("Opening URL " + prop.getProperty("url"));
            }

    }

    @Override
    public void navigateToAdminUI() throws TimeoutException, NoSuchElementException{

        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        driver.navigate().refresh();
        //selUtils.setTimeOutForElements(driver, 2000);
        driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void loginToAdminUI() throws TimeoutException, NoSuchElementException{

        email.clear();
        driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

        selUtils.highlightElement(driver, email);
        email.sendKeys(prop.getProperty("email"));

        selUtils.highlightElement(driver, password);
        password.sendKeys(prop.getProperty("password"));

        selUtils.highlightElement(driver, loginBtn);
        clickBtn(loginBtn);

    }

    @Override
    public void acceptAgreement() throws TimeoutException, NoSuchElementException{
         driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
         selUtils.highlightElement(driver,agreeBtn);
         clickBtn(agreeBtn);
    }

    @Override
    public void clickPullDownAdminMenu() throws TimeoutException, NoSuchElementException{

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        selUtils.highlightElement(driver,adminMenu);
        clickBtn(adminMenu);

    }

    @Override
    public void goToAiseraApp()throws TimeoutException, NoSuchElementException{
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        selUtils.highlightElement(driver,aiseraApp);
        clickBtn(aiseraApp);
    }

    @Override
    public void selectAiseraApp(String appName)throws TimeoutException, NoSuchElementException{
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        String viewDetailXpath =
                "//div[contains(text(),'"+appName+"')]/ancestor::div[contains(@class,'grid-item-cell entity-card no-fields empty')]//child::span[contains(@class,'flaticon abeckicon icon-detail_view')]";

        WebElement viewDetail = null;
        selUtils.getElementbyXpath(driver, viewDetailXpath, viewDetail, prop);
        selUtils.scrollDown(driver);
        selUtils.setTimeOutForElements(driver, 1000);
    }

    @Override
    public void gotToChannel(String channelName)throws TimeoutException, NoSuchElementException{
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        String channelXpath = "//div[contains(text(), '"+channelName+"')]";
        WebElement channel = null;
        selUtils.getElementbyXpath(driver, channelXpath, channel, prop);
    }

    @Override
    public void launchWebchat()throws TimeoutException, NoSuchElementException{
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        selUtils.highlightElement(driver,testBtn);
        clickBtn(testBtn);
        driver.switchTo().frame(0);
    }

    @Override
    public void loginToWebchat()throws TimeoutException, NoSuchElementException{
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        selUtils.highlightElement(driver,chatUserEmail);
        chatUserEmail.sendKeys(prop.getProperty("chatUser"));

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        selUtils.highlightElement(driver,continueBtn);
        clickBtn(continueBtn);

    }

    @Override
    public void validateBotGreeting()throws Exception{
        // add validatation messages based on the env
    }

    @Override
    public void testKBSearch()throws Exception{
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        //add parameterized test code
    }

    @Override
    public void testActions()throws Exception{
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        //add parameterized test code
    }

    @Override
    public void testServiceCatalog()throws Exception{
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        //add parameterized test code
    }

}
