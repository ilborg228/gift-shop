package ru.samara.giftshop.config;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.repository.ProductsRepository;
import ru.samara.giftshop.service.CategoryServiceImpl;

@Configuration
public class MyConfig {
    @MockBean
    private CategoryRepository r;

    @Bean
    public CategoryServiceImpl getService(){
        return new CategoryServiceImpl(r);
    }
}
