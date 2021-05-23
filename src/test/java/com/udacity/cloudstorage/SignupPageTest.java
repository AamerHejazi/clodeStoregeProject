package com.udacity.cloudstorage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class SignupPageTest {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "success-msg")
    private WebElement successMsg;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public SignupPageTest(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public WebElement getInputFirstName() {
        return inputFirstName;
    }

    public WebElement getInputLastName() {
        return inputLastName;
    }

    public WebElement getInputUsername() {
        return inputUsername;
    }

    public WebElement getInputPassword() {
        return inputPassword;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getSuccessMsg() {
        return successMsg;
    }

    public void createUserName(Integer userNumber){
        this.getInputFirstName().sendKeys("Mohammed");
        this.getInputLastName().sendKeys("Ahmed");
        System.out.println("amer"+userNumber);
        this.getInputUsername().sendKeys("amer"+userNumber);
        this.getInputPassword().sendKeys("123321");
        this.getSubmitButton().click();

    }

    public String getSuccessMessage(){
        return getSuccessMsg().getText();
    }
}
