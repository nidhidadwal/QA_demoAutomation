package com.aisera.qa.automation.test.common;

public class Constants {
	
	public static  String PROPERTIES_FILE_NAME = null;
	public static  String QUERY_FILE_NAME = "query.txt";
	
	//ADMIN UI LOGIN constants
	public static final String USER_EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String EMAIL_ADDRESS_XPATH = "//input[@placeholder='Enter email address']";
	public static final String LOGIN_BTN_XPATH = "//button[@class='btn btn-primary btn-login']"; 

	//ADMIN Home page constants
	public static final String AGREE_BTN_XPATH = "//button[@class='btn btn-primary ok-button pull-right']"; 
	public static final String ADMIN_MENU_XPATH = "//span[@class='flaticon flaticon-zplex flaticon-chevron-down']"; 
	public static final String AISERA_APP_XPATH = "//div[contains(text(), 'Aisera Apps')]"; 
	
	//ADMIN Aisera Apps page constants
	
	//ADMIN Channel details page constants
	public static final String TEST_BTN_XPATH = "//button[contains(text(),'Test')]";

	//Webchat frame page constants
	public static final String USER_NAME_XPATH = "//input[@placeholder='Enter email address']";
	public static final String CONTINUE_BTN_XPATH = "//button[@class='btn btn-plain continue-btn']";
	public static final String GREETING_XPATH = "//div[@class='answer-content']//child::div";
	public static final String GREETING1 = "I can answer questions about Tickets, Application provisioning, Group management and other IT related topics.";
	public static final String GREETING2 = "I am a conversational AI Bot. How can I help you?";
		
	//Webchat / Query / Actions constants
	public static final String TYPE_QUESTION_XPATH = "//input[@placeholder='Type your question here']";
	public static final String CHAT_QUESTION_XPATH = "//div[@class='chat-question force-wrap']";
	public static final String CHAT_RESPONSE_XPATH = "//div[@class='answer-content']//p";
	
	public static final String YES_BTN_XPATH = "//button[contains(text(),'Yes')]";
	public static final String NO_BTN_XPATH = "//button[contains(text(),'No')]";
	public static final String CREATE_TKT_XPATH = "//button[contains(text(),'Create Ticket')]";

}
