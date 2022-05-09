package ru.samara.giftshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.dto.MyMail;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.exceptions.*;
import ru.samara.giftshop.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService extends BaseService{

    private final CategoryRepository repo;

    public Category saveNewItem(Category category) throws JsonProcessingException {
        if (!repo.existsByCategoryName(category.getCategoryName())){
            MyMail mail = MyMail.builder()
                    .to("shirokih_i@mail.ru")
                    .subject(category.getCategoryName())
                    .text("New category was created!!")
                    .build();
            rabbitTemplate.convertAndSend("notificationQueue", jsonMapper.writeValueAsString(mail));
            return repo.save(category);
        }
        else
            throw new ApiException(DataValidationResponse.CATEGORY_ALREADY_EXIST);
    }

    public List<Category> findAll() {
        return (List<Category>) repo.findAll();
    }

    public void delete(Long id) {
        if (repo.findById(id).isPresent()) {
            repo.delete(repo.findById(id).get());
        } else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }

    public void update(Category p){
        Optional<Category> op = repo.findById(p.getId());
        if(op.isPresent()) {
            Category old = op.get();
            if(old.getCategoryName()!=null && p.getCategoryName()==null) p.setCategoryName(old.getCategoryName());
            repo.save(p);
        }
        else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }

    public List<Product> findByName(String categoryName) {
        Category c = repo.getByCategoryName(categoryName)
                .orElseThrow(()->new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND));
        return c.getProducts();
    }
}
