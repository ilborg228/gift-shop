create table categories (
    id bigint not null AUTO_INCREMENT,
    category_name varchar(255),
    img_source varchar(255),
    primary key (id)
) engine=InnoDB AUTO_INCREMENT=1;

create table products (
      id bigint not null AUTO_INCREMENT,
      description varchar(255),
      height integer,
      img_source varchar(255),
      name varchar(255),
      price double precision,
      category_id bigint references categories(id),
      primary key (id)
) engine=InnoDB AUTO_INCREMENT=1;


create table users (
       id bigint not null AUTO_INCREMENT,
       password varchar(255),
       role varchar(255),
       username varchar(255) not null,
       primary key (id)
) engine=InnoDB AUTO_INCREMENT=1;

create table comments (
    id bigint not null AUTO_INCREMENT,
    creation timestamp,
    score_value varchar(255),
    text varchar(255),
    product_id bigint references products(id),
    user_id bigint references users(id),
    primary key (id)
) engine=InnoDB AUTO_INCREMENT=1;

create table favorites (
    id bigint not null AUTO_INCREMENT,
    product_id bigint references products(id),
    user_id bigint references users(id),
    primary key (id)
) engine=InnoDB AUTO_INCREMENT=1;

create table orders (
    id bigint not null AUTO_INCREMENT,
    address varchar(255),
    order_creation timestamp,
    product_id bigint references products(id),
    user_id bigint references users(id),
    primary key (id)
) engine=InnoDB AUTO_INCREMENT=1;
