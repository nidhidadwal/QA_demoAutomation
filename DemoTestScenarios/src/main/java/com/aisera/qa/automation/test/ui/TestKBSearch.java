package com.aisera.qa.automation.test.ui;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestKBSearch {

    @Test
    //start kb search test
    public void startKBSearchTest(){

        System.setProperty("webdriver.chrome.driver", "/Users/Nidhi/Downloads/WebDriversEXEfiles/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://login.dev1.aisera.net/");
        driver.close();
        driver.quit();
    }

}
