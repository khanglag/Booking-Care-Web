-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 28, 2024 lúc 06:08 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.1.25

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
                                                                            ('doctor0', 'doctor0', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'minhkhang'),
                                                                            ('doctor1', 'doctor0', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'xuanhoang'),
                                                                            ('doctor2', 'doctor0', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'nguyenhoang'),
                                                                            ('pt00001', 'patient', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'nguyenvana'),
                                                                            ('pt00002', 'patient', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'vanminh'),
                                                                            ('pt00003', 'patient', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'nguyeny'),
                                                                            ('pt00004', 'patient', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'lyduong'),
                                                                            ('pt00005', 'patient', '$2a$10$CIYqQbKa086yZDbUkuPP2u1zmS9TZR9zz34iQ5xuJQTY0U..HvQKS', 'nguyenvy');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `appointment`
--

CREATE TABLE `appointment` (
                               `appointment_id` int(11) NOT NULL,
                               `available_datetime` datetime NOT NULL,
                               `doctor_id` varchar(7) NOT NULL,
                               `package_id` varchar(7) DEFAULT NULL,
                               `patient_id` varchar(7) NOT NULL,
                               `time_id` varchar(7) NOT NULL,
                               `examination_day` date NOT NULL,
                               `note` tinytext DEFAULT NULL,
                               `status` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `appointment`
--

INSERT INTO `appointment` (`appointment_id`, `available_datetime`, `doctor_id`, `package_id`, `patient_id`, `time_id`, `examination_day`, `note`, `status`) VALUES
                                                                                                                                                                (1, '2024-11-29 15:23:19', 'doctor1', 'xnut000', 'pt00001', '0000002', '2024-12-03', 'Phoi kho tho', 'CONFIRMED'),
                                                                                                                                                                (2, '2024-11-26 20:54:35', 'doctor2', 'tq20000', 'pt00002', '0000001', '2024-12-02', 'Toi muon kiem tra tong quat luon mot the', 'SCHEDULED'),
                                                                                                                                                                (3, '2024-11-28 18:00:18', 'doctor0', 'tq10000', 'pt00003', '0000001', '2024-12-07', 'khong co', 'CONFIRMED'),
                                                                                                                                                                (4, '2024-11-28 18:02:08', 'doctor1', 'tq10000', 'pt00004', '0000002', '2024-12-05', 'da toi dao nay noi man ngua nhieu qua', 'CONFIRMED'),
                                                                                                                                                                (5, '2024-11-26 06:03:14', 'doctor2', 'tq30000', 'pt00005', '0000001', '2024-12-02', 'kiem tra tong quat', 'SCHEDULED'),
                                                                                                                                                                (6, '2024-11-28 16:04:47', 'doctor0', 'xnnm000', 'pt00003', '0000001', '2024-12-06', 'toi muon hieu ro ve nhom mau cua minh', 'CONFIRMED'),
                                                                                                                                                                (7, '2024-11-26 12:06:29', 'doctor2', 'xnut002', 'pt00004', '0000002', '2024-12-05', 'khong co', 'SCHEDULED');

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
                                                                                   (803, 'tq10000', '- Đo nồng độ Canxi (Ca) trong huyết thanh\n- Đo nồng độ Sắt (Fe)\n- Đo nồng độ 25-OH Vitamin D\n- Đo nồng độ Phốt Pho (P) trong huyết thanh\n- Đo nồng độ Ferritin\n- Đo nồng ', 'Gói khám tổng quát Sliver - 22 chỉ số'),
                                                                                   (1650, 'tq20000', 'Xét nghiệm vi chất (6 Chỉ số)\n- Đo nồng độ Canxi (Ca) trong huyết thanh\n- Đo nồng độ Sắt (Fe)\n- Đo nồng độ 25-OH Vitamin D\n- Đo nồng độ Phốt Pho (P) trong huyết thanh\n- Đo nồng độ Ferritin\n- Đo nồng ', 'Gói khám tổng quát Gold - 28 chỉ số'),
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
                                  `record_id` int(11) NOT NULL,
                                  `patient_id` varchar(7) NOT NULL,
                                  `description` tinytext NOT NULL,
                                  `updated_at` datetime(6) DEFAULT NULL,
                                  `doctor_id` varchar(7) NOT NULL,
                                  `diagnosis` varchar(255) NOT NULL,
                                  `treatment_plan` varchar(255) DEFAULT NULL,
                                  `create_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `medical_record`
--

INSERT INTO `medical_record` (`record_id`, `patient_id`, `description`, `updated_at`, `doctor_id`, `diagnosis`, `treatment_plan`, `create_at`) VALUES
                                                                                                                                                   (1, 'pt00001', 'dau oc hay dau nhuc, hay chong mat', '2024-11-28 09:34:38.000000', 'doctor0', 'Dau oc cang thang, met moi qua muc, tuan hoan mau nao khong tot', 'Kiem tra bo phan nao va cac day than kinh o nao', '2024-11-28 08:29:27.000000'),
                                                                                                                                                   (2, 'pt00002', 'Di ve sinh ra mau, hay kho chiu tai bung', '2024-11-28 10:42:59.000000', 'doctor2', 'Benh soi than', 'Xet nghiem than', '2024-11-28 10:37:38.000000'),
                                                                                                                                                   (3, 'pt00003', 'Dao nay da bong sam vang, hay kho chiu, rung toc nhieu', '2024-11-28 09:03:38.000000', 'doctor0', 'Gan co nguy co bi viem', 'Xet nghiem gan', '2024-11-28 08:29:27.000000'),
                                                                                                                                                   (4, 'pt00004', 'Da xuat hien nhung vet san sui gay ngua', '2024-11-28 11:34:38.000000', 'doctor1', 'Viem da co dia', 'Xet nghiem Da lieu', '2024-11-28 11:29:27.000000'),
                                                                                                                                                   (5, 'pt00005', 'Toi thoi gian kiem tra dinh ki hang nam', '2024-11-28 14:45:01.000000', 'doctor2', 'Kiem tra dinh ki', 'Xet nghiem tong quat', '2024-11-28 14:29:27.000000');

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
                                                                                                                               ('doctor0', '0865674317', '123456789100', 'Binh Tri Dong, Binh Tan', 'KHOA NOI', 'khangminh.do203@gmail.com', 'male', 'Do Minh Khang'),
                                                                                                                               ('doctor1', '0336065760', '123456789111', 'Duong Ba Trac, Quan 8', 'KHOA NGOAI', 'vodinhxuanhoang@gmail.com', 'male', 'Vo Dinh Xuan Hoang'),
                                                                                                                               ('doctor2', '0392208279', '123456789101', 'Hoc Mon', 'DA KHOA', 'ankhang18122003@gmail.com', 'male', 'Vu Hoang Nguyen'),
                                                                                                                               ('pt00001', '0368779041', '123456789102', 'Quan 8', 'Benh ve than kinh\r\n', 'nguyenvana@gmail.com', 'male', 'Nguyen Van A'),
                                                                                                                               ('pt00002', '1234567890', '123456789103', 'Go Vap', 'Benh than', 'khangminh.do23@gmail.com', 'male', 'Le Van Minh'),
                                                                                                                               ('pt00003', '0336065760', '123456789104', 'Tan Binh', 'Benh gan', 'nguyeny21@gmail.com', 'female', 'Nguyen Thi Y'),
                                                                                                                               ('pt00004', '0356728762', '123456789105', 'Quan 5', 'Be da lieu', 'vanminh3@gmail.com', 'male', 'Ly Duong'),
                                                                                                                               ('pt00005', '0865674317', '123456789106', 'Binh Tan', 'Kiem tra tong quat dinh ki', 'nguyenthivy@gmail.com', 'female', 'Nguyen Thi Vy');

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
-- Chỉ mục cho bảng `medical_record`
--
ALTER TABLE `medical_record`
    ADD PRIMARY KEY (`record_id`),
  ADD KEY `FKl2821tupt5erv541fbu9g72if` (`patient_id`),
  ADD KEY `FKs0rupgcbopy2qyop8kpb7u884` (`doctor_id`);

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
    MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `medical_record`
--
ALTER TABLE `medical_record`
    MODIFY `record_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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

--
-- Các ràng buộc cho bảng `medical_record`
--
ALTER TABLE `medical_record`
    ADD CONSTRAINT `FKl2821tupt5erv541fbu9g72if` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKs0rupgcbopy2qyop8kpb7u884` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
