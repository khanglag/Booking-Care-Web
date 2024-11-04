-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 03, 2024 lúc 02:55 PM
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
-- Cơ sở dữ liệu: `booking_care_web`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `account_id` char(7) NOT NULL,
  `username` text NOT NULL,
  `password` varchar(20) NOT NULL,
  `role_id` char(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`account_id`, `username`, `password`, `role_id`) VALUES
('admin00', 'Admin', 'admin00', 'admin00'),
('doctor0', 'Minh Khang', 'doctor0', 'doctor0'),
('doctor1', 'Xuan Hoang', 'doctor1', 'doctor0'),
('doctor2', 'Hoang Nguyen', 'doctor2', 'doctor0'),
('pt00001', 'Nguyen Van A', 'pt00001', 'patient'),
('support', 'Anh Thu', 'support', 'support');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `appointment`
--

CREATE TABLE `appointment` (
  `appointment_id` char(7) NOT NULL,
  `patient_id` char(7) NOT NULL,
  `doctor_id` char(7) NOT NULL,
  `available_datetime` datetime NOT NULL,
  `package_id` char(7) NOT NULL,
  `examination_day` date NOT NULL,
  `time_id` char(7) NOT NULL,
  `note` text NOT NULL,
  `status` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `appointment`
--

INSERT INTO `appointment` (`appointment_id`, `patient_id`, `doctor_id`, `available_datetime`, `package_id`, `examination_day`, `time_id`, `note`, `status`) VALUES
('0000001', 'pt00001', 'doctor1', '2024-10-10 19:40:49', 'xnut000', '2024-10-13', '0000001', 'Triệu chứng: máu đen', 'DA DUYET');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `checkup_packpage`
--

CREATE TABLE `checkup_packpage` (
  `package_id` char(7) NOT NULL,
  `name` text NOT NULL,
  `description` text NOT NULL,
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `checkup_packpage`
--

INSERT INTO `checkup_packpage` (`package_id`, `name`, `description`, `amount`) VALUES
('tq10000', 'Gói khám tổng quát Sliver - 22 chỉ số', 'Xét nghiệm gan (9 Chỉ số)\n- Định lượng Bilirubin Trực Tiếp\n- Định lượng Bilirubin Gián Tiếp\n- Định lượng Bilirubin Toàn Phần\n- Định lượng men gan ALAT\n- Định lượng men gan ASAT\n- Định lượng men gan Gamma-Glutamyl Transferase\n- Định lượng men phosphatase kiềm trong máu\n- Đo Protein Toàn Phần [Huyết Thanh]\n- Định lượng albumin trong máu\nXét nghiệm tim mạch (7 Chỉ số)\n- Định lượng chất béo Triglycerides\n- Định lượng Cholesterol Toàn Phần\n- Đo nồng độ HDL Cholesterol (Cholesterol tốt)\n- Đo nồng độ LDL Cholesterol (Cholesterol xấu)\n- Tỷ lệ Cholesterol/HDL\n- Xét nghiệm protein Phản Ứng C Độ Nhạy Cao (hs CRP)\n- Đo nồng độ Homocysteine\nXét nghiệm tuyến giáp (2 Chỉ số)\n- Đo nồng độ nội tiết tố Kích Thích Tuyến Giáp (TSH)\n- Địnhh lượng nội tiết tố T4 Tự Do\nXét nghiệm huyết học (1 Chỉ số)\n- Công thức máu toàn bộ (CBC)\nXét nghiệm viêm gan (3 Chỉ số)\n- Kháng nguyên bề mặt Viêm gan B (HBsAg)Định tính\n- Kháng thể với Kháng nguyên bề mặt virus Viêm gan B (HBsAb)\n- Kháng thể Viêm gan C (HCV Ab)', 789),
('tq20000', 'Gói khám tổng quát Gold - 28 chỉ số', 'Xét nghiệm vi chất (6 Chỉ số)\r\n- Đo nồng độ Canxi (Ca) trong huyết thanh\r\n- Đo nồng độ Sắt (Fe)\r\n- Đo nồng độ 25-OH Vitamin D\r\n- Đo nồng độ Phốt Pho (P) trong huyết thanh\r\n- Đo nồng độ Ferritin\r\n- Đo nồng độ Vitamin B12\r\nXét nghiệm gan (9 Chỉ số)\r\n- Định lượng Bilirubin Trực Tiếp\r\n- Định lượng Bilirubin Gián Tiếp\r\n- Định lượng Bilirubin Toàn Phần\r\n- Định lượng men gan ALAT\r\n- Định lượng men gan ASAT\r\n- Định lượng men gan Gamma-Glutamyl Transferase\r\n- Định lượng men phosphatase kiềm trong máu\r\n- Đo Protein Toàn Phần [Huyết Thanh]\r\n- Định lượng albumin trong máu\r\nXét nghiệm tim mạch (7 Chỉ số)\r\n- Định lượng chất béo Triglycerides\r\n- Định lượng Cholesterol Toàn Phần\r\n- Đo nồng độ HDL Cholesterol (Cholesterol tốt)\r\n- Đo nồng độ LDL Cholesterol (Cholesterol xấu)\r\n- Tỷ lệ Cholesterol/HDL\r\n- Xét nghiệm protein Phản Ứng C Độ Nhạy Cao (hs CRP)\r\n- Đo nồng độ Homocysteine\r\nXét nghiệm tuyến giáp (2 Chỉ số)\r\n- Đo nồng độ nội tiết tố Kích Thích Tuyến Giáp (TSH)\r\n- Địnhh lượng nội tiết tố T4 Tự Do\r\nXét nghiệm huyết học (1 Chỉ số)\r\n- Công thức máu toàn bộ (CBC)\r\nXét nghiệm viêm gan (3 Chỉ số)\r\n- Kháng nguyên bề mặt Viêm gan B (HBsAg)Định tính\r\n- Kháng thể với Kháng nguyên bề mặt virus Viêm gan B (HBsAb)\r\n- Kháng thể Viêm gan C (HCV Ab)', 1650),
('tq30000', 'Gói khám tổng quát Diamond - 36 chỉ số', 'Xét nghiệm tiểu đường (3 Chỉ số)\r\n- Định lượng đường huyết đói - Glucose Fasting (Đường đói)\r\n- Đo mức đường huyết trung bình (HbA1c)\r\n- Ước Lượng Đường huyết Trung Bình (eAG)\r\nXét nghiệm thận (5 Chỉ số)\r\n- Định lượng Creatinin trong huyết thanh\r\n- Đo nồng độ Urê trong máu\r\n- Đo nồng độ Axit Uric huyết thanh\r\n- Tổng Phân Tích Nước Tiểu\r\n- Độ Lọc Cầu Thận Ước Tính (eGFR)\r\nXét nghiệm vi chất (6 Chỉ số)\r\n- Đo nồng độ Canxi (Ca) trong huyết thanh\r\n- Đo nồng độ Sắt (Fe)\r\n- Đo nồng độ 25-OH Vitamin D\r\n- Đo nồng độ Phốt Pho (P) trong huyết thanh\r\n- Đo nồng độ Ferritin\r\n- Đo nồng độ Vitamin B12\r\nXét nghiệm gan (9 Chỉ số)\r\n- Định lượng Bilirubin Trực Tiếp\r\n- Định lượng Bilirubin Gián Tiếp\r\n- Định lượng Bilirubin Toàn Phần\r\n- Định lượng men gan ALAT\r\n- Định lượng men gan ASAT\r\n- Định lượng men gan Gamma-Glutamyl Transferase\r\n- Định lượng men phosphatase kiềm trong máu\r\n- Đo Protein Toàn Phần [Huyết Thanh]\r\n- Định lượng albumin trong máu\r\nXét nghiệm tim mạch (7 Chỉ số)\r\n- Định lượng chất béo Triglycerides\r\n- Định lượng Cholesterol Toàn Phần\r\n- Đo nồng độ HDL Cholesterol (Cholesterol tốt)\r\n- Đo nồng độ LDL Cholesterol (Cholesterol xấu)\r\n- Tỷ lệ Cholesterol/HDL\r\n- Xét nghiệm protein Phản Ứng C Độ Nhạy Cao (hs CRP)\r\n- Đo nồng độ Homocysteine\r\nXét nghiệm tuyến giáp (2 Chỉ số)\r\n- Đo nồng độ nội tiết tố Kích Thích Tuyến Giáp (TSH)\r\n- Địnhh lượng nội tiết tố T4 Tự Do\r\nXét nghiệm huyết học (1 Chỉ số)\r\n- Công thức máu toàn bộ (CBC)\r\nXét nghiệm viêm gan (3 Chỉ số)\r\n- Kháng nguyên bề mặt Viêm gan B (HBsAg)Định tính\r\n- Kháng thể với Kháng nguyên bề mặt virus Viêm gan B (HBsAb)\r\n- Kháng thể Viêm gan C (HCV Ab)', 2401),
('xnnm000', 'Xét nghiệm nhóm máu (ABO + Rhesus) tự động', 'Nhóm máu A+ nếu có kháng nguyên A và kháng nguyên Rh.\r\nNhóm máu A- nếu có kháng nguyên A nhưng không có kháng nguyên Rh.\r\nNhóm máu B+ nếu có kháng nguyên B và kháng nguyên Rh.\r\nNhóm máu B- nếu có kháng nguyên B nhưng không có kháng nguyên Rh.\r\nNhóm máu AB+ nếu có cả kháng nguyên A, B và Rh.\r\nNhóm máu AB- nếu có kháng nguyên A, B nhưng không có kháng nguyên Rh.\r\nNhóm máu O+ nếu không có kháng nguyên A hoặc B, nhưng có kháng nguyên Rh.\r\nNhóm máu O- nếu không có cả kháng nguyên A, B và Rh.', 110),
('xnut000', 'Gói khám cho Nam giới - Xét nghiệm Tầm soát ung thư toàn diện - 7 chỉ số', 'Ung thư phổi (1 Chỉ số)\r\n- Dấu ấn ung thư Cyfra 21-1(Phổi)\r\nUng thư tuyến giáp (1 Chỉ số)\r\n- Đo nồng độ Thyroglobulin\r\nUng thư (2 Chỉ số)\r\n- Đo nồng độ Alpha-Fetoprotein (AFP)\r\n- Dấu ấn ung thư carcinoembryonic (CEA)\r\nUng thư tuyến tụy, mật (1 Chỉ số)\r\n- Dấu ấn ung thư CA 19-9\r\nUng thư da (1 Chỉ số)\r\n- Kháng Nguyên Ung Thư Biểu Mô Tế Bào Vảy (SCC)\r\nUng thư tuyến tiền liệt (1 Chỉ số)\r\n- Xét nghiệm PSA Toàn Phần', 1600),
('xnut001', 'Gói khám cho Nữ giới (Cơ bản) - Xét nghiệm Tầm soát ung thư toàn diện - 8 chỉ số', 'Ung thư phổi (1 Chỉ số)\r\n- Dấu ấn ung thư Cyfra 21-1(Phổi)\r\nUng thư tuyến giáp (1 Chỉ số)\r\n- Đo nồng độ Thyroglobulin\r\nUng thư (2 Chỉ số)\r\n- Đo nồng độ Alpha-Fetoprotein (AFP)\r\n- Dấu ấn ung thư carcinoembryonic (CEA)\r\nUng thư tuyến tụy, mật (1 Chỉ số)\r\n- Dấu ấn ung thư CA 19-9\r\nUng thư da (1 Chỉ số)\r\n- Kháng Nguyên Ung Thư Biểu Mô Tế Bào Vảy (SCC)\r\nSản Phụ Khoa (1 Chỉ số)\r\n- Dấu ấn ung thư CA 125\r\nUng thư vú (1 Chỉ số)\r\n- Dấu ấn ung thư CA 15-3\r\n', 1803),
('xnut002', 'Gói khám cho Nữ giới (Nâng cao) - Xét nghiệm Tầm soát ung thư toàn diện - 9 chỉ số', 'Ung thư phổi (1 Chỉ số)\r\n- Dấu ấn ung thư Cyfra 21-1(Phổi)\r\nUng thư tuyến giáp (1 Chỉ số)\r\n- Đo nồng độ Thyroglobulin\r\nUng thư (2 Chỉ số)\r\n- Đo nồng độ Alpha-Fetoprotein (AFP)\r\n- Dấu ấn ung thư carcinoembryonic (CEA)\r\nUng thư tuyến tụy, mật (1 Chỉ số)\r\n- Dấu ấn ung thư CA 19-9\r\nUng thư da (1 Chỉ số)\r\n- Kháng Nguyên Ung Thư Biểu Mô Tế Bào Vảy (SCC)\r\nSản Phụ Khoa (2 Chỉ số)\r\n- Dấu ấn ung thư CA 125\r\n- HPV 40 kèm bộ tự lấy mẫu\r\nUng thư vú (1 Chỉ số)\r\n- Dấu ấn ung thư CA 15-3\r\n', 2405);

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
  `role_id` char(7) NOT NULL,
  `name` text NOT NULL
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
  `time_id` char(7) NOT NULL,
  `time_start` time NOT NULL,
  `time_end` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `time_frame`
--

INSERT INTO `time_frame` (`time_id`, `time_start`, `time_end`) VALUES
('0000001', '08:00:00', '11:30:00'),
('0000002', '13:30:00', '18:00:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `user_id` char(7) NOT NULL,
  `name` text NOT NULL,
  `phone_number` char(10) NOT NULL,
  `email` text NOT NULL,
  `gender` text NOT NULL,
  `address` text NOT NULL,
  `description` text NOT NULL,
  `identification_card` char(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`user_id`, `name`, `phone_number`, `email`, `gender`, `address`, `description`, `identification_card`) VALUES
('admin00', 'Admin', '0368779041', 'Admin@gmai.com', 'female', '1000 An Duong Vuong', '', ''),
('doctor0', 'Do Minh Khang', '0865674317', 'khanglag@gmail.com', 'male', 'Binh Tri Dong, Binh Tan', 'KHOA NOI', '123456789100'),
('doctor1', 'Vo Dinh Xuan Hoang', '0336065760', 'vodinhxuanhoang@gmail.com', 'male', 'Duong Ba Trac, Quan 8', 'KHOA NGOAI', '123456789111'),
('doctor2', 'Vu Hoang Nguyen', '0392208279', 'ankhang18122003@gmail.com', 'male', 'Hoc Mon', 'DA KHOA', '123456789101'),
('pt00001', 'Nguyen Van A', '0368779041', 'nguyenvana@gmail.com', 'male', 'quan 8', '', ''),
('support', 'Nguyen Thi Anh Thu', '0368779041', 'nguyenanhthu15082003@gmail.com', 'female', '148 Luu Huu Phuoc', '', '');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`),
  ADD KEY `role_id` (`role_id`);

--
-- Chỉ mục cho bảng `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `package_id` (`package_id`),
  ADD KEY `time_id` (`time_id`),
  ADD KEY `u_id` (`doctor_id`),
  ADD KEY `user_id` (`patient_id`);

--
-- Chỉ mục cho bảng `checkup_packpage`
--
ALTER TABLE `checkup_packpage`
  ADD PRIMARY KEY (`package_id`);

--
-- Chỉ mục cho bảng `medical_record`
--
ALTER TABLE `medical_record`
  ADD PRIMARY KEY (`patient_id`);

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
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `account_ibfk_3` FOREIGN KEY (`account_id`) REFERENCES `user` (`user_id`);

--
-- Các ràng buộc cho bảng `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `checkup_packpage` (`package_id`),
  ADD CONSTRAINT `appointment_ibfk_5` FOREIGN KEY (`time_id`) REFERENCES `time_frame` (`time_id`),
  ADD CONSTRAINT `appointment_ibfk_6` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `appointment_ibfk_7` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`);

--
-- Các ràng buộc cho bảng `medical_record`
--
ALTER TABLE `medical_record`
  ADD CONSTRAINT `medical_record_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
