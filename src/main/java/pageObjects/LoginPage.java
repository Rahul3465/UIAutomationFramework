package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jxl.Cell;
import resources.Base;

public class LoginPage
{
	public WebDriver driver;

	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[id='user_email']")
	private WebElement email;

	@FindBy(css = "input[type='password']")
	private WebElement password;
	
	@FindBy(css = "input[type='submit']")
	private WebElement login;
	
	

	public void getEmail(String sheet, String uniqueName)
	{
		Cell[] record=null;
		try
		{
			record= Base.readExcel(sheet,uniqueName);
		}
		catch(Exception e){}

		String un = record[1].getContents().trim();
		String pwd = record[2].getContents().trim();	
		email.sendKeys(un);
		password.sendKeys(pwd);
		login.click();
		
	}
}
