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

}

