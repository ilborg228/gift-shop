package ru.samara.giftshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.exceptions.ProductNotFoundException;
import ru.samara.giftshop.exceptions.ProductAlreadyExistException;
import ru.samara.giftshop.repository.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository repo;

    @Override
    public void saveNewItem(ProductEntity product) {
        if(!repo.existsByName(product.getName())){
            repo.save(product);
        }
        else
            throw new ProductAlreadyExistException();
    }

    @Override
    public List<ProductEntity> findAll() {
        List<ProductEntity> l = (List<ProductEntity>) repo.findAll();
        return l;
    }

    @Override
    public ProductEntity delete(Long id){
        Optional<ProductEntity> op =repo.findById(id);
        if(op.isPresent()) {
            repo.delete(op.get());
            return op.get();
        }
        else {
            throw new ProductNotFoundException(id.toString());
        }
    }

    @Override
    public void update(ProductEntity p){
        Optional<ProductEntity> op = repo.findById(p.getId());
        if(op.isPresent()) {
            ProductEntity old = op.get();
            if(old.getName()!=null && p.getName()==null) p.setName(old.getName());
            if(old.getPrice()!=null && p.getPrice()==null) p.setPrice(old.getPrice());
            if(old.getImgSource()!=null && p.getImgSource()==null) p.setImgSource(old.getImgSource());
            if(old.getHeight()!=null && p.getHeight()==null) p.setHeight(old.getHeight());
            if(old.getCategory()!=null && p.getCategory()==null) p.setCategory(old.getCategory());
            repo.save(p);
        }
        else {
            throw new ProductNotFoundException(p.getId().toString());
        }
    }
}