package testsuites;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import po.loginpo;

public class login_test extends loginpo{
	
	/**********Opening the ADL application******************/
	@Test(priority =1)
	public void welcomePage() {
		
		loadApplication("browser", "urlKey");
		Assert.assertEquals(getTitle(), "AUL Corp.");	
	
	}
	
	/*************login to the application*********************/
	@Test (priority = 2)
	@Parameters({"username", "password"})
    public void loggedIn(String user, String pass) throws InterruptedException {
		
		String header = login(user, pass);
		Assert.assertEquals(header, "Dashboard");
		
	}
	
	/***************logout to the application********************/
	@Test(priority = 3)
    public void logout1() {
		
		logout();
	}

	/**********Closing the browser and selenium instance******************/
	@AfterClass
	public void close() {

		driver.close();
		driver = null;
	}


}
