alter table products drop column img_source;

create table product_images (
    id bigint not null AUTO_INCREMENT,
    img_source varchar(255) not null,
    primary_image boolean not null default false,
    product_id bigint references products(id),
    primary key (id)
) engine=InnoDB AUTO_INCREMENT=1;

ALTER TABLE favorites RENAME product_favorites;

alter table categories add column child_id bigint references categories(id);