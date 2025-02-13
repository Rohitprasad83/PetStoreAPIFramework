package api.utilities;

import com.aventstack.extentreports.ExtentTest;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener, IInvokedMethodListener {

    private ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        // Log test start in Extent Report
        test = ExtentManager.getReporter().createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log success to Extent Report
        test.pass("Test Passed");
//        Response response = (Response) result.getAttribute("response"); // If you've set the response in your tests
//        test.info("Response: " + response.getBody().asString());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log failure to Extent Report
        test.fail(result.getThrowable());
//        Response response = (Response) result.getAttribute("response"); // If you've set the response in your tests
//        test.info("Response: " + response.getBody().asString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log skipped test to Extent Report
        test.skip("Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Handle partial success if necessary
    }

    @Override
    public void onStart(ITestContext context) {
        // Initialize the Extent Report before the tests start
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the report after tests are finished
        ExtentManager.flushReport();
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // You can use this to log before test method execution if needed
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // Log after invocation if necessary
    }
}
