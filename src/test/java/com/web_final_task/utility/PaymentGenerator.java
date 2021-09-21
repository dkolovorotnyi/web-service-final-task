package com.web_final_task.utility;

import com.web_final_task.entity.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.web_final_task.utility.CommonFaker.getFakerWithDefaultLocale;
import static java.lang.String.*;

public class PaymentGenerator {

    public static Payment generatePayment(boolean verified) {
        return Payment.builder()
                .id(Long.parseLong(getFakerWithDefaultLocale().number().digits(1)))
                .userId((long) getFakerWithDefaultLocale().number().numberBetween(1, 999))
                .cardHolder(getFakerWithDefaultLocale().artist().name().toUpperCase(Locale.US))
                .cardNumber(getFakerWithDefaultLocale().finance().creditCard())
                .cvv(valueOf(getFakerWithDefaultLocale().number().randomNumber(9, false)))
                .expiryMonth(12)
                .expiryYear(12)
                .verified(verified)
                .build();
    }
    public static Payment generatePaymentCustomExpiryMonthAndExpiryYear(int expiryMonth, int expiryYear) {
        return Payment.builder()
                .id(Long.parseLong(getFakerWithDefaultLocale().number().digits(1)))
                .userId(Long.valueOf(getFakerWithDefaultLocale().number().digits(1)))
                .cardHolder(getFakerWithDefaultLocale().artist().name().toUpperCase(Locale.US))
                .cardNumber(getFakerWithDefaultLocale().finance().creditCard())
                .cvv(valueOf(getFakerWithDefaultLocale().number().randomNumber(9, false)))
                .expiryMonth(expiryMonth)
                .expiryYear(expiryYear)
                .verified(false)
                .build();
    }

    public static Payment generatePaymentWithCustomCVV(String cvv) {
        return Payment.builder()
                .id(Long.parseLong(getFakerWithDefaultLocale().number().digits(1)))
                .userId(Long.valueOf(getFakerWithDefaultLocale().number().digits(1)))
                .cardHolder(getFakerWithDefaultLocale().artist().name().toUpperCase(Locale.US))
                .cardNumber(getFakerWithDefaultLocale().finance().creditCard())
                .cvv(cvv)
                .expiryMonth(1)
                .expiryYear(23)
                .verified(false)
                .build();
    }

    public static Payment generatePaymentWithCustomUserId(long userId) {
        return Payment.builder()
                .id(getFakerWithDefaultLocale().number().numberBetween(1, 900))
                .userId(userId)
                .cardHolder(getFakerWithDefaultLocale().artist().name().toUpperCase(Locale.US))
                .cardNumber(getFakerWithDefaultLocale().finance().creditCard())
                .cvv(valueOf(getFakerWithDefaultLocale().number().randomNumber(9, false)))
                .expiryMonth(1)
                .expiryYear(23)
                .verified(false)
                .build();
    }

    public static List<Payment> generateListOfPayment(int numberOfPayments, long userId) {
        ArrayList<Payment> payments = new ArrayList<>();

        for (int i = 0; i < numberOfPayments; i++) {
            Payment payment = generatePaymentWithCustomUserId(userId);
            payments.add(payment);
        }
        return payments;
    }
}
