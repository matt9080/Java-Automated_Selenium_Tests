package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class scanPageObjects {


    private WebDriver webXDriver;
    private scanLocators scanLocators = new scanLocators();
    private ChromeOptions options = new ChromeOptions();
    private Actions actions;

    public scanPageObjects(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");   // Tells the system where to find the chrome driver.
        options.addArguments("--start-fullscreen");     // Option to run the driver in either fullscreen mode or in headless mode, can choose between the 2 by commenting out either line.
        //options.addArguments("headless");
        webXDriver = new ChromeDriver(options);
        actions = new Actions(webXDriver); // Creates instance of action passing the webDriver as a parameter.
    }
    public void quit(){
        webXDriver.quit(); // Method which tells the webDriver to quit, used in the test's @After method.
    }

    public void navigateToHome(){
        webXDriver.navigate().to(scanLocators.SCAN_HOME_URL); // Method which tells the webDriver to navigate to a String URL, which in this case is the website's home page.
    }


    private void checkIfPopUpExists(){ // The website in question has a popUp which appears on the login page, this can interfere with the webDriver.
        if(!webXDriver.findElements(By.id(scanLocators.MODAL_ID)).isEmpty()){ // Checks the page if there exist any elements with the ID of the popup.
            WebElement popupElement = webXDriver.findElement(By.id(scanLocators.MODAL_ID)); // Searches if a webElement with the modal ID is found, and saves to a variable webELement.
            popupElement.findElement(By.className(scanLocators.MODAL_CLOSE_CLASS)).click(); // Searches within the previously found webElement for an element with classname and clicks it, closing the modal.
        }
    }

    public void Login(String email, String password){ // Method which takes 2 Strings as parameters.
        webXDriver.navigate().to(scanLocators.SCAN_LOGIN_URL);  // Tells the driver to navigate to the Scan Login URL.
       // webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try{    // Try catch is used here as it was noted that when the webDriver access the login page, the driver is blocked by a modal.
            WebElement loginField = webXDriver.findElement(By.id(scanLocators.LOGIN_EMAIL_ID)); // Searches the page for a webElement with ID of the email entry field and saves it to a variable WebElement.
            WebElement passwordField = webXDriver.findElement(By.id(scanLocators.LOGIN_PASSWORD_ID)); // Searches the page for a webElement with ID of the password entry field and saves it to a variable WebElement.

            loginField.sendKeys(email); // Sends the webElement containing the login field, the email passed to the method.
            passwordField.sendKeys(password); // Sends the webElement containing the password field, the password passed to the method.

            webXDriver.findElement(By.id(scanLocators.LOGIN_SUBMIT_BUTTON)).click(); // Finds the element with ID of the submit button and clicks it.

        }catch (Exception e){ //  If for some reason driver fails, (typically because a field can't be found due to being blocked by the modal)
            checkIfPopUpExists(); // Method which checks if the modal is there and closes it.
            Login(email,password); // Recursively call the login method again.
        }

       // webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); // Tells the webDriver to wait 1 second before resuming, in order to give the subsequent page time to load.

    }

    public boolean loginURLCheck() { // Method which returns a boolean, based on the current URL.
        String currentUrl = webXDriver.getCurrentUrl(); // Gets the current URL.
        String accountURL = "https://www.scanmalta.com/newstore/customer/account/"; // String containing the URL of the post-login Page.
        return currentUrl.equals(accountURL); // Returns comparision of both URLs.
    }

    public boolean loginInvalidURLCheck() { // Method which returns a boolean, based on the current URL.
        String currentUrl = webXDriver.getCurrentUrl(); // Gets the current URL.
        String accountURL = "https://www.scanmalta.com/newstore/customer/account/login/"; // String containing the URL of the login page.
       // webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); // Tells the driver to wait a second.
        return currentUrl.equals(accountURL); // Returns comparision of both String urls.
    }

    public void searchForProduct(String product2SearchFor){ // Method which takes a String, describing what product to search fof.
        WebElement searchInputField = webXDriver.findElement(By.id(scanLocators.SEARCH_BAR_ID)); // Searches the page for the ID of the product search field and stores it in a WebElement.
        searchInputField.sendKeys(product2SearchFor); // Sends the keys to the searchProduct webElement.
        searchInputField.sendKeys(Keys.RETURN); // Simulates the user pressing the enter key on the searchProduct webElement.
       // webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); // Tells the driver to wait a second in order to give the next page adequate time to load.
    }

    private List<WebElement> getListOfAllProducts(){ // Private method which accepts return of a list of webElements.
        return webXDriver.findElements(By.className(scanLocators.PRODUICT_LI_SINGLE_CLASS)); // Returns list of all elements with class name of products in the product search.
    }

    public void getNClickFirstProduct(){ // Method which find the first element and clicks it.
        WebElement firstProduct = getListOfAllProducts().get(0); // Gets the first element from the list of products and stores in a WebElement variable.
        firstProduct.click(); // Clicks the firstProduct WebElement
        //webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); // Tells the driver to wait a second in order to give the next page adequate time to load.
    }

    public boolean verifyProductPage(){ // Method which returns boolean.
        int productPageSize = webXDriver.findElements(By.className(scanLocators.PRODUCT_PAGE_CONTAINERR_CLASS)).size(); // Searches the page for class found when a product is clicked.
        return productPageSize != 0; // If element is found with said class return true.
    }

    public void navigateToCart(){
        webXDriver.navigate().to(scanLocators.SCAN_SHOPPING_CART_URL); // Tells the driver to navigate to String url, of Scan's shopping cart.
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); // Tells the driver to wait a second in order to give the next page adequate time to load.
    }

    public void ifCartEmpty() { // Method which checks if the shopping cart is empty.
        if(!webXDriver.getPageSource().contains("You have no items in your shopping cart.")){ // if statement, which checks it any text in the page is String found when cart is empty.
            WebElement cartyEmpty = webXDriver.findElement(By.id(scanLocators.SHOPPING_CART_EMPTY_ID)); // Finds element by ID, of empty cart button and stores it in a WebElement.
            actions.moveToElement(cartyEmpty); // Action to move to the empty cart.
            actions.perform();
            ((JavascriptExecutor) webXDriver)
                    .executeScript("window.scrollBy(0, 250)", ""); // JavaScript Executor which scrolls the page down slightly.
            // Both the Action and the JavaScript executor were implemented because when just the Action was used the element was at the very bottom of the page and the driver was unable to fine it.
            // The same thing happened when just the JavaScript Executor was used, therefore what needed to be done is to find the element and scroll down slightly so it appears in the middle of the screen.
            cartyEmpty.click(); // Clicks the WebElement containing the empty cart button.
            //webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); // Tells the driver to wait a second in order to give the next page adequate time to load.
        }
    }

    public void navigateToProduct(){ // Method to navigate to a pre - determined product page.
        webXDriver.navigate().to(scanLocators.SCAN_PRODUCT_DETAILS_URL);
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public void addProductToCart(){ // Adds product to cart.
        webXDriver.findElement(By.id(scanLocators.ADD_TO_CART_ID)).click(); // Finds element with ID, of button to add product to cart and clicks it.
       // webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); // Tells the driver to wait a second in order to give the next page adequate time to load.
    }

    public boolean checkIfCartContainsSingleItem(){ // Method to check if cart contains a single Item.
       WebElement tableElement = webXDriver.findElement(By.id("shopping-cart-table")); // Finds ID of shopping cart table.
       int tableSize = tableElement.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size(); // Checks the amount of TRs in it, which represent indiviual items in the shopping cart.
       return  tableSize == 1; // Return true if just 1 item is found.
    }

    private List<WebElement> getListOfAddToCartBtn(){ //Method which accepts return of list of WebElements.
        return webXDriver.findElements(By.className(scanLocators.PRODUCT_ADD_TO_CART_BUTTON_CLASS)); // Returns list of buttons in the search product page which allow user to add product to cart.
    }

    public void addAmountOfProductsToCart(int numberOfProducts){ // Method which accepts parameter of type int which determines the amount of products to search for.
        webXDriver.navigate().to(scanLocators.SCAN_SEARCH_PRODUCT_URL); // Navigates to pre-determined searched product.
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); // Tells the driver to wait a second in order to give the next page adequate time to load.
        for(int i = 1; i <= numberOfProducts  ; i++){ // For loop which stops when required number of products are added to the cart.
            List<WebElement> webElementsAtag = getListOfAddToCartBtn(); // Get list of webElements.
            WebElement currencyTag = webElementsAtag.get(i - 1); // Find the next element in the list and store it in a WebElement variable.
            actions.moveToElement(currencyTag);
            actions.perform();
            ((JavascriptExecutor) webXDriver)
                    .executeScript("window.scrollBy(0, 250)", "");
            currencyTag.click();
            //webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            if(!(i == numberOfProducts  )){ // If the number of products is not reached, return to the searched product URL and wait a second.
                webXDriver.navigate().to(scanLocators.SCAN_SEARCH_PRODUCT_URL);
                webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
           // webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }
    }

        /*
        The method checkCartItems along with the other method which checks cart items could have been optimized as a single method which returns an integer
         and then that integer is used to determine if the test passed or not. However for the sake of simplicity I will leave both methods as is.
         */
    public boolean checkCartItems(int numberOfProducts){
        int tableSize = 0;
        try{ // When adding product to cart, a modal pops up which interferes with the driver.
            WebElement tableElement = webXDriver.findElement(By.id("shopping-cart-table"));
             tableSize = tableElement.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).size();
        }catch (Exception e){ // If the driver fails to find either element, then the removeCartModal is called and the checkCartItems is called recursively.
            removeCartModal();
            checkCartItems(numberOfProducts);
        }
        return  tableSize == numberOfProducts; // Returns true if the amount of products in the table is equal to the number of products passed to the method.
    }

    private void removeCartModal(){ // Method which removes the cart modal.
        if(!webXDriver.findElement(By.id("reveal-messages")).isDisplayed()){ // Checks if the cart modal is displayed.
            webXDriver.findElement(By.className("close-reveal-modal")).click(); // If the cart modal is displayed then click it.
        }
    }

    public void cartHas1Product(){ // Method which removes single product from the cart.
        webXDriver.navigate().to(scanLocators.SCAN_SHOPPING_CART_URL); // Navigates to the carts URL
        webXDriver.findElement(By.className("btn-remove2")).click(); // Clicks the button to remove single product.
        webXDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); // Tells the driver to wait a second in order to give the next page adequate time to load.
    }

}

