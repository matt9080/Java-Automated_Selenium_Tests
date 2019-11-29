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

    scanPageObjects scanPageObjects;

    //ACOUNT - VALID
    final String EMAIL_VALID = "uomscanwebtesting@yopmail.com";
    final String PASSWORD_VALID = "thisPassword1234";

    //ACOUNT - INVALID
    final String EMAIL_INVALID = "baduomscanwebtesting@yopmail.com";
    final String PASSWORD_INVALID = "badthisPassword1234";

    //PRODUCT
    final String PRODUCT_TO_SEARCH = "apple";

    @Before
    public void before(){
        scanPageObjects = new scanPageObjects();
    }

    @After
    public void after(){
        scanPageObjects.quit();
        scanPageObjects = null;
    }

    @Given("I am a user on the website")
    public void userOnSite(){
        scanPageObjects.navigateToHome();
    }

    @When("I log in using valid credentials")
    public void loginCorrect(){
        scanPageObjects.Login(EMAIL_VALID,PASSWORD_VALID);
    }

    @Then("I should be logged in")
    public void verifyLogin(){
        assertTrue(scanPageObjects.loginURLCheck());
    }

    @When("I log in using invalid credentials")
    public void loginIncorrect(){
        scanPageObjects.Login(EMAIL_INVALID,PASSWORD_INVALID);
    }

    @Then("I should not be logged in")
    public void verifyLoginIncorrect(){
        assertTrue(scanPageObjects.loginInvalidURLCheck());
    }

    @Given("I am a logged in user on the website")
    public void loggedInUser(){
        loginCorrect();
    }
    @When("I search for a product")
    public void searchForAProduct(){
        scanPageObjects.searchForProduct(PRODUCT_TO_SEARCH);
    }
    @And("I select the first product in the list")
    public void selectFirstProduct(){
        scanPageObjects.getNClickFirstProduct();
    }

    @Then("I should see the product details")
    public void verifyProductDetails(){
        assertTrue(scanPageObjects.verifyProductPage());
    }

    @And("my shopping cart is empty")
    public void isCartEmpty(){
        scanPageObjects.navigateToCart();
        scanPageObjects.ifCartEmpty();
    }

    @When("I view the details of a product")
    public void viewProductDetails(){
        scanPageObjects.navigateToProduct();
    }

    @And("I choose to buy the product")
    public void buyProduct(){
        scanPageObjects.addProductToCart();
    }

    @Then("my shopping cart should contain 1 item")
    public void cartShouldContainSingleItem(){
        assertTrue(scanPageObjects.checkIfCartContainsSingleItem());
    }

    @When("I add {int} products to my shopping cart")
    public void addNumberOfItems(int noProducts) {
        scanPageObjects.addAmountOfProductsToCart(noProducts);
    }

    @Then("my shopping cart should contain {int} items")
    public void verifyCartAmount(int noProducts){
        assertTrue(scanPageObjects.checkCartItems(noProducts));

    }

    @And("my shopping cart has 2 products")
    public void cartHas2Items(){
        isCartEmpty();
        scanPageObjects.addAmountOfProductsToCart(2);
    }

    @When("I remove the first product in my cart")
    public void removeSingleProductFromCart(){
        scanPageObjects.cartHas1Product();
    }


}
