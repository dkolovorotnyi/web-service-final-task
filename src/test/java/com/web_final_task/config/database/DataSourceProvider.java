package com.web_final_task.config.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.util.Objects;

import static com.web_final_task.config.ConfigManager.databaseSettings;

public enum DataSourceProvider {
    INSTANCE;

    private DataSource dataSource;

    public DataSource getDataSource() {
        if (Objects.isNull(dataSource)) {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUrl(databaseSettings().getDBUrl());
            mysqlDataSource.setUser(databaseSettings().getUsername());
            mysqlDataSource.setPassword(databaseSettings().getPassword());
            dataSource = mysqlDataSource;
        }
        return dataSource;
    }
}
