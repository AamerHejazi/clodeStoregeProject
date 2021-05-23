package com.udacity.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.ListIterator;


public class CredentialsTest {
    private final WebDriver driver;

    @FindBy(id = "add-credential-button")
    private WebElement addCredentialButton;

    @FindBy(id = "credential-id")
    private WebElement credentialId;

    @FindBy(id = "credential-key")
    private WebElement credentialKey;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "submit-credential")
    private WebElement submitCredential;

    @FindBy(id = "edit-credential-button")
    private WebElement selectFirstEditCredential;

    @FindBy(id = "edit-credential-username")
    private WebElement editCredentialUsername;

    @FindBy(id = "edit-credential-password")
    private WebElement editCredentialPassword;

    @FindBy(id = "edit-credential-url")
    private WebElement editCredentialUrl;

    @FindBy(id = "submit-edit-credential")
    private WebElement submitEditedCredential;

    @FindBy(id = "close-edit-credential")
    private WebElement closeEditCredential;

    @FindBy(id = "table-credential-user")
    private WebElement tableCredentialUser;

    @FindBy(id = "table-credential-pass")
    private WebElement tableCredentialPass;

    @FindBy(id = "table-credential-url")
    private WebElement tableCredentialUrl;

    @FindBy(css = "#table-credential-url")
    private List<WebElement> tableCredentialsUrl;

    @FindBy(css = "#table-credential-user")
    private List<WebElement> tableCredentialsUser;


    @FindBy(id = "deleteCredentialSubmit")
    private WebElement deleteCredentialSubmit;

    @FindBy(css = "#credentials-table-body > tr")
    private List<WebElement> tableCredentialRecords;

    @FindBy(id = "cred-success-msg")
    private WebElement successMsg;

    public CredentialsTest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getCloseEditCredential() {
        return closeEditCredential;
    }

    public WebElement getAddCredentialButton() {
        return addCredentialButton;
    }

    public WebElement getCredentialId() {
        return credentialId;
    }

    public WebElement getCredentialKey() {
        return credentialKey;
    }

    public WebElement getCredentialUrl() {
        return credentialUrl;
    }

    public WebElement getCredentialUsername() {
        return credentialUsername;
    }

    public WebElement getCredentialPassword() {
        return credentialPassword;
    }

    public WebElement getNavCredentialsTab() {
        return navCredentialsTab;
    }

    public WebElement getSubmitCredential() {
        return submitCredential;
    }

    public WebElement getSelectFirstEditCredential() {
        return selectFirstEditCredential;
    }

    public WebElement getEditCredentialUsername() {
        return editCredentialUsername;
    }

    public WebElement getEditCredentialPassword() {
        return editCredentialPassword;
    }

    public WebElement getEditCredentialUrl() {
        return editCredentialUrl;
    }

    public WebElement getSubmitEditedCredential() {
        return submitEditedCredential;
    }

    public WebElement getTableCredentialUser() {
        return tableCredentialUser;
    }

    public WebElement getTableCredentialPass() {
        return tableCredentialPass;
    }

    public WebElement getTableCredentialUrl() {
        return tableCredentialUrl;
    }

    public List<WebElement> getTableCredentialsUrl() {
        return tableCredentialsUrl;
    }

    public List<WebElement> getTableCredentialsUser() {
        return tableCredentialsUser;
    }

    public List<WebElement> getTableCredentialRecords() {
        return tableCredentialRecords;
    }


    public WebElement getDeleteCredentialSubmit() {
        return deleteCredentialSubmit;
    }

    public WebElement getSuccessMsg() {
        return successMsg;
    }

    public void createNewCredential(String url, String user, String pass) throws InterruptedException {
        Thread.sleep(5000);
        getNavCredentialsTab().click();
        Thread.sleep(3000);
        getAddCredentialButton().click();
        Thread.sleep(3000);
        getCredentialUrl().sendKeys(url);
        getCredentialUsername().sendKeys(user);
        getCredentialPassword().sendKeys(pass);
        getSubmitCredential().click();
    }

    public void editFirstCredential() throws InterruptedException {

        Thread.sleep(3000);
        getNavCredentialsTab().click();
        Thread.sleep(3000);
        getSelectFirstEditCredential().click();

        Thread.sleep(3000);
        //clear old username
        getEditCredentialUsername().clear();
        //set a new user
        getEditCredentialUsername().sendKeys("changedUsername");
        getSubmitEditedCredential().click();
    }

    public String checkNewUser() {
        getNavCredentialsTab().click();
        return getTableCredentialUser().getText();
    }

    public String checkNewPass() {
        return getTableCredentialPass().getText();
    }

    public String checkNewUrl() throws InterruptedException {
        getNavCredentialsTab().click();
        Thread.sleep(3000);
        System.out.println(getTableCredentialUrl().getText());
        return getTableCredentialUrl().getText();
    }

    public String checkEditedUsername() throws InterruptedException {
        getNavCredentialsTab().click();
        Thread.sleep(3000);
        return getTableCredentialUser().getText();

    }

    public String deleteFirstCredential(String urlTobeDeleted, String userTobeDeleted) throws InterruptedException {
        getNavCredentialsTab().click();
        Thread.sleep(3000);

        for (WebElement element : getTableCredentialRecords()) {

            String url = element.findElement(By.id("table-credential-url")).getText();
            String user = element.findElement(By.id("table-credential-user")).getText();

            if (url.equals(urlTobeDeleted) && user.equals(userTobeDeleted)) {
                Thread.sleep(3000);
                element.findElement(By.id("delete-credential-button")).click();
                Thread.sleep(3000);
                getDeleteCredentialSubmit().click();
                Thread.sleep(3000);
                getNavCredentialsTab().click();
                Thread.sleep(3000);
                return getSuccessMsg().getText();
            }
        }
        return "false";
    }

    public boolean isCredentialExist(String url, String username) throws InterruptedException {
        Thread.sleep(3000);
        getNavCredentialsTab().click();
        Thread.sleep(3000);

        ListIterator<WebElement> urlIterator = getTableCredentialsUrl().listIterator();
        ListIterator<WebElement> userIterator = getTableCredentialsUser().listIterator();

        String currentUrl;
        String currentUsername;
        while (urlIterator.hasNext() && userIterator.hasNext()) {

            currentUrl = urlIterator.next().getText();
            currentUsername = userIterator.next().getText();

            if (currentUrl.equals(url) && currentUsername.equals(username)) {
                return true;
            }
        }
        return false;
    }


}
