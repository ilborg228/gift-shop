create table categories (
    id bigint not null,
    category_name varchar(255),
    img_source varchar(255),
    primary key (id)
) engine=InnoDB;

create table products (
      id bigint not null,
      description varchar(255),
      height integer,
      img_source varchar(255),
      name varchar(255),
      price double precision,
      category_id bigint references categories(id),
      primary key (id)
) engine=InnoDB;


create table users (
       id bigint not null,
       password varchar(255),
       role varchar(255),
       username varchar(255) not null,
       primary key (id)
) engine=InnoDB;

create table comments (
    id bigint not null,
    creation datetime(6),
    score_value varchar(255),
    text varchar(255),
    product_id bigint references products(id),
    user_id bigint references users(id),
    primary key (id)
) engine=InnoDB;

create table favorites (
    id bigint not null,
    product_id bigint references products(id),
    user_id bigint references users(id),
    primary key (id)
) engine=InnoDB;

create table orders (
    id bigint not null,
    address varchar(255),
    order_creation datetime(6),
    product_id bigint references products(id),
    user_id bigint references users(id),
    primary key (id)
) engine=InnoDB;
