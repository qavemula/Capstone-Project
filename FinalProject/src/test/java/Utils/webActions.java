package Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class webActions {

	private static WebElement element;

	// Method to click a WebElement
	public static void clickXPATH(WebDriver driver, String XPATH) {
		element = driver.findElement(By.xpath(XPATH));
		if (!element.isSelected()) {
			element.click();
			System.out.println(element);
		}
	}

	// Method to click a WebElement by ID
	public static void clickID(WebDriver driver, String ID) {
		element = driver.findElement(By.id(ID));
		if (!element.isSelected()) {
			element.click();
			System.out.println(element);
		}
	}

	// Method to select radioButton

	public static WebElement selectRadioButton(WebDriver driver, String cssSelector) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
			if (!element.isSelected()) {
				element.click();
				System.out.println("Selected radio button: " + cssSelector);
			} else {
				System.out.println("Radio button already selected: " + cssSelector);
			}
			return element;
		} catch (Exception e) {
			System.err.println("Failed to find or click radio button: " + cssSelector);
			e.printStackTrace();
			throw e;
		}
	}

	// Method to enter text
	public static void sendKeys(WebDriver driver, String XPATH, String string) {
		WebElement element = driver.findElement(By.xpath(XPATH));
		element.click();
		element.sendKeys(string);
	}

	public static void performEnter(WebDriver driver, String XPATH, Keys keys) {
		driver.findElement(By.xpath(XPATH)).sendKeys(Keys.ENTER);
		// element.sendKeys(string, Keys.ENTER);

	}

	public static void enterText(WebDriver driver, String cssSelector, String string) {
		WebElement element = driver.findElement(By.cssSelector(cssSelector));
		element.click();
		element.sendKeys(string);

	}

	public static void enterTextByXPATH(WebDriver driver, String XPATH, String string) {
		WebElement element = driver.findElement(By.xpath(XPATH));

		element.sendKeys(string);
		element.click();

	}

	public static void enterTextByID(WebDriver driver, String id, String text) {
		if (text != null) {
			driver.findElement(By.id(id)).sendKeys(text);
		} else {
			System.out.println("Warning: Skipped entering text for ID " + id + " because value is null.");
		}
	}

	// Method to click the element by css Selector
	public static void clickCssSelector(WebDriver driver, String cssSelector) {
		element = driver.findElement(By.cssSelector(cssSelector));
		if (!element.isSelected()) {
			element.click();
		}
	}

	// Method to verify the page title
	public boolean verifyPageTitle(WebDriver driver, String expectedTitle) {
		String actualTitle = driver.getTitle();
		return actualTitle.equals(expectedTitle);
	}

	// Method to verify if an element is displayed
	public boolean isElementDisplayed(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		return element.isDisplayed();
	}

	public static void clickElementByScroll(WebDriver driver, String XPATH) {

		WebDriverWait wait;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement element = driver.findElement(By.id(XPATH));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static void selectDropdownByVisibleText(WebDriver driver, String id, String visibleText) {
		WebElement dropdownElement = driver.findElement(By.id(id));
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByVisibleText(visibleText);
		System.out.println("Selected: " + visibleText);
	}

	public static void selectDropdownByValue(WebDriver driver, String id, String value) {
		WebElement dropdownElement = driver.findElement(By.id(id));
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByValue(value);
		System.out.println("Selected value: " + value);
	}

	public static void selectDropdownByIndex(WebDriver driver, String id, int index) {
		WebElement dropdownElement = driver.findElement(By.id(id));
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByIndex(index);
		System.out.println("Selected index: " + index);
	}

	// Take a screenshot when the test fails
	public static String captureScreenshot(WebDriver driver, String testName) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		String path = "extentReports/screenshots/" + testName + "_" + timestamp + ".png";

		try {
			File dest = new File(path);
			new File("extentReports/screenshots").mkdirs(); // Ensure folder exists
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			System.out.println("Screenshot capture failed: " + e.getMessage());
		}
		return path;
	}

	// Method to close the WebDriver
	public void closeDriver(WebDriver driver) {
		driver.quit();
	}

}
