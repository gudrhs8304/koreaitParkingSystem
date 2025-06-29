-- SQL Schema Definitions

-- dd
CREATE TABLE car_type
(

    code VARCHAR(20) PRIMARY KEY, -- 예: 'normal', 'disabled', 'compact', 'electric'
    name VARCHAR(20) NOT NULL     -- 예: '일반', '장애인', '경차', '전기차'
);

CREATE TABLE parking_log
(

    id            INT AUTO_INCREMENT PRIMARY KEY,

    car_number    VARCHAR(15),
    car_type_code VARCHAR(20),

    parking_spot  INT,
    in_time       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    out_time      DATETIME,
    fee           INT,

    FOREIGN KEY (car_type_code) REFERENCES car_type (code)
);

CREATE TABLE monthly_member
(

    car_number  VARCHAR(15) PRIMARY KEY,
    driver_name VARCHAR(50),
    phone       VARCHAR(20),
    start_date  DATE,
    end_date    DATE
);


CREATE TABLE admin
(
    username VARCHAR(30) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE pricing_policy (
                                id INT PRIMARY KEY AUTO_INCREMENT,
                                name VARCHAR(50),  -- ex: '기본요금', '추가요금', '최대요금'
                                price INT
);


CREATE TABLE car (
                     car_number VARCHAR(15) PRIMARY KEY,
                     car_type_code VARCHAR(20),
                     driver_name VARCHAR(50),
                     phone VARCHAR(20),
                     FOREIGN KEY (car_type_code) REFERENCES car_type(code)
);

CREATE TABLE parking_spot (
                              spot_number INT PRIMARY KEY,
                              is_occupied BOOLEAN DEFAULT FALSE
);
CREATE TABLE discount_policy (
                                 car_type_code VARCHAR(20) PRIMARY KEY,
                                 discount_rate INT, -- 예: 50은 50% 할인
                                 FOREIGN KEY (car_type_code) REFERENCES car_type(code)
);
ALTER TABLE pricing_policy
    ADD COLUMN duration_minutes INT, -- 적용 시간 (예: 60분, 30분)
    ADD COLUMN is_additional BOOLEAN DEFAULT FALSE, -- 추가 요금 여부
    ADD COLUMN is_daily_max BOOLEAN DEFAULT FALSE; -- 일일 최대 요금 여부
