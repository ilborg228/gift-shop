package ru.samara.giftshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.repository.CommentRepository;
import ru.samara.giftshop.repository.ProductsRepository;

public class BaseTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper jsonMapper;
    @Autowired
    protected ProductsRepository productsRepository;
    @Autowired
    protected CategoryRepository categoryRepository;
    @Autowired
    protected CommentRepository commentRepository;
}