package dto;

import com.github.javafaker.Faker;

public class ContactFactory {

    public static Contact getContact(String leadsource) {
        Faker faker = new Faker();

        return new Contact(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().phoneNumber(),
                faker.phoneNumber().phoneNumber(), faker.job().title(), faker.job().field(),
                faker.name().username(), faker.phoneNumber().phoneNumber(),
                faker.address().fullAddress(), faker.address().city(), faker.address().country(),
                faker.address().countryCode(), faker.address().country(), faker.lorem().sentence(10),
                leadsource);
    }
}