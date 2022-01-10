package po;

import utils.baseClass;
import utils.utilityClass;

public class loginpo extends baseClass{
	
	
	utilityClass event = new utilityClass();
	
	/*************page object for login page is declared ********************/
	public String header = "//h3";
	public String arrowButton = "header > div > div > button > mat-icon";
	public String logout = "//button[contains(text(),'Logout')]";
	public String userpass = "//input";
	public String submit ="//button";
	
	/***********************Login to ADL page 
	 * @throws InterruptedException *****************************************/
	public String login(String username, String password) {
		
		event.inputfield("xpath", userpass, username, 0);
		event.inputfield("xpath", userpass, password, 1);
		event.clickfield("xpath", submit, 1);
		String header1 = event.text("xpath", header, 0);
		return header1;
	}

	/************************Logout of ADL page***************************************/
	public void logout(){
		event.clickfield("cssSelector", arrowButton);
		event.clickfield("xpath", logout);
	}
	
}