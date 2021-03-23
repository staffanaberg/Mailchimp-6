package Mailchimp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepDefinitions {

    WebDriver driver;
    Generator gen;


    @Given("Navigated to the site")
    public void navigated_to_the_site() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        driver.manage().window().maximize();

        Thread.sleep(1000);
        driver.quit();
    }

    @Given("write a username")
    public void write_a_username() {
        gen = new Generator();
        System.out.println(gen.unixTime() + gen.letter() + "@hotmail.com");

    }

    @Given("write a password")
    public void write_a_password() {

    }

    @When("press register")
    public void press_register() {

    }

    @Then("the user is register")
    public void the_user_is_register() {

    }
}
