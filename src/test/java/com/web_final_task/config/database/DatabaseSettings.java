package com.web_final_task.config.database;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/test/resources/dbsettings.properties"
})
public interface DatabaseSettings extends Config {

    @Config.Key("spring.datasource.url")
    String getDBUrl();

    @Config.Key("spring.datasource.username")
    String getUsername();

    @Config.Key("spring.datasource.password")
    String getPassword();
}
