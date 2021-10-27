package ru.samara.giftshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.exceptions.CategoryAlreadyExistException;
import ru.samara.giftshop.exceptions.NoSuchProductException;
import ru.samara.giftshop.exceptions.ProductAlreadyExistException;
import ru.samara.giftshop.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService{

    private CategoryRepository repo;

    @Autowired
    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public void addItem(CategoryEntity category) throws CategoryAlreadyExistException {
        if(!repo.existsByCategoryName(category.getCategoryName()))
            repo.save(category);
        else
            throw new CategoryAlreadyExistException();
    }

    public List<CategoryEntity> getAllItems() {
        return (List<CategoryEntity>) repo.findAll();
    }

    public void deleteItem(Long id) throws NoSuchProductException {
        if(repo.findById(id).isPresent()) {
            repo.delete(repo.findById(id).get());
        }
        else {
            throw new NoSuchProductException();
        }
    }

    public void updateItems(CategoryEntity p) throws NoSuchProductException {
        if(repo.findById(p.getId()).isPresent()) {
            CategoryEntity old = repo.findById(p.getId()).get();
            if(old.getCategoryName()!=null && p.getCategoryName()==null) p.setCategoryName(old.getCategoryName());

            repo.save(p);
        }
        else {
            throw new NoSuchProductException();
        }
    }
}
