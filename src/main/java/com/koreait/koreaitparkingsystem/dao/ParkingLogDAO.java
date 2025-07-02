//package com.koreait.koreaitparkingsystem.dao;
//
//import com.koreait.koreaitparkingsystem.util.DBConnection;
//import com.koreait.koreaitparkingsystem.vo.CarVO;
//import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
//import lombok.Cleanup;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public enum ParkingLogDAO {
//    INSTANCE;
//
//    public void insertEntry(ParkingLogVO log) {
//        String sql = "INSERT INTO parking_log (car_number, car_type_code, parking_spot) VALUES (?, ?, ?)";
//        try {
//            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
//            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, log.getCarNumber());
//            preparedStatement.setString(2, log.getCarTypeCode());
//            preparedStatement.setInt(3, log.getParkingSpot());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void updateExit(String carNumber, Timestamp outTime, int fee) {
//        String sql = "UPDATE parking_log SET out_time = ?, fee = ? WHERE car_number = ? AND out_time IS NULL";
//        try {
//            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
//            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setTimestamp(1, outTime);
//            preparedStatement.setInt(2, fee);
//            preparedStatement.setString(3, carNumber);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public ParkingLogVO selectActiveLogByCarNumber(String carNumber) {
//        String sql = "SELECT * FROM parking_log WHERE car_number = ? AND out_time IS NULL";
//        try {
//            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
//            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, carNumber);
//            @Cleanup ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                return ParkingLogVO.builder()
//                        .id(rs.getInt("id"))
//                        .carNumber(rs.getString("car_number"))
//                        .carTypeCode(rs.getString("car_type_code"))
//                        .parkingSpot(rs.getInt("parking_spot"))
//                        .inTime(rs.getTimestamp("in_time").toLocalDateTime())
//                        .build();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    public List<ParkingLogVO> selectLongTermParked(int hours) {
//        String sql = "SELECT * FROM parking_log WHERE out_time IS NULL AND TIMESTAMPDIFF(HOUR, in_time, NOW()) >= ?";
//        List<ParkingLogVO> list = new ArrayList<>();
//        try {
//            @Cleanup Connection conn = DBConnection.INSTANCE.getConnection();
//            @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, hours);
//            @Cleanup ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                list.add(ParkingLogVO.builder()
//                        .id(rs.getInt("id"))
//                        .carNumber(rs.getString("car_number"))
//                        .carTypeCode(rs.getString("car_type_code"))
//                        .parkingSpot(rs.getInt("parking_spot"))
//                        .inTime(rs.getTimestamp("in_time").toLocalDateTime())
//                        .build());
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
//
//    // 출차된 로그 중 가장 최근 기록 1건을 반환
//    public ParkingLogVO selectLastLogByCarNumber(String carNumber) {
//        String sql = "SELECT * FROM parking_log WHERE car_number = ? AND out_time IS NOT NULL ORDER BY out_time DESC LIMIT 1";
//        try {
//                @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
//                @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                  preparedStatement.setString(1, carNumber);
//            @Cleanup ResultSet rs = preparedStatement.executeQuery();
//                if (rs.next()) {
//                    return ParkingLogVO.builder()
//                            .id(rs.getInt("id"))
//                            .carNumber(rs.getString("car_number"))
//                            .carTypeCode(rs.getString("car_type_code"))
//                            .parkingSpot(rs.getInt("parking_spot"))
//                            .inTime(rs.getTimestamp("in_time").toLocalDateTime())
//                            .outTime(rs.getTimestamp("out_time").toLocalDateTime())
//                            .fee(rs.getInt("fee"))
//                            .build();
//                }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//
//
//}
