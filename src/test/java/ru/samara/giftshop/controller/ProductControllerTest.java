package ru.samara.giftshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.service.CategoryService;
import ru.samara.giftshop.service.ProductService;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @MockBean
    private ProductService productService;
    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addProduct() throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());
        when(categoryService.findById(any())).thenReturn(categoryEntity);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0);
        productEntity.setHeight(1);
        productEntity.setImgSource("Img Source");

        mockMvc.perform(post("/addproduct/1")
                        .content(objectMapper.writeValueAsString(productEntity))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAllProducts() throws Exception {

        mockMvc.perform(get("/allproducts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getProductByName() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void updateProduct() {
    }
}