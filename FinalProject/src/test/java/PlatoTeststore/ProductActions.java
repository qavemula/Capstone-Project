package PlatoTeststore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utils.Locators;

public class ProductActions {

    public static void addProductToCart(WebDriver driver, String productUrl) throws InterruptedException {
        driver.get(Locators.CAMERAPRODUCTURL);
        Thread.sleep(2000);
        driver.findElement(By.id(Locators.ADDTOCART_BUTTON)).click();
        System.out.println("Product added to cart.");
    }

    public static void openUserDropdown(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(Locators.ADDTOCARTICON)).click();
        System.out.println("User dropdown clicked.");
    }
}
