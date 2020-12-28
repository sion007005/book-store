DROP TABLE IF EXISTS `book_store`.`category`;
CREATE TABLE `book_store`.`category` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `parent_id` BIGINT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `level` INTEGER NOT NULL,
  `order` INTEGER NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `modified_by` VARCHAR(45) NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));


INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (1, 0, 'root', 0, 0, now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (2, 1, '유아/어린이', 1, 0, now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (3, 1, '경제/경영', 1, 0, now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (4, 1, '소설/시/희곡', 1, 0, now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (5, 2, '초등 한국사', 2, 0, now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (6, 2, '아동 문학론', 2, 0, now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (7, 3, '기업 경영', 2, 0, now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (8, 3, '마케팅/세일즈', 2, 0, now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (9, 7, '경영 일반', 3, 0, now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (10, 7, '무역', 3, 0, now(), 'sion', now(), 'sion', 0);
INSERT INTO `book_store`.`category`(`id`, `parent_id`, `name`,`level`,`order`,`created_at`,`created_by`,`modified_at`,`modified_by`,`deleted`) VALUES (11, 7, '고객관리', 3, 0, now(), 'sion', now(), 'sion', 0);