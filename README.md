# book-store

# 서비스 소개
도서 쇼핑몰과 쇼핑몰을 관리하는 툴

# 개발 환경
JDK11, Gradle 6.7.1, Spring Boot 2.4.0, Spring Framework 5.3.1, MySql 8.0.23, MyBatis 3.5.5, Lombok 1.8.16, HikariCP 3.4.5 , Jsoup 1.13.1, Slf4j 1.7.30

# 프로젝트 구성
* gradle을 활용한 admin/front/domain 멀티모듈
* admin과 front에는 각각 필요한 요청에 따른 Controller가 존재하며, domain 모듈을 참조한다. 
* domain에는 비즈니스 로직인 Service와, 디비와 인터페이싱하는 Repository가 있다.

# 데이터베이스 설계 원칙(ERD - 마지막 페이지에 첨부)
1) 테이블명 및 컬럼명이 두개 이상의 단어로 이루어질 경우에는 underscore를 활용해 네이밍한다.
2) row 삭제는 deleted 컬럼 값 변경을 통한 소프트 삭제 처리한다.
3) PK는 id bigint auto_increment로 통일한다.
4) 공통 컬럼 : created_at, created_by, modified_at, modified_by, deleted
5) 스키마 변경 사항에 대한 SQL 파일의 버전 관리를 한다. 
6) JOIN, WHERE 조건 절에 들어가는 컬럼들은 INDEX를 생성한다. 
