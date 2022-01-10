/**
 * 
 */
package utils;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

/**
 * @author ankit.srivastava
 *
 */
public class baseClass{
	
	//private static final String 'test1' = null;
	public static WebDriver driver;
	public Properties prop;
	
	
/****************Method to read properties file ***************************************************/
	public void init() {
		// init the prop file
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//projectconfig.properties");
				prop.load(fs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
/********************Method to get current web driver*******************************************************/
	public static WebDriver getDriver() {
		if(driver == null) {
			driver = createDriver("Chrome");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
	        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			driver.manage().window().maximize();	
		}
		return driver;
		
	}
	

	/********************Method to create web driver*******************************************************/	
	private static WebDriver createDriver(String browser) {
		if (browser.equals("Mozilla")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("Chrome")) {
		    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "//chromedriver.exe" );
			driver = new ChromeDriver();
		} else if (browser.equals("IE")) {
//			System.setProperty("webdriver.chrome.driver", prop.getProperty("iedriver_exe"));
//			driver = new InternetExplorerDriver();
		}
		return driver;
	}
	
	/********** Open the test environment ***********/
	public void navigate(String urlKey) {

		driver.get(prop.getProperty(urlKey));
	}

	/****** Title of the webpage ******/
	public String getTitle() {

		return (driver.getTitle());
    }


	/*********** Load application **********/
	public void loadApplication(String browser, String urlKey) {

		init();
		getDriver();
		navigate(urlKey);
	}
	

	/*********** Verify header of the webpage ***********/
	public String verifyheader(String Header) {
		String header = driver.findElement(By.tagName(prop.getProperty(Header))).getText();
		return (header);
	}
	
	
	
    /************************Read XLSX data from excel file*************************/
	@SuppressWarnings("resource")
	@DataProvider(name = "test1")
	public  Object[][] getExcelData() {
		//// read data from data provider excel and append in string array
		Object[][] excelData = null;
		try {
			//// read file
			FileInputStream fs = new FileInputStream("C:\\AUL\\Workspace\\adlautomation\\Testing.xlsx");
			//// get workbook based on sheet
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			//// get excel sheet
			Sheet sh = wb.getSheet("Test");
			//// no of rows
			int totalNoOfRows = sh.getLastRowNum() - sh.getFirstRowNum();
			excelData = new Object[totalNoOfRows][1];
			//// no of columns
			int totalNoOfColumn = sh.getRow(0).getLastCellNum();
			//// iterate through rows and columns
			for (int i = 1; i <= totalNoOfRows; i++) {
				String[] data = new String[totalNoOfColumn];
				Row row = sh.getRow(i);
				for (int j = 0; j < totalNoOfColumn; j++) {
					String abc = "";
					try {
						CellType cellType = row.getCell(j).getCellType();
						//// Switch case to convert excel data to excel
						switch (cellType.toString().toLowerCase()) {
						case "string":
							abc = row.getCell(j).getStringCellValue();
							break;
						case "blank":
							abc = row.getCell(j).getStringCellValue();
							break;
						case "numeric":
							abc = Double.toString(row.getCell(j).getNumericCellValue());
							break;
						default:
							abc = row.getCell(j).getStringCellValue();
						}
					} catch (Exception e) {
						abc = "";
					}
					//// appened data in string array
					data[j] = abc;
				}
				excelData[i - 1][0] = data;
			}
		} catch (Exception e) {
			//// do nothing

		}
	
		return excelData;
	}

	/************************Contract data read from XLSX to Hashmap*************************/
	public HashMap<String, String> contractData(String[] inputArray) {
		HashMap<String, String> searchData = new HashMap<String, String>();
		for (int i = 0; i < inputArray.length; i++) {
			//// Switch Case to Transform Data
			switch (i) {
			case 0:
				searchData.put("Firstname", inputArray[i]);
				break;
			case 1:
				searchData.put("Lastname", inputArray[i]);
				break;
			case 2:
				searchData.put("Vin", inputArray[i]);
				break;
			case 3:
				searchData.put("Mileage", inputArray[i]);
				break;
			case 4:
				searchData.put("Zipcode", inputArray[i]);
				break;
			case 5:
				searchData.put("Address", inputArray[i]);
				break;
			default:
				searchData.put("NoData", inputArray[i]);
				break;
			}
		}
		return searchData;
	}
}

