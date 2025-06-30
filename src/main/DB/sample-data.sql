-- 1. 차량 유형 (car_type)
INSERT INTO car_type (code, name) VALUES
                                      ('normal', '일반'),
                                      ('disabled', '장애인'),
                                      ('compact', '경차'),
                                      ('electric', '전기차');

-- 2. 할인 정책 (discount_policy)
INSERT INTO discount_policy (car_type_code, discount_rate) VALUES
                                                               ('normal', 0),
                                                               ('disabled', 50),
                                                               ('compact', 30),
                                                               ('electric', 0);

-- 3. 요금 정책 (pricing_policy)
INSERT INTO pricing_policy (name, price, duration_minutes, is_additional, is_daily_max) VALUES
                                                                                            ('기본요금', 2000, 60, FALSE, FALSE),
                                                                                            ('추가요금', 1000, 30, TRUE, FALSE),
                                                                                            ('일일최대요금', 15000, NULL, FALSE, TRUE);

-- 4. 관리자 계정 (admin)
INSERT INTO admin (username, password) VALUES
                                           ('admin1', 'hashed_pw_1'),
                                           ('manager', 'hashed_pw_2');

-- 5. 주차 공간 (parking_spot)
INSERT INTO parking_spot (spot_number, is_occupied) VALUES
                                                        (1, TRUE),
                                                        (2, TRUE),
                                                        (3, TRUE),
                                                        (4, FALSE),
                                                        (5, TRUE),
                                                        (6, FALSE),
                                                        (7, TRUE),
                                                        (8, FALSE),
                                                        (9, TRUE),
                                                        (10, TRUE);

-- 6. 차량 정보 (car)
INSERT INTO car (car_number, car_type_code, driver_name, phone) VALUES
                                                                    ('12가3456', 'normal', '김철수', '010-1234-5678'),
                                                                    ('34나7890', 'electric', '이영희', '010-2345-6789'),
                                                                    ('56다1234', 'compact', '박민수', '010-3456-7890'),
                                                                    ('78라5678', 'compact', '최지훈', '010-4567-8901'),
                                                                    ('90마1234', 'disabled', '한예슬', '010-5678-9012'),
                                                                    ('88바8888', 'electric', '정우성', '010-6789-0123'),
                                                                    ('77사7777', 'compact', '김하늘', '010-7890-1234'),
                                                                    ('66아6666', 'normal', '이동욱', '010-8901-2345'),
                                                                    ('55자5555', 'disabled', '장나라', '010-9012-3456'),
                                                                    ('44차4444', 'normal', '조인성', '010-0123-4567');

-- 7. 월정액 회원 (monthly_member)
INSERT INTO monthly_member (car_number, driver_name, phone, start_date, end_date) VALUES
                                                                                      ('12가3456', '김철수', '010-1234-5678', '2025-06-01', '2025-06-30'),
                                                                                      ('34나7890', '이영희', '010-2345-6789', '2025-06-15', '2025-07-14'),
                                                                                      ('56다1234', '박민수', '010-3456-7890', '2025-06-10', '2025-07-09');

-- 8. 주차 기록 (parking_log)
INSERT INTO parking_log (car_number, car_type_code, parking_spot, in_time, out_time, fee) VALUES
                                                                                              ('12가3456', 'normal', 1, '2025-06-29 08:00:00', '2025-06-29 10:00:00', 2000),
                                                                                              ('34나7890', 'electric', 2, '2025-06-29 09:30:00', NULL, NULL),
                                                                                              ('78라5678', 'compact', 3, '2025-06-29 07:45:00', '2025-06-29 08:30:00', 1000),
                                                                                              ('90마1234', 'disabled', 5, '2025-06-29 06:00:00', '2025-06-29 09:00:00', 3000),
                                                                                              ('56다1234', 'compact', 7, '2025-06-29 10:15:00', NULL, NULL),
                                                                                              ('88바8888', 'electric', 9, '2025-06-29 11:00:00', NULL, NULL),
                                                                                              ('77사7777', 'compact', 10, '2025-06-29 10:45:00', NULL, NULL),
                                                                                              ('66아6666', 'normal', 4, '2025-06-29 09:00:00', '2025-06-29 11:00:00', 2000),
                                                                                              ('55자5555', 'disabled', 6, '2025-06-29 08:30:00', NULL, NULL),
                                                                                              ('44차4444', 'normal', 8, '2025-06-29 07:00:00', '2025-06-29 08:00:00', 1000);