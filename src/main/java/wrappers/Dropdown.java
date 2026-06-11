package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Dropdown {

    WebDriver driver;
    String label;

    public Dropdown(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        log.info("Selecting '{}' inside select '{}'", option, label);
        String SELECT_PATTERN = "//*[contains(text(), '%s')]/following-sibling::div//";
        driver.findElement(By.xpath(String.format(SELECT_PATTERN + "select", label))).click();
        driver.findElement(By.xpath(String.format(SELECT_PATTERN + "option[contains(text(), '%s')]", label, option)))
                .click();
    }
}
