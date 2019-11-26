package pageObjects;

import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class scanPageObjects {


    WebDriver webXDriver;
    scanLocators scanLocators = new scanLocators();
//    public List<WebElement> ProductCatalogue;

    public scanPageObjects(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        webXDriver = new ChromeDriver();
    }

    public void navigateToHome(){
        webXDriver.navigate().to(scanLocators.SCAN_HOME_URL);
    }
    public void Login(String email, String password){
        webXDriver.navigate().to(scanLocators.SCAN_LOGIN_URL);
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        WebElement loginField = webXDriver.findElement(By.id(scanLocators.LOGIN_EMAIL_ID));
        WebElement passwordField = webXDriver.findElement(By.id(scanLocators.LOGIN_PASSWORD_ID));

        loginField.sendKeys(email);
        passwordField.sendKeys(password);

        webXDriver.findElement(By.id(scanLocators.LOGIN_SUBMIT_BUTTON)).click();
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

    }

    public boolean loginURLCheck() {
        String currentUrl = webXDriver.getCurrentUrl();
        String accountURL = "https://www.scanmalta.com/newstore/customer/account/";
        webXDriver.quit();
        return currentUrl.equals(accountURL);
    }

    public boolean loginInvalidURLCheck() {
        String currentUrl = webXDriver.getCurrentUrl();
        String accountURL = "https://www.scanmalta.com/newstore/customer/account/login/";
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        webXDriver.quit();
        return currentUrl.equals(accountURL);
    }

    public void searchForProduct(String product2SearchFor){
        WebElement searchInputField = webXDriver.findElement(By.id(scanLocators.SEARCH_BAR_ID));
        searchInputField.sendKeys(product2SearchFor);
        searchInputField.sendKeys(Keys.RETURN);
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public List<WebElement> getListOfAllProducts(){
        return webXDriver.findElements(By.className(scanLocators.PRODUICT_LI_SINGLE_CLASS));
    }

    public void getNClickFirstProduct(){
        WebElement firstProduct = getListOfAllProducts().get(1);
        firstProduct.click();
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public boolean verifyProductPage(){
        int productPageSize = webXDriver.findElements(By.className(scanLocators.PRODUCT_PAGE_CONTAINERR_CLASS)).size();
        webXDriver.quit();
        return productPageSize != 0;
    }

    public void navigateToCart(){
        webXDriver.navigate().to(scanLocators.SCAN_SHOPPING_CART_URL);
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public void ifCartEmpty() {
        if(!webXDriver.getPageSource().contains("You have no items in your shopping cart.")){
            webXDriver.findElement(By.id(scanLocators.SHOPPING_CART_EMPTY_ID)).click();
            webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }
    }

    public void navigateToProduct(){
        webXDriver.navigate().to(scanLocators.SCAN_PRODUCT_DETAILS_URL);
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public void addProductToCart(){
        webXDriver.findElement(By.id(scanLocators.ADD_TO_CART_ID)).click();
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public boolean checkIfCartContainsSingleItem(){
       WebElement tableElement = webXDriver.findElement(By.id("shopping-cart-table"));
       int tableSize = tableElement.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size();
       webXDriver.quit();
       return  tableSize == 1;
    }


}

