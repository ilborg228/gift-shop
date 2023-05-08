alter table products drop height;
alter table products add column in_stock tinyint(1) default 1 not null;