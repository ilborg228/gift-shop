insert into categories(category_name, img_source) values ('Test123', 'https://www.rabbitmq.com/img/logo-rabbitmq.svg');

insert into products(description, height, name, price, category_id) values ('Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus animi asperiores cum debitis dolor, dolorem doloremque eum explicabo facilis id ipsum molestiae, nam odio optio quam reiciendis soluta vero voluptatibus?,',
                             100,'Фантастический прикол', 1, 2100);

create table stat (
    id bigint not null AUTO_INCREMENT,
    view_count bigint not null default 0,
    product_id bigint references products(id),
    primary key (id)
) engine=InnoDB AUTO_INCREMENT=1;