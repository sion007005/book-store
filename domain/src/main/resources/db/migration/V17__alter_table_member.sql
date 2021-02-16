alter table member modify column admin TINYINT(1) NOT NULL DEFAULT 0;
alter table member drop `is_admin`;

alter table address modify column default_address TINYINT(1) NOT NULL DEFAULT 0;