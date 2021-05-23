package com.udacity.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class NotesTest {
    public NotesTest(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "add-new-note-button")
    private WebElement addNewNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTittleTextBox;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionTextBox;

    @FindBy(id = "submit-create-new-note")
    private WebElement submitCreateNewNote;

    @FindBy(id = "note-success-message")
    private WebElement noteSuccessMessage;

    @FindBy(id = "note-error-msg")
    private WebElement noteErrorMsg;

    @FindBy(id = "delete-note-button")
    private WebElement deleteNoteButton;

    @FindBy(id = "delete-note-confirm-button")
    private WebElement deleteNoteConfirmButton;

    @FindBy(id = "update-note-button")
    private WebElement updateNoteButton;

    @FindBy(id = "edit-note-title")
    private WebElement editNoteTitleTextBox;

    @FindBy(id = "edit-note-description")
    private WebElement editNoteDescriptionTextBox;

    @FindBy(id = "save-note-change-button")
    private WebElement saveNoteChangeButton;

    private WebElement getNavNotesTab() {
        return navNotesTab;
    }

    private WebElement getAddNewNoteButton() {
        return addNewNoteButton;
    }

    private WebElement getNoteTittleTextBox() {
        return noteTittleTextBox;
    }

    private WebElement getNoteDescriptionTextBox() {
        return noteDescriptionTextBox;
    }

    private WebElement getSubmitCreateNewNote() {
        return submitCreateNewNote;
    }

    private WebElement getNoteSuccessMessage() {
        return noteSuccessMessage;
    }

    private WebElement getNoteErrorMsg() {
        return noteErrorMsg;
    }

    public WebElement getDeleteNoteButton() {
        return deleteNoteButton;
    }

    public WebElement getDeleteNoteConfirmButton() {
        return deleteNoteConfirmButton;
    }

    public WebElement getUpdateNoteButton() {
        return updateNoteButton;
    }

    public WebElement getEditNoteTitleTextBox() {
        return editNoteTitleTextBox;
    }

    public WebElement getEditNoteDescriptionTextBox() {
        return editNoteDescriptionTextBox;
    }

    public WebElement getSaveNoteChangeButton() {
        return saveNoteChangeButton;
    }

    public String createNewNote() throws InterruptedException {

        getNavNotesTab().click();
        Thread.sleep(3000);
        getAddNewNoteButton().click();
        Thread.sleep(3000);
        getNoteTittleTextBox().sendKeys("This is an automatic note tittle");
        getNoteDescriptionTextBox().sendKeys("This is an automatic note description");
        getSubmitCreateNewNote().click();
        Thread.sleep(3000);
        getNavNotesTab().click();
        Thread.sleep(3000);
        if(!getNoteSuccessMessage().getText().isEmpty()){
            return getNoteSuccessMessage().getText();
        }else {
            return getNoteErrorMsg().getText();
        }
    }

    public String deleteExistNote() throws InterruptedException {
        getNavNotesTab().click();
        Thread.sleep(3000);
        getDeleteNoteButton().click();
        Thread.sleep(3000);
        getDeleteNoteConfirmButton().click();
        getNavNotesTab().click();
        Thread.sleep(3000);
        if(!getNoteSuccessMessage().getText().isEmpty()){
            return getNoteSuccessMessage().getText();
        }else {
            return "false";
        }
    }

    public String editExistNote() throws InterruptedException {
        getNavNotesTab().click();
        Thread.sleep(3000);
        getUpdateNoteButton().click();
        Thread.sleep(3000);
        getEditNoteTitleTextBox().clear();
        getEditNoteTitleTextBox().sendKeys("updatedNoteTittle");
        getEditNoteDescriptionTextBox().clear();
        getEditNoteDescriptionTextBox().sendKeys("updatedNoteDescription");
        getSaveNoteChangeButton().click();
        Thread.sleep(3000);
        getNavNotesTab().click();

        if(!getNoteSuccessMessage().getText().isEmpty()){
            return getNoteSuccessMessage().getText();
        }else {
            return "false";
        }
    }

}
