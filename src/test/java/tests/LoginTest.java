package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals("Некорректный заголовок", "Products", productsPage.getTitle());
    }

    @Test
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required",
                "Некорректное отображение текста ошибки при пустом пароле");
    }

    @Test
    public void checkLoginWithEmptyUser() {
        loginPage.open();
        loginPage.login("", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required",
                "Некорректное отображение текста ошибки при пустом юзернейме");
    }

    @Test
    public void checkLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Неправильное отображение текста ошибки при некорректных данных");
    }
}
