-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 22, 2024 lúc 09:13 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `bookingcare`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
                           `account_id` varchar(7) NOT NULL,
                           `role_id` varchar(7) NOT NULL,
                           `password` varchar(250) NOT NULL,
                           `username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`account_id`, `role_id`, `password`, `username`) VALUES
                                                                            ('admin00', 'admin00', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'Admin'),
                                                                            ('doctor0', 'doctor0', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'Minh Khang'),
                                                                            ('doctor1', 'doctor0', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'Xuan Hoang'),
                                                                            ('doctor2', 'doctor0', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'Hoang Nguyen'),
                                                                            ('pt00001', 'patient', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'Nguyen Van A');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `appointment`
--

CREATE TABLE `appointment` (
                               `appointment_id` int(11) NOT NULL,
                               `available_datetime` date NOT NULL,
                               `doctor_id` varchar(7) NOT NULL,
                               `package_id` varchar(7) DEFAULT NULL,
                               `patient_id` varchar(7) NOT NULL,
                               `time_id` varchar(7) NOT NULL,
                               `examination_day` datetime(6) NOT NULL,
                               `note` tinytext DEFAULT NULL,
                               `status` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `appointment`
--

INSERT INTO `appointment` (`appointment_id`, `available_datetime`, `doctor_id`, `package_id`, `patient_id`, `time_id`, `examination_day`, `note`, `status`) VALUES
    (1, '2024-11-22', 'doctor1', 'tq20000', 'pt00001', '0000001', '2024-11-22 17:04:19.000000', 'Test note', 'Scheduled');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `checkup_packpage`
--

CREATE TABLE `checkup_packpage` (
                                    `amount` double NOT NULL,
                                    `package_id` varchar(7) NOT NULL,
                                    `description` tinytext NOT NULL,
                                    `name` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `checkup_packpage`
--

INSERT INTO `checkup_packpage` (`amount`, `package_id`, `description`, `name`) VALUES
                                                                                   (789, 'tq10000', 'Xét nghiệm gan (9 Chỉ số)\n- Định lượng Bilirubin Trực Tiếp\n- Định lượng Bilirubin Gián Tiếp\n- Định lượng Bilirubin Toàn Phần\n- Định lượng men gan ALAT\n- Định lượng men gan ASAT\n- Định lượng men gan Gamm', 'Gói khám tổng quát Sliver - 22 chỉ số'),
                                                                                   (1650, 'tq20000', 'Xét nghiệm vi chất (6 Chỉ số)\r\n- Đo nồng độ Canxi (Ca) trong huyết thanh\r\n- Đo nồng độ Sắt (Fe)\r\n- Đo nồng độ 25-OH Vitamin D\r\n- Đo nồng độ Phốt Pho (P) trong huyết thanh\r\n- Đo nồng độ Ferritin\r\n- Đo nồng ', 'Gói khám tổng quát Gold - 28 chỉ số'),
                                                                                   (2401, 'tq30000', 'Xét nghiệm tiểu đường (3 Chỉ số)\r\n- Định lượng đường huyết đói - Glucose Fasting (Đường đói)\r\n- Đo mức đường huyết trung bình (HbA1c)\r\n- Ước Lượng Đường huyết Trung Bình (eAG)\r\nXét nghiệm thận (', 'Gói khám tổng quát Diamond - 36 chỉ số'),
                                                                                   (110, 'xnnm000', 'Nhóm máu A+ nếu có kháng nguyên A và kháng nguyên Rh.\r\nNhóm máu A- nếu có kháng nguyên A nhưng không có kháng nguyên Rh.\r\nNhóm máu B+ nếu có kháng nguyên B và kháng nguyên Rh.\r\nNhóm máu B- nếu có kháng nguyên B nhưn', 'Xét nghiệm nhóm máu (ABO + Rhesus) tự động'),
                                                                                   (1600, 'xnut000', 'Ung thư phổi (1 Chỉ số)\r\n- Dấu ấn ung thư Cyfra 21-1(Phổi)\r\nUng thư tuyến giáp (1 Chỉ số)\r\n- Đo nồng độ Thyroglobulin\r\nUng thư (2 Chỉ số)\r\n- Đo nồng độ Alpha-Fetoprotein (AFP)\r\n- Dấu ấn ung thư carcinoembryonic ', 'Gói khám cho Nam giới - Xét nghiệm Tầm soát ung thư toàn diện - 7 chỉ số'),
                                                                                   (1803, 'xnut001', 'Ung thư phổi (1 Chỉ số)\r\n- Dấu ấn ung thư Cyfra 21-1(Phổi)\r\nUng thư tuyến giáp (1 Chỉ số)\r\n- Đo nồng độ Thyroglobulin\r\nUng thư (2 Chỉ số)\r\n- Đo nồng độ Alpha-Fetoprotein (AFP)\r\n- Dấu ấn ung thư carcinoembryonic ', 'Gói khám cho Nữ giới (Cơ bản) - Xét nghiệm Tầm soát ung thư toàn diện - 8 chỉ số'),
                                                                                   (2405, 'xnut002', 'Ung thư phổi (1 Chỉ số)\r\n- Dấu ấn ung thư Cyfra 21-1(Phổi)\r\nUng thư tuyến giáp (1 Chỉ số)\r\n- Đo nồng độ Thyroglobulin\r\nUng thư (2 Chỉ số)\r\n- Đo nồng độ Alpha-Fetoprotein (AFP)\r\n- Dấu ấn ung thư carcinoembryonic ', 'Gói khám cho Nữ giới (Nâng cao) - Xét nghiệm Tầm soát ung thư toàn diện - 9 chỉ số');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `medical_record`
--

CREATE TABLE `medical_record` (
                                  `patient_id` char(7) NOT NULL,
                                  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `medical_record`
--

INSERT INTO `medical_record` (`patient_id`, `description`) VALUES
    ('pt00001', 'Bệnh:...');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
                        `role_id` varchar(7) NOT NULL,
                        `name` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`role_id`, `name`) VALUES
                                           ('admin00', 'Admin'),
                                           ('doctor0', 'Doctor'),
                                           ('patient', 'Patient'),
                                           ('support', 'Supporter');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `time_frame`
--

CREATE TABLE `time_frame` (
                              `time_end` time(6) NOT NULL,
                              `time_start` time(6) NOT NULL,
                              `time_id` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `time_frame`
--

INSERT INTO `time_frame` (`time_end`, `time_start`, `time_id`) VALUES
                                                                   ('11:30:00.000000', '08:00:00.000000', '0000001'),
                                                                   ('18:00:00.000000', '13:30:00.000000', '0000002');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
                        `user_id` varchar(7) NOT NULL,
                        `phone_number` varchar(10) DEFAULT NULL,
                        `identification_card` varchar(12) DEFAULT NULL,
                        `address` varchar(255) DEFAULT NULL,
                        `description` varchar(255) DEFAULT NULL,
                        `email` varchar(255) NOT NULL,
                        `gender` varchar(255) DEFAULT NULL,
                        `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`user_id`, `phone_number`, `identification_card`, `address`, `description`, `email`, `gender`, `name`) VALUES
                                                                                                                               ('admin00', '0368779041', '', '1000 An Duong Vuong', '', 'Admin@gmai.com', 'female', 'Admin'),
                                                                                                                               ('doctor0', '0865674317', '123456789100', 'Binh Tri Dong, Binh Tan', 'KHOA NOI', 'khangminh.do2003@gmail.com', 'male', 'Do Minh Khang'),
                                                                                                                               ('doctor1', '0336065760', '123456789111', 'Duong Ba Trac, Quan 8', 'KHOA NGOAI', 'vodinhxuanhoang@gmail.com', 'male', 'Vo Dinh Xuan Hoang'),
                                                                                                                               ('doctor2', '0392208279', '123456789101', 'Hoc Mon', 'DA KHOA', 'ankhang18122003@gmail.com', 'male', 'Vu Hoang Nguyen'),
                                                                                                                               ('pt00001', '0368779041', '', 'quan 8', '', 'nguyenvana@gmail.com', 'male', 'Nguyen Van A'),
                                                                                                                               ('pt00002', '1234567890', 'ID123456', '123 Street', 'Test description', 'khangminh.do23@gmail.com', 'Male', 'John Doe'),
                                                                                                                               ('pt00003', '1234567890', 'ID123456', '123 Street', 'Test description', 'khangminh.do2@gmail.com', 'Male', 'John Doe'),
                                                                                                                               ('pt00004', NULL, NULL, NULL, NULL, 'khangdo14042003@gmail.com', NULL, 'Minh Khang Đỗ'),
                                                                                                                               ('sp00001', '0368779041', '', '148 Luu Huu Phuoc', '', 'nguyenanhthu15082003@gmail.com', 'female', 'Nguyen Thi Anh Thu');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
    ADD PRIMARY KEY (`account_id`),
  ADD KEY `FKd4vb66o896tay3yy52oqxr9w0` (`role_id`);

