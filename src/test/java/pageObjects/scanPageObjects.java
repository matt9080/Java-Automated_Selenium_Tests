package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class scanPageObjects {


    WebDriver webXDriver;
    scanLocators scanLocators = new scanLocators();
//    public List<WebElement> ProductCatalogue;

    public scanPageObjects(){
        System.setProperty("webdriver.chrome.driver","D:\\ChromeDriver\\chromedriver.exe");
        webXDriver = new ChromeDriver();
    }
    public void closeModal(){
        if(webXDriver.findElement(By.className(scanLocators.MODAL_CLOSE_CLASS)).isEnabled()){
            webXDriver.findElement(By.className(scanLocators.MODAL_CLOSE_CLASS)).click();
        }

    }
    public void goToHomePage() throws MalformedURLException, InterruptedException {
        webXDriver.navigate().to(new URL(scanLocators.SCAN_HOME_URL));
//        webXDriver.sleep(150);
        closeModal();
//        webXDriver.wait(500);
    }

    public void goToLoginPage() throws MalformedURLException, InterruptedException {
        webXDriver.navigate().to(new URL(scanLocators.SCAN_LOGIN_URL));
//        webXDriver.wait(150);
        closeModal();
//        webXDriver.wait(500);
    }

    public void login(String email, String password) throws InterruptedException {
        webXDriver.findElement(By.className(scanLocators.LOGIN_EMAIL_ID)).sendKeys(email);
//        webXDriver.wait(250);
        webXDriver.findElement(By.id(scanLocators.LOGIN_PASSWORD_ID)).sendKeys(password);
//        webXDriver.wait(250);
        webXDriver.findElement(By.id(scanLocators.LOGIN_SUBMIT_BUTTON)).click();
//        webXDriver.wait(250);
    }

    public void quit(){
        webXDriver.quit();
    }

    public boolean isUserCorrectlyLoggedIn(){
        return  webXDriver.getCurrentUrl().equals(scanLocators.SCAN_LOGGED_IN_DASHBOARD);
    }

    public boolean incorrectLoginVerification() {
        return webXDriver.findElement(By.id(scanLocators.INCORRECT_EMAIL_NOTIFICATION)).isDisplayed()
                && webXDriver.findElement(By.id(scanLocators.INCORRECT_PASSWORD_NOTIFICATION)).isDisplayed();
    }

    public void searchProduct(String productName){
        WebElement searchBar = webXDriver.findElement(By.id(scanLocators.SEARCH_BAR_ID));
        searchBar.sendKeys(productName);
        searchBar.sendKeys(Keys.ENTER);
    }

    public List<WebElement> getListOfProducts(){
        return webXDriver.findElements(By.className(scanLocators.PRODUICT_LI_SINGLE_CLASS));
    }

    public WebElement getFirstProduct(){
        return getListOfProducts().get(1);
    }

    public boolean isProductPage(){
        return webXDriver.findElement(By.className(scanLocators.PRODUCT_PAGE_CONTAINERR_CLASS)).isDisplayed();
    }

    public void isCartEmpty(){
        webXDriver.navigate().to(scanLocators.SCAN_SHOPPING_CART_URL);
        if(webXDriver.findElement(By.className(scanLocators.SHOPPING_CART_EMPTY)).isDisplayed()){
            webXDriver.navigate().to(scanLocators.SCAN_HOME_URL);
        }else{
            webXDriver.findElement(By.id(scanLocators.SHOPPING_CART_EMPTY_ID)).click();
            webXDriver.navigate().to(scanLocators.SCAN_HOME_URL);

        }
    }


}
