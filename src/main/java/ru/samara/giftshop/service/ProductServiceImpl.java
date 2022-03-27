package ru.samara.giftshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.exceptions.*;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.repository.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void saveNewItem(Product product, Long categoryId) {
        if(productsRepository.existsByName(product.getName())){
            throw new ApiException(DataValidationResponse.PRODUCT_ALREADY_EXIST);
        }
        if(!categoryRepository.existsById(categoryId)){
            throw new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND);
        }
        productsRepository.save(product);
    }

    @Override
    public List<Product> getByCategoryId(Long categoryId) {
        if(!categoryRepository.existsById(categoryId)){
            throw new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND);
        }
        return productsRepository.getProductsByCategoryId(categoryId);
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