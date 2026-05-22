package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @Test(testName = "Проверка добавления товара на Product",
            description = "Проверка добавления товара на Product",
            groups = {"product", "smoke"},
            retryAnalyzer = Retry.class)
    public void checkAddToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(),
                "Products", "Переход на страницу не выполнен!");
        productsPage.clickAddToCartButton();
        assertEquals(productsPage.getRemoveButtonTitle(),
                "Remove", "Продукт не был добавлен!");

    }

    @Test(testName = "Проверка перехода в Cart",
            description = "Проверка перехода в Cart",
            groups = {"product", "smoke"},
            retryAnalyzer = Retry.class)
    public void checkShoppingCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(),
                "Products", "Переход на страницу не выполнен");
        productsPage.clickShoppingCart();
        assertEquals(cartPage.getTitle(), "Your cart",
                "Переход на страницу не выполнен!");
    }
}
