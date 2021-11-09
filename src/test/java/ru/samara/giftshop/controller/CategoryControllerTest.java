package ru.samara.giftshop.controller;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.service.CategoryService;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CategoryControllerTest {

    @Autowired
    private CategoryRepository repository;
    @Autowired
    private CategoryService service;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void  saveProduct(){
        CategoryEntity c = createTestCategory();
        ResponseEntity<CategoryEntity> response = restTemplate.postForEntity("/addcategory", c, CategoryEntity.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getCategoryName()).isEqualTo("Category Name");
    }

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    private CategoryEntity createTestCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());
        return categoryEntity;
    }
}
