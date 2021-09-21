package com.web_final_task.utility;

import com.web_final_task.entity.xml.CreateUserRequest;
import com.web_final_task.entity.xml.NewAddress;
import com.web_final_task.entity.xml.NewPayment;
import com.web_final_task.entity.xml.State;
import com.web_final_task.entity.xml.UserDetails;
import lombok.SneakyThrows;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.Locale;

import static com.web_final_task.utility.CommonFaker.getFakerWithDefaultLocale;

public class UserDetailsGenerator {

    @SneakyThrows
    public static UserDetails generateUserDetails(long id) {
        final int year = getFakerWithDefaultLocale().number().numberBetween(1990, 2019);
        final int month = getFakerWithDefaultLocale().number().numberBetween(1, 12);
        final int day = getFakerWithDefaultLocale().number().numberBetween(1, 31);

        final LocalDate localDate = LocalDate.of(year, month, day);
        final XMLGregorianCalendar birthday = DateConvertor.ConvertBirthdayToXMLGregorianCalendar(localDate);

        return UserDetails.builder()
                .id(id)
                .email(getFakerWithDefaultLocale().internet().emailAddress())
                .name(getFakerWithDefaultLocale().name().firstName())
                .lastName(getFakerWithDefaultLocale().name().lastName())
                .birthday(birthday)
                .build();
    }


    @SneakyThrows
    public static CreateUserRequest generateWithValidDate() {
        final int year = getFakerWithDefaultLocale().number().numberBetween(1990, 2019);
        final int month = getFakerWithDefaultLocale().number().numberBetween(1, 12);
        final int day = getFakerWithDefaultLocale().number().numberBetween(1, 31);

        final LocalDate localDate = LocalDate.of(year, month, day);
        final XMLGregorianCalendar birthday = DateConvertor.ConvertBirthdayToXMLGregorianCalendar(localDate);

        return CreateUserRequest.builder()
                .email(getFakerWithDefaultLocale().internet().emailAddress())
                .name(getFakerWithDefaultLocale().name().firstName())
                .birthday(birthday)
                .lastName(getFakerWithDefaultLocale().name().lastName())
                .payments(createListOfPayment(1))
                .addresses(createListOfAddress(1))
                .build();
    }

    @SneakyThrows
    public static CreateUserRequest generateWithoutPaymentsAndAddresses() {
        final int year = getFakerWithDefaultLocale().number().numberBetween(1990, 2019);
        final int month = getFakerWithDefaultLocale().number().numberBetween(1, 12);
        final int day = getFakerWithDefaultLocale().number().numberBetween(1, 31);

        final LocalDate localDate = LocalDate.of(year, month, day);
        final XMLGregorianCalendar birthday = DateConvertor.ConvertBirthdayToXMLGregorianCalendar(localDate);

        return CreateUserRequest.builder()
                .email(getFakerWithDefaultLocale().internet().emailAddress())
                .name(getFakerWithDefaultLocale().name().firstName())
                .lastName(getFakerWithDefaultLocale().name().lastName())
                .birthday(birthday)
                .build();
    }

    public static CreateUserRequest.Addresses createListOfAddress(int countOfAddresses) {
        CreateUserRequest.Addresses addresses = new CreateUserRequest.Addresses();
        for (int i = 0; i < countOfAddresses; i++) {

            NewAddress newAddress = getNewAddress();
            addresses.getAddress().add(newAddress);
        }
        return addresses;
    }

    public static NewAddress getNewAddress() {
        NewAddress newAddress = NewAddress.builder()
                .city(getFakerWithDefaultLocale().address().city())
                .line1(getFakerWithDefaultLocale().address().fullAddress())
                .line2(getFakerWithDefaultLocale().address().fullAddress())
                .state(State.AE)
                .zip(getFakerWithDefaultLocale().address().zipCode())
                .build();
        return newAddress;
    }

    public static CreateUserRequest.Payments createListOfPayment(int countOfPayments) {
        CreateUserRequest.Payments payments = new CreateUserRequest.Payments();

        for (int i = 0; i < countOfPayments; i++) {

            NewPayment payment = NewPayment.builder()
                    .cardNumber(getFakerWithDefaultLocale().finance().creditCard())
                    .cvv(String.valueOf(getFakerWithDefaultLocale().number().randomNumber(9, false)))
                    .cardholder(getFakerWithDefaultLocale().artist().name().toUpperCase(Locale.US))
                    .expiryMonth(getFakerWithDefaultLocale().number().numberBetween(1, 12))
                    .expiryYear(99)
                    .build();

            payments.getPayment().add(payment);
        }
        return payments;
    }
}
