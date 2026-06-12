package step;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginStep {

    WebDriver driver;
    LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }
    @Step("Авторизация пользователя")
    public void auth(String user, String password) {
        loginPage.open()
                .login(user, password);
    }
}
