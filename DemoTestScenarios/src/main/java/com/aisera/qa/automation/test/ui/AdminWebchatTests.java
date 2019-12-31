package com.aisera.qa.automation.test.ui;

import com.aisera.qa.automation.test.controller.AdminWebchatPage;
import com.aisera.qa.automation.test.controller.AdminWebchat;
import io.github.bonigarcia.seljup.SeleniumExtension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


@ExtendWith(SeleniumExtension.class)
@DisplayName("Admin Page Test Flow")

public class AdminWebchatTests {

    private AdminWebchat adminPageAPI;
    static String envName =null;
    static String appName = null;
    static String channelName = null;
    private static final Logger logger = LogManager.getLogger(AdminWebchatTests.class.getName());


    @BeforeEach
    void beforeEach(ChromeDriver driver)  {
        this.adminPageAPI = PageFactory.initElements(driver, AdminWebchatPage.class);


        try{
            envName = System.getProperties().getProperty("EnvName"); // dev1
            appName = System.getProperties().getProperty("AppName"); // IT Helpdesk for dev1
            channelName = System.getProperties().getProperty("ChannelName"); // Nik webchat 2 for dev1

            logger.debug("Env Name: " + envName+" Aisera App:  " + appName+" Webchat Channel: " + channelName);

            //se up arguments and read properties file
            this.adminPageAPI.setUpTestArguments(envName,appName,channelName);
            //once argument set go to Admin Url
            this.adminPageAPI.navigateToAdminUI();
        }catch (IOException e) {
            logger.error("Unable to load properties file ", e);
            e.printStackTrace();
        } catch (Exception ex) {
            logger.error("Exception caught in beforeEach ", ex);
            ex.printStackTrace();
        }
        finally{
            driver.close();
            driver.quit();
        }

    }

    @Test
    @DisplayName("Login to Admin UI")
    void loginAdminUI()  {

        try {
            adminPageAPI.loginToAdminUI();
        }
        catch (TimeoutException e) {
            logger.error(" Admin Login Page did not load within 4000 milliseconds!");
            e.printStackTrace();

        } catch(NoSuchElementException e){
            logger.error("Unable to find element : ", e);
            e.printStackTrace();
        }

    }

    @Test
    @DisplayName("Accept Agreement")
    void acceptAgree(){
        try {
            adminPageAPI.acceptAgreement();
        }
        catch (TimeoutException e) {
            logger.error(" Accept Agreement popup did not load within 2000 milliseconds!");
        }
        catch(NoSuchElementException e){
            logger.error("Unable to find element : ", e);
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Click Pull Down Menu on Admin UI")
    void clickAdminMenu(){

        try{
            adminPageAPI.clickPullDownAdminMenu();
        }
        catch (TimeoutException e) {
            logger.error(" Admin menu did not load within 3000 milliseconds!");
        }
        catch(NoSuchElementException e){
            logger.error("Unable to find element : ", e);
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Click and Navigate to Aisera App on Admin UI")
    void goToApp(){
        try{
            adminPageAPI.goToAiseraApp();
        }
        catch (TimeoutException e) {
            logger.error(" Aisera App did not load within 3000 milliseconds!");
        }
        catch(NoSuchElementException e){
            logger.error("Unable to find element : ", e);
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Select Aisera App on Admin UI")
    void selectApp(){
        try{
            adminPageAPI.selectAiseraApp(appName);
        }
        catch (TimeoutException e) {
            logger.error(" Aisera App details page did not load within 3000 milliseconds!");
        }
        catch(NoSuchElementException e){
            logger.error("Unable to find element : ", e);
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Go to Web Chat Channel on Admin UI")
    void selectChannel(){

        try{
            adminPageAPI.gotToChannel(channelName);
        }
        catch (TimeoutException e) {
            logger.error(" Channel did not load within 3000 milliseconds!");
        }
    }

    @Test
    @DisplayName("Launch the Web chat")
    void launchWebChat(){
        try{
            adminPageAPI.launchWebchat();
        }
        catch (TimeoutException e) {
            logger.error(" WebChat frame did not load within 3000 milliseconds!");
        }
    }

    @Test
    @DisplayName("Login to Web chat")
    void loginToWebChat(){

        try{
            adminPageAPI.loginToWebchat();
        }
        catch (TimeoutException e) {
            logger.error(" Webchat login did not work within 3000 milliseconds!");
        }
    }

    @Test
    @DisplayName((" Validate Greeting "))
    void validateGreeting(){
        try{
            adminPageAPI.validateBotGreeting();
        }
        catch (Exception e) {
            logger.error(" Exception in validateGreeting !");
        }

    }

    @Test
    @DisplayName("Test KB on web chat")
    void testKBsearch(){

        try{
            adminPageAPI.testKBSearch();
        }
        catch (Exception e) {
            logger.error(" Exception in testKBSearch !");
        }
    }

    @Test
    @DisplayName("Test Actions on web chat")
    void testTicketActions(){

        try{
            adminPageAPI.testActions();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test Service Catalog on web chat")
    void testServicecatalog(){

        try{
            adminPageAPI.testServiceCatalog();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void afterAll(){

    }
}
