package com.udacity.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPageTest {



    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-login-button")
    private WebElement submitLoginButton;

    @FindBy(id = "error-msg")
    private WebElement errorMsg;

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    public LoginPageTest(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public WebElement getInputUsername() {
        return inputUsername;
    }

    public WebElement getInputPassword() {
        return inputPassword;
    }

    public WebElement getSubmitLoginButton() {
        return submitLoginButton;
    }

    public WebElement getErrorMsg() {
        return errorMsg;
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }
}
