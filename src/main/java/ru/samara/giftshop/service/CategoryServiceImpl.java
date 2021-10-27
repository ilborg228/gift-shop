package ru.samara.giftshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.exceptions.CategoryAlreadyExistException;
import ru.samara.giftshop.exceptions.NoSuchProductException;
import ru.samara.giftshop.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository repo;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public void addItem(CategoryEntity category) throws CategoryAlreadyExistException {
        if (!repo.existsByCategoryName(category.getCategoryName()))
            repo.save(category);
        else
            throw new CategoryAlreadyExistException();
    }

    @Override
    public List<CategoryEntity> getAllItems() {
        return (List<CategoryEntity>) repo.findAll();
    }

    @Override
    public void deleteItem(Long id) throws NoSuchProductException {
        if (repo.findById(id).isPresent()) {
            repo.delete(repo.findById(id).get());
        } else {
            throw new NoSuchProductException();
        }
    }

    @Override
    public void updateItems(CategoryEntity p) throws NoSuchProductException {
        if (repo.findById(p.getId()).isPresent()) {
            repo.save(p);
        } else {
            throw new NoSuchProductException();
        }
    }

    @Override
    public CategoryEntity getItemByID(Long categoryId) throws NoSuchProductException {
        Optional<CategoryEntity> c = repo.findById(categoryId);
        if (c.isPresent()) {
            return c.get();
        } else {
            throw new NoSuchProductException();
        }
    }
}
