package com.koreait.koreaitparkingsystem.datebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException, SQLException {
        Connection connection = null;
        String url = "jdbc:mysql://sa98077.ipdisk.co.kr:3306/koreaPark";
        String user = "kmg";
        String password = "kmg@123";

        // JDBC 로딩
        Class.forName("org.mysql.jdbc.Driver");

        // 데이터베이스 연결
        return DriverManager.getConnection(url, user, password);
    }
}
