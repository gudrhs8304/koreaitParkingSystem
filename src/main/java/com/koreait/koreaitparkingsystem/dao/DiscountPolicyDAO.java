package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.util.DBConnection;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public enum DiscountPolicyDAO {
    INSTANCE;

    public int selectDiscountRate(String carTypeCode) {
        String sql = "select discount_rate from discount_policy where car_type_code=?";

        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carTypeCode);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("discount_rate");
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return 0;
    }

    public void insertDiscountRate(String carTypeCode, int discountRate) {
        String sql = "INSERT INTO discount_policy (car_type_code, discount_rate) VALUES (?,?)";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carTypeCode);
            preparedStatement.setInt(2, discountRate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public void updateDiscountRate(String carTypeCode, int discountRate) {
        String sql = "update discount_policy set discount_rate=? where car_type_code=?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carTypeCode);
            preparedStatement.setInt(2, discountRate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public void deleteDiscountRate(String carTypeCode) {
        String sql = "delete from discount_policy where car_type_code=?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carTypeCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
