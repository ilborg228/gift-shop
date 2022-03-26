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
        Category category = new Category();
        category.setCategoryName("test");
        category.setImgSource("http://samaragiftshop.ru/src/shop/2.jpg");
        categoryRepository.saveAllAndFlush(List.of(category1,category2));

        Product product = new Product();
        product.setName("test-тест1");
        product.setImgSource("http://samaragiftshop.ru/src/shop/2.jpg");
        product.setCategory(category);
        productsRepository.saveAllAndFlush(List.of(product4,product1,product2,product3));
    }

}
