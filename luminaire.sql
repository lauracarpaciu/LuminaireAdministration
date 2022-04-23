-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2022 at 10:11 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `luminaire`
--

-- --------------------------------------------------------

--
-- Table structure for table `activation_tokens`
--

CREATE TABLE `activation_tokens` (
  `id` bigint(20) NOT NULL,
  `activated_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `expired_at` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `id` bigint(20) NOT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `companies`
--

CREATE TABLE `companies` (
  `cui` varchar(30) NOT NULL,
  `name` varchar(100) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `invoices`
--

CREATE TABLE `invoices` (
  `id` bigint(20) NOT NULL,
  `invoice` longblob,
  `invoice_number` varchar(255) DEFAULT NULL,
  `service_order_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `luminairecases`
--

CREATE TABLE `luminairecases` (
  `id` bigint(20) NOT NULL,
  `luminaire_problems` longtext
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `luminaires`
--

CREATE TABLE `luminaires` (
  `id` bigint(20) NOT NULL,
  `luminaire_manufacturer` varchar(30) NOT NULL,
  `luminaire_model` varchar(30) NOT NULL,
  `serial_number_or_vin` varchar(17) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `persons`
--

CREATE TABLE `persons` (
  `cnp` varchar(13) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pices`
--

CREATE TABLE `pices` (
  `id` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  `part_name` varchar(255) NOT NULL,
  `part_number` varchar(255) NOT NULL,
  `price` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `piece_orders`
--

CREATE TABLE `piece_orders` (
  `id` bigint(20) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `part_name` varchar(255) DEFAULT NULL,
  `part_number` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `service_order_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `service_orders`
--

CREATE TABLE `service_orders` (
  `id` bigint(20) NOT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `luminaire_id` bigint(20) DEFAULT NULL,
  `luminaire_problems_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(20) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `is_non_loked` bit(1) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_authorities`
--

CREATE TABLE `user_authorities` (
  `user_id` bigint(20) NOT NULL,
  `authorities` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `works`
--

CREATE TABLE `works` (
  `id` bigint(20) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `timed_work` double DEFAULT NULL,
  `work_description` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `work_orders`
--

CREATE TABLE `work_orders` (
  `id` bigint(20) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `timed_work` double NOT NULL,
  `work_description` varchar(255) DEFAULT NULL,
  `work_price` double NOT NULL,
  `service_order_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `work_prices`
--

CREATE TABLE `work_prices` (
  `id` bigint(20) NOT NULL,
  `electrical_work_price` double NOT NULL,
  `house_work_price` double NOT NULL,
  `mechanical_work_price` double NOT NULL,
  `normal_work_price` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activation_tokens`
--
ALTER TABLE `activation_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi2jlb18s6vxuuwkntf1tx1b6s` (`user_id`);

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK350n4nxlr0gyelhv7hf4cyeh2` (`service_order_id`);

--
-- Indexes for table `luminairecases`
--
ALTER TABLE `luminairecases`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `luminaires`
--
ALTER TABLE `luminaires`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_a1gr9xslrctgu4men1pn0i8w1` (`serial_number_or_vin`);

--
-- Indexes for table `persons`
--
ALTER TABLE `persons`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pices`
--
ALTER TABLE `pices`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `piece_orders`
--
ALTER TABLE `piece_orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf86cha9dihwsmb6g2msc3rby9` (`service_order_id`);

--
-- Indexes for table `service_orders`
--
ALTER TABLE `service_orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi4dpeq8cla97anwynjh988qn5` (`client_id`),
  ADD KEY `FK354m5c6n8m1k7wi6nh6fdul7u` (`luminaire_id`),
  ADD KEY `FK145sfutlvps7lvq3x35rwub9f` (`luminaire_problems_id`),
  ADD KEY `FKsugyp357jjxilk3qr5ne55k1x` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_authorities`
--
ALTER TABLE `user_authorities`
  ADD KEY `FKmj13d0mnuj4cd8b6htotbf9mm` (`user_id`);

--
-- Indexes for table `works`
--
ALTER TABLE `works`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `work_orders`
--
ALTER TABLE `work_orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjqylb8m13l5c0p3xhtqhibrcl` (`service_order_id`);

--
-- Indexes for table `work_prices`
--
ALTER TABLE `work_prices`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
