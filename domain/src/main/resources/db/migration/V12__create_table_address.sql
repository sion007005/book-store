DROP TABLE IF EXISTS `book_store`.`address`;
CREATE TABLE `book_store`.`address` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `member_id` BIGINT NOT NULL,
    `name` VARCHAR(10) NOT NULL,
    `address_basic` VARCHAR(200) NOT NULL,
    `address_detail` VARCHAR(200) NOT NULL,
    `zip_code` INTEGER NOT NULL,
    `created_at` DATETIME NOT NULL,
    `created_by` VARCHAR(45) NOT NULL,
    `modified_at` DATETIME NOT NULL,
    `modified_by` VARCHAR(45) NOT NULL,
    `deleted` TINYINT(1) NOT NULL DEFAULT 0,
PRIMARY KEY (`id`));