alter table member modify column password varchar(100) not null;
alter table member modify column password_salt varchar(100) not null;
alter table member modify column email varchar(50) not null unique;
alter table member ADD is_admin boolean not null;
alter table member change admin admin boolean not null;