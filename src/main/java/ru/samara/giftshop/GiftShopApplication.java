package ru.samara.giftshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.repository.CommentRepository;
import ru.samara.giftshop.repository.ProductsRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class GiftShopApplication {

    @Autowired
    private  CategoryRepository categoryRepository;
    @Autowired
    private  ProductsRepository productsRepository;
    @Autowired
    private  CommentRepository commentRepository;

    public static void main(String[] args) {
        SpringApplication.run(GiftShopApplication.class, args);
    }

    @PostConstruct
    private void init(){
        Category category1 = new Category();
        category1.setCategoryName("test");
        category1.setImgSource("http://samaragiftshop.ru/src/shop/2.jpg");
        Category category2 = new Category();
        category2.setCategoryName("test2");
        category2.setImgSource("http://samaragiftshop.ru/src/shop/2.jpg");
        categoryRepository.saveAllAndFlush(List.of(category1,category2));

        Product product1 = new Product();
        product1.setName("test-тест1");
        product1.setImgSource("http://samaragiftshop.ru/src/shop/2.jpg");
        product1.setCategory(category1);
        Product product2 = new Product();
        product2.setName("test-тест2");
        product2.setImgSource("http://samaragiftshop.ru/src/shop/2.jpg");
        product2.setCategory(category1);
        Product product3 = new Product();
        product3.setName("test-тест3");
        product3.setImgSource("http://samaragiftshop.ru/src/shop/2.jpg");
        product3.setCategory(category1);
        Product product4 = new Product();
        product4.setName("test-тест4");
        product4.setImgSource("http://samaragiftshop.ru/src/shop/2.jpg");
        product4.setCategory(category1);
        productsRepository.saveAllAndFlush(List.of(product4,product1,product2,product3));
    }

}
