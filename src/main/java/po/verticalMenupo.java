package po;

import utils.baseClass;
import utils.utilityClass;

public class verticalMenupo extends baseClass{

	utilityClass event = new utilityClass();
	
	/*************page object for vertical menu is declared ********************/
	public String erate = "mat-nested-tree-node:nth-child(2) > li > button";
	public String contract = "//a[contains(text(),'Rate/Contract')]"; 
	
	/***************************Navigate to Contract page
	 * @throws InterruptedException *******************************/
	public void navigatetoContract() throws InterruptedException {
	
		event.clickfield("cssSelector", erate);
		event.clickfield("xpath", contract);
		
	}
}
