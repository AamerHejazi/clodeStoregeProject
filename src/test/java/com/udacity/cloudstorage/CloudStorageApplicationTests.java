package com.udacity.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	//TODO /* you should create this user and password in the bellow function successLogin() to run this test cases */
	@LocalServerPort
	private Integer port;
	public  String baseURL;
	private static WebDriver driver;


	private static CredentialsTest credentialsTest;
	private static LoginPageTest loginPageTest;
	private static NotesTest notesTest;
	private static SignupPageTest signupPageTest;

	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		driver = new ChromeDriver();
		credentialsTest = new CredentialsTest(driver);
		loginPageTest = new LoginPageTest(driver);
		notesTest = new NotesTest(driver);
		signupPageTest = new SignupPageTest(driver);
		baseURL =  "http://localhost:" + this.port;
	}

	@AfterEach
	public void  afterEach() throws InterruptedException {

		if (driver != null) {
			//Thread.sleep(10000);
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get(baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void invalidUserOrPassword(){
		driver.get(baseURL + "/login");
		loginPageTest.getInputUsername().sendKeys("Khaled");
		loginPageTest.getInputPassword().sendKeys("123456");
		loginPageTest.getSubmitLoginButton().click();
		Assertions.assertEquals("Invalid username or password",loginPageTest.getErrorMsg().getText());
	}

	@Test
	public void successUserAccess() throws InterruptedException {
		successLogin();
		Assertions.assertEquals(baseURL+"/home",driver.getCurrentUrl());
		loginPageTest.getLogoutButton().click();
		Thread.sleep(5000);
	}

	@Test
	public void unauthorizedAccess(){
		driver.get(baseURL + "/home");
		Assertions.assertEquals(baseURL+"/login",driver.getCurrentUrl());
	}

	@Test
	public void unauthorizedAccessToHomeAfterLogout(){
		successLogin();
		loginPageTest.getLogoutButton().click();
		System.out.println(driver.getCurrentUrl());
		Assertions.assertNotEquals(driver.getCurrentUrl(), baseURL + "/home");
	}

	/*********************************** test sign up page ***************************************************/
	@Test																									 //
	public void getSignupPage(){																			 //
		driver.get(baseURL + "/signup");																	 //
		Assertions.assertEquals("Sign Up", driver.getTitle());										 //
	}																										 //
																											 //
	@Test																									 //
	public void signup(){																					 //
		driver.get(baseURL + "/signup");																	 //
		signupPageTest.createUserName(port);																 //
		Assertions.assertEquals("You successfully signed up! Please continue to the login page.",
				signupPageTest.getSuccessMessage());														 //
	}																										 //
	/********************************* End test sign up page *************************************************/

	/*********************************** test Credential page ************************************************/

	@Test
	public void createCredential() throws InterruptedException {
		successLogin();
		credentialsTest.createNewCredential("https://www.google.com.sa", "Aamer", "123456789");
		//TODO you can try with another new credential but you should add same url and user above to bellow  assertTrue
		Assertions.assertTrue(credentialsTest.isCredentialExist("https://www.google.com.sa", "Aamer"));
	}

	@Test
	public void editCredential() throws InterruptedException {
		successLogin();
		credentialsTest.editFirstCredential();
		Assertions.assertEquals("changedUsername", credentialsTest.checkEditedUsername());

	}

	@Test
	public void deleteCredential() throws InterruptedException {
		successLogin();
	 	Assertions.assertEquals("Success! Credential deleted successfully",credentialsTest.deleteFirstCredential("https://www.google.com.sa", "Aamer"));
	}

	/********************************* End test note page **********************************************/
	@Test
	public void createNote() throws InterruptedException {
		successLogin();
		Assertions.assertEquals("Success! New note added successfully",notesTest.createNewNote());
	}


	@Test
	public void deleteNote() throws InterruptedException{
		successLogin();
		Assertions.assertEquals("Success! Note deleted successfully", notesTest.deleteExistNote());
	}


	@Test
	public void editNote() throws InterruptedException{
		successLogin();
		Assertions.assertEquals("Success! Note updated successfully",notesTest.editExistNote());
	}
	/*********************************** test note page ************************************************/
	
	public void successLogin(){
		driver.get(baseURL + "/login");
		//TODO you should create this user to complete the test
		loginPageTest.getInputUsername().sendKeys("ahijazi");
		loginPageTest.getInputPassword().sendKeys("332120741");
		loginPageTest.getSubmitLoginButton().click();
	}



}