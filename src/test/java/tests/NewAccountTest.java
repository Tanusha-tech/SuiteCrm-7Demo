package tests;

import dto.Account;
import dto.AccountFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

@Owner("Lyamkina Tatyana")
@Epic("E2E")
@Feature("Создание аккаунтов")
public class NewAccountTest extends BaseTest {

    Account account = Account.builder()
            .name("")
            .numberPhone("88005553535")
            .fax("127939")
            .website("site.com")
            .street("Mira 12")
            .type("Analyst")
            .industry("Banking")
            .build();

    Account account2 = AccountFactory.getAccount("Analyst", "Banking");

    @Test
    @Description("Создание нового аккаунта")
    public void checkAddNewAccount() {
        loginStep.auth("will", "will");
        newAccountPage.open()
                .isPageOpened()
                .addNewAccount(account2)
                .verifyUnselectCheckbox()
                .selectCheckbox()
                .verifySelectCheckbox()
                .clickSave()
                .verifyAccount(account2);
    }

    @Test
    @Description("Создание нового аккаунта. Негатив")
    public void checkAddNewAccountNegative() {
        loginStep.auth("will", "will");
        newAccountPage.open()
                .isPageOpened()
                .addNewAccount(account)
                .clickSave()
                .verifyAccountNegative();
    }
}