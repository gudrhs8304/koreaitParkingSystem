package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.dto.PricingPolicyDTO;
import com.koreait.koreaitparkingsystem.util.DBConnection;
import com.koreait.koreaitparkingsystem.vo.PricingPolicyVO;
import com.koreait.koreaitparkingsystem.vo.DiscountPolicyVO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public enum PricingPolicyDAO {
    INSTANCE;

    public List<PricingPolicyVO> selectAllFees() {
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

    public void updateFeeById(int id,int price) {
        String sql = "UPDATE pricing_policy SET price = ? WHERE id = ?";

        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, price);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("실행 쿼리: UPDATE pricing_policy SET price = " + price + " WHERE id = " + id);
    }

    public PricingPolicyVO selectPriceById(int id) {
        String sql = "select id,price,name from pricing_policy WHERE id = ?";

        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                PricingPolicyVO policy = PricingPolicyVO.builder()
                        .price(resultSet.getInt("price"))
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                return policy;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return null;

    }

    public int getBaseFee () {
        String sql = "SELECT price FROM pricing_policy WHERE name = '기본요금'";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("price");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int getExtraFee () {
        String sql = "SELECT price FROM pricing_policy WHERE name = '추가요금'";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("price");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int selectDailyMaxFee () {
        String sql = "SELECT price FROM pricing_policy WHERE name = '일일최대요금'";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("price");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
