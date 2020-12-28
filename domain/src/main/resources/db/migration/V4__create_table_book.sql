DROP TABLE IF EXISTS `book_store`.`book`;
CREATE TABLE `book_store`.`book` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `isbn` VARCHAR(10) NOT NULL,
  `isbn13` VARCHAR(13) NOT NULL,
  `published_at` DATETIME NOT NULL,
  `publisher` VARCHAR(20) NOT NULL,
  `price` INTEGER NOT NULL,
  `sale_price` INTEGER NOT NULL,
  `thumbnail` VARCHAR(100) NULL,
  `status` VARCHAR(20) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `modified_by` VARCHAR(45) NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

DROP TABLE IF EXISTS `book_store`.`author`;
CREATE TABLE `book_store`.`author` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `book_id` BIGINT(11) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`));

DROP TABLE IF EXISTS `book_store`.`translator`;
CREATE TABLE `book_store`.`translator` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `book_id` BIGINT(11) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`));

DROP TABLE IF EXISTS `book_store`.`category_book`;
CREATE TABLE `book_store`.`category_book` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `category_id` BIGINT(11) NOT NULL,
  `book_id` BIGINT(11) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `modified_by` VARCHAR(45) NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));