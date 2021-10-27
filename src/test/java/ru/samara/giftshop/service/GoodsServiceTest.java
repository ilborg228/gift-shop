package ru.samara.giftshop.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.samara.giftshop.entity.ProductEntity;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class GoodsServiceTest {

    @MockBean
    private GoodsService service;

    @Test
    void saveProduct() {
        ProductEntity p = new ProductEntity();
        assertDoesNotThrow(()->service.saveItem(p));
    }

    @Test
    void selectAllGoods() {
        System.out.println(service.getAllItems());
    }

    @Test
    void deleteProduct() {
        assertDoesNotThrow(()->service.deleteItem(100L));
    }
}