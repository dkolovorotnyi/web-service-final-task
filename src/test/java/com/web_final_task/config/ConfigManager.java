package com.web_final_task.config;

import com.web_final_task.config.database.DatabaseSettings;
import org.aeonbits.owner.ConfigFactory;

public class ConfigManager {

    public static DatabaseSettings databaseSettings() {
        return ConfigFactory.create(DatabaseSettings.class, System.getProperties());
    }

    public static ApplicationConfig applicationConfig() {
        return ConfigFactory.create(ApplicationConfig.class, System.getProperties());
    }
}
