package Academy;
import org.testng.annotations.Test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class HomePage extends Base
{
	public static Logger log = LogManager.getLogger(Base.class.getName());
	@BeforeTest
	public void initialize() throws Exception
	{
		driver = initializeDriver();
		log.info("Driver is initialized");

	}

	@Test
	public void navigation()
	{
		driver.get(prop.getProperty("url"));
		LandingPage l = new LandingPage(driver);
		l.landing();
		
		LoginPage lp = new LoginPage(driver);
		lp.getEmail("sampleSheet", "Login");
	}

	@AfterTest
	public void teardown()
	{
		driver.quit();
		driver = null;
	}
}
