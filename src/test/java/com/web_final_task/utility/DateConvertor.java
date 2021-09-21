package com.web_final_task.utility;

import com.web_final_task.entity.xml.UserDetails;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

public class DateConvertor {

    @SneakyThrows
    @NotNull
    public static XMLGregorianCalendar ConvertBirthdayToXMLGregorianCalendar(LocalDate birthday) {
        return DatatypeFactory
                .newInstance()
                .newXMLGregorianCalendar(birthday.toString());
    }

    @NotNull
    public static LocalDate convertBirthdayToLocalDateTime(UserDetails userDetails) {
        return LocalDate.of(
                userDetails.getBirthday().getYear(),
                userDetails.getBirthday().getMonth(),
                userDetails.getBirthday().getDay());
    }
}
