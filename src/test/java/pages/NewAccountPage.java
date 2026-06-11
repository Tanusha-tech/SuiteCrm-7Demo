package pages;

import dto.Account;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import wrappers.*;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class NewAccountPage extends BasePage {

    private final By TITLE_ACCOUNT = By.xpath("//div[@class='moduleTitle']//h2");
    private final By ERROR_NAME = By.xpath("//div[contains(text(),'Missing required field: Name')]");

    public NewAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NewAccountPage isPageOpened() {
        assertTrue(driver.findElement(By.xpath("//title[contains(text(),'CREATE') and contains(text(), 'Accounts')]"))
                .isEnabled());
        return this;
    }

    @Override
    @Step("Открытие страницы \"CREATE ACCOUNT\"")
    public NewAccountPage open() {
        driver.get("https://demo.suiteondemand.com/index.php?module=Accounts&action=EditView&return_module=" +
                "Accounts&return_action=DetailView");
        return this;
    }

    public String getTitleAccount() {
        return driver.findElement(TITLE_ACCOUNT).getText();
    }

    public String getErrorName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ERROR_NAME)
        );
        return errorElement.getText().trim();
    }

    @Step("Заполнение карточки ACCOUNT")
    public NewAccountPage addNewAccount(Account account) {
        log.info("Adding new account with name: '{}'", account.getName());
        new Input(driver, "Name").write(account.getName());
        new Input(driver, "Office Phone").write(account.getNumberPhone());
        new Input(driver, "Fax").write(account.getFax());
        new Input(driver, "Website").write(account.getWebsite());
        new AdrdeessTextArea(driver, "Billing Address", "Street").write(account.getStreet());
        new AdrdeessTextArea(driver, "Shipping Address", "Street").write(account.getStreet());
        new AdreessInput(driver, "Billing Address","City").write(account.getCity());
        new AdreessInput(driver, "Billing Address","State").write(account.getRegion());
        new AdreessInput(driver, "Billing Address","Postal Code").write(account.getPostal());
        new AdreessInput(driver, "Billing Address","Country").write(account.getCountry());
        new Textarea(driver,"Description").write(account.getDescription());
        new Dropdown(driver, "Type").select(account.getType());
        new Dropdown(driver, "Industry").select(account.getIndustry());
        return this;
    }

    @Step("Проверка чекбоксов на активность")
    public NewAccountPage verifySelectCheckbox() {
        log.info("Verify select checkbox");
        new Checkbox(driver, "OptOut").verifySelectedCheckbox();
        new Checkbox(driver, "Invalid").verifySelectedCheckbox();
        return this;
    }

    @Step("Проверка чекбоксов на неактивность")
    public NewAccountPage verifyUnselectCheckbox() {
        log.info("Verify unselect checkbox");
        new Checkbox(driver, "OptOut").verifyUnselectedCheckbox();
        new Checkbox(driver, "Invalid").verifyUnselectedCheckbox();
        return this;
    }

    @Step("Заполнение чекбоксов")
    public NewAccountPage selectCheckbox() {
        log.info("Choose checkbox for new account");
        new Checkbox(driver, "OptOut").clickCheckbox();
        new Checkbox(driver, "Invalid").clickCheckbox();
        return this;
    }

    @Step("Сохранение карточки ACCOUNT")
    public NewAccountPage clickSave() {
        log.info("Save new account");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",  driver.findElement(By.id("SAVE")));
        return this;
    }

    @Step("Проверка после создания карточки ACCOUNT")
    public void verifyAccount(Account account) {
        log.info("Verify new account '{}' = '{}'", getTitleAccount().toUpperCase(), account.getName().toUpperCase());
        assertEquals(getTitleAccount().toUpperCase(), account.getName().toUpperCase(),
                "Аккаунт не создан или некорректное имя аккаунта");
    }

    @Step("Проверка если не заполнены обязательные поля")
    public void verifyAccountNegative() {
        log.info("Checking the empty field warning '{}'", getErrorName());
        assertEquals(getErrorName(), "Missing required field: Name",
                "Нотификация не отображается или поле заполнено");
    }
}
