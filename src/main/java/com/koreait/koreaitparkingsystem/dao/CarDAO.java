package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.util.DBConnection;
import com.koreait.koreaitparkingsystem.vo.CarVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum CarDAO {
    INSTANCE;

    public CarVO selectCarByNum(String carNumber) {
        String sql = "SELECT * FROM car WHERE car_number = ?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carNumber);
            @Cleanup ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return CarVO.builder()
                        .carNumber(rs.getString("car_number"))
                        .carTypeCode(rs.getString("car_type_code"))
                        .driverName(rs.getString("driver_name"))
                        .phone(rs.getString("phone"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void insertCar(CarVO carVO) {
        if (selectIsRegistered(carVO.getCarNumber())) { // 새로 추가
            throw new IllegalStateException("이미 등록된 차량입니다: " + carVO.getCarNumber());
        }
        String sql = "INSERT INTO car VALUES (?, ?, ?, ?)";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carVO.getCarNumber());
            preparedStatement.setString(2, carVO.getCarTypeCode());
            preparedStatement.setString(3, carVO.getDriverName());
            preparedStatement.setString(4, carVO.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean selectIsRegistered(String carNumber) {
        String sql = "SELECT COUNT(*) FROM car WHERE car_number = ?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carNumber);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CarVO findByCarNumber(String carNumber) {
        String sql = "SELECT * FROM car WHERE car_number = ?";
        try {
            @Cleanup Connection conn = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, carNumber);
            @Cleanup ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return CarVO.builder()
                            .carNumber(rs.getString("car_number"))
                            .driverName(rs.getString("driver_name"))
                            .phone(rs.getString("phone"))
                            .build();
                }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
