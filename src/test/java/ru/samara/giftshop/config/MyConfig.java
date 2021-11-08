package ru.samara.giftshop.config;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.samara.giftshop.repository.ProductsRepository;
import ru.samara.giftshop.service.ProductServiceImpl;

@Configuration
public class MyConfig {
    @MockBean
    private ProductsRepository r;

   @Bean
   public ProductServiceImpl getService(){
       return new ProductServiceImpl(r);
   }
}
