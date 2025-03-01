package api.utilities;

import io.restassured.response.Response;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;

import java.util.HashMap;
import java.util.Map;

public class ExtentListener implements ITestListener {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static Map<String, ExtentTest> classLevelTests = new HashMap<>();
    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getName();
        ExtentTest classTest;

        if (classLevelTests.containsKey(className)) {
            classTest = classLevelTests.get(className);
        } else {
            classTest = ExtentReportManager.getReportInstance().createTest(className);
            classLevelTests.put(className, classTest);
        }

        ExtentTest methodTest = classTest.createNode(result.getName());
        test.set(methodTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, result.getName() + " Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, result.getName() + " Test Failed");

        // Capture exception details
        if (result.getThrowable() != null) {
            test.get().log(Status.FAIL, result.getThrowable().toString());
        }

        // Get the last logged response from LogResponse utility
        try {
            Response lastResponse = LogResponse.getLastResponse();
            if (lastResponse != null) {
                test.get().log(Status.FAIL, "Response Status Code: " + lastResponse.getStatusCode());
                test.get().log(Status.FAIL, "Response Body: " + lastResponse.getBody().asPrettyString());
            }
        } catch (Exception e) {
            test.get().log(Status.FAIL, "Failed to capture API response: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, result.getName() + " Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.getReportInstance().flush();
    }
}