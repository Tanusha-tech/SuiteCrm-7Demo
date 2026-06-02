package dto;

import com.github.javafaker.Faker;

public class AccountFactory {

    public static Account getAccount(String type, String industry) {
        Faker faker = new Faker();
        return new Account(faker.company().name(), faker.phoneNumber().phoneNumber(),
                faker.phoneNumber().phoneNumber(), faker.internet().url(),
                faker.address().streetAddress(), faker.address().cityName(),
                faker.address().state(), faker.address().zipCode(), faker.address().country(),
                type, industry, faker.lorem().sentence(10));
    }
}
