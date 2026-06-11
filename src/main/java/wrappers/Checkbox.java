package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class Checkbox {

    WebDriver driver;
    String label;

    public Checkbox(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void clickCheckbox() {
        log.info("Click checkbox '{}'", label);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                driver.findElement(By.xpath(String.format("//input[@type='checkbox' and contains(@name, '%s')]", label))));
    }

    public void verifySelectedCheckbox() {
        log.info("Checkbox '{}' is already selected", label);
        assertTrue(driver.findElement(By.xpath(String.format("//input[@type='checkbox' and contains(@name, '%s')]", label)))
                .isSelected());
    }

    public void verifyUnselectedCheckbox() {
        log.info("Checkbox '{}' is already unselected", label);
        assertFalse(driver.findElement(By.xpath(String.format("//input[@type='checkbox' and contains(@name, '%s')]", label)))
                .isSelected());
    }

}
