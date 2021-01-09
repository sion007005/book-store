alter table book change isbn isbn10 varchar(10);

-- yes 24에 isbn13만 있는 책도 있어서 null 허용
alter table book modify column isbn10 varchar(10) null;
alter table book modify column title varchar(100) not null;
alter table book modify column publisher varchar(50) not null;

DROP TABLE IF EXISTS `book_store`.`book_category`;
create table book_category (
	`id` BIGINT(11) NOT NULL AUTO_INCREMENT,
	`category_id` BIGINT(11) NOT NULL,
    `book_id` BIGINT(11) NOT NULL,
    `created_at` DATETIME NOT NULL,
    `created_by` VARCHAR(45) NOT NULL,
    `modified_at` DATETIME NOT NULL,
    `modified_by` VARCHAR(45) NOT NULL,
    `deleted` TINYINT(1) NOT NULL DEFAULT 0,
	PRIMARY KEY (`id`)
);
