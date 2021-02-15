ALTER TABLE `order_product` RENAME `order_item`;
ALTER TABLE `order_item` ADD `order_status` VARCHAR(50) NOT NULL;
ALTER TABLE `order_item` ADD `sale_price` INTEGER NOT NULL; /* 주문 할 당시의 판매가 */
ALTER TABLE `order_item` ADD `member_id` BIGINT NOT NULL;

/* 새로 추가한 컬럼 순서 변경 */
ALTER TABLE `order_item` modify COLUMN `sale_price` INTEGER NOT NULL AFTER quantity;
ALTER TABLE `order_item` modify COLUMN `order_status` VARCHAR (50) NOT NULL AFTER `sale_price`;
ALTER TABLE `order_item` modify COLUMN `member_id` BIGINT NOT NULL AFTER `order_id`;
ALTER TABLE `order` CHANGE user_id member_id BIGINT NOT NULL;

ALTER TABLE book ADD stock_quantity INTEGER NOT NULL default 500 AFTER `isbn13`;
