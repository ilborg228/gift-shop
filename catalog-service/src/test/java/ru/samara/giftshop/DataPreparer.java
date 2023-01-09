package ru.samara.giftshop;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.repository.ProductRepository;

@Component
public class DataPreparer {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public Category buildCategory(){
        Category category = new Category();
        category.setCategoryName(RandomStringUtils.random(32,true,true));
        category.setImageUrl(RandomStringUtils.random(32,true,true));
        return category;
    }

    public Category addCategory() {
        return categoryRepository.save(buildCategory());
    }
}
