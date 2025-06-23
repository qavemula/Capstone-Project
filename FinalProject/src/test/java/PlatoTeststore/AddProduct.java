package PlatoTeststore;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.Test;

import Utils.ExtentTestManager;
import Utils.Locators;

public class AddProduct extends BaseTest {

	@Test
	public void addProductTest() throws InterruptedException {
		ProductActions.addProductToCart(driver, Locators.CAMERAPRODUCTURL);
		ExtentTestManager.getTest().pass("Product added to Cart");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert")));

		String successMessage = message.getText().replace("×", "").trim();
		System.out.println("Success message: >>>" + successMessage + "<<<");
		ExtentTestManager.getTest().pass("Success message displayed");

		Assert.assertTrue(successMessage.contains("Success:"),
				"Expected success message not found. Actual: " + successMessage);

	}
}
