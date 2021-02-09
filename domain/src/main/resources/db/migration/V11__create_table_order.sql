-- 주문 테이블
DROP TABLE IF EXISTS `book_store`.`order`;
CREATE TABLE `book_store`.`order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `total_price` INTEGER NOT NULL,
  `payment_type` VARCHAR(10) NOT NULL,
  `order_status` VARCHAR(10) NOT NULL,
  `address_basic` VARCHAR(200) NOT NULL,
  `address_detail` VARCHAR(200) NOT NULL,
  `zip_code` INTEGER NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `message` VARCHAR(100) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `modified_by` VARCHAR(45) NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
PRIMARY KEY (`id`));

-- 주문 상품 테이블
DROP TABLE IF EXISTS `book_store`.`order_product`;
CREATE TABLE `book_store`.`order_product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT NOT NULL,
  `book_id` BIGINT NOT NULL,
  `quantity` INTEGER NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `modified_by` VARCHAR(45) NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
PRIMARY KEY (`id`));