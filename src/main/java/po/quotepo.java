package po;

import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.baseClass;
import utils.utilityClass;

public class quotepo extends baseClass{
	
	utilityClass event = new utilityClass();
	contractpo co = new contractpo();
	
	/*************page object for Save quote page is declared ********************/
	String savequote = "//span[contains(text(),'Save Quote')]";
	
	/************************Create contract 
	 * @throws InterruptedException ****************************************/
	public String createQuote(String [] inputArray) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(event.element("xpath", co.getProducts)));
		HashMap<String, String> searchData1 = new HashMap<String, String>();
		searchData1 = contractData(inputArray);
		event.inputfield("cssSelector", co.textbox, searchData1.get("Firstname"), 0);
		event.inputfield("cssSelector", co.textbox, searchData1.get("Lastname") , 1);
		event.inputfield("cssSelector", co.textbox, searchData1.get("Mileage"), 5);
		event.inputfield("cssSelector", co.textbox, searchData1.get("Vin"), 6);
		event.clickfield("xpath", co.getProducts);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(event.element("xpath", co.selectProgram, 2)));
		event.clickfield("xpath", co.selectProgram, 2);
		wait.until(ExpectedConditions.visibilityOf(event.element("xpath", co.table, 1)));
		event.clickfield("xpath", co.table, 1);
		event.inputfield("cssSelector", co.contract, "10000", 0);
		event.inputfield("cssSelector", co.textbox, "20301", 14);
		event.inputfield("cssSelector", co.textbox, "Address", 13);
		event.clearfield("cssSelector", co.textbox , 17 );
		event.inputfield("cssSelector", co.textbox, "1234567890", 17);
		event.clickfield("xpath", savequote);
		Thread.sleep(4000);
		return (event.text("cssSelector", co.successMessage));
	}

}
