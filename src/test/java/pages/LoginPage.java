package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@Log4j2
public class LoginPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @FindBy(id = "user_name")
    private WebElement loginField;
    @FindBy(id = "username_password")
    private WebElement passwordField;
    @FindBy(name = "Login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public LoginPage open() {
        log.info("LoginPage opening");
        driver.get("https://demo.suiteondemand.com/index.php?action=Login&module=Users");
        return this;
    }


    @Override
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginField));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't opened");
        }
        return this;
    }

    public void login(String user, String password) {
        log.info("Login in wit credential: '{}', '{}'", user, password);
        loginField.sendKeys(user);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
