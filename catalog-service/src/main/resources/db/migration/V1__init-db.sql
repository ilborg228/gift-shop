create table categories (
    id            bigint        auto_increment primary key,
    category_name varchar(255)  null,
    image_url     varchar(255)  null,
    parent_id     bigint        null,
    has_child     boolean       default false
);

create table products (
    id          bigint          auto_increment primary key,
    description varchar(2048)   null,
    height      int             null,
    name        varchar(255)    null,
    price       double          null,
    category_id bigint          null,
    views       bigint          default 0
);

create table users (
    id       bigint         auto_increment primary key,
    password varchar(255)   null,
    role     integer        null,
    username varchar(255)   not null
);

create table comments (
    id          bigint          auto_increment primary key,
    creation    timestamp       null,
    score_value int             not null,
    text        varchar(2048)   null,
    product_id  bigint          null,
    user_id     bigint          null
);

create table product_favorites (
    id         bigint   auto_increment primary key,
    product_id bigint   null,
    user_id    bigint   null
);

create table orders (
    id             bigint           auto_increment primary key,
    address        varchar(2048)    null,
    order_creation timestamp        null,
    user_id        bigint           null,
    status         integer          null
);

create table order_products (
    order_id       bigint   null,
    product_id     bigint   null
);

create table product_images (
    id            bigint                    auto_increment primary key,
    image_url     varchar(255)              not null,
    primary_image tinyint(1)    default 0   not null,
    product_id    bigint                    null
);


insert into categories(category_name, image_url, has_child) values ('Test123', 'https://www.rabbitmq.com/img/logo-rabbitmq.svg', 1);
insert into categories(category_name, image_url, parent_id) values ('Test1', 'https://www.rabbitmq.com/img/logo-rabbitmq.svg', 1);

insert into products(description, height, name, price, category_id) values ('Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus animi asperiores cum debitis dolor, dolorem doloremque eum explicabo facilis id ipsum molestiae, nam odio optio quam reiciendis soluta vero voluptatibus?,',
                                                                            100,'Фантастический прикол', 2100, 2);
insert into product_images(image_url, primary_image, product_id) VALUES ('http://samaragiftshop.ru/src/shop/2.jpg', 1, 1);
insert into product_images(image_url, primary_image, product_id) VALUES ('http://samaragiftshop.ru/src/shop/3.jpg', 0, 1);
insert into product_images(image_url, primary_image, product_id) VALUES ('http://samaragiftshop.ru/src/shop/4.jpg', 0, 1);
