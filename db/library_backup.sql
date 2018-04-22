-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 17, 2017 at 08:55 PM
-- Server version: 5.6.37
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE `authors` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`id`, `name`, `surname`) VALUES
(1, 'Jack', 'Daniels'),
(2, 'Chyngyz', 'Aitmatov'),
(3, 'Jir', 'Akagawa'),
(4, 'Horatio', 'Alger'),
(5, 'Jeffrey', 'Archer'),
(6, 'David', 'Baldacci'),
(7, 'Stan', 'Jan Berenstain'),
(8, 'Enid', 'Blyton'),
(9, 'Norman', 'Bridwell'),
(10, 'Dan', 'Brown'),
(11, 'Edgar', 'Rice Burroughs'),
(12, 'Erskine', 'Caldwell'),
(13, 'Lewis', 'Carroll'),
(14, 'Barbara', 'Cartland'),
(15, 'Agatha', 'Christie'),
(16, 'Tom', 'Clancy'),
(17, 'Paulo', 'Coelho'),
(18, 'Jackie', 'Collins'),
(19, 'Robin', 'Cook'),
(20, 'Catherine', 'Cookson'),
(21, 'Patricia', 'Cornwell'),
(22, 'John', 'Creasey'),
(23, 'Michael', 'Crichton'),
(24, 'Clive', 'Cussler'),
(25, 'Roald', 'Dahl'),
(26, 'Janet', 'Dailey'),
(27, 'Frédéric', 'Dard'),
(28, 'EL', 'James'),
(29, 'Ian', 'Fleming'),
(30, 'Ken', 'Follett'),
(31, 'Erle', 'Stanley Gardner'),
(32, 'Anne', 'Golon'),
(33, 'Zane', 'Grey'),
(34, 'John', 'Grisham'),
(35, 'Arthur', 'Hailey'),
(36, 'Roger', 'Hargreaves'),
(37, 'Hermann', 'Hesse'),
(38, 'Eleanor', 'Hibbert'),
(39, 'Mary', 'Higgins Clark'),
(40, 'Evan', 'Hunter'),
(41, 'Jin', 'Yong'),
(42, 'Penny', 'Jordan'),
(43, 'Judith', 'Krantz'),
(44, 'Stephen', 'King'),
(45, 'Masashi', 'Kishimoto'),
(46, 'Dean', 'Koontz'),
(47, 'Louis', 'LAmour'),
(48, 'C.', 'Lewis'),
(49, 'Astrid', 'Lindgren'),
(50, 'Robert', 'Ludlum'),
(51, 'Alistair', 'MacLean'),
(52, 'Debbie', 'Macomber'),
(53, 'Ann', 'M. Martin'),
(54, 'Karl', 'May'),
(55, 'Stephenie', 'Meyer'),
(56, 'James', 'A.Michener'),
(57, 'Seiichi', 'Morimura'),
(58, 'Andrew', 'Neiderman'),
(59, 'Nicholas', 'Sparks'),
(60, 'Kyotaro', 'Nishimura'),
(61, 'Eiichiro', 'Oda'),
(62, 'Gilbert', 'Patten'),
(63, 'James', 'Patterson'),
(64, 'Beatrix', 'Potter'),
(65, 'Alexander', 'Pushkin'),
(66, 'Anne', 'Rice'),
(67, 'Harold', 'Robbins'),
(68, 'Nora', 'Roberts'),
(69, 'Denise', 'Robins'),
(70, 'J.', 'K.Rowling'),
(71, 'Richard', 'Scarry'),
(72, 'Dr.', 'Seuss'),
(73, 'William', 'Shakespeare'),
(74, 'Sidney', 'Sheldon'),
(75, 'Rytar', 'Shiba'),
(76, 'Georges', 'Simenon'),
(77, 'Frank', 'G.'),
(78, 'Wilbur', 'Smith'),
(79, 'Mickey', 'Spillane'),
(80, 'Danielle', 'Steel'),
(81, 'R.', 'L.Stine'),
(82, 'Rex', 'Stout'),
(83, 'Corín', 'Tellado'),
(84, 'J.', 'R.Tolkien'),
(85, 'Leo', 'Tolstoy'),
(86, 'Akira', 'Toriyama'),
(87, 'Yasuo', 'Uchida'),
(88, 'Gérard', 'de Villiers'),
(89, 'Edgar', 'Wallace'),
(90, 'Irving', 'Wallace'),
(91, 'Cao', 'Xueqin'),
(92, 'Eiji', 'Yoshikawa'),
(93, 'Name:', 'Surname:'),
(94, 'Azamatzhon', 'Akhunzhanov'),
(95, 'WGVRESDFCERF', 'WGVRESDFCERF'),
(96, 'WGVRESDFCERFwwdihwiaksdhiw', 'wwdihwiaksdhiw'),
(97, 'ttfsgjhdnklem;dfcml', 'ttfsgjhdnklem;dfcml'),
(98, 'testbek', 'testovna'),
(99, 'Azamatjon', 'Ahunjanov');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `book_name` varchar(300) NOT NULL,
  `author_id` int(11) NOT NULL,
  `price` float DEFAULT NULL,
  `barcode` varchar(30) NOT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `column` int(20) NOT NULL,
  `row` int(20) NOT NULL,
  `books_number` int(20) NOT NULL DEFAULT '0',
  `publisher_id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `description` varchar(1000) NOT NULL,
  `published_year` year(4) NOT NULL,
  `department_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `book_name`, `author_id`, `price`, `barcode`, `subject_id`, `column`, `row`, `books_number`, `publisher_id`, `category_id`, `description`, `published_year`, `department_id`) VALUES
