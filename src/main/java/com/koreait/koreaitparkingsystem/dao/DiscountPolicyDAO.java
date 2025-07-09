package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.util.DBConnection;
import com.koreait.koreaitparkingsystem.vo.DiscountPolicyVO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum DiscountPolicyDAO {
    INSTANCE;

    public int selectDiscountRate(String carTypeCode) {
        String sql = "select discount_rate from discount_poli" +
                "cy where car_type_code=?";

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
            preparedStatement.setInt(1, discountRate);
            preparedStatement.setString(2,carTypeCode);
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
    public List<DiscountPolicyVO> selectAll() {
        List<DiscountPolicyVO> list = new ArrayList<>();
        String sql = "SELECT car_type_code, discount_rate FROM discount_policy";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                DiscountPolicyVO vo = DiscountPolicyVO.builder()
                        .carTypeCode(resultSet.getString("car_type_code"))
                        .discountRate(resultSet.getInt("discount_rate"))
                        .build();
                list.add(vo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
