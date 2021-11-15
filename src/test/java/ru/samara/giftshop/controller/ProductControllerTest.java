package ru.samara.giftshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.samara.giftshop.config.MyConfig;
import ru.samara.giftshop.service.ProductService;
import ru.samara.giftshop.service.ProductServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@WebMvcTest(ProductController.class)
class ProductControllerTest {
    private final ProductService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public ProductControllerTest(ProductService service) {
        this.service = service;
    }

    @Test
    void getAllProducts() {
        assertThat(service).isNotNull();

    }
}