(1, 'And it was all so ...', 74, 970, '05M3TFYPW0CO', 3, 95, 66, 3, 2, 14, 'Test description - And it was all so ...', 2012, 6),
(2, 'And below was the earth', 69, 202, '0SVMA4384JG4', 62, 57, 56, 8, 2, 17, 'Test description - And below was the earth', 2005, 18),
(3, 'But things went to war', 48, 324, '38ZXIUE0AM2B', 16, 46, 91, 7, 2, 5, 'Test description - But things went to war', 1998, 16),
(4, 'And Berlin was so far away', 6, 430, 'OJOI3P03R64P', 23, 76, 6, 6, 1, 20, 'Test description - And Berlin was so far away', 2007, 26),
(5, 'And the roads do not end ...', 60, 325, 'SSLJD7MVAZ4M', 63, 12, 25, 1, 2, 1, 'Test description - And the roads do not end ...', 2009, 26),
(6, 'It is my book', 99, 238, '4KP9PDSVC6OA', 34, 47, 41, 4, 1, 16, 'Test description - And now about this', 1999, 23),
(7, 'AL Ordin-Nashchokin', 41, 841, 'XQPPKEQLBR1G', 22, 14, 84, 4, 2, 12, 'Test description - AL Ordin-Nashchokin', 1995, 7),
(8, 'A.N. Ostrovsky', 2, 3, 'ME2GW5CBHHLZ', 1, 46, 60, 8, 1, 7, 'Test description - A.N. Ostrovsky', 2009, 23),
(9, 'AP Lensky', 12, 500, '9LHRERU5VWVN', 32, 66, 43, 0, 2, 4, 'Test description - AP Lensky', 2013, 6),
(10, 'A. P. Chekhov', 75, 320, '1GK76F7N6SG5', 40, 59, 24, 8, 1, 3, 'Test description - A. P. Chekhov', 1982, 17),
(11, 'A. P. Chekhov', 30, 766, 'F4X3501BCIPE', 26, 39, 86, 0, 2, 12, 'Test description - A. P. Chekhov', 2005, 12),
(12, 'AS Ter-Oganyan: Life, Fate and Contemporary Art', 29, 76, 'I0U8KIZWM8Z4', 24, 78, 5, 9, 1, 13, 'Test description - AS Ter-Oganyan: Life, Fate and Contemporary Art', 2005, 7),
(13, 'A.I. Kuinji', 9, 612, '8VMI60E1XC4K', 61, 28, 3, 0, 1, 3, 'Test description - A.I. Kuinji', 1980, 27),
(15, 'A.S. Pushkin in the memoirs of his contemporaries. Volume 2', 18, 535, 'NP05OQS2KBR1', 8, 81, 31, 2, 2, 9, 'Test description - A.S. Pushkin in the memoirs of his contemporaries. Volume 2', 1985, 2),
(16, 'Abakumov Viktor Semenovich. Assistant Beria', 28, 115, '0NR6MNILC03A', 46, 24, 19, 3, 2, 3, 'Test description - Abakumov Viktor Semenovich. Assistant Beria', 2008, 10),
(17, 'Abalkin Leonid Ivanovich. Assistant to Brezhnev', 38, 28, 'SY0FX1C8JPF0', 19, 94, 42, 8, 2, 4, 'Test description - Abalkin Leonid Ivanovich. Assistant to Brezhnev', 2006, 18),
(19, 'The Abwehr: the shield and sword of the Third Reich', 83, 471, 'IUV3RKH1IVOO', 62, 55, 8, 3, 2, 8, 'Test description - The Abwehr: the shield and sword of the Third Reich', 1976, 28),
(20, 'Abd al-Qadir', 88, 283, 'LAW7DGX22JL2', 31, 57, 20, 9, 1, 16, 'Test description - Abd al-Qadir', 1988, 28),
(21, 'Abel - Fisher', 3, 327, '4DOCNGLCJBG6', 32, 89, 79, 7, 2, 11, 'Test description - Abel - Fisher', 2004, 29),
(22, 'Abel Parker Apsher.Gos.sekretar US under President John Tyler', 73, 418, 'OJOUWXXJA4OB', 46, 96, 90, 0, 2, 8, 'Test description - Abel Parker Apsher.Gos.sekretar US under President John Tyler', 1975, 3),
(23, 'The Abyssinians. The descendants of King Solomon', 74, 432, 'UVB6LXHRU0QV', 16, 78, 45, 8, 1, 15, 'Test description - The Abyssinians. The descendants of King Solomon', 1991, 13),
(24, 'Abram Hannibal: The Black Ancestor of Pushkin', 42, 182, 'XOBOZND4WLCR', 3, 88, 19, 5, 2, 18, 'Test description - Abram Hannibal: The Black Ancestor of Pushkin', 1984, 27),
(25, 'The Absolute Weapon of America', 47, 205, 'WCHJX9CI2PH4', 53, 85, 11, 6, 1, 4, 'Test description - The Absolute Weapon of America', 1987, 10),
(26, 'Abu-Nasr al-Farabi', 68, 171, '52HZA6IG1RJD', 50, 10, 45, 1, 1, 17, 'Test description - Abu-Nasr al-Farabi', 2000, 26),
(27, 'Adventurers of the Civil War: A Historical Investigation', 66, 89, 'BK10FZMGNXWF', 57, 72, 31, 8, 1, 10, 'Test description - Adventurers of the Civil War: A Historical Investigation', 1976, 12),
(28, 'slfhiskjdfbvh sjxcb vbnc ', 15, 968, '9AEDB7KJSMEE', 15, 71, 94, 5, 1, 10, 'Test description - Adventurers of the Civil War: A Historical Investigation', 1997, 11),
(29, 'Habakkuk', 50, 7, '3VIOOJULO2HM', 59, 50, 91, 5, 2, 18, 'Test description - Habakkuk', 1982, 17),
(30, 'Avvakum Petrovich (Biographical note)', 16, 769, 'L9BZ0MBYBHW8', 13, 30, 48, 4, 1, 14, 'Test description - Avvakum Petrovich (Biographical note)', 1994, 25),
(31, 'Avvakum Petrovich (Biographical note)', 47, 464, '8BXLLL4BKV35', 50, 24, 16, 8, 1, 3, 'Test description - Avvakum Petrovich (Biographical note)', 1995, 15),
(32, 'August', 69, 708, 'W8SHMJ94LG57', 25, 13, 82, 3, 1, 13, 'Test description - August', 1978, 26),
(33, 'August Bebel', 10, 804, 'FLJZNS1U3RS8', 50, 6, 4, 7, 1, 14, 'Test description - August Bebel', 1996, 3),
(34, 'August master of survival. Life of Charles II', 53, 531, 'XEETINIM8AES', 18, 79, 82, 0, 1, 11, 'Test description - August master of survival. Life of Charles II', 1996, 17),
(35, 'Aircraft designer A. S. Moskalyov. On the 95th anniversary of his birth', 39, 72, 'FQEQZYOS9BG9', 41, 14, 23, 7, 2, 6, 'Test description - Aircraft designer A. S. Moskalyov. On the 95th anniversary of his birth', 2002, 22),
(36, 'Aircraft carrier', 30, 451, 'ILNSB1HGX0FM', 36, 60, 77, 4, 1, 3, 'Test description - Aircraft carrier', 2012, 27),
(37, 'Aircraft Carriers, Volume 1', 78, 625, '3IQ50FOU8OUJ', 15, 74, 67, 0, 2, 20, 'Test description - Aircraft Carriers, Volume 1', 1999, 14),
(38, 'Aircraft Carriers, Volume 2', 57, 830, 'NIFFDVMUXY1G', 33, 10, 71, 9, 1, 4, 'Test description - Aircraft Carriers, Volume 2', 1999, 13),
(39, 'Aviation and nuclear tests', 42, 955, 'PXMMFB4LMJ5O', 60, 10, 98, 2, 1, 11, 'Test description - Aviation and nuclear tests', 1994, 26),
(40, 'Avitaminosis', 31, 144, 'Z7K91P9LL207', 31, 84, 29, 6, 1, 9, 'Test description - Avitaminosis', 1994, 2),
(41, 'Avicenna', 19, 638, 'M9CVK4LOHTJX', 12, 52, 95, 5, 1, 11, 'Test description - Avicenna', 2008, 19),
(42, 'Abraham Lincoln. His life and social activities', 33, 982, 'N3BWC5FBPMA1', 20, 55, 40, 5, 2, 14, 'Test description - Abraham Lincoln. His life and social activities', 1986, 28),
(43, 'Australian Studies', 4, 291, 'PDUQWAQO1YMU', 43, 44, 87, 1, 1, 19, 'Test description - Australian Studies', 2010, 1),
(44, 'Australian Robinzone', 77, 781, '6BZIOAPQNFDV', 49, 96, 60, 7, 1, 4, 'Test description - Australian Robinzone', 2007, 4),
(45, 'Autobiographical note. Memories', 39, 557, '5JOWPIEJT2H8', 48, 24, 6, 1, 2, 15, 'Test description - Autobiographical note. Memories', 2003, 17),
(46, 'Autobiographical novel', 25, 897, 'JQZSW1FXVIIN', 52, 39, 55, 3, 2, 9, 'Test description - Autobiographical novel', 2015, 15),
(47, 'Autobiographical prose', 81, 117, 'ZNIAPOKO3CV6', 59, 15, 14, 3, 1, 13, 'Test description - Autobiographical prose', 2011, 24),
(48, 'Autobiography', 43, 799, 'ZCOK7G13K41Q', 11, 85, 35, 6, 1, 14, 'Test description - Autobiography', 1994, 19),
(49, 'Autobiography', 11, 173, 'LJE8V9FP0WTT', 52, 92, 78, 2, 2, 20, 'Test description - Autobiography', 1976, 22),
(50, 'Autobiography', 46, 340, 'N16EHCKDFM06', 38, 72, 42, 7, 2, 16, 'Test description - Autobiography', 1995, 21),
(51, 'Autobiography', 16, 288, 'PYLKRLP0SPEY', 46, 76, 2, 2, 1, 14, 'Test description - Autobiography', 1999, 12),
(52, 'Autobiography', 36, 605, 'M21GV4NN0T8G', 17, 75, 42, 4, 2, 16, 'Test description - Autobiography', 1989, 25),
(53, 'Autobiography', 32, 19, 'YKUX69TEKPIX', 34, 56, 96, 6, 2, 1, 'Test description - Autobiography', 2001, 5),
(54, 'Autobiography', 84, 340, 'XB3P0B8HP0VQ', 32, 65, 16, 1, 2, 6, 'Test description - Autobiography', 2008, 4),
(55, 'Autobiography', 6, 460, 'IKLUJRRZAMCB', 54, 5, 68, 8, 1, 16, 'Test description - Autobiography', 2007, 2),
(56, 'Autobiography', 41, 152, 'BTFTRV8TYUDM', 12, 15, 22, 9, 2, 19, 'Test description - Autobiography', 1988, 25),
(57, 'Autobiography', 73, 265, 'VLQCM03OC0GA', 39, 50, 71, 2, 1, 14, 'Test description - Autobiography', 1993, 7),
(58, 'Autobiography', 90, 953, '60TBORUTV5R4', 7, 71, 56, 6, 1, 2, 'Test description - Autobiography', 2006, 16),
(59, 'Autobiography', 54, 586, 'GBFBTPNMCDW8', 43, 28, 15, 7, 1, 20, 'Test description - Autobiography', 1979, 3),
(60, 'Autobiography', 18, 820, 'YFY3DQY26J8Z', 33, 5, 17, 1, 2, 5, 'Test description - Autobiography', 1983, 20),
(61, 'Autobiography', 32, 346, 'LSBYZWQ9QN0E', 3, 29, 5, 7, 1, 3, 'Test description - Autobiography', 1999, 8),
(62, 'Autobiography', 57, 338, 'WI7GJ6DA40SU', 31, 52, 80, 5, 2, 10, 'Test description - Autobiography', 1996, 8),
(63, 'Autobiography', 15, 176, 'ESXWN0LSHQRQ', 29, 20, 73, 6, 2, 7, 'Test description - Autobiography', 1980, 12),
(64, 'Autobiography', 54, 903, 'YTEAZZ7AZXPN', 7, 96, 26, 0, 2, 18, 'Test description - Autobiography', 2010, 9),
(65, 'Autobiography', 69, 964, '0Z3CUXV612YY', 44, 71, 18, 9, 1, 20, 'Test description - Autobiography', 1997, 6),
(66, 'Autobiography', 46, 739, 'DHEBN0XSNDQ1', 54, 63, 50, 4, 1, 19, 'Test description - Autobiography', 2005, 4),
(67, 'Autobiography', 13, 748, 'D3V6IRS69KN8', 47, 8, 86, 4, 1, 17, 'Test description - Autobiography', 2001, 28),
(68, 'Autobiography (for the Sakharov Collection)', 78, 469, 'IPAKHI28LVTH', 35, 3, 70, 5, 1, 1, 'Test description - Autobiography (for the Sakharov Collection)', 1976, 14),
(69, 'Autobiography of Tamerlane', 31, 212, 'J7AI3Y81RDMY', 40, 91, 68, 5, 1, 19, 'Test description - Autobiography of Tamerlane', 1991, 13),
(70, 'Autobiography: Moab - washing bowl washing', 69, 850, 'AKZN3N68XXF1', 38, 68, 50, 8, 1, 7, 'Test description - Autobiography: Moab - washing bowl washing', 2006, 24),
(71, 'Autobiography.', 55, 178, 'HQLUZ5BVCUN2', 42, 98, 60, 6, 2, 20, 'Test description - Autobiography.', 1975, 23),
(72, 'Autobiography. Autologous', 79, 774, 'LRIY8X89M8AA', 33, 24, 73, 9, 2, 13, 'Test description - Autobiography. Autologous', 1996, 15),
(73, 'Autobiography. Old pepper shaker', 24, 759, 'JRFAC15L5HQB', 53, 23, 63, 0, 1, 11, 'Test description - Autobiography. Old pepper shaker', 2008, 5),
(74, 'Autobiographies', 42, 176, '969Q6IKPGB4D', 37, 91, 82, 3, 2, 15, 'Test description - Autobiographies', 2010, 5),
(75, '?????????????', 87, 412, '0OS6VAKVHJL2', 56, 70, 44, 2, 2, 8, 'Test description - ?????????????', 2006, 11),
(76, 'The Kalashnikov assault rifle. Symbol of Russia', 39, 346, 'H2VUSTLJW2VB', 26, 21, 96, 2, 1, 8, 'Test description - The Kalashnikov assault rifle. Symbol of Russia', 2014, 9),
(77, 'Cars of the Soviet Army 1946-1991', 91, 80, 'KWJREOZNPFWC', 43, 7, 88, 1, 1, 4, 'Test description - Cars of the Soviet Army 1946-1991', 2016, 11),
(78, 'Autonomous swimming', 37, 75, 'CXM7MHFWPYQ3', 29, 29, 24, 8, 2, 8, 'Test description - Autonomous swimming', 1995, 26),
(79, 'Self-portrait in faces. Human text. Book 2', 33, 436, 'LRSZ7BB6KPPC', 39, 22, 8, 0, 1, 4, 'Test description - Self-portrait in faces. Human text. Book 2', 1980, 13),
(80, 'Self-portrait: The romance of my life', 78, 478, 'AZCSG7KTXBYM', 41, 76, 20, 4, 1, 12, 'Test description - Self-portrait: The romance of my life', 2012, 13),
(81, 'Agasfer from the constellation Cygnus', 90, 366, 'HYBWTRCZ6FW6', 46, 59, 96, 6, 2, 1, 'Test description - Agasfer from the constellation Cygnus', 2007, 24),
(82, 'Agatha Christie', 49, 708, 'B87XVOX5E826', 19, 93, 36, 4, 2, 1, 'Test description - Agatha Christie', 2003, 27),
(83, 'Agatha Christie. The English Secret', 56, 665, 'WC0C0EPPNXX8', 41, 2, 67, 2, 2, 10, 'Test description - Agatha Christie. The English Secret', 2003, 4),
(84, 'Agent Zigzag. The true military history of Eddie Chapman, lover, traitor, hero and spy', 88, 1, 'PP0SD78RSBDY', 3, 5, 66, 8, 2, 4, 'Test description - Agent Zigzag. The true military history of Eddie Chapman, lover, traitor, hero and spy', 2016, 25),
(85, 'Agents are not born', 78, 936, 'PI9LV4MBCK34', 30, 96, 96, 3, 1, 13, 'Test description - Agents are not born', 1998, 24),
(86, 'Agents of the Comintern. Soldiers of the world revolution.', 54, 355, 'J4VLUPM202SH', 11, 54, 76, 3, 2, 6, 'Test description - Agents of the Comintern. Soldiers of the world revolution.', 2006, 19),
(87, 'Agents of Russia', 64, 513, '4C2IUXAYWNGA', 39, 43, 25, 2, 1, 3, 'Test description - Agents of Russia', 2003, 21),
(88, 'Agitator of United Russia: Questions Answers', 13, 692, 'LJV9VRY32S9F', 26, 4, 39, 2, 1, 8, 'Test description - Agitator of United Russia: Questions Answers', 2013, 18),
(89, 'Agni Yoga. Ekita lives in space.', 51, 357, '28J1AM3CJHDV', 31, 29, 47, 3, 1, 14, 'Test description - Agni Yoga. Ekita lives in space.', 1981, 28),
(90, 'The agony of the USSR. I witnessed the murder of the Superpower', 10, 178, 'YFD7SS7BQMA9', 17, 95, 25, 6, 1, 6, 'Test description - The agony of the USSR. I witnessed the murder of the Superpower', 2006, 27),
(91, 'The agony of Stalingrad. The Volga flows in blood', 8, 541, 'Z6C71DZNW5TX', 1, 69, 87, 3, 1, 16, 'Test description - The agony of Stalingrad. The Volga flows in blood', 1992, 22),
(92, 'Aggression. Declassified documents of the Foreign Intelligence Service of the Russian Federation 1939-1941', 22, 428, 'Q9SP41GYU7GS', 17, 38, 9, 5, 2, 14, 'Test description - Aggression. Declassified documents of the Foreign Intelligence Service of the Russian Federation 1939-1941', 1990, 28),
(93, 'Adam Mickiewicz. His life and literary activity', 13, 875, 'XQHU0YRN8IKA', 30, 20, 69, 7, 1, 19, 'Test description - Adam Mickiewicz. His life and literary activity', 1991, 16),
(94, 'Adam Smith', 49, 203, 'VRN02PFA9MIQ', 49, 1, 69, 3, 2, 4, 'Test description - Adam Smith', 1980, 23),
(95, 'Adam Smith. His life and scientific activity', 23, 516, '0ZDGT7XG6A2H', 59, 26, 43, 7, 2, 19, 'Test description - Adam Smith. His life and scientific activity', 1975, 9),
(96, 'Adam Worth is a prototype of Professor Moriarty', 32, 696, 'DIEWH3TBA23S', 1, 38, 56, 0, 2, 20, 'Test description - Adam Worth is a prototype of Professor Moriarty', 1985, 5),
(97, 'Adenauer. Father of the new Germany', 21, 693, 'UC6IWA02QVPC', 23, 55, 33, 2, 1, 2, 'Test description - Adenauer. Father of the new Germany', 1991, 25),
(98, 'Adzhimushkai', 46, 512, 'R2NXZG4AMJIO', 39, 48, 97, 1, 2, 5, 'Test description - Adzhimushkai', 2015, 25),
(99, 'Admiral Arseniy Golovko', 78, 522, 'HH24RPBW2TXN', 23, 56, 70, 5, 2, 1, 'Test description - Admiral Arseniy Golovko', 2001, 20),
(100, 'Admiral GI Butakov', 48, 581, 'ZPYKYWXSZ8QN', 18, 97, 41, 7, 2, 7, 'Test description - Admiral GI Butakov', 2016, 23),
(101, 'Admiral Horatio Nelson', 83, 716, '3K6NZRWNO14O', 35, 22, 88, 5, 1, 8, 'Test description - Admiral Horatio Nelson', 2010, 28),
(102, 'Admiral David Beatty and the British Navy in the first half of the twentieth century', 55, 262, 'VOUW2DFUH7FL', 18, 56, 57, 1, 1, 15, 'Test description - Admiral David Beatty and the British Navy in the first half of the twentieth century', 1998, 11),
(103, 'Admiral David Beatty. The history of the British fleet in the late XIX - early XX century.', 85, 366, 'YQX0GWJX1L8I', 63, 74, 4, 6, 1, 13, 'Test description - Admiral David Beatty. The history of the British fleet in the late XIX - early XX century.', 1989, 28),
(104, 'Admiral Kolchak', 19, 620, 'EJG9W9J1WHF6', 12, 83, 73, 9, 1, 1, 'Test description - Admiral Kolchak', 2007, 24),
(105, 'Admiral Kolchak, the supreme ruler of Russia', 32, 445, 'ABIWDCRYKTVZ', 35, 56, 62, 1, 1, 4, 'Test description - Admiral Kolchak, the supreme ruler of Russia', 2008, 4),
(106, 'ADMIRAL KOLCHAK: TRUTH AND MYTHS', 69, 120, 'EC4LIHSC75EH', 59, 58, 45, 4, 2, 6, 'Test description - ADMIRAL KOLCHAK: TRUTH AND MYTHS', 1980, 2),
(107, 'Admiral Kolchak. Life, feat, memoryAdmiral Kolchak. Life, feat, memory', 19, 754, 'PM0UA7XDOI6H', 35, 30, 53, 4, 2, 12, 'Test description - Admiral Kolchak. Life, feat, memory', 1989, 26),
(108, 'Admiral Kolchak. Unknown about the known', 2, 372, 'BPRDFR1IZFY5', 42, 54, 55, 8, 1, 20, 'Test description - Admiral Kolchak. Unknown about the known', 2003, 12),
(109, 'Admiral Kolchak. Minutes of the interrogation.', 15, 893, 'YVJNNDA52RKJ', 30, 98, 86, 8, 2, 15, 'Test description - Admiral Kolchak. Minutes of the interrogation.', 1978, 10),
(110, 'Admiral Kornilov', 51, 70, 'Z65DP1HJYNX6', 57, 26, 20, 2, 2, 13, 'Test description - Admiral Kornilov', 1982, 10),
(111, 'Admiral Kuznetsov', 3, 763, 'ILXF15KCHJZJ', 17, 8, 41, 8, 1, 15, 'Test description - Admiral Kuznetsov', 1980, 20),
(112, 'Admiral Makarov', 10, 938, 'HIS2EJBGOFTL', 61, 2, 46, 5, 2, 16, 'Test description - Admiral Makarov', 2006, 14),
(113, 'Admiral Makarov', 12, 663, 'SJ5O9LF3YN22', 56, 68, 40, 1, 1, 8, 'Test description - Admiral Makarov', 2006, 6),
(114, 'Admiral Michael de Ruyter', 37, 456, 'Y7ZPO90JRXL4', 6, 27, 97, 0, 1, 2, 'Test description - Admiral Michael de Ruyter', 2008, 18),
(115, 'Admiral Nakhimov', 86, 487, 'RPREUI1U32TD', 50, 99, 9, 7, 2, 6, 'Test description - Admiral Nakhimov', 2015, 16),
(116, 'Admiral Nelson', 83, 891, 'AG2MX6JQASZA', 37, 80, 18, 2, 1, 16, 'Test description - Admiral Nelson', 1991, 23),
(117, 'Admiral Nelson. Hero and lover', 14, 136, '0TSFTLDEDA8W', 25, 89, 33, 6, 2, 21, 'Test description - Admiral Nelson. Hero and lover', 1992, 8),
(118, 'Admiral Nimitz', 5, 996, '36SA2NJZ3CVX', 35, 75, 64, 1, 1, 9, 'Test description - Admiral Nimitz', 1989, 2),
(119, 'Admiral Oktyabrsky against Mussolini', 62, 143, 'WNHW3HY37D4H', 11, 78, 82, 7, 1, 21, 'Test description - Admiral Oktyabrsky against Mussolini', 1994, 10),
(120, 'Admiral of the Soviet Union', 30, 184, 'BGVDCI96I1BX', 38, 77, 11, 3, 1, 5, 'Test description - Admiral of the Soviet Union', 1981, 14),
(121, 'Admiral of the Soviet Navy', 15, 771, 'A5UISC1RXP81', 32, 30, 66, 2, 2, 17, 'Test description - Admiral of the Soviet Navy', 1994, 13),
(122, 'Admiral of the FSB (Hero of Russia Herman Ugryumov)', 33, 129, '6VDTGRIN7DYH', 46, 65, 75, 7, 2, 11, 'Test description - Admiral of the FSB (Hero of Russia Herman Ugryumov)', 2004, 21),
(123, 'Admiral of the FSB. Documentary novel', 52, 870, '1AX1176WVMX0', 25, 13, 12, 3, 1, 17, 'Test description - Admiral of the FSB. Documentary novel', 1992, 9),
(124, 'Admiral Andrew Cunningham', 60, 469, '15DQ22R119GB', 63, 42, 69, 2, 1, 3, 'Test description - Admiral Andrew Cunningham', 2007, 8),
(125, 'Admiral Yamamoto. The path of a samurai who defeated Pearl Harbor. 1921-1943', 37, 896, '2RBYV86MZGY8', 7, 25, 31, 1, 1, 2, 'Test description - Admiral Yamamoto. The path of a samurai who defeated Pearl Harbor. 1921-1943', 1987, 11),
(126, 'Admiral.The tragic fate of Kolchak', 73, 481, '3TU5CNQX6W0G', 13, 24, 83, 4, 1, 19, 'Test description - Admiral.The tragic fate of Kolchak', 1989, 4),
(127, 'Admiral routes (or outbreaks of memory and information from outside)', 92, 932, 'OWZIX15MOKMF', 13, 92, 61, 8, 1, 3, 'Test description - Admiral routes (or outbreaks of memory and information from outside)', 1999, 15),
(128, 'Adolf Hitler (Volume 1)', 13, 98, 'OSEQHJ96TKQH', 63, 60, 25, 5, 2, 5, 'Test description - Adolf Hitler (Volume 1)', 1997, 4),
(129, 'Adolf Hitler (Volume 2)', 14, 346, 'S2TXOMYZ52XL', 8, 75, 32, 1, 1, 1, 'Test description - Adolf Hitler (Volume 2)', 2003, 1),
(130, 'Adolf Hitler (Volume 3)', 68, 750, 'GAFKENEGLV46', 39, 4, 13, 5, 2, 7, 'Test description - Adolf Hitler (Volume 3)', 2005, 23),
(131, 'Adolf Quetelet. His life and scientific activity', 67, 655, 'B5A9X6FXRF36', 24, 97, 97, 2, 1, 2, 'Test description - Adolf Quetelet. His life and scientific activity', 1990, 26),
(132, 'Addresses and dates', 55, 755, 'J9MRF1P11HLD', 40, 36, 13, 7, 1, 5, 'Test description - Addresses and dates', 2016, 5),
(133, 'Address book', 72, 550, 'NQMO04RGPBH4', 61, 12, 74, 5, 1, 15, 'Test description - Address book', 1978, 27),
(134, 'Hells Island. Soviet prison in the far north', 3, 290, 'IP9Z2CXNCGHK', 58, 42, 51, 7, 2, 19, 'Test description - Hells Island. Soviet prison in the far north', 1978, 9),
(135, 'Azart (lane V. Shibaev)', 26, 779, 'PF3CFXOHRJKW', 46, 45, 96, 3, 1, 4, 'Test description - Azart (lane V. Shibaev)', 1981, 18),
(136, 'ABC', 5, 156, '0T6LNUW48EOK', 42, 23, 48, 6, 2, 6, 'Test description - ABC', 1984, 7),
(137, 'The ABC of my life', 77, 422, 'GU5LKENS8WMM', 61, 52, 62, 3, 1, 11, 'Test description - The ABC of my life', 1997, 6),
(138, 'Azef', 52, 23, '4KMKLD3W2YY8', 40, 62, 17, 1, 2, 13, 'Test description - Azef', 1977, 8),
(139, 'Asian attraction', 89, 714, 'LA2UV9DK0JTT', 31, 20, 77, 6, 2, 13, 'Test description - Asian attraction', 1991, 22),
(140, 'The Azimuth of the \"Ural Pathfinder\"', 68, 142, 'DSS35P8XQDEE', 36, 25, 81, 5, 2, 21, 'Test description - The Azimuth of the \"Ural Pathfinder\"', 2007, 28),
(141, 'Asia. Time of beauty', 80, 930, 'IUH9SNHOOQWT', 62, 40, 86, 4, 1, 7, 'Test description - Asia. Time of beauty', 1984, 19),
(142, 'Azores', 62, 564, 'F3SV624U0YMW', 52, 13, 87, 6, 1, 6, 'Test description - Azores', 2016, 7),
(143, 'Aivazovsky', 67, 376, 'KQAT69OUDYWU', 1, 72, 14, 1, 1, 1, 'Test description - Aivazovsky', 2005, 8),
(144, 'Aivazovsky', 36, 396, 'EA71890O8W8J', 17, 41, 55, 0, 1, 12, 'Test description - Aivazovsky', 1996, 15),
(145, 'Ides cool. Essay on the life and work of Vladislav Khodasevich (1886-1939)', 6, 320, '2N9DPQQVZQFC', 11, 26, 37, 5, 2, 9, 'Test description - Ides cool. Essay on the life and work of Vladislav Khodasevich (1886-1939)', 2016, 24),
(146, 'Ayni', 33, 337, 'KPIR607AWJKE', 23, 22, 86, 0, 2, 4, 'Test description - Ayni', 2005, 17),
(147, 'Isadora Duncan: the novel of one life', 16, 250, 'BI03WQJAD01U', 6, 83, 37, 5, 1, 4, 'Test description - Isadora Duncan: the novel of one life', 2003, 11),
(148, 'Academician from Cherepovets', 68, 960, '5TGHCHNUU2HG', 56, 30, 69, 1, 2, 21, 'Test description - Academician from Cherepovets', 1979, 4),
(149, 'Academician Landau; How we lived', 29, 590, '0117UV8B33TU', 51, 29, 27, 5, 2, 3, 'Test description - Academician Landau; How we lived', 2002, 15),
(150, 'Academician Landau. How we lived', 53, 447, '55HGVI96E1HA', 55, 19, 47, 4, 1, 10, 'Test description - Academician Landau. How we lived', 1999, 4),
(151, 'Academician Nikolai Amosov: \"I live in Russian interests\": [based on the materials of the conversation with NM Amosov]', 2, 468, 'YBU1UYHUZ5CM', 42, 20, 89, 0, 2, 21, 'Test description - Academician Nikolai Amosov: \"I live in Russian interests\": [based on the materials of the conversation with NM Amosov]', 1997, 2),
(152, 'Academician S.P. Korolev', 20, 923, '2XFRN4DQ7YI5', 13, 19, 85, 0, 2, 21, 'Test description - Academician S.P. Korolev', 1976, 27),
(153, 'Aquarium - 3', 44, 760, 'I2NJ4SQVDXEA', 38, 47, 50, 2, 1, 8, 'Test description - Aquarium - 3', 1989, 13),
(154, 'Aquarium as a way to care for a tennis court', 44, 365, '842TTKVGFRR0', 18, 31, 95, 3, 1, 12, 'Test description - Aquarium as a way to care for a tennis court', 1977, 14),
(155, 'Aquarium-2', 34, 651, 'VCWM8VV3BFNE', 10, 73, 41, 4, 1, 8, 'Test description - Aquarium-2', 2005, 2),
(156, 'Aksakov', 27, 928, 'HUWAR96GE8TC', 44, 5, 67, 3, 2, 4, 'Test description - Aksakov', 2015, 20),
(157, 'Aksakovs. Their life and literary activity', 14, 291, 'VZYZ3KJXZIO4', 39, 97, 87, 8, 1, 10, 'Test description - Aksakovs. Their life and literary activity', 1994, 13),
(158, 'Axel Berg', 24, 607, 'ZH5EP9BVS53L', 27, 50, 30, 8, 1, 5, 'Test description - Axel Berg', 2000, 28),
(159, 'Aksenov', 57, 415, 'FR6D7I9N8QW1', 57, 9, 69, 7, 1, 8, 'Test description - Aksenov', 1990, 18),
(160, 'Aksenov', 5, 618, 'G4WY50AQUSNR', 38, 73, 67, 6, 1, 5, 'Test description - Aksenov', 2008, 6),
(161, 'Aksenov', 87, 61, 'DY85G6V1BDH5', 46, 97, 8, 7, 2, 11, 'Test description - Aksenov', 2001, 1),
(162, 'Actor Rakhimov', 79, 435, 'CDMT77IDGMTC', 23, 89, 25, 1, 2, 2, 'Test description - Actor Rakhimov', 1994, 15),
(163, 'Acting book', 81, 885, 'RQQ8XGOEQDKT', 28, 30, 63, 4, 2, 5, 'Test description - Acting book', 2013, 16),
(164, 'Actors of foreign cinema. Issue 3', 45, 647, '40538AFHJ160', 35, 84, 44, 8, 2, 7, 'Test description - Actors of foreign cinema. Issue 3', 1977, 5),
(165, 'Actors of our cinema. Sukhorukov, Khabensky and others', 77, 796, '8X9NAFWK49GL', 25, 48, 34, 5, 2, 10, 'Test description - Actors of our cinema. Sukhorukov, Khabensky and others', 2006, 5),
(166, 'Alexander I', 28, 694, '0BUFHWI1IT2B', 46, 26, 88, 5, 1, 19, 'Test description - Alexander I', 1989, 8),
(167, 'Alexander I', 80, 344, '09EYSCXBCN0L', 57, 76, 68, 0, 1, 19, 'Test description - Alexander I', 1995, 24),
(168, 'Alexander I', 47, 867, 'MMMJH003N3G0', 10, 39, 5, 6, 1, 8, 'Test description - Alexander I', 2015, 12),
(169, 'Alexander I. The most mysterious emperor of Russia', 57, 989, 'AWZ01LFK1FST', 32, 40, 87, 3, 1, 9, 'Test description - Alexander I. The most mysterious emperor of Russia', 2011, 14),
(170, 'Alexander I. The Sphinx on the throne', 71, 22, 'RWAJ5ZL7542Y', 9, 11, 11, 8, 1, 4, 'Test description - Alexander I. The Sphinx on the throne', 2003, 6),
(171, 'Alexander II', 74, 412, 'VPOFJA3HQKWN', 14, 55, 66, 1, 2, 16, 'Test description - Alexander II', 1988, 16),
(172, 'Alexander II, or The History of Three Solitudes', 2, 330, '0ZKG8DGU6989', 10, 66, 88, 6, 1, 1, 'Test description - Alexander II, or The History of Three Solitudes', 1987, 4),
(173, 'Alexander II. Life and death', 86, 555, 'OW8TYX1IHVM7', 36, 63, 30, 1, 1, 7, 'Test description - Alexander II. Life and death', 1981, 25),
(174, 'Alexander III', 61, 97, 'FDJ1LNSW1TFM', 12, 70, 93, 8, 2, 2, 'Test description - Alexander III', 2011, 3),
(175, 'Alexander III - Peacemaker. 1881-18', 83, 68, 'MDNZUDL460GS', 16, 49, 22, 8, 1, 6, 'Test description - Alexander III - Peacemaker. 1881-18', 2012, 22),
(176, 'testbook', 98, 12, 'lqkjeo32jh4roni4', 2, 65, 85, 3, 13, 4, 'its a test', 2017, 8);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(450) NOT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `description`, `parent_id`) VALUES
(1, 'Scientific', 'Scientific books catalogue', 8),
(2, 'Readable', 'Bunch of readdable books', 8),
(3, 'Not Readable', 'Bunch of not readable books', NULL),
(4, 'Another', 'Another category', 5),
(5, 'Fantasy - fantasy', 'Test description - Fantasy - fantasy', NULL),
(6, 'Books about love, novels', 'Test description - Books about love, novels', NULL),
(7, 'Business literature', 'Test description - Business literature', NULL),
(8, 'Internet computers', 'Test description - Internet computers', NULL),
(9, 'Humorous literature', 'Test description - Humorous literature', NULL),
(10, 'Books about the house and family Book ', 'Test description - Books about the house and family', NULL),
(11, 'Childrens literature', 'Test description - Childrens literature', NULL),
(12, 'Documental literature', 'Test description - Documental literature', NULL),
(13, 'Detectives - thrillers', 'Test description - Detectives - thrillers', NULL),
(14, 'Genre is not defined', 'Test description - Genre is not defined', NULL),
(15, 'Religious literature', 'Test description - Religious literature', NULL),
(16, 'Poetry and dramaturgy', 'Test description - Poetry and dramaturgy', NULL),
(17, 'Vintage books', 'Test description - Vintage books', NULL),
(18, 'References', 'Test description - References', NULL),
(19, 'Adventure', 'Test description - Adventure', NULL),
(20, 'Scientific and educational', 'Test description - Scientific and educational', NULL),
(21, 'Prose', 'Test description - Prose', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) NOT NULL,
  `faculty_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`id`, `name`, `code`, `faculty_id`) VALUES
(1, 'Computer Science', '', 1),
(2, 'Electronics and Nanoelectronics Engineering', '', 1),
(3, 'Applied Mathematics & Informatics', '', 1),
(4, 'Turkology', '', 2),
(5, 'Chineese Language and Translation', '', 2),
(6, 'Simultaneous Translation ', '', 2),
(7, 'English Language and Literature', '', 2),
(8, 'Pedagogy', '', 2),
(9, 'Psychology', '', 2),
(10, 'Journalism', '', 2),
(11, 'World Economy and International Business ', '', 3),
(12, 'International Relations', '', 3),
(13, 'Management', '', 3),
(14, 'Finance & Banking', '', 3),
(15, 'Law', '', 3),
(16, 'Accounting and audit', '', 3),
(17, 'Industrial Engineering', '', 3),
(18, 'General Medicine - Pediatrics', '', 4),
(19, 'Economy and Accounting', '', 5),
(20, 'Banking', '', 5),
(21, 'Marketing', '', 5),
(22, 'Computer Systems and Complexes', '', 5),
(23, 'Software engineer', '', 5),
(24, 'Design', '', 5),
(25, 'Jurisprudence', '', 5),
(26, 'Kyrgyz Language Teaching', '', 6),
(27, 'Russian Language Teaching', '', 6),
(28, 'Turkish Language Teaching', '', 6),
(29, 'English Language Teaching', '', 6);

-- --------------------------------------------------------

--
-- Table structure for table `faculties`
--

CREATE TABLE `faculties` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `faculties`
--

INSERT INTO `faculties` (`id`, `name`, `code`) VALUES
(1, 'New Technologies', 'NT'),
(2, 'Social Sciences', 'SS'),
(3, 'Economics and Administrative Sciences', 'EC'),
(4, 'Medicine', 'MED'),
(5, 'Vocational education', 'VE'),
(6, 'Preparatory School', 'PS');

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `add_book` int(1) NOT NULL DEFAULT '0',
  `is_recieve` int(1) NOT NULL DEFAULT '0',
  `is_get` int(1) NOT NULL DEFAULT '0',
  `extend_num` int(2) NOT NULL DEFAULT '0',
  `is_extend` int(1) NOT NULL DEFAULT '0',
  `extend_time` int(11) NOT NULL DEFAULT '0',
  `delete_book` int(1) NOT NULL DEFAULT '0',
  `can_pay` int(1) NOT NULL DEFAULT '0',
  `fine_amount` float NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`id`, `name`, `description`, `add_book`, `is_recieve`, `is_get`, `extend_num`, `is_extend`, `extend_time`, `delete_book`, `can_pay`, `fine_amount`) VALUES
(1, 'Admin', 'Group for Administratiors', 1, 1, 1, 99, 1, 200, 1, 1, 75.6),
(2, 'Librarian', 'Group for Librarians', 1, 1, 1, 99, 1, 15, 1, 1, 13),
(3, 'Strudent', 'Group for students', 0, 1, 1, 3, 1, 15, 0, 0, 20.1),
(4, 'Guest', 'Group for guests', 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `issues`
--

CREATE TABLE `issues` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `date_issue` datetime NOT NULL,
  `date_return` datetime DEFAULT NULL,
  `returned_date` datetime DEFAULT NULL,
  `extended` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `issues`
--

INSERT INTO `issues` (`id`, `user_id`, `book_id`, `date_issue`, `date_return`, `returned_date`, `extended`) VALUES
(27, 6, 17, '2017-12-10 19:13:45', '2018-07-05 23:31:58', '2017-12-21 00:00:00', 1),
(28, 7, 27, '2017-12-17 19:14:17', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `publishers`
--

CREATE TABLE `publishers` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(300) DEFAULT NULL,
  `contact_number` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `publishers`
--

INSERT INTO `publishers` (`id`, `name`, `address`, `contact_number`) VALUES
(1, 'Publisher Inc. ', 'Bishkek, unknown 1', '031254786'),
(2, 'Republisher Inc.', 'Bishkek, unknown 2', '0551002246'),
(3, '123456789', NULL, NULL),
(4, 'sfjvnsdkjfnlkdj', NULL, NULL),
(5, 'klsdjnjddsnk', NULL, NULL),
(6, 'djsbfvisekjdhfncl', NULL, NULL),
(7, 'auwshfvinujksd', NULL, NULL),
(8, 'company name', NULL, NULL),
(9, 'Azamat Inc.', NULL, NULL),
(10, 'WGVRESDFCERF', NULL, NULL),
(11, 'wwdihwiaksdhiw', NULL, NULL),
(12, 'ttfsgjhdnklem;dfcml', NULL, NULL),
(13, 'test inc', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ratings`
--