--
-- Chỉ mục cho bảng `appointment`
--
ALTER TABLE `appointment`
    ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `FK1l62fhlqqc08wmgrvn7hjtfom` (`doctor_id`),
  ADD KEY `FK6ri1gkarbuy1bw1fka0dvhugd` (`package_id`),
  ADD KEY `FKg90ck1kd0p4rbamwc22jd2oql` (`patient_id`),
  ADD KEY `FKcv7wswx3l4hpqev324jncdjw0` (`time_id`);

--
-- Chỉ mục cho bảng `checkup_packpage`
--
ALTER TABLE `checkup_packpage`
    ADD PRIMARY KEY (`package_id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
    ADD PRIMARY KEY (`role_id`);

--
-- Chỉ mục cho bảng `time_frame`
--
ALTER TABLE `time_frame`
    ADD PRIMARY KEY (`time_id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `appointment`
--
ALTER TABLE `appointment`
    MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account`
--
ALTER TABLE `account`
    ADD CONSTRAINT `FKd4vb66o896tay3yy52oqxr9w0` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `FKoh0gud9ye26qi2hgbfquypb21` FOREIGN KEY (`account_id`) REFERENCES `user` (`user_id`);

--
-- Các ràng buộc cho bảng `appointment`
--
ALTER TABLE `appointment`
    ADD CONSTRAINT `FK1l62fhlqqc08wmgrvn7hjtfom` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FK6ri1gkarbuy1bw1fka0dvhugd` FOREIGN KEY (`package_id`) REFERENCES `checkup_packpage` (`package_id`),
  ADD CONSTRAINT `FKcv7wswx3l4hpqev324jncdjw0` FOREIGN KEY (`time_id`) REFERENCES `time_frame` (`time_id`),
  ADD CONSTRAINT `FKg90ck1kd0p4rbamwc22jd2oql` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
