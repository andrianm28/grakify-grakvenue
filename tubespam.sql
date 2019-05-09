-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2019 at 10:24 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 5.6.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tubespam`
--

-- --------------------------------------------------------

--
-- Table structure for table `fasilitas`
--

CREATE TABLE `fasilitas` (
  `id_fasilitas` int(9) NOT NULL,
  `id_venue` int(9) DEFAULT NULL,
  `fasilitas` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fasilitas`
--

INSERT INTO `fasilitas` (`id_fasilitas`, `id_venue`, `fasilitas`) VALUES
(1, 1, '- Ruang Ganti\r\n- Toilet\r\n- Cafe\r\n- Mushola\r\n- Tempat Makan'),
(2, 2, '- Ruang Ganti\r\n- Toilet'),
(3, 3, '- Ruang Ganti\r\n- Toilet'),
(4, 4, '- Ruang Ganti\r\n- Toilet\r\n- Mushola\r\n- Cafe\r\n- Tempat Makan\r\n- Kolam Renang\r\n- Parkir'),
(5, 5, '- Ruang Ganti\r\n- Toilet'),
(6, 6, '- Ruang ganti \r\n- Toilet'),
(7, 7, '- Ruang ganti\r\n- Toilet'),
(8, 8, '- Ruang Ganti\r\n- Toilet\r\n- Mushola\r\n'),
(9, 9, '- Ruang Ganti\r\n- Toilet\r\n- Parkir'),
(10, 10, '- Ruang Ganti\r\n- Toilet'),
(11, 11, '- RUang Ganti\r\n- Toilet'),
(12, 12, '- Ruang Ganti \r\n- Toilet'),
(13, 13, '- Ruang Ganti\r\n- Toilet'),
(14, 14, '- Ruang Ganti\r\n- Toliet\r\n- Tempat Makan'),
(15, 15, '- Ruang Ganti\r\n- Toilet\r\n- Kantin'),
(16, 16, '- Ruang Ganti\r\n- Toilet\r\n- Parkir'),
(17, 17, '- Ruang Ganti\r\n- Toilet'),
(18, 18, '- Ruang Ganti\r\n- Toilet');

-- --------------------------------------------------------

--
-- Table structure for table `gambar_lapangan`
--

