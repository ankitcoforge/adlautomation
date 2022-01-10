package testsuites;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import po.dashBoardpo;
import po.loginpo;

public class dashboard_test extends dashBoardpo{
	
	loginpo lo = new loginpo();

	/*************login to the application*********************/
	@BeforeClass
	public void login() {
		
		loadApplication("browser", "urlKey");
		lo.login("Dealer7032823", "4558600");
	}
	
	
	/**************Dashboard related test cases******************/
	@Test
	public void dashboard() {
		
		
		Assert.assertEquals(getCompanyName(), "Dealer Elite Writer, LLC");
        Assert.assertEquals(getName(), "Don JTOT");
	}
	
	/***************logout to the application********************/
	@AfterClass
	public void close() {

		lo.logout();
		driver.close();
		driver = null;
	}

}
