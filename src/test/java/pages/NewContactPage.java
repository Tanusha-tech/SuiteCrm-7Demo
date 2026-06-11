package pages;

import dto.Contact;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import wrappers.*;

import java.time.Duration;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

@Log4j2
public class NewContactPage extends BasePage {
    public NewContactPage(WebDriver driver) {
        super(driver);
    }

    private final By TITLE_CONTACT = By.xpath("//div[@class='moduleTitle']//h2");
    private final By ERROR_LASTNAME = By.xpath("//div[contains(text(),'Missing required field: Last Name')]");

    public String getTitleContact() {
        return driver.findElement(TITLE_CONTACT).getText();
    }

    public String getErrorName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ERROR_LASTNAME)
        );
        return errorElement.getText().trim();
    }

    @Override
    @Step("Открытие страницы \"CREATE CONTACT\"")
    public NewContactPage open() {
        driver.get("https://demo.suiteondemand.com/index.php?module=" +
                "Contacts&action=EditView&return_module=Contacts&return_action=DetailView");
        return this;
    }

    @Override
    public NewContactPage isPageOpened() {
        assertTrue(driver.findElement(By.xpath("//title[contains(text(),'CREATE') " +
                        "and contains(text(), 'Contacts')]"))
                .isEnabled());
        return this;
    }

    @Step("Заполнение карточки CONTACT")
    public NewContactPage addNewContact(Contact contact) {
        log.info("Adding new contact with LastName '{}'", contact.getLastName());
        new Input(driver, "First Name").write(contact.getFirstName());
        new Input(driver, "Last Name").write(contact.getLastName());
        new Input(driver, "Office Phone").write(contact.getOfficePhone());
        new Input(driver, "Mobile").write(contact.getMobile());
        new Input(driver, "Fax").write(contact.getFax());
        new Input(driver, "Job Title").write(contact.getJobTitle());
        new Input(driver, "Department").write(contact.getDepartment());
        new Input(driver, "Account Name").write(contact.getAccountName());
        new AdrdeessTextArea(driver, "Primary Address", "Address").write(contact.getAddress());
        new AdreessInput(driver, "Primary Address", "City").write(contact.getCity());
        new AdreessInput(driver, "Primary Address", "Region").write(contact.getRegion());
        new AdreessInput(driver, "Primary Address", "Postal Code").write(contact.getPostalCode());
        new AdreessInput(driver, "Primary Address", "Country").write(contact.getCountry());
        new Textarea(driver, "Description").write(contact.getDescription());
        new Dropdown(driver, "Lead Source").select(contact.getLeadSource());
        return this;
    }

    @Step("Проверка чекбоксов на активность")
    public NewContactPage verifySelectCheckbox() {
        log.info("Verify select checkbox");
        new Checkbox(driver, "OptOut").verifySelectedCheckbox();
        new Checkbox(driver, "Invalid").verifySelectedCheckbox();
        new Checkbox(driver, "alt").verifySelectedCheckbox();
        return this;
    }

    @Step("Проверка чекбоксов на неактивность")
    public NewContactPage verifyUnselectCheckbox() {
        log.info("Verify unselect checkbox");
        new Checkbox(driver, "OptOut").verifyUnselectedCheckbox();
        new Checkbox(driver, "Invalid").verifyUnselectedCheckbox();
        new Checkbox(driver, "alt").verifyUnselectedCheckbox();
        return this;
    }

    @Step("Заполнение чекбоксов")
    public NewContactPage selectCheckbox() {
        log.info("Choose checkbox for new contact");
        new Checkbox(driver, "OptOut").clickCheckbox();
        new Checkbox(driver, "Invalid").clickCheckbox();
        new Checkbox(driver, "alt").clickCheckbox();
        return this;
    }
    @Step("Сохранение карточки CONTACT")
    public NewContactPage clickSave() {
        log.info("Save new contact");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",  driver.findElement(By.id("SAVE")));
        return this;
    }

    @Step("Проверка после создания карточки CONTACT")
    public void verifyContact(Contact contact) {
        log.info("Verify new contact '{}' = '{}'", getTitleContact().toUpperCase(),
                String.format("%s %s", contact.getFirstName(), contact.getLastName()).toUpperCase());
        assertEquals(getTitleContact().toUpperCase(),
                String.format("%s %s", contact.getFirstName(), contact.getLastName()).toUpperCase(),
                "Контакт не создан или некорректное имя контакт");
    }

    @Step("Проверка если не заполнены обязательные поля")
    public void verifyContactNegative() {
        log.info("Checking the empty field warning '{}'", getErrorName());
        assertEquals(getErrorName(), "Missing required field: Last Name",
                "Нотификация не отображается или поле заполнено");
    }

}
