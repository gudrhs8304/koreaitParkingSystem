package com.koreait.koreaitparkingsystem.dao;

import com.koreait.koreaitparkingsystem.util.DBConnection;
import com.koreait.koreaitparkingsystem.vo.CarParkingLogVO;
import com.koreait.koreaitparkingsystem.vo.CarStatusVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum CarStatusDAO {
    INSTANCE;

    // 작성자: 김동환 (차량 유형 반환)
    public String selectNameByCode(String code) {
        String sql = "SELECT name FROM car_type WHERE code = ?";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // 작성자: 김동환 (전체 주차 자리 수)
    public int countAllSpots() {
        String sql = "SELECT COUNT(*) FROM parking_spot";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    // 작성자: 김동환 (전체 입차 중 차량)
    public List<CarParkingLogVO> selectAllActiveLogs() {
        List<CarParkingLogVO> list = new ArrayList<>();
        String sql = "SELECT * FROM parking_log WHERE out_time IS NULL";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CarParkingLogVO vo = CarParkingLogVO.builder()
                        .id(resultSet.getInt("id"))
                        .carNumber(resultSet.getString("car_number"))
                        .carTypeCode(resultSet.getString("car_type_code"))
                        .inTime(resultSet.getTimestamp("in_time").toLocalDateTime())
                        .build();
                list.add(vo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }

    // 작성자: 김동환 (검색된 차량 목록)
    public List<CarParkingLogVO> selectLogs(String keyword) {
        List<CarParkingLogVO> list = new ArrayList<>();
        String sql = """
                 SELECT p.*, c.driver_name, c.phone
                       FROM parking_log p
                       JOIN car c ON p.car_number = c.car_number
                       WHERE p.out_time IS NULL
                         AND (
                              p.car_number LIKE CONCAT('%', ?, '%')
                           OR c.driver_name LIKE CONCAT('%', ?, '%')
                           OR c.phone LIKE CONCAT('%', ?, '%')
                         )
                """;
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, keyword);
            preparedStatement.setString(3, keyword);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CarParkingLogVO vo = CarParkingLogVO.builder()
                        .id(resultSet.getInt("id"))
                        .carNumber(resultSet.getString("car_number"))
                        .carTypeCode(resultSet.getString("car_type_code"))
                        .inTime(resultSet.getTimestamp("in_time").toLocalDateTime())
                        .build();
                list.add(vo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // 작성자 : 김동환 (현재 사용 중인 차량 수)
    public int countActiveLogs() {
        String sql = "SELECT COUNT(*) FROM parking_log WHERE out_time IS NULL";
        try {
            @Cleanup Connection connection = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    // 작성자 : 김동환 (차량번호로 상세 정보 조회)
    public CarStatusVO findByCarNumber(String carNumber) {
        String sql = "SELECT * FROM car WHERE car_number = ?";
        try {
            @Cleanup Connection conn = DBConnection.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, carNumber);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return CarStatusVO.builder()
                        .carNumber(resultSet.getString("car_number"))
                        .driverName(resultSet.getString("driver_name"))
                        .phone(resultSet.getString("phone"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // 작성자: 김동환 (주차 자리 수가 고정이면 안써도됨(전체 주차 공간))
//    public int getTotalSpotCount() {
//        String sql = "SELECT COUNT(*) FROM parking_spot";
//        try {
//            @Cleanup Connection conn = DBConnection.INSTANCE.getConnection();
//            @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
//            @Cleanup ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) return rs.getInt(1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return 0;
//    }

    //    public Integer assignSpotStatus() { // 원본 업그레이드 신규 추가
//        String selectSql = "SELECT spot_number FROM parking_spot WHERE is_occupied = FALSE ORDER BY spot_number LIMIT 1";
//        String updateSql = "UPDATE parking_spot SET is_occupied = TRUE WHERE spot_number = ?"; // 새로 추가
//        Connection connection = null;
//
//        try {
//            connection = DBConnection.INSTANCE.getConnection();
//            connection.setAutoCommit(false);
//
//            @Cleanup PreparedStatement selectStmt = connection.prepareStatement(selectSql);
//            ResultSet rs = selectStmt.executeQuery();
//            if (!rs.next()) return null;
//
//            int spot = rs.getInt("spot_number");
//            rs.close();
//
//            @Cleanup PreparedStatement updateStmt = connection.prepareStatement(updateSql);
//            updateStmt.setInt(1, spot);
//            updateStmt.executeUpdate();
//
//            connection.commit();
//            return spot;
//
//        } catch (SQLException e) {
//            try {
//                if (connection != null) connection.rollback();
//            } catch (Exception ex) {
//            }
//            throw new RuntimeException("자리 할당 중 오류 발생", e);
//        } finally {
//            try {
//                if (connection != null) connection.setAutoCommit(true);
//                connection.close();
//            } catch (Exception ignore) {
//            }
//        }
//    }
}

