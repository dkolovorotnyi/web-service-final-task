package com.web_final_task.utility;

import com.web_final_task.entity.xml.CreateUserRequest;
import com.web_final_task.entity.xml.UserDetails;
import lombok.SneakyThrows;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

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
}
