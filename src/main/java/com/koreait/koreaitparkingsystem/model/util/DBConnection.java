package com.koreait.koreaitparkingsystem.model.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBConnection {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        String url = "jdbc:mariadb://sa98077.ipdisk.co.kr:3306/koreaPark";
        String user = "sa98077";
        String password = "lee97531!!@";

        Class.forName("org.mariadb.jdbc.Driver"); // forName 으로 클래스화

        log.info("Connecting to database...");
        return DriverManager.getConnection(url, user, password); // 직접 리턴.
    }
}
