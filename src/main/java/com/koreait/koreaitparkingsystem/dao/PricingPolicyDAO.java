package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.util.DBConnection;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.PricingPolicyVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PricingPolicyDAO {

    public List<PricingPolicyVO> selectAllPolicies() {
        String sql = "SELECT * FROM pricing_policy";
        List<PricingPolicyVO> list = new ArrayList<>();
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PricingPolicyVO policy = PricingPolicyVO.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .price(resultSet.getInt("price"))
                        .durationMinutes(resultSet.getInt("duration_minutes"))
                        .isAdditional(resultSet.getBoolean("is_additional"))
                        .isDailyMax(resultSet.getBoolean("is_daily_max"))
                        .build();
                list.add(policy);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void updatePolicy(PricingPolicyVO policy) {
        String sql = "UPDATE pricing_policy SET  price = ?, duration_minutes = ?, is_additional = ?, is_daily_max = ? WHERE id = ?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, policy.getPrice());
            preparedStatement.setInt(2, policy.getDurationMinutes());
            preparedStatement.setBoolean(3, policy.isAdditional());
            preparedStatement.setBoolean(4, policy.isDailyMax());
            preparedStatement.setInt(5, policy.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}