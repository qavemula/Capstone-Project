package PlatoTeststore;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.ExcelUtils;
import Utils.Locators;
import Utils.ExtentTestManager;
import Utils.webActions;

public class LoginPage extends BaseTest {

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/TestData/TestData.xlsx";
        ExcelUtils.loadExcel(filePath, "Sheet1");
        int rowCount = ExcelUtils.getRowCount();

        Object[][] data = new Object[rowCount - 1][2];
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Email
            data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Password
        }

        ExcelUtils.closeExcel();
        return data;
    }

    @Test(dataProvider = "LoginData")
    public void enterLoginDetails(String Email, String Password) throws InterruptedException {
        ExtentTestManager.getTest().pass("Started the test");

        webActions.clickXPATH(driver, Locators.ACCOUNTICON);
        ExtentTestManager.getTest().info("Clicked on account icon");

        webActions.clickXPATH(driver, Locators.LOGIN);
        ExtentTestManager.getTest().info("Clicked on login link");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        webActions.enterTextByID(driver, Locators.EMAILFIELD, Email);
        ExtentTestManager.getTest().info("Entered email");

        webActions.enterTextByID(driver, Locators.PASSWORD, Password);
        ExtentTestManager.getTest().info("Entered password");

        webActions.clickXPATH(driver, Locators.LOGINBUTTON);
        ExtentTestManager.getTest().info("Clicked login button");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement logo = wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.LOGO))
            ));

            String logoText = logo.getText();
            ExtentTestManager.getTest().pass("Login successful. Logo text: " + logoText);
        } catch (StaleElementReferenceException e) {
            ExtentTestManager.getTest().fail("Login failed due to stale element: " + e.getMessage());
        } catch (Exception ex) {
            ExtentTestManager.getTest().fail("Login test encountered error: " + ex.getMessage());
        }
    }
}
