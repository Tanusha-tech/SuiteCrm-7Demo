package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Успешное заполнение страницы Checkout",
            description = "Открытие страницы Checkout",
            groups = {"checkout", "smoke"},
            retryAnalyzer = Retry.class)
    public void isPersonalInfoValid() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickShoppingCart();
        cartPage.clickCheckoutButton();
        assertEquals(checkoutPage.getTitle(),
                "Checkout: Your Information", "Страница не открылась");
        checkoutPage.fillPersonalInfo("Tatyana", "Lyamkina", "134134");
    }

    @DataProvider(name = "Тестовые данные для негативных проверок")
    public Object[][] checkoutData() {
        return new Object[][] {
                {"", "Lyamkina", "134134", "Error: First Name is required"},
                {"Tatyana", "", "134134", "Error: Last Name is required"},
                {"Tatyana", "Lyamkina", "", "Error: Postal Code is required"}
        };
    }

    @Test(testName = "Негавтивное заполнение Checkout",
            description = "Негавтивное заполнение Checkout",
            groups = {"checkout", "regression"},
            dataProvider = "Тестовые данные для негативных проверок")
    public void negativeCheckout(String firstName, String lastName, String postalCode, String errorMessage) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickShoppingCart();
        cartPage.clickCheckoutButton();
        softAssert.assertEquals(checkoutPage.getTitle(),
                "Checkout: Your Information", "Страница не открылась");
        checkoutPage.fillPersonalInfo(firstName, lastName, postalCode);
        softAssert.assertEquals(checkoutPage.getErrorMessage(),
                errorMessage, "Сообщение об ошибке не появилось!");
        softAssert.assertAll();
    }
}
