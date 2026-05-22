package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.cssSelector("#user-name");
    private final By PASSWORD_FIELD = By.cssSelector("#password");
    private final By LOGIN_BUTTON = By.cssSelector("#login-button");
    private final By ERROR_MESSAGE = By.xpath("//*[contains(@class, 'error-message')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Login")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Вход в магазин с именем пользователя: '{user}' и паролем: '{password}'")
    public void login(String user, String password) {
        open();
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
