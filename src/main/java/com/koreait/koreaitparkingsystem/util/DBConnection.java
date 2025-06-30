package com.koreait.koreaitparkingsystem.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j

public enum DBConnection {
    INSTANCE;

    private final HikariDataSource dataSource;

    DBConnection(){

        HikariConfig config = new HikariConfig();

        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://sa98077.ipdisk.co.kr:3306/koreaPark");
        config.setUsername("kmk");
        config.setPassword("kmk@123");
        config.addDataSourceProperty("cachePrepStmts", "true"); // ps
        config.addDataSourceProperty("prepStmtCacheSize", "250"); // ps의 캐시 크기
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); // ps의 캐시 sql 제한.

        dataSource = new HikariDataSource(config);
    }
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
