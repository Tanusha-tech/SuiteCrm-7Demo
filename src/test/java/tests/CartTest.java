package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class CartTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Добавление товара в корзину",
            description = "Добавление товара в корзину",
            groups = {"cart", "smoke"},
            retryAnalyzer = Retry.class)
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

    @Test(testName = "Открытие страницы Checkout",
            description = "Открытие страницы Checkout",
            groups = {"cart", "smoke"},
            retryAnalyzer = Retry.class)
    public void checkCheckoutButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton();
        productsPage.clickShoppingCart();
        softAssert.assertEquals(cartPage.getTitle(),
                "Your Cart","Переход на страницу не выполнен!");
        cartPage.clickCheckoutButton();
        softAssert.assertEquals(checkoutPage.getTitle(),
                "Checkout: Your Information", "Переход на страницу не был выполнен!");
        softAssert.assertAll();
    }

    @Test(testName = "Продолжение покупок(Continue Shopping)",
            description = "Продолжение покупок(Continue Shopping)",
            groups = {"cart", "smoke"},
            retryAnalyzer = Retry.class)
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

