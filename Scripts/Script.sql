SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'springitem'
AND TABLE_NAME LIKE 't6_%'
;

select * from t6_authority;
insert into t6_authority(name) values ('ROLE_ADMIN');