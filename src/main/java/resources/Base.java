package resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import jxl.Cell;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Base
{
	public static WebDriver driver;
	public static Properties prop;
	private final String propertyFilePath = "properties/data.properties";

	public Base(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			prop = new Properties();
			try {
				prop.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		} 
	}
	public WebDriver initializeDriver() throws IOException
	{
		prop = new Properties();
		//FileInputStream fis = new FileInputStream("/Users/rahul/Documents/workspace_sample/E2EProject/src/main/java/resources/data.properties");
		FileInputStream fis = new FileInputStream(propertyFilePath);
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);

		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./geckodriver_mac_0.23.0");
			driver = new FirefoxDriver();
		}

		else if (browserName.equals("safari"))
		{
			driver = new SafariDriver();
		} 
		try{
			driver.manage().window().maximize();
		}catch(Exception e)
		{
			driver.manage().window().fullscreen();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public void getScreenshot(String result) throws Exception
	{
		String dateName = new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss").format(new Date());
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File src = scrShot.getScreenshotAs(OutputType.FILE);
		File dest = new File("/Users/rahul/Documents/workspace_sample/E2EProject/failure_screenshots/"+ result +dateName+".png");
		FileUtils.copyFile(src, dest);
	}


	public static Cell[] readExcel(String sheetName, String uniqueValue) throws BiffException, IOException,Exception
	{
		Workbook wrk1;
		Sheet sheet1;
		//Cell colRow;

		String testDataForDefaultReadExcel = prop.getProperty("testDataForDefaultReadExcel");
		wrk1 = Workbook.getWorkbook(new File(testDataForDefaultReadExcel)); // Connecting to excel workbook.
		sheet1 = wrk1.getSheet(sheetName); // Connecting to the sheet

		LabelCell cell=sheet1.findLabelCell(uniqueValue);
		int row=cell.getRow();
		Cell[] record = sheet1.getRow(row);
		return record;
	}

	//Read from Specified Excel
	public static Cell[] readExcel(String filePath, String sheetName, String uniqueValue) throws BiffException, IOException, Exception
	{
		Workbook wrk1;
		Sheet sheet1;

		wrk1 = Workbook.getWorkbook(new File(filePath)); // Connecting to excel workbook.
		sheet1 = wrk1.getSheet(sheetName); // Connecting to the sheet

		LabelCell cell=sheet1.findLabelCell(uniqueValue);
		int row=cell.getRow();
		Cell[] record = sheet1.getRow(row);
		return record;
	}
}
