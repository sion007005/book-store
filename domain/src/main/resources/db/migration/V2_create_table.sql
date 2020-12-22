CREATE TABLE `book_store`.`member` (
  `idx` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NULL,
  `password_salt` VARCHAR(50) NULL,
  `phone` VARCHAR(15) NOT NULL,
  `profile_img_path` VARCHAR(100) NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `modified_by` VARCHAR(45) NOT NULL,
  `is_deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idx`));