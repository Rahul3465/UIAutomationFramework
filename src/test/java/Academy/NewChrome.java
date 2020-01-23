package Academy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewChrome
{
	WebDriver driver;
	
	@BeforeMethod
	public void a()
	{
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
	}
	
	@Test
	public void b() throws InterruptedException
	{
		driver.get("https://www.google.com");
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
	}
	
	@AfterMethod
	public void c()
	{
		driver.quit();
	}
}
