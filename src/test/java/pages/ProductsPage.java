package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private static final By TITLE = By.xpath("//*[@data-test='title']");
    private static final By SHOPPING_CART = By.xpath("//*[@data-test='shopping-cart-link']");
    private static final By ADD_TO_CART = By.xpath("//*[@data-test='add-to-cart-sauce-labs-backpack']");
    private final By REMOVE_BUTTON = By.xpath("//*[@data-test='remove-sauce-labs-backpack']");
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара") //поменять на новый метод addToCart
    public void clickAddToCartButton() {
        driver.findElement(ADD_TO_CART).click();
    }

    public String getRemoveButtonTitle() {
        return driver.findElement(REMOVE_BUTTON).getText();
    }

    @Step("Переход в Cart из Product")
    public void clickShoppingCart() {
        driver.findElement(SHOPPING_CART).click();
    }

    @Step("Добавление товара '{product}' в корзину")
    public void addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
    }
}