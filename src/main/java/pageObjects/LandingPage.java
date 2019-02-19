package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage
{
	public WebDriver driver;

	//	By signin = By.cssSelector("a[href*='sign_in']");
	//	By title  = By.cssSelector(".text-center>h2");
	//	By navBar  = By.cssSelector(".nav.navbar-nav.navbar-right>li>a");

	@FindBy(css = "a[href*='sign_in']")
	private WebElement signin;

	@FindBy(css = ".text-center>h2")
	private WebElement title;

	@FindBy(css = ".nav.navbar-nav.navbar-right>li>a")
	private WebElement navBar;


	public LandingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//	public WebElement getLogin()
	//	{
	//		return driver.findElement(signin);
	//	}
	//	
	//	public WebElement getTitle()
	//	{
	//		return driver.findElement(title);
	//	}
	//	
	//	public WebElement getNavBar()
	//	{
	//		return driver.findElement(navBar);
	//	}

	public void landing()
	{
		signin.click();
	}

	public String getTitle()
	{
		String title1 = title.getText();
		return title1;
	}
}
