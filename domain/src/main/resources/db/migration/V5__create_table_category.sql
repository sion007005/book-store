DROP TABLE IF EXISTS `book_store`.`category`;
CREATE TABLE `book_store`.`category` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `parent_id` BIGINT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `level` INTEGER NOT NULL,
  `order` INTEGER NOT NULL,
  `link` VARCHAR(100) NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `modified_by` VARCHAR(45) NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));


INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (1, 0, 'root', 0, 1, now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`, `link`, `created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (2, 1, '국내도서', 1, 1, "http://www.yes24.com/Mall/Main/Book/001?CategoryNumber=001", now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`, `link`, `created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (3, 1, '외국도서', 1, 2, "http://www.yes24.com/Mall/Main/Foreign/002?CategoryNumber=002", now(), 'sion', now(), 'sion', 0);