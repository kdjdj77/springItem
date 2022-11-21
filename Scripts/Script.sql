SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'springitem'
AND TABLE_NAME LIKE 'db_%'
;

select * from db_user;
select * from db_authority;
select * from db_user_authorities;

select * from db_like;
select * from db_buy;
select * from db_cart;
select * from db_review;
select * from db_itemfile;
select * from db_contentfile;

select * from db_item;
select * from db_color;
select * from db_size;
select * from db_category;
select * from db_tag;
