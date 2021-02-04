DROP TABLE IF EXISTS `book_store`.`cart`;
CREATE TABLE `book_store`.`cart` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `book_id` BIGINT NOT NULL,
  `quantity` INTEGER NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `modified_by` VARCHAR(45) NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
PRIMARY KEY (`id`));