CREATE TABLE `gambar_lapangan` (
  `id_gambar` int(9) NOT NULL,
  `id_venue` int(9) DEFAULT NULL,
  `img` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jam_operasional`
--

CREATE TABLE `jam_operasional` (
  `id_jam` int(9) NOT NULL,
  `id_venue` int(9) DEFAULT NULL,
  `buka_jam_operasional` varchar(8) DEFAULT NULL,
  `tutup_jam_operasional` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jam_operasional`
--

INSERT INTO `jam_operasional` (`id_jam`, `id_venue`, `buka_jam_operasional`, `tutup_jam_operasional`) VALUES
(1, 1, '08:00', '24:00'),
(2, 2, '08:00', '23:00'),
(3, 3, '07:00', '23:00'),
(4, 4, '08:00', '23:00'),
(5, 5, '08:00', '23:00'),
(6, 6, '24 Jam', '24 Jam'),
(7, 7, '24 jam', '24 Jam'),
(8, 8, '08:00', '24:00'),
(9, 9, '08:00', '24:00'),
(10, 10, '08:00', '24:00'),
(11, 11, '08:00', '24:00'),
(12, 12, '08:00', '24:00'),
(13, 13, '08:00', '24:00'),
(14, 14, '08:00', '23:00'),
(15, 15, '08:00', '23:00'),
(16, 16, '08:00', '24:00'),
(17, 17, '08:00', '24:00'),
(18, 18, '08:00', '24:00');

-- --------------------------------------------------------

--
-- Table structure for table `jenis_olahraga`
--

CREATE TABLE `jenis_olahraga` (
  `id_olahraga` int(9) NOT NULL,
  `id_venue` int(9) DEFAULT NULL,
  `jenis_orahraga` varchar(20) DEFAULT NULL,
  `jenis_lapangan` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jenis_olahraga`
--

INSERT INTO `jenis_olahraga` (`id_olahraga`, `id_venue`, `jenis_orahraga`, `jenis_lapangan`) VALUES
(1, NULL, 'Futsal', 'Futsal'),
(2, NULL, 'Basket', 'Basket'),
(3, NULL, 'Badminton', 'Badminton'),
(4, NULL, 'Tenis Lapangan', 'Tenis Lapangan');

-- --------------------------------------------------------

--
-- Table structure for table `venue`
--

CREATE TABLE `venue` (
  `id_venue` int(9) NOT NULL,
  `nama_venue` varchar(25) DEFAULT NULL,
  `deskripsi` varchar(100) NOT NULL,
  `alamat` varchar(150) DEFAULT NULL,
  `gmaps_longtitude` varchar(25) DEFAULT NULL,
  `gmaps_latitude` varchar(25) DEFAULT NULL,
  `kontak` varchar(20) NOT NULL,
  `jumlah_lapangan` int(5) DEFAULT NULL,
  `harga` int(20) DEFAULT NULL,
  `gambar` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `venue`
--

INSERT INTO `venue` (`id_venue`, `nama_venue`, `deskripsi`, `alamat`, `gmaps_longtitude`, `gmaps_latitude`, `kontak`, `jumlah_lapangan`, `harga`, `gambar`) VALUES
(1, 'Lampung Walk', '', 'Jl Urip Sumoharjo No. 61, Way Halim Permai, Bandar Lampung, Lampung 35133', NULL, NULL, '07215613646', 3, 100000, NULL),
(2, 'Alsha Futsal', '', 'Jl. Palapa 9 No.2, Gn. Terang, Langkapura, Bandar Lampung, Lampung 35148', NULL, NULL, '0811729348', 1, 100000, NULL),
(3, 'Azka Futsal ', '', 'Jl. Purnawirawan 1, Langkapura, Bandar Lampung, Lampung 35151', NULL, NULL, '081279616160', 1, 100000, NULL),
(4, 'The Dome Sport Arena', '', 'Jl. Pulau Morotai No 53, Jagabaya III, Sukabumi, Bandar Lampung, Lampung 35122', NULL, NULL, '081236008185', 3, 150000, NULL),
(5, 'D\'Planet Futsal Stadium', '', 'Jl Pramuka, Rajabasa, Bandar Lampung, Lampung 3515', NULL, NULL, '0822795603003', 3, 120000, NULL),
(6, 'Garuda Futsal', '', 'Jl, Karya Muda 1, Sumur Putri, Teluk Betung Utara, Bandar Lampung, Lampung 35211', NULL, NULL, '082180154665', 2, 120000, NULL),
(7, 'Golden Boy Futsal', '', 'Jl, Yos Sudarso No. 109, Sukaraja, Teluk Betung Selatan, Bandar Lampung', NULL, NULL, '0721472158', 2, 100000, NULL),
(8, 'Harmoni Futsal', '', 'Jl. Ulangan No. 8, Segala Minde, Tanjung Karang Barat, Bandar Lampung, Lampung 35111', NULL, NULL, '081279005713', 2, 100000, NULL),
(9, 'Intan Futsal', '', 'Jl. Penengahan, Tanjung Karang Pusat, Bandar Lampung, Lampung 35125', NULL, NULL, '085768108668', 1, 95000, NULL),
(10, 'Interganda Futsal Arena', '', 'Jl. Darussalam No. 82, Susunan Baru, Tanjung Karang, Bandar Lampung, Lampung 35111', NULL, NULL, '081271398385', 2, 100000, NULL),
(11, 'Lacentro Futsal', '', 'Jl. Mawar No. 28, Rawa Laut, Engal, Bandar Lampung, Lampung 35213', NULL, NULL, '0721255861', 1, 125000, NULL),
(12, 'Lampung Futsal', '', 'Jl. Abdul Muis, Gedong Meneng, Rajabasa, Bandar Lampung, :ampung 35145', NULL, NULL, '082282603432', 3, 110000, NULL),
(13, 'Kartika Futsal', '', 'Jl. Gg. Walet, Bakung, Teluk Betung Barat, Bandar Lampung, Lampung 35223', NULL, NULL, '07218018037', 2, 100000, NULL),
(14, 'Raya Futsal', '', 'Jl. Ratu Dibalau, Tanjung Seneng, Bandar Lampung, Lampung 35135', NULL, NULL, '085366248152', 3, 110000, NULL),
(15, 'Srikandi Futsal', '', 'Jl. Sumarjo, Labuhan Ratu, Kedaton, Bandar Lampung, Lampung 35132', NULL, NULL, '08978954813', 2, 90000, NULL),
(16, 'Star Futsal', '', 'Jl. Durian II, Way Dadi, Sukarame, Bandar Lampung, Lampung 35136', NULL, NULL, '085669989970', 3, 120000, NULL),
(17, 'Twin Futsal', '', 'Jl. Mata Intan, Segala Mider, Tanjung Karang Barat, Bandar Lampung, Lampung 35111', NULL, NULL, '081278383208', 2, 120000, NULL),
(18, 'Club Futsal Center', '', 'Jl. Padat Karya, Rajabada, Bandar Lampung, Lampung 35142', NULL, NULL, '082179925281', 1, 110000, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fasilitas`
--
ALTER TABLE `fasilitas`
  ADD PRIMARY KEY (`id_fasilitas`),
  ADD KEY `id_venue` (`id_venue`);

--
-- Indexes for table `gambar_lapangan`
--
ALTER TABLE `gambar_lapangan`
  ADD PRIMARY KEY (`id_gambar`),
  ADD KEY `id_venue` (`id_venue`);

--
-- Indexes for table `jam_operasional`
--
ALTER TABLE `jam_operasional`
  ADD PRIMARY KEY (`id_jam`),
  ADD KEY `venue` (`id_venue`);

--
-- Indexes for table `jenis_olahraga`
--
ALTER TABLE `jenis_olahraga`
  ADD PRIMARY KEY (`id_olahraga`),
  ADD KEY `id_venue` (`id_venue`);

--
-- Indexes for table `venue`
--
ALTER TABLE `venue`
  ADD PRIMARY KEY (`id_venue`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
