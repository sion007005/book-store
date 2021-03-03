alter table theme_section add `displayStartDate` datetime not null default CURRENT_TIMESTAMP after `description`;
alter table theme_section add displayEndDate DATETIME not null default CURRENT_TIMESTAMP AFTER `displayStartDate`;
alter table theme_section add onDisplay boolean not null AFTER `displayEndDate`;

-- order_no의 중복을 허용하기 위해 인덱스 삭제
DROP INDEX order_no ON theme_section;

alter table theme_book change thema_section_id theme_section_id bigint not null;