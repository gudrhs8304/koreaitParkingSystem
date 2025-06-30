package com.koreait.koreaitparkingsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Connection connection = null;
        String url = "jdbc:mysql://sa98077.ipdisk.co.kr:3306/koreaPark";
        String user = "khg";
        String password = "khg@123";

        // JDBC 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 데이터베이스 연결
        return DriverManager.getConnection(url, user, password);
    }
}
