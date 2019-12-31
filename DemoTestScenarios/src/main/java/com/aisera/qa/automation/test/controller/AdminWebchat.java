package com.aisera.qa.automation.test.controller;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import java.io.IOException;

public interface AdminWebchat {
    void setUpTestArguments(String envName, String appName, String channelName) throws Exception;
    void navigateToAdminUI() throws TimeoutException, NoSuchElementException;
    void loginToAdminUI() throws TimeoutException, NoSuchElementException;
    void acceptAgreement() throws TimeoutException, NoSuchElementException;
    void clickPullDownAdminMenu() throws TimeoutException, NoSuchElementException;
    void goToAiseraApp() throws TimeoutException, NoSuchElementException;
    void selectAiseraApp(String appName) throws TimeoutException, NoSuchElementException;
    void gotToChannel(String channelName) throws TimeoutException, NoSuchElementException;
    void launchWebchat() throws TimeoutException, NoSuchElementException;
    void loginToWebchat() throws TimeoutException, NoSuchElementException;
    void validateBotGreeting() throws Exception;
    void testKBSearch() throws Exception;
    void testActions() throws Exception;
    void testServiceCatalog() throws Exception;

}
