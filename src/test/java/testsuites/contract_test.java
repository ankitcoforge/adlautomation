/**
 * 
 */
package testsuites;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import po.contractpo;
import po.loginpo;
import po.verticalMenupo;

/**
 * @author ankit.srivastava
 *
 */
public class contract_test extends contractpo{

	loginpo lo = new loginpo();
	verticalMenupo vo = new verticalMenupo();
	
	/*************login to the application*********************/
	@BeforeClass
	public void login() {
		
		loadApplication("browser", "urlKey");
	    lo.login("Dealer7032823", "4558600");
	}
	
	/*****************Contract creation test case***************/
	@Test(priority = 1, dataProvider ="test1")
    public void createContract1(String[] inputArray) throws InterruptedException {
		
		vo.navigatetoContract();
		String text = createContract(inputArray);
		Assert.assertEquals(text, "You have successfully generated a contract!");
	    
	}
	
	/***************logout to the application********************/
	@AfterClass
	public void close() {

		lo.logout();
		driver.close();
		driver = null;
	}


}
