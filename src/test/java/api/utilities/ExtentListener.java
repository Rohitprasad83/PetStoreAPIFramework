package api.utilities;

import io.restassured.response.Response;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;

public class ExtentListener implements ITestListener {
    private ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = ExtentReportManager.getReportInstance().createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, result.getName() + " Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, result.getName() + " Test Failed");

        // Capture exception details
        if (result.getThrowable() != null) {
            test.log(Status.FAIL, result.getThrowable().toString());
        }

        // Get the last logged response from LogResponse utility
        try {
            Response lastResponse = LogResponse.getLastResponse();
            if (lastResponse != null) {
                test.log(Status.FAIL, "Response Status Code: " + lastResponse.getStatusCode());
                test.log(Status.FAIL, "Response Body: " + lastResponse.getBody().asPrettyString());
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to capture API response: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, result.getName() + " Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.getReportInstance().flush();
    }
}