-- 테이블명 변경
alter table `thema_book` rename `theme_book`;
alter table `thema_section` rename `theme_section`;

-- 컬럼명 변경
alter table `theme_book` change `thema_section_id` `theme_section_id` BIGINT NOT NULL;