package com.aisera.qa.automation.test.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class StartValidateWebchat {
	
		
		public void startWebchat(WebDriver driver) 
				throws FileNotFoundException, IOException {

			//1. read each line from file and enter/input as chat question / query
			//2. receive the output for this line / query
			//3. validate this query
			try {

				BufferedReader br = new BufferedReader(new FileReader(Constants.QUERY_FILE_NAME));

				String line;

				while ((line = br.readLine()) != null) {
					
					System.out.println(" printing line from query file " + line);
					
					// for each line send the line as question and press enter
					WebElement questionElement = driver.findElement(By.xpath(Constants.TYPE_QUESTION_XPATH));
					//highlightElement(driver, questionElement);
					
					System.out.println(" sending " + line+" as a query ");

					questionElement.sendKeys(line);
					questionElement.sendKeys(Keys.ENTER);
					//setTimeOutForElements(driver, 3000);
					
					
					//receive the output for this line / query
					List<WebElement> chatReponseElements = driver.findElements(By.xpath(Constants.CHAT_RESPONSE_XPATH));
					List<String> chatResponse = new ArrayList<String>();

					if(!chatReponseElements.isEmpty()) {
						for (WebElement e : chatReponseElements) {
							chatResponse.add(e.getText());
						}
					}
					
					// read output as a list
					
					
					System.out.println("Received following response for this query "+chatResponse);
				}

				br.close();

			} catch (IOException e) {
				System.out.println("IO exception in inputChatQuestion - query.txt not found");
				e.printStackTrace();
			}
		}

		
	

}
