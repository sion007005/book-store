-- 디비를 생성한 후 아래와 같이 데이타베이스 생성, 사용자 생성, 사용자에게 권한 부여를 수행해 준다.
/* 로컬 */
CREATE DATABASE book_store CHARACTER SET utf8mb4;

create user sion@'%' identified by 'winter!@#$';
grant all privileges on book_store.* to sion@'%';

create user sion@'localhost' identified by 'winter!@#$';
grant all privileges on book_store.* to sion@'localhost';

FLUSH PRIVILEGES;
