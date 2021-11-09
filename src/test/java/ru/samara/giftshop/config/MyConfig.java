package ru.samara.giftshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.samara.giftshop.controller.ProductController;
import ru.samara.giftshop.repository.ProductsRepository;
import ru.samara.giftshop.service.CategoryService;
import ru.samara.giftshop.service.CategoryServiceImpl;
import ru.samara.giftshop.service.ProductService;
import ru.samara.giftshop.service.ProductServiceImpl;

@Configuration
public class MyConfig {
    @MockBean
    private ProductsRepository r;

    @Autowired
    private ProductService s;

    @MockBean
    private CategoryServiceImpl c;
}
