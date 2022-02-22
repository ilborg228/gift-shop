package ru.samara.giftshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.exceptions.*;
import ru.samara.giftshop.repository.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    @Override
    public void saveNewItem(Product product) {
        if(!productsRepository.existsByName(product.getName())){
            productsRepository.save(product);
        }
        else
            throw new ApiException(DataValidationResponse.PRODUCT_ALREADY_EXIST);
    }

    @Override
    public List<Product> findAll() {
        List<Product> l = (List<Product>) productsRepository.findAll();
        return l;
    }

    @Override
    public Product delete(Long id){
        Optional<Product> op = productsRepository.findById(id);
        if(op.isPresent()) {
            productsRepository.delete(op.get());
            return op.get();
        }
        else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public void update(Product p){
        Optional<Product> op = productsRepository.findById(p.getId());
        if(op.isPresent()) {
            Product old = op.get();
            if(old.getName()!=null && p.getName()==null) p.setName(old.getName());
            if(old.getPrice()!=null && p.getPrice()==null) p.setPrice(old.getPrice());
            if(old.getImgSource()!=null && p.getImgSource()==null) p.setImgSource(old.getImgSource());
            if(old.getHeight()!=null && p.getHeight()==null) p.setHeight(old.getHeight());
            if(old.getCategory()!=null && p.getCategory()==null) p.setCategory(old.getCategory());
            productsRepository.save(p);
        }
        else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }
}