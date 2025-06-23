package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.loadXMLConfig(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Project", "Plato Teststore");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Automation QA");
        }
        return extent;
    }
}
