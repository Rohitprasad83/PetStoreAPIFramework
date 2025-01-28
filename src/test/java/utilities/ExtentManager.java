package utilities;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    public static ExtentReports getReporter() {
        if (extent == null) {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            // Create reports directory if it doesn't exist
            String reportPath = System.getProperty("user.dir") + "/reports/";
            java.io.File file = new java.io.File(reportPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            // Specify the location of the report with timestamp
            sparkReporter = new ExtentSparkReporter(reportPath + "TestReport_" + timeStamp + ".html");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            
            // Add more system info
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            
            // Configure report appearance
            sparkReporter.config().setDocumentTitle("API Test Report");
            sparkReporter.config().setReportName("Pet Store API Test Automation Report");
        }
        return extent;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
