
package PlatoTeststore;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Utils.ExtentTestManager;
import Utils.Locators;
import Utils.webActions;
import junit.framework.Assert;

public class ProductPage extends BaseTest {

	@Test
	public void validateSearchResults() {
		driver.get(Locators.SEARCHURL);
	//	driver.get("http://webtest.pqatesting.com/clean/index.php?route=account/account");
		ExtentTestManager.getTest().pass("Started the test");
		webActions.enterTextByXPATH(driver, Locators.SEARCHBAR, "Sam");
		ExtentTestManager.getTest().pass("Entered text in Search BAR");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Clicking in the search icon
		webActions.performEnter(driver, Locators.SEARCHBUTTON, Keys.ENTER);
		

		WebElement bodyElement = driver.findElement(By.cssSelector(Locators.SEARCHRESULTSTEXT));
		String searchFound = bodyElement.getText();
		System.out.println(bodyElement.getText());

		try {

			Assert.assertEquals(searchFound, "Products meeting the search criteria");
			ExtentTestManager.getTest().pass("Desired product found");
			System.out.println(searchFound + "Found");
		} catch (AssertionError e) {
			Reporter.log(e.toString());
			System.out.println("Test failed. See report for details");
			Assert.fail();
		}

	}
}