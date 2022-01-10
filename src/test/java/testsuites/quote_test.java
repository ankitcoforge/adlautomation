package testsuites;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import po.loginpo;
import po.quotepo;
import po.verticalMenupo;

public class quote_test extends quotepo{


	loginpo lo = new loginpo();
	verticalMenupo vo = new verticalMenupo();
	
	/*************login to the application*********************/
	@BeforeClass
	public void login() {
		
		loadApplication("browser", "urlKey");
		lo.login("Dealer7032823", "4558600");
	}
	
	/********************quote creation test case****************/
	@Test(priority = 1, dataProvider ="test1")
    public void createQuote1(String [] inputArray) throws InterruptedException {
		
		vo.navigatetoContract();
		String text = createQuote(inputArray);
		Assert.assertEquals(text, "Your Quote has been successfully saved!");
	    
	}
	
	/***************logout to the application********************/
	@AfterClass
	public void close() {

		lo.logout();
		driver.close();
		driver = null;
	}

}
