package ru.samara.giftshop.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.samara.giftshop.entity.ProductEntity;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class GoodsControllerTest {

    @MockBean
    private GoodsController controller;

    @Test
    void getAllProducts() {
        controller.addProduct(new ProductEntity());
        System.out.println(controller.getAllProducts());
    }
}