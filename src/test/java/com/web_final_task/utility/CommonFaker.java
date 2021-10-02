package com.web_final_task.utility;

import com.github.javafaker.Faker;

import java.util.Locale;

public class CommonFaker {

  private static final Faker faker = new Faker(Locale.US);

    public static Faker getFakerWithDefaultLocale() {
        return faker;
    }
}
