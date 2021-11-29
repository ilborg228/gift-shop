package ru.samara.giftshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.service.CategoryServiceImpl;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ContextConfiguration(classes = {CategoryController.class})
@WebMvcTest
public class CategoryControllerTest {

    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryServiceImpl categoryServiceImpl;

    @Autowired
    private CategoryServiceImpl service;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddCategory() throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());
        categoryEntity.setImgSource("Img Source");
        when(this.categoryServiceImpl.saveNewItem((CategoryEntity) any())).thenReturn(categoryEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setId(123L);
        categoryEntity1.setProducts(new ArrayList<ProductEntity>());
        categoryEntity1.setImgSource("Img Source");
        String content = (new ObjectMapper()).writeValueAsString(categoryEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addcategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"categoryName\":\"Category Name\",\"imgSource\":\"Img Source\",\"products\":[]}"));
    }

    @Test
    public void testDeleteCategory() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deletecategory/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }

    @Test
    public void testGetAllCategories() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allcategories");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }

    @Test
    public void testGetOneCategory() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/categories/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }

    @Test
    public void testUpdateCategory() throws Exception {
        doNothing().when(this.categoryServiceImpl).update((CategoryEntity) any());

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());
        categoryEntity.setImgSource("Img Source");
        String content = (new ObjectMapper()).writeValueAsString(categoryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updatecategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("ÐÐ°ÑÐµÐ³Ð¾ÑÐ¸Ñ ÑÑÐ¿ÐµÑÐ½Ð¾ Ð¾Ð±Ð½Ð¾Ð²Ð»ÐµÐ½Ð°"));
    }
}
