package tests;

import dto.Contact;
import dto.ContactFactory;
import io.qameta.allure.*;
import org.testng.annotations.Test;


@Owner("Lyamkina Tatyana")
@Epic("E2E")
@Feature("Создание контактов")
public class NewContactTest extends BaseTest {

    Contact contactNegative = Contact.builder()
            .lastName("")
            .fax("127939")
            .build();

    Contact contact = ContactFactory.getContact("Other");

    @Test
    @Description("Проверка создания нового контакта")
    public void checkAddNewContact() {
        loginStep.auth("will", "will");
        newContactPage.open()
                .isPageOpened()
                .addNewContact(contact)
                .verifyUnselectCheckbox()
                .selectCheckbox()
                .verifySelectCheckbox()
                .clickSave()
                .verifyContact(contact);
    }

    @Test
    @Description("Создание нового контакта. Негатив")
    public void checkAddNewAccountNegative() {
        loginStep.auth("will", "will");
        newContactPage.open()
                .isPageOpened()
                .addNewContact(contactNegative)
                .clickSave()
                .verifyContactNegative();
    }
}
