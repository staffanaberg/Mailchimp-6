package Mailchimp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    WebDriver driver;
    Generator gen;
    String username;

    @Given("Navigated to the site")
    public void navigated_to_the_site() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        driver.manage().window().maximize();
    }

    @And("write an email")
    public void write_an_email() throws InterruptedException {
        // Create user
        gen = new Generator();
        username = gen.unixTime() + gen.letter();

        WebElement emailBox = driver.findElement(By.cssSelector("input[type=email][id=email]"));
        emailBox.sendKeys(username + "@hotmail.com");
    }

    @Given("write an username")
    public void write_an_username() throws InterruptedException {
        WebElement usernameBox = driver.findElement(By.cssSelector("input[type=text][id=new_username]"));
        usernameBox.sendKeys(username);
    }

    @Given("write a password")
    public void write_a_password() throws InterruptedException {
        WebElement passwordBox = driver.findElement(By.cssSelector("input[type=password][id=new_password]"));
        passwordBox.sendKeys(username + "A!");
    }

    @When("press Sign Up button")
    public void press_sign_up_button() throws InterruptedException {
        WebElement signUpButton = driver.findElement(By.cssSelector("input[type=password][id=new_password]"));
        signUpButton.sendKeys(Keys.ENTER);
    }

    @Then("the user is register")
    public void the_user_is_register() throws InterruptedException {
        Thread.sleep(3000);
        WebElement check = driver.findElement(By.cssSelector("p[class='margin-bottom--lv5']"));
        String actual = check.getAttribute("outerText");
        String expected = "We’ve sent a message to " + username + "@hotmail.com" + " with a link to activate your account.";
        assertEquals(expected, actual);
        driver.quit();
    }

    @And("write a long username, {int} characters")
    public void writeALongUsernameCharacters(int hundreds) {
        WebElement usernameBox = driver.findElement(By.cssSelector("input[type=text][id=new_username]"));
        usernameBox.sendKeys(gen.letter().repeat(hundreds));
    }

    @Then("too long username error message shows")
    public void tooLongUsernameErrorMessageShows() throws InterruptedException {
        Thread.sleep(3000);
        WebElement errorMessage = driver.findElement(By.cssSelector("span[class='invalid-error']"));
        String actual = errorMessage.getAttribute("outerText");
        String expected = "Enter a value less than 100 characters long";
        assertEquals(expected, actual);
        driver.quit();
    }

    @And("write an already taken username")
    public void writeAnAlreadyTakenUsername() {
        WebElement usernameBox = driver.findElement(By.cssSelector("input[type=text][id=new_username]"));
        usernameBox.sendKeys("takenUsername");
    }


    @Then("name already exists error message shows")
    public void nameAlreadyExistsErrorMessageShows() throws InterruptedException {
        WebElement errorMessage = driver.findElement(By.cssSelector("span[class='invalid-error']"));
        String actual = errorMessage.getAttribute("outerText");
        String expected = "Another user with this username already exists. Maybe it's your evil twin. Spooky.";
        assertEquals(expected, actual);
        driver.quit();
    }

    @And("write no email")
    public void writeNoEmail() {
        gen = new Generator();
        username = gen.unixTime() + gen.letter();
    }

    @Then("no email error message shows")
    public void noEmailErrorMessageShows() throws InterruptedException {
        Thread.sleep(3000);
        WebElement errorMessage = driver.findElement(By.cssSelector("span[class='invalid-error']"));
        String actual = errorMessage.getAttribute("outerText");
        String expected = "Please enter a value";
        assertEquals(expected, actual);
        driver.quit();
    }
}
