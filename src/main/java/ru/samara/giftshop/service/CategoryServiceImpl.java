package ru.samara.giftshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.exceptions.CategoryAlreadyExistException;
import ru.samara.giftshop.exceptions.NoSuchCategoryException;
import ru.samara.giftshop.exceptions.NoSuchProductException;
import ru.samara.giftshop.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository repo;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public void saveNewItem(CategoryEntity category){
        if (!repo.existsByCategoryName(category.getCategoryName()))
            repo.save(category);
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
            throw new NoSuchProductException();
        }
    }

    @Override
    public void update(CategoryEntity p){
        if (repo.findById(p.getId()).isPresent()) {
            repo.save(p);
        } else {
            throw new NoSuchProductException();
        }
    }

    @Override
    public CategoryEntity findById(Long id){
        return repo.findById(id)
                .orElseThrow(()->new NoSuchCategoryException(id));
    }
}
