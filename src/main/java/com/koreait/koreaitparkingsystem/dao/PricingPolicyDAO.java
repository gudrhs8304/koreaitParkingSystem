package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.database.DBConnection;
import com.koreait.koreaitparkingsystem.vo.PricingPolicyVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PricingPolicyDAO {

    public void updateIsAdditional(PricingPolicyVO pricingPolicyVO) {
        String sql = "update pricing_policy set name = ?, price = ?, duration_minutes = ?, is_additional = ?, is_daily_max = ? where id = ?";
        try {
            @Cleanup Connection connection = DBConnection.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pricingPolicyVO.getName());
            preparedStatement.setInt(2, pricingPolicyVO.getPrice());
            preparedStatement.setInt(3, pricingPolicyVO.getDuration_minutes());
            preparedStatement.setBoolean(4, pricingPolicyVO.is_additional());
            preparedStatement.setBoolean(5, pricingPolicyVO.is_daily_max());
            preparedStatement.setInt(6, pricingPolicyVO.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
