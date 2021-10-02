package com.web_final_task.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/test/resources/application.properties"
})
public interface ApplicationConfig extends Config {

    @Config.Key("user.management.url")
    String getUserManagementUrl();

    @Config.Key("user.payment.url")
    String getPaymentUrl();
}
