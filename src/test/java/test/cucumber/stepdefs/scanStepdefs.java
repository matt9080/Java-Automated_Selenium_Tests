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
    @When(" I search for a product")
    public void searchForAProduct(){

    }
}
