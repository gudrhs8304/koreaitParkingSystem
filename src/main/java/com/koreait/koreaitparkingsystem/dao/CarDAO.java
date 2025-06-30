package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.util.DBConnection;
import com.koreait.koreaitparkingsystem.vo.CarVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDAO {

    public CarVO selectCarByNum(String carNumber) {
        String sql = "select * from car where car_number=?";

        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carNumber);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return CarVO.builder()
                        .carNumber(resultSet.getString("car_number"))
                        .carTypeCode(resultSet.getString("car_type_code"))
                        .driverName(resultSet.getString("driver_name"))
                        .phone(resultSet.getString("phone"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void insertCar(CarVO carVO) {
        String sql = "insert into car values(?,?,?,?)";
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
        String sql = "select count(*) from car where car_number=?";
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
}