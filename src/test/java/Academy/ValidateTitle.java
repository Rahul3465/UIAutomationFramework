package Academy;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import pageObjects.LandingPage;
import resources.Base;

public class ValidateTitle extends Base
{
	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void initialize() throws Exception
	{
		driver = initializeDriver();
		log.info("Driver is initialized");
		driver.get(prop.getProperty("url"));
		log.info("Navigated to Home page");
	}

	@Test()
	public void pageNavigation() throws Exception
	{
		LandingPage l = new LandingPage(driver);
		System.out.println(l.getTitle());
		Assert.assertEquals(l.getTitle(), "FEATURED COURSES");
		log.info("Successfully validated text message");
	}

	@AfterTest
	public void teardown()
	{
		driver.quit();
		driver = null;
	}

}
