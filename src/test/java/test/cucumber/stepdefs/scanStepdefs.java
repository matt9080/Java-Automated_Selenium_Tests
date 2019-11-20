package test.cucumber.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.scanPageObjects;

import java.net.MalformedURLException;
import java.util.List;

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
        scanPageObjects = null;
    }

    @Given("I am a user on the website")
    public void userOnSite()  {
        try {
            scanPageObjects.goToHomePage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @When("I log in using valid credentials")
    public void loginValid() throws MalformedURLException, InterruptedException {
        scanPageObjects.goToLoginPage();
        scanPageObjects.login(EMAIL_VALID,PASSWORD_VALID);
    }

    @Then("I should be logged in")
    public void loginVerification(){
        assertTrue(scanPageObjects.isUserCorrectlyLoggedIn());
        scanPageObjects.quit();
    }
    @Then("I should not be logged in")
    public void incorrectLoginCheck(){
        assertTrue(scanPageObjects.incorrectLoginVerification());
    }
    @When("I log in using invalid credentials")
    public void loginInvalid() throws InterruptedException {
        scanPageObjects.login(EMAIL_INVALID,PASSWORD_INVALID);
    }

    @Given("I am a logged in user on the website")
    public void loggedInUserOnSire() throws MalformedURLException, InterruptedException {
        scanPageObjects.goToLoginPage();
        scanPageObjects.login(EMAIL_VALID,PASSWORD_VALID);
        scanPageObjects.goToHomePage();
    }

    @When("I search for a product")
    public void productSearch(){
        scanPageObjects.searchProduct(PRODUCT_TO_SEARCH);
    }

    @And("I select the first product in the list")
    public void selectProduct() throws InterruptedException {
        scanPageObjects.getFirstProduct().click();
    }

    @Then("I should see the product details")
    public void verifyProductDetails() {
        assertTrue(scanPageObjects.isProductPage());
        scanPageObjects.quit();
    }

    @And("my shopping cart is empty")
    public void shoppingCartEmpty(){
        scanPageObjects.isCartEmpty();
    }

    @When("I view the details of a product")
    public void productDetails(){

    }
}
