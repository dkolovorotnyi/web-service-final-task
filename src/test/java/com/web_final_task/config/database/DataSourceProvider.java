package com.web_final_task.config.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public enum DataSourceProvider {
    INSTANCE;

    private final String DATABASE_URL = "jdbc:mysql://localhost:3306/course_app";
    private final String LOGIN = "app_user";
    private final String PASSWORD = "app_password";
    private DataSource dataSource;

    public DataSource getDataSource() {
        if (dataSource == null) {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUrl(DATABASE_URL);
            mysqlDataSource.setUser(LOGIN);
            mysqlDataSource.setPassword(PASSWORD);
            dataSource = mysqlDataSource;
        }
        return dataSource;
    }
}
