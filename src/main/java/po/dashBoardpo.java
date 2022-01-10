package po;

import utils.baseClass;
import utils.utilityClass;

public class dashBoardpo extends baseClass{
	
	utilityClass event = new utilityClass();
	
	/*************page object for dashboard page is declared ********************/
	String companyName = "div:nth-child(4) > p > b:nth-child(1)";
	String name = "div:nth-child(4) > p > b:nth-child(3)";
	
	public String getCompanyName () {
		
	   return(event.text("cssSelector", companyName));
			
	}
	
	public String getName () {
		
		   return(event.text("cssSelector", name));
				
	}
	

}

