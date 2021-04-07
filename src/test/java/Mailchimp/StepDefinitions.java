package Mailchimp;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    WebDriver driver;
    Generator gen;
    String generatedName;

    @Before
    public void beforeTheTest(){
        // Create generated name
        gen = new Generator();
        generatedName = gen.unixTime() + gen.letter();
    }

    @Given("Navigated to the site")
    public void navigated_to_the_site()  {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        driver.manage().window().maximize();
    }


    // EMAIL
    @And("write an email")
    public void write_an_email()  {
        WebElement emailBox = driver.findElement(By.cssSelector("input[type=email][id=email]"));
        emailBox.sendKeys(generatedName + "@hotmail.com");
    }

    @And("write an no email")
    public void writeNoEmail() {
    }


    // USERNAME
    @Given("write an username")
    public void write_an_username() {
        WebElement usernameBox = driver.findElement(By.cssSelector("input[type=text][id=new_username]"));
        usernameBox.sendKeys(generatedName);
    }

    @And("write an username with {int} characters")
    public void writeALongUsernameCharacters(int hundreds) {
        WebElement usernameBox = driver.findElement(By.cssSelector("input[type=text][id=new_username]"));
        usernameBox.sendKeys(gen.letter().repeat(hundreds));
    }

    @And("write an taken username")
    public void writeAnAlreadyTakenUsername() {
        WebElement usernameBox = driver.findElement(By.cssSelector("input[type=text][id=new_username]"));
        usernameBox.sendKeys("takenUsername");
    }


    // PASSWORD
    @Given("write a password")
    public void write_a_password()  {
        WebElement passwordBox = driver.findElement(By.cssSelector("input[type=password][id=new_password]"));
        passwordBox.sendKeys(generatedName + "A!");
    }


    // SIGN UP BUTTON
    @When("press Sign Up button")
    public void press_sign_up_button() {
        WebElement signUpButton = driver.findElement(By.cssSelector("input[type=password][id=new_password]"));
        signUpButton.sendKeys(Keys.ENTER);
    }


    // MESSAGE
    @Then("{string} is visible for user")
    public void messageIsVisibleForUser(String message) {
        WebElement errorMessage = driver.findElement(By.cssSelector("span[class='invalid-error']"));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(errorMessage));

        String actual = errorMessage.getAttribute("outerText");
        assertEquals(message, actual);
    }

    @Then("approved message is visible for user")
    public void messageIsVisibleForUser() {
        WebElement approvedMessage = driver.findElement(By.cssSelector("p[class='margin-bottom--lv5']"));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(approvedMessage));

        String actual = approvedMessage.getAttribute("outerText");
        String expected = "We’ve sent a message to " + generatedName + "@hotmail.com" + " with a link to activate your account.";
        assertEquals(expected, actual);
    }

    @After
    public void afterTheTest() {
        driver.quit();
    }
}
