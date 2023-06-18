alter table users modify id binary(16);
alter table orders modify user_id binary(16);
alter table product_favorites modify user_id binary(16);
alter table comments modify user_id binary(16);