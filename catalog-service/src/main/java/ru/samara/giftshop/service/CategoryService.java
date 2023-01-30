package ru.samara.giftshop.service;

import lombok.RequiredArgsConstructor;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService extends BaseService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(CategoryDto category) throws Exception {
        if (!categoryRepository.existsByCategoryName(category.getCategoryName())){
            MyMail mail = MyMail.builder()
                    .to("shirokih_i@mail.ru")
                    .subject("New category was created")
                    .text(category.getCategoryName() + "was created")
                    .build();
            rabbitTemplate.convertAndSend("notificationQueue", jsonMapper.writeValueAsString(mail));
            updateParentCategory(category.getParentId());
            return categoryRepository.save(DtoMapper.toCategory(category));
        }
        else
            throw new ApiException(DataValidationResponse.CATEGORY_ALREADY_EXIST);
    }

    private void updateParentCategory(Long parentId) {
        Category category = new Category();
        category.setId(parentId);
        category.setHasChild(true);
        update(category);
    }

    public List<CategoryDto> findAll(Long parentId, Integer page, Integer pageSize, OrderBy orderBy, OrderByType orderByType) {
        orderBy = orderBy == null ? OrderBy.ID : orderBy;
        Sort sort = Sort.by(Sort.Direction.fromString(orderByType.getDirection()),orderBy.getColumn());
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        List<Category> categories = categoryRepository.findAllByParentId(parentId, pageable);
        return categories.stream().map(DtoMapper::toCategoryDTO).collect(Collectors.toList());
    }

    public void delete(Long id) {
        Optional<Category> opt = categoryRepository.findById(id);
        if (opt.isPresent()) {
            categoryRepository.delete(opt.get());
        } else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }

    public void update(Category category){
        Optional<Category> op = categoryRepository.findById(category.getId());
        if(op.isPresent()) {
            Category old = op.get();
            if(old.getCategoryName()!=null && category.getCategoryName()==null) {
                category.setCategoryName(old.getCategoryName());
            }
            categoryRepository.save(category);
        }
        else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }
}
