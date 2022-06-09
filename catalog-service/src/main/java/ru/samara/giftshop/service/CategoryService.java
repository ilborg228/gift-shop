package ru.samara.giftshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.dto.CategoryDto;
import ru.samara.giftshop.dto.DtoMapper;
import ru.samara.giftshop.dto.MyMail;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.exceptions.*;
import ru.samara.giftshop.helpers.OrderBy;
import ru.samara.giftshop.helpers.OrderByType;
import ru.samara.giftshop.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService extends BaseService{

    private final CategoryRepository categoryRepository;

    public Category saveNewItem(Category category) throws JsonProcessingException {
        if (!categoryRepository.existsByCategoryName(category.getCategoryName())){
            MyMail mail = MyMail.builder()
                    .to("shirokih_i@mail.ru")
                    .subject(category.getCategoryName())
                    .text("New category was created!!")
                    .build();
            rabbitTemplate.convertAndSend("notificationQueue", jsonMapper.writeValueAsString(mail));
            return categoryRepository.save(category);
        }
        else
            throw new ApiException(DataValidationResponse.CATEGORY_ALREADY_EXIST);
    }

    public List<CategoryDto> findAll(Integer page, Integer pageSize, OrderBy orderBy, OrderByType orderByType) {
        Sort sort = Sort.by(Sort.Direction.fromString(orderByType.getDirection()),orderBy.getColumn());
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(DtoMapper::toCategoryDTO).getContent();
    }

    public void delete(Long id) {
        Optional<Category> opt = categoryRepository.findById(id);
        if (opt.isPresent()) {
            categoryRepository.delete(opt.get());
        } else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }

    public void update(Category p){
        Optional<Category> op = categoryRepository.findById(p.getId());
        if(op.isPresent()) {
            Category old = op.get();
            if(old.getCategoryName()!=null && p.getCategoryName()==null) {
                p.setCategoryName(old.getCategoryName());
            }
            categoryRepository.save(p);
        }
        else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }
}
