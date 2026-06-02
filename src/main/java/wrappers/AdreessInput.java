package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class AdreessInput {

    WebDriver driver;
    String addressLabel;
    String label;

    public AdreessInput(WebDriver driver, String addressLabel, String label) {
        this.driver = driver;
        this.addressLabel = addressLabel;
        this.label = label;
    }

    public void write(String text) {
        log.info("Writing '{}' in to '{}' in '{}'", text, label, addressLabel);
        driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]/ancestor::div[contains(@class, 'row-item')]" +
                "//*[contains(text(),'%s')]/ancestor::tr//input", addressLabel, label))).sendKeys(text);
    }
}

