package ru.samara.giftshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.exceptions.NoSuchProductException;
import ru.samara.giftshop.exceptions.ProductAlreadyExistException;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.repository.GoodsRepository;

import java.util.List;

@Service
public class ProductService implements GoodsService{

    private GoodsRepository repo;

    @Autowired
    private CategoryRepository repository2;

    @Autowired
    public ProductService(GoodsRepository repo) {
        this.repo = repo;
    }

    @Override
    public void saveItem(ProductEntity product) throws ProductAlreadyExistException {
        if(!repo.existsByName(product.getName())){
            product.setCategory(repository2.findById(7L).get());
            repo.save(product);
        }
        else
            throw new ProductAlreadyExistException();
    }

    @Override
    public List<ProductEntity> getAllItems() {
        List<ProductEntity> l = (List<ProductEntity>) repo.findAll();
        return (List<ProductEntity>) repo.findAll();
    }

    @Override
    public void deleteItem(Long id) throws NoSuchProductException{
        if(repo.findById(id).isPresent()) {
            repo.delete(repo.findById(id).get());
        }
        else {
            throw new NoSuchProductException();
        }
    }

    @Override
    public void updateItems(ProductEntity p) throws NoSuchProductException{
        if(repo.findById(p.getId()).isPresent()) {
            ProductEntity old = repo.findById(p.getId()).get();
            if(old.getName()!=null && p.getName()==null) p.setName(old.getName());
            if(old.getPrice()!=null && p.getPrice()==null) p.setPrice(old.getPrice());
            if(old.getImgSource()!=null && p.getImgSource()==null) p.setImgSource(old.getImgSource());
            if(old.getHeight()!=null && p.getHeight()==null) p.setHeight(old.getHeight());
            if(old.getWidth()!=null && p.getWidth()==null) p.setWidth(old.getWidth());
            repo.save(p);
        }
        else {
            throw new NoSuchProductException();
        }
    }
}