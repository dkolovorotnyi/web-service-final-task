package com.web_final_task.utility;

import com.github.javafaker.Faker;

import java.util.Locale;

public class CommonFaker {

    public static Faker getFakerWithDefaultLocale() {
        return new Faker(Locale.US);
    }

}
