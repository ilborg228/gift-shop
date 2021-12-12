package ru.samara.giftshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.exceptions.CategoryAlreadyExistException;
import ru.samara.giftshop.exceptions.CategoryNotFoundException;
import ru.samara.giftshop.exceptions.ProductNotFoundException;
import ru.samara.giftshop.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository repo;

    @Override
    public CategoryEntity saveNewItem(CategoryEntity category){
        if (!repo.existsByCategoryName(category.getCategoryName()))
            return repo.save(category);
        else
            throw new CategoryAlreadyExistException();
    }

    @Override
    public List<CategoryEntity> findAll() {
        return (List<CategoryEntity>) repo.findAll();
    }

    @Override
    public void delete(Long id) {
        if (repo.findById(id).isPresent()) {
            repo.delete(repo.findById(id).get());
        } else {
            throw new ProductNotFoundException(id.toString());
        }
    }

    @Override
    public void update(CategoryEntity p){
        Optional<CategoryEntity> op = repo.findById(p.getId());
        if(op.isPresent()) {
            CategoryEntity old = op.get();
            if(old.getCategoryName()!=null && p.getCategoryName()==null) p.setCategoryName(old.getCategoryName());
            repo.save(p);
        }
        else {
            throw new ProductNotFoundException(p.getId().toString());
        }
    }

    @Override
    public CategoryEntity findById(Long id){
        return repo.findById(id)
                .orElseThrow(()->new CategoryNotFoundException(Long.toString(id)));
    }

    @Override
    public List<ProductEntity> findByName(String categoryName) {
        CategoryEntity c = repo.findByCategoryName(categoryName)
                .orElseThrow(()->new CategoryNotFoundException(categoryName));
        return c.getProducts();
    }
}
