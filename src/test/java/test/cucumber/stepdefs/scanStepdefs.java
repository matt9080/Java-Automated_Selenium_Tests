package test.cucumber.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import pageObjects.scanPageObjects;



import static junit.framework.TestCase.assertTrue;

public class scanStepdefs {

    private scanPageObjects scanPageObjects;

    //ACCOUNT - VALID
    private final String EMAIL_VALID = "uomscanwebtesting@yopmail.com"; // Valid email address to login
    final String PASSWORD_VALID = "thisPassword1234"; // Valid password to login

    //ACCOUNT - INVALID
    private final String EMAIL_INVALID = "baduomscanwebtesting@yopmail.com"; // Invalid email to simulate failed login attempt.
    private final String PASSWORD_INVALID = "badthisPassword1234"; // Invalid password to simulate failed login attempt.

    //PRODUCT
    private final String PRODUCT_TO_SEARCH = "apple"; // String which contains name of product to be searched, will later br passed to searching method.

    @Before
    public void before(){
        scanPageObjects = new scanPageObjects();
    } // Before each test instantiates new instance of scanPageObjects.

    @After
    public void after(){
        scanPageObjects.quit(); // After each test is completed, calls the pageObject method quit, which tells the driver to quit.
        scanPageObjects = null; // Nullifies scan page objects, to make each test as neutral as possible when running multiple tests after each other.
    }

    @Given("I am a user on the website")
    public void userOnSite(){
        scanPageObjects.navigateToHome();
    } // Given user is on site, this method uses the driver to navigate to the scan home url.

    @When("I log in using valid credentials")
    public void loginCorrect(){
        scanPageObjects.Login(EMAIL_VALID,PASSWORD_VALID);
    } // This method calls the login method which takes 2 parameters ot type string.

    @Then("I should be logged in")
    public void verifyLogin(){
        assertTrue(scanPageObjects.loginURLCheck());
    }

    @When("I log in using invalid credentials")
    public void loginIncorrect(){
        scanPageObjects.Login(EMAIL_INVALID,PASSWORD_INVALID); // Passes correct email and password to the login method.
    }

    @Then("I should not be logged in")
    public void verifyLoginIncorrect(){
        assertTrue(scanPageObjects.loginInvalidURLCheck()); // Verifies that the incorrect URL is present post failing login.

    }

    @Given("I am a logged in user on the website")
    public void loggedInUser(){
        loginCorrect(); // Method which correctly logs in the user.
    }
    @When("I search for a product")
    public void searchForAProduct(){
        scanPageObjects.searchForProduct(PRODUCT_TO_SEARCH); // Searches for a product
    }
    @And("I select the first product in the list")
    public void selectFirstProduct(){
        scanPageObjects.getNClickFirstProduct(); // Gets and clicks the first product it finds.
    }

    @Then("I should see the product details")
    public void verifyProductDetails(){
        assertTrue(scanPageObjects.verifyProductPage()); // Method to verify that the product page is opened.
    }

    @And("my shopping cart is empty")
    public void isCartEmpty(){
        scanPageObjects.navigateToCart(); // Navigates to cart.
        scanPageObjects.ifCartEmpty(); // If cart is not empty, then it empties it.
    }

    @When("I view the details of a product")
    public void viewProductDetails(){
        scanPageObjects.navigateToProduct(); // Navigate to a product.
    }

    @And("I choose to buy the product")
    public void buyProduct(){
        scanPageObjects.addProductToCart(); // Adds product to cart.
    }

    @Then("my shopping cart should contain 1 item")
    public void cartShouldContainSingleItem(){
        assertTrue(scanPageObjects.checkIfCartContainsSingleItem()); // Verifies that the shopping cart returns single item.
    }

    @When("I add {int} products to my shopping cart")
    public void addNumberOfItems(int noProducts) {
        scanPageObjects.addAmountOfProductsToCart(noProducts); // Adds a number of Items to the shopping cart based on the Integer passed in @When
    }

    @Then("my shopping cart should contain {int} items")
    public void verifyCartAmount(int noProducts){
        assertTrue(scanPageObjects.checkCartItems(noProducts)); // Checks that the product contains a number of items based on the Integer passed in @Then

    }

    @And("my shopping cart has 2 products")
    public void cartHas2Items(){
        isCartEmpty(); // Checks if cart is empty, if not empties it.
        scanPageObjects.addAmountOfProductsToCart(2); // Adds 2 products to the cart.
    }

    @When("I remove the first product in my cart")
    public void removeSingleProductFromCart(){
        scanPageObjects.cartHas1Product(); // Removes single product from the cart.
    }


}
