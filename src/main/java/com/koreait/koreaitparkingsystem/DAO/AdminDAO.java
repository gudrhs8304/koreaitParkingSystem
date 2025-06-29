package com.koreait.koreaitparkingsystem.DAO;

import com.koreait.koreaitparkingsystem.VO.AdminVO;
import com.koreait.koreaitparkingsystem.datebase.DBConnection;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.C;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class AdminDAO {
    private static AdminDAO instance;

    private AdminDAO() {

    }
    public static AdminDAO getInstance() {
        if(instance == null) {
            instance = new AdminDAO();
        }
        return instance;
    }

    // 로그인 처리
    public boolean selectLogin(String username, String password) {
        log.info("username: " + username + " password: " + password);
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try {
            @Cleanup Connection connection = DBConnection.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public AdminVO selectAdmin(String username) {
        log.info("username: " + username);
        String sql = "SELECT * FROM admin WHERE username = ?";
        try {
            @Cleanup Connection connection = DBConnection.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                AdminVO adminVO = AdminVO.builder()
                        .username(resultSet.getString("sername"))
                        .password(resultSet.getString("password"))
                        .build();
                return adminVO;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return  null;
    }

    public void updateAdmin(AdminVO adminVO) {
        log.info("adminVO" + adminVO);
        String sql = "UPDATE admin SET password = ? WHERE username = ?";
        try {
            @Cleanup Connection connection = DBConnection.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, adminVO.getPassword());
            preparedStatement.setString(2, adminVO.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
