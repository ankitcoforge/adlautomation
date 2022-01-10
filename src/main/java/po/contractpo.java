package po;

import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.baseClass;
import utils.utilityClass;

public class contractpo extends baseClass{
	
    utilityClass event = new utilityClass();
    
    /*************page object for contract page is declared ********************/
	
	public String successMessage = "div.notification__container__message > span";
	public String textbox = "adl-text-input > div > div.text-field__input.secure > input";
	public String getProducts = "//span[contains(text(),'Get Products')]";
	public String selectProgram = "//mat-checkbox/label/div";
	public String table = "//td";
	public String contract = "div.text-field__input.secure.text-field__input--prefix > input";
	public String checkbox = "//mat-checkbox";
	public String button = "//button";
	public String generateContract = "//span[contains(text(),'Proceed to generate contract(s)')]";
	public String programfirstname ="//span[contains(text(),'";
	public String programlastname = "')]/preceding-sibling::div";
	public String total = "adl-rate-contract-footer > footer > div.total__price.col-4_sm-6 ";
	

	
	/************************Create contract 
	 * @throws InterruptedException ****************************************/
	public String createContract(String[] inputArray) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(event.element("xpath", getProducts)));
		HashMap<String, String> searchData1 = new HashMap<String, String>();
		searchData1 = contractData(inputArray);
		event.inputfield("cssSelector", textbox, searchData1.get("Firstname"), 0);
		event.inputfield("cssSelector", textbox, searchData1.get("Lastname"), 1);
		event.inputfield("cssSelector", textbox, searchData1.get("Mileage"), 5);
		event.inputfield("cssSelector", textbox, searchData1.get("Vin"), 6);
		event.clickfield("xpath", getProducts);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(event.element("xpath", selectProgram, 2)));
		event.clickfield("xpath", selectProgram, 2);
		wait.until(ExpectedConditions.visibilityOf(event.element("xpath", table, 1)));
		event.clickfield("xpath", table, 1);
		event.inputfield("cssSelector", contract, "10000", 0);
		event.inputfield("cssSelector", textbox, "20130", 14);
		event.inputfield("cssSelector", textbox, "Address", 13);
		event.clearfield("cssSelector", textbox , 17 );
		event.inputfield("cssSelector", textbox, "1234567890", 17);
		boolean x = calculatePrice();
		Assert.assertEquals(x, true, "Total Price Mismatch");
		event.clickfield("xpath", generateContract);
		Thread.sleep(4000);
		event.clickfield("xpath", checkbox, 7);
		event.clickfield("xpath", checkbox, 8);
		event.clickfield("xpath", button, 16);
		Thread.sleep(7000);
		return (event.text("cssSelector", successMessage));	
		
	}
	
	public boolean calculatePrice() {
		String covPrice = event.text("xpath", table, 1);
		String price = covPrice.substring(1);
		String totalPrice = event.text("cssSelector",total);
		String[] calPrice = totalPrice.split("\\W+");
		if(price.compareTo(calPrice[1]) == 0){
			 return false;
		} else 
			return false;
		
	}
	
}
