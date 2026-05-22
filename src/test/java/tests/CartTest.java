package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Retry;

import static org.testng.Assert.assertEquals;


public class CartTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Отображение товара в корзине",
            description = "Отображение товара в корзине",
            groups = {"cart", "smoke"},
            retryAnalyzer = Retry.class)
    @Description("Отображение товара в корзине")
    @Epic("E2E")
    @Feature("Cart")
    @TmsLink("Cart-2")
    @Owner("Lyamkina Tatyana")
    public void checkCartIsNotEmpty() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton();
        productsPage.clickShoppingCart();
        softAssert.assertEquals(cartPage.getTitle(),
                "Your Cart", "Переход на страницу не выполнен!");
        softAssert.assertTrue(cartPage.isCartNotEmpty(), "Корзина пуста!");
        softAssert.assertAll();
    }

    @Test(testName = "Проверка перехода в Cart",
            description = "Проверка перехода в Cart",
            groups = {"product", "smoke"},
            retryAnalyzer = Retry.class)
    @Description("Проверка перехода в Cart из Product")
    @Epic("E2E")
    @Feature("Cart")
    @TmsLink("Cart-2")
    @Owner("Lyamkina Tatyana")
    public void checkShoppingCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(),
                "Products", "Переход на страницу не выполнен");
        productsPage.clickShoppingCart();
        assertEquals(cartPage.getTitle(), "Your Cart",
                "Переход на страницу не выполнен!");
    }
}

