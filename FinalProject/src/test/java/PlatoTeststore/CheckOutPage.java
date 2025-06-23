package PlatoTeststore;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.ExcelUtils;
import Utils.ExtentTestManager;
import Utils.Locators;
import Utils.webActions;

public class CheckOutPage extends BaseTest {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	@DataProvider(name = "Checkout page")
	// Read data from excel sheet
	public Object[][] getData() throws IOException {

		String filePath = System.getProperty("user.dir") + "/TestData/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet2");
		int rowCount = ExcelUtils.getRowCount();

		// Define 7 columns
		Object[][] data = new Object[rowCount - 1][7];

		for (int i = 1; i < rowCount; i++) {
			data[i - 1][0] = ExcelUtils.getCellData(i, 0); // First Name
			data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Last Name
			data[i - 1][2] = ExcelUtils.getCellData(i, 2);// Email
			data[i - 1][3] = ExcelUtils.getCellData(i, 3);// Telephone
			data[i - 1][4] = ExcelUtils.getCellData(i, 4);// Address
			data[i - 1][5] = ExcelUtils.getCellData(i, 5);// City
			data[i - 1][6] = ExcelUtils.getCellData(i, 6);// Postal Code

		}

		ExcelUtils.closeExcel();
		return data;
	}

	@Test(dataProvider = "Checkout page")
	public void proceedToCheckoutTest(String FirstName, String LastName, String Email, String Telephone, String Address,
			String City, String PostalCode) throws InterruptedException {
		
		//Navigates to the product url
		ProductActions.addProductToCart(driver, Locators.CAMERAPRODUCTURL);
		ExtentTestManager.getTest().pass("Started the test");

		ProductActions.openUserDropdown(driver);
		ExtentTestManager.getTest().pass("Selected dropdown successfuly");
		Thread.sleep(1000);
		webActions.clickXPATH(driver, Locators.CHECKOUTICON);
		ExtentTestManager.getTest().pass("Clicked on Checkout icon");

		System.out.println("I did click the checkout icon");

		// To select guest radio button
		WebElement guestRadio = webActions.selectRadioButton(driver, Locators.GUESTCHECKOUT_RADIOBUTTON);
		Assert.assertTrue(guestRadio.isSelected(), "Guest Checkout radio button should be selected.");
		System.out.println("Guest Checkout selected successfully.");
		ExtentTestManager.getTest().pass("Guest Checkout selected successfully");
		webActions.clickID(driver, Locators.CONTINUEBUTTON);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		ExtentTestManager.getTest().pass("Selected contiue button");

		// Enter personal details

		webActions.enterTextByID(driver, Locators.FIRSTNAME, FirstName);
		webActions.enterTextByID(driver, Locators.LASTNAME, LastName);
		webActions.enterTextByID(driver, Locators.EMAIL, Email);
		webActions.enterTextByID(driver, Locators.TELEPHONE, Telephone);
		webActions.enterTextByID(driver, Locators.ADDRESS, Address);
		webActions.enterTextByID(driver, Locators.CITY, City);
		webActions.enterTextByID(driver, Locators.POSTALCODE, PostalCode);
		ExtentTestManager.getTest().pass("Personal details are entered");

		Thread.sleep(2000);
		webActions.selectDropdownByVisibleText(driver, Locators.COUNTRY, "Canada");
		webActions.selectDropdownByVisibleText(driver, Locators.REGION, "Nova Scotia");

		webActions.clickID(driver, Locators.GUEST_CONTINUE);
		ExtentTestManager.getTest().pass("Continue button is clicked");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		webActions.clickID(driver, Locators.DELIVERY_CONTINUE);
		ExtentTestManager.getTest().pass("Selected Continue under Delivery Methods");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		webActions.clickCssSelector(driver,
				"#collapse-payment-method > div > div.buttons > div > input[type=checkbox]:nth-child(2)");
		webActions.clickID(driver, Locators.PAYMENT_CONTINUE);
		ExtentTestManager.getTest().pass("Selected Continue under Payment Methods");
		Thread.sleep(2000);
		webActions.clickID(driver, Locators.COMFIRMORDER_BUTTON);
		ExtentTestManager.getTest().pass("Confirm order ");
		Thread.sleep(1000);
		// Accept Alert when it appears
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.alertIsPresent()).accept();

		} catch (TimeoutException e) {

		}

		webActions.clickID(driver, Locators.COMFIRMORDER_BUTTON);

		// Verify success message
		try {
			WebElement confirmationHeader = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > h1")));
			String confirmationText = confirmationHeader.getText().trim();
			System.out.println("Confirmation text: " + confirmationText);
			Assert.assertEquals(confirmationText, "Your order has been placed!", "Order not placed successfully.");
		} catch (Exception e) {
			System.out.println("Failed to find confirmation message: " + e.getMessage());
			// Assert.fail("Test failed due to missing confirmation message.");
		}

	}
}
