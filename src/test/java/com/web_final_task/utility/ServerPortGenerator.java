package com.web_final_task.utility;

public class ServerPortGenerator {
    public static int RANDOM_PORT = CommonFaker.getFakerWithDefaultLocale().number().numberBetween(2020, 9090);

    public static int generatePort() {
       return CommonFaker.getFakerWithDefaultLocale().number().numberBetween(2020, 9090);
    }
}
