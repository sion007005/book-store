alter table order_item add book_title VARCHAR(50) NOT NULL AFTER book_id;
alter table order_item add cover_image_path VARCHAR(100) NULL AFTER book_title;

alter table book change thumbnail cover_image_path VARCHAR(100) NULL;