CREATE TABLE `ratings` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `points` int(11) NOT NULL DEFAULT '0',
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ratings`
--

INSERT INTO `ratings` (`id`, `user_id`, `book_id`, `points`, `date`) VALUES
(1, 1, 12, 4, '2017-12-16 19:48:22'),
(2, 1, 12, 4, '2017-12-16 19:48:28'),
(3, 1, 12, 3, '2017-12-16 19:48:41'),
(4, 6, 4, 2, '2017-12-17 22:52:06');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `Description` varchar(300) NOT NULL,
  `department_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`id`, `name`, `Description`, `department_id`) VALUES
(1, 'Language Arts', 'TEST - Language Arts', 20),
(2, 'Mathematics', 'TEST - Mathematics', 6),
(3, 'Science', 'TEST - Science', 7),
(4, 'Health', 'TEST - Health', 26),
(5, 'Handwriting', 'TEST - Handwriting', 14),
(6, 'Physical Education (P.E.)', 'TEST - Physical Education (P.E.)', 8),
(7, 'Art', 'TEST - Art', 19),
(8, 'Music', 'TEST - Music', 13),
(9, 'Instrumental Music – specific instrument', 'TEST - Instrumental Music – specific instrument', 26),
(10, 'Movement or Eurythmy', 'TEST - Movement or Eurythmy', 22),
(11, 'Handwork or handcrafts', 'TEST - Handwork or handcrafts', 19),
(12, 'Life Lab or gardening', 'TEST - Life Lab or gardening', 21),
(13, 'Dramatics', 'TEST - Dramatics', 2),
(14, 'Dance', 'TEST - Dance', 28),
(15, 'Spanish or other foreign language', 'TEST - Spanish or other foreign language', 28),
(16, 'Leadership', 'TEST - Leadership', 14),
(17, 'Special Education Day Class', 'TEST - Special Education Day Class', 3),
(18, 'Resource Program', 'TEST - Resource Program', 19),
(19, 'Speech', 'TEST - Speech', 29),
(20, 'Adaptive P.E.', 'TEST - Adaptive P.E.', 15),
(21, 'Occupational Therapy', 'TEST - Occupational Therapy', 25),
(22, 'Middle School Subjects', 'TEST - Middle School Subjects', 10),
(23, 'CORE – core subjects class', 'TEST - CORE – core subjects class', 15),
(24, 'Reading', 'TEST - Reading', 5),
(25, 'Language arts', 'TEST - Language arts', 19),
(26, 'Speech and Debate', 'TEST - Speech and Debate', 6),
(27, 'English', 'TEST - English', 22),
(28, 'Basic Math', 'TEST - Basic Math', 4),
(29, 'Pre-algebra', 'TEST - Pre-algebra', 7),
(30, 'Consumer Math', 'TEST - Consumer Math', 28),
(31, 'Algebra', 'TEST - Algebra', 16),
(32, 'Geometry', 'TEST - Geometry', 18),
(33, 'Honors Math in Algebra or Geometry', 'TEST - Honors Math in Algebra or Geometry', 7),
(34, 'Life Science', 'TEST - Life Science', 19),
(35, 'Earth Science', 'TEST - Earth Science', 1),
(36, 'Physical Science', 'TEST - Physical Science', 29),
(37, 'Health', 'TEST - Health', 27),
(38, 'Social Studies', 'TEST - Social Studies', 2),
(39, 'Geography', 'TEST - Geography', 12),
(40, 'Ancient Civilizations', 'TEST - Ancient Civilizations', 18),
(41, 'Medieval and Renaissance', 'TEST - Medieval and Renaissance', 9),
(42, 'U.S. History and Government', 'TEST - U.S. History and Government', 6),
(43, 'French / Spanish / Latin', 'TEST - French / Spanish / Latin', 26),
(44, 'Computer Science or Lab', 'TEST - Computer Science or Lab', 9),
(45, 'Art', 'TEST - Art', 6),
(46, 'Home Economics', 'TEST - Home Economics', 6),
(47, 'Woodshop', 'TEST - Woodshop', 14),
(48, 'Metal Shop', 'TEST - Metal Shop', 19),
(49, 'Business Technology', 'TEST - Business Technology', 1),
(50, 'Instrumental Music', 'TEST - Instrumental Music', 6),
(51, 'Band', 'TEST - Band', 16),
(52, 'Choir', 'TEST - Choir', 6),
(53, 'Drama', 'TEST - Drama', 28),
(54, 'Physical Education', 'TEST - Physical Education', 11),
(55, 'Sports', 'TEST - Sports', 23),
(56, 'Special Education Day Class', 'TEST - Special Education Day Class', 2),
(57, 'Resource Program', 'TEST - Resource Program', 10),
(58, 'Speech Therapy', 'TEST - Speech Therapy', 14),
(59, 'Occupational Therapy', 'TEST - Occupational Therapy', 13);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `card_id` varchar(11) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `department_id` varchar(45) DEFAULT NULL,
  `group_id` int(11) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `balance` float NOT NULL DEFAULT '0',
  `password` varchar(200) NOT NULL,
  `login` varchar(45) NOT NULL,
  `gender` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `surname`, `card_id`, `tel`, `address`, `birth_date`, `department_id`, `group_id`, `status`, `balance`, `password`, `login`, `gender`) VALUES
(6, 'Azamatjon', 'Ahunjanov', '15010108666', '0551006638', 'Tunguch, 21 06fjkbik', '1998-03-12', '17', 1, 1, 250, '123456', 'admin', 1),
(7, 'Daniyar', 'Changylov', '15010108665', '0551006638', 'Tunguch, 21 dkfslfmlsd', '1997-03-12', '17', 2, 1, 564, '123456', 'dancho', 1),
(8, 'Zarlyk', 'Zhusubaliev', '15010108635', '0551006638', 'Tunguch, 21 dkfslfmlsd', '1997-03-12', '17', 3, 1, 400, '123456', 'zako01', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `faculties`
--
ALTER TABLE `faculties`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `issues`
--
ALTER TABLE `issues`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `publishers`
--
ALTER TABLE `publishers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ratings`
--
ALTER TABLE `ratings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authors`
--
ALTER TABLE `authors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;
--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=177;
--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `faculties`
--
ALTER TABLE `faculties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `issues`
--
ALTER TABLE `issues`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `publishers`
--
ALTER TABLE `publishers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `ratings`
--
ALTER TABLE `ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
