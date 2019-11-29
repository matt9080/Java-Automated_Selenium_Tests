package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class scanPageObjects {


    WebDriver webXDriver;
    scanLocators scanLocators = new scanLocators();
    ChromeOptions options = new ChromeOptions();
    Actions actions;

//    public List<WebElement> ProductCatalogue;

    public scanPageObjects(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        options.addArguments("--start-fullscreen");
        //options.addArguments("headless");
        webXDriver = new ChromeDriver(options);
        actions = new Actions(webXDriver);
    }
    public void quit(){
        webXDriver.quit();
    }

    public void navigateToHome(){
        webXDriver.navigate().to(scanLocators.SCAN_HOME_URL);
    }


    private void checkIfPopUpExists(){
        if(!webXDriver.findElements(By.id("exitintent-popup")).isEmpty()){
            WebElement popupElement = webXDriver.findElement(By.id("exitintent-popup"));
            popupElement.findElement(By.className("close-reveal-modal")).click();
        }
    }

    public void Login(String email, String password){
        webXDriver.navigate().to(scanLocators.SCAN_LOGIN_URL);
       // webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try{
            WebElement loginField = webXDriver.findElement(By.id(scanLocators.LOGIN_EMAIL_ID));
            WebElement passwordField = webXDriver.findElement(By.id(scanLocators.LOGIN_PASSWORD_ID));

            loginField.sendKeys(email);
            passwordField.sendKeys(password);

            webXDriver.findElement(By.id(scanLocators.LOGIN_SUBMIT_BUTTON)).click();
        }catch (Exception e){
            checkIfPopUpExists();
            Login(email,password);
        }

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
        WebElement firstProduct = getListOfAllProducts().get(0);
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
            WebElement cartyEmpty = webXDriver.findElement(By.id(scanLocators.SHOPPING_CART_EMPTY_ID));
            actions.moveToElement(cartyEmpty);
            actions.perform();
            ((JavascriptExecutor) webXDriver)
                    .executeScript("window.scrollBy(0, 250)", "");
            cartyEmpty.click();
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
       return  tableSize == 1;
    }

    public List<WebElement> getListOfAddToCartBtn(){
        return webXDriver.findElements(By.className(scanLocators.PRODUCT_ADD_TO_CART_BUTTON_CLASS));
    }

    public void addAmountOfProductsToCart(int numberOfProducts){
        webXDriver.navigate().to(scanLocators.SCAN_SEARCH_PRODUCT_URL);
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        for(int i = 1; i <= numberOfProducts  ; i++){
            List<WebElement> webElementsAtag = getListOfAddToCartBtn();
            WebElement currencyTag = webElementsAtag.get(i - 1);
            actions.moveToElement(currencyTag);
            actions.perform();
            ((JavascriptExecutor) webXDriver)
                    .executeScript("window.scrollBy(0, 250)", "");
            currencyTag.click();
            //webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            if(!(i == numberOfProducts  )){
                webXDriver.navigate().to(scanLocators.SCAN_SEARCH_PRODUCT_URL);
                webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
           // webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }
    }

    public boolean checkCartItems(int numberOfProducts){
        int tableSize = 0;
        try{
            WebElement tableElement = webXDriver.findElement(By.id("shopping-cart-table"));
             tableSize = tableElement.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size();
        }catch (Exception e){
            foxxKristu();
            checkCartItems(numberOfProducts);
        }
        return  tableSize == numberOfProducts;
    }

    private void foxxKristu(){
        if(!webXDriver.findElement(By.id("reveal-messages")).isDisplayed()){
            webXDriver.findElement(By.className("close-reveal-modal")).click();
        }
    }

    public void cartHas1Product(){
        webXDriver.navigate().to(scanLocators.SCAN_SHOPPING_CART_URL);
        webXDriver.findElement(By.className("btn-remove2")).click();
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

}

