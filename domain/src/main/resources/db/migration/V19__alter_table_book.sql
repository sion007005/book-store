alter table book add author1 varchar(10) not null after stock_quantity;
alter table book add author2 varchar(10) null after author1;
alter table book add author3 varchar(10) null after author2;
alter table book add translator1 varchar(10) not null after author3;
alter table book add translator2 varchar(10) null after translator1;
alter table book add translator3 varchar(10) null after translator2;