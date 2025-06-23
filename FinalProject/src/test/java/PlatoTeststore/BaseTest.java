package PlatoTeststore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utils.ExtentTestManager;
import Utils.Locators;

public class BaseTest {
	protected WebDriver driver;
	private static ExtentReports extent;

	@BeforeSuite
	public void setupReporting() {
	    extent = new ExtentReports();

	    // Create timestamp for filename, e.g., "2025-06-20_12-34-56"
	    String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	    
	    // Build report file path with timestamp
	    String reportPath = "extentReports/automation-Execution-report-" + timestamp + ".html";
	    
	    ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	    spark.loadXMLConfig(new File("src/test/resources/spark-config.xml"));

	    extent.attachReporter(spark);
	    extent.setSystemInfo("Project", "PlatoFinalProject");
	    extent.setSystemInfo("Browser", "Chrome");
	    extent.setSystemInfo("OS", System.getProperty("os.name"));

	    System.out.println("Extent report will be saved at: " + reportPath);
	}

	@BeforeMethod
	public void setUp(java.lang.reflect.Method method) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(Locators.BASE_URL);

		ExtentTest test = extent.createTest(method.getName());
		ExtentTestManager.setTest(test);
		System.out.println("Driver initialized and browser launched.");
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws InterruptedException {
		Thread.sleep(2000);
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentTestManager.getTest().pass("Test passed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().skip("Test skipped");
		}

		if (driver != null) {
			driver.quit();
			System.out.println("Driver quit successfully.");
		}

		ExtentTestManager.endTest();
	}

	@AfterSuite
	public void flushReports() {
		extent.flush();
	}
}
