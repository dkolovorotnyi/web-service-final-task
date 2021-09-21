package com.web_final_task.config.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public enum DataSourceProvider {
    INSTANCE;

    private DataSource dataSource;

    public DataSource getDataSource() {
        if (dataSource == null) {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/course_app");
            mysqlDataSource.setUser("app_user");
            mysqlDataSource.setPassword("app_password");
            dataSource = mysqlDataSource;
        }
        return dataSource;
    }

}
