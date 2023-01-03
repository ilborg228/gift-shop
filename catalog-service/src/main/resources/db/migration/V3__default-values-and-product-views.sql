alter table products add column views int default 0;

insert into categories(category_name, img_source) values ('Test123', 'https://www.rabbitmq.com/img/logo-rabbitmq.svg');

insert into products(description, height, name, price, category_id) values ('Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus animi asperiores cum debitis dolor, dolorem doloremque eum explicabo facilis id ipsum molestiae, nam odio optio quam reiciendis soluta vero voluptatibus?,',
                                                                            100,'Фантастический прикол', 2100, 1);
insert into product_images(image_url, primary_image, product_id) VALUES ('http://samaragiftshop.ru/src/shop/2.jpg', 1, 1);
insert into product_images(image_url, primary_image, product_id) VALUES ('http://samaragiftshop.ru/src/shop/3.jpg', 0, 1);
insert into product_images(image_url, primary_image, product_id) VALUES ('http://samaragiftshop.ru/src/shop/4.jpg', 0, 1);