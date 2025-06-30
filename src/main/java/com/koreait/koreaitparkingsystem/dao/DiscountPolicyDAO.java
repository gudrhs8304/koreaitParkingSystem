package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.database.DBConnection;
import com.koreait.koreaitparkingsystem.vo.DiscountPolicyVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountPolicyDAO {

    public DiscountPolicyVO select(String car_type_code) {
        String sql = "select discount_rate from discount_policy where car_type_code = ?";

        try {
            @Cleanup Connection connection = DBConnection.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, car_type_code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                DiscountPolicyVO discountPolicyVO = DiscountPolicyVO.builder()
                        .discount_rate(resultSet.getInt("discount_rate"))
                        .car_type_code(resultSet.getString("car_type_code"))
                        .build();
                return discountPolicyVO;

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
