package Academy;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;

public class listeners implements ITestListener 
{
	Base b = new Base();

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result)
	{
		try {
			b.getScreenshot(result.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public void onTestSkipped(ITestResult arg0) {
	// TODO Auto-generated method stub

}

public void onTestStart(ITestResult arg0) {
	// TODO Auto-generated method stub

}

public void onTestSuccess(ITestResult arg0) {
	// TODO Auto-generated method stub

}

}
