package Academy;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OTPReading
{
	public WebDriver driver;
	public static final String ACCOUNT_SID = "ACdaba1aa79f0486d5fd3c97a315e9ba8a";
	public static final String AUTH_TOKEN = "630db85485d4cca42670e1a5b0b03783";
	
	@BeforeMethod
	public void a()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void b() throws InterruptedException
	{
		/*driver.get("https://www.google.com");
		Thread.sleep(3000);
		System.out.println(driver.getTitle());*/
		
		driver.findElement(By.cssSelector("a#nav-link-accountList>span>span")).click();
		driver.findElement(By.id("createAccountSubmit")).click();
		driver.findElement(By.id("ap_customer_name")).sendKeys("Rahul");
		driver.findElement(By.id("auth-country-picker-container")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'United States +1')]")).click();
		driver.findElement(By.id("ap_phone_number")).sendKeys("7329431630");;
		driver.findElement(By.id("ap_password")).sendKeys("rahul@534");;
		driver.findElement(By.id("continue")).click();
		
		//Get the OTP using Twilio API's
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		String smsBody = getMessage();
		//System.out.println(smsBody);
		String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
		//System.out.println(OTPNumber);
		driver.findElement(By.id("auth-pv-enter-code")).sendKeys(OTPNumber);
		driver.findElement(By.id("auth-verify-button")).click();
		Thread.sleep(3000);
	}
	
	@AfterMethod
	public void c()
	{
		driver.quit();
	}
	
	public static String getMessage(){
		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals("+17329431630")).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
	}
	
	private static Stream<Message> getMessages(){
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		return StreamSupport.stream(messages.spliterator(), false);
	}
}
