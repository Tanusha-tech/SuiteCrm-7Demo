package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Retry;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Проверка добавления товара на Product",
            description = "Проверка добавления товара на Product",
            groups = {"product", "smoke"},
            retryAnalyzer = Retry.class)
    @Description("Проверка добавления товара на Product")
    @Epic("E2E")
    @Feature("Product")
    @TmsLink("Product-1")
    @Owner("Lyamkina Tatyana")
    public void checkAddToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(),
                "Products", "Переход на страницу не выполнен!");
        productsPage.addToCart("Sauce Labs Backpack");
        assertEquals(productsPage.getRemoveButtonTitle(),
                "Remove", "Продукт не был добавлен!");

    }

    @Test(testName = "Проверка добавления Fake товара на Product",
            description = "Проверка добавления Fake товара на Product",
            groups = {"product", "smoke"},
            retryAnalyzer = Retry.class)
    @Description("Проверка добавления Fake товара на Product")
    @Epic("E2E")
    @Feature("Product")
    @TmsLink("Product-1.1")
    @Owner("Lyamkina Tatyana")
    public void checkAddToCartFake() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(),
                "Products", "Переход на страницу не выполнен!");
        productsPage.addToCart("Рюкзак");
        assertEquals(productsPage.getRemoveButtonTitle(),
                "Remove", "Продукт не был добавлен!");

    }

    @Test(testName = "Продолжение покупок(Continue Shopping)",
            description = "Продолжение покупок(Continue Shopping)",
            groups = {"cart", "smoke"},
            retryAnalyzer = Retry.class)
    @Description("Продолжение покупок(Continue Shopping) из Cart")
    @Epic("E2E")
    @Feature("Product")
    @TmsLink("Product-3")
    @Owner("Lyamkina Tatyana")
    public void checkContinueShoppingButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton();
        productsPage.clickShoppingCart();
        softAssert.assertEquals(cartPage.getTitle(),
                "Your Cart", "Переход на страницу не выполнен!");
        cartPage.clickContinueShoppingButton();
        softAssert.assertEquals(productsPage.getTitle(),
                "Products", "Переход на страницу не выполнен!");
        softAssert.assertAll();
    }
}
