CREATE TABLE `book_store`.`sample` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `description` VARCHAR(500) NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `modified_by` VARCHAR(45) NOT NULL,
  `delete` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

