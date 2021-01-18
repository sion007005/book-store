DROP TABLE IF EXISTS `book_store`.`thema_section`;
CREATE TABLE `book_store`.`thema_section` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `order_no` INTEGER NOT NULL UNIQUE,
  `type` VARCHAR(50) NOT NULL, /* official thema name, enum class로 관리 */
  `title` VARCHAR(50) NOT NULL, /* 관리자가 정하는 테마 섹션 타이틀 */
  `description` VARCHAR(500) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `modified_by` VARCHAR(45) NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

DROP TABLE IF EXISTS `book_store`.`thema_book`;
CREATE TABLE `book_store`.`thema_book` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `thema_section_id` BIGINT(11) NOT NULL,
  `book_id` BIGINT(11) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `modified_at` DATETIME NOT NULL,
  `modified_by` VARCHAR(45) NOT NULL,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));
