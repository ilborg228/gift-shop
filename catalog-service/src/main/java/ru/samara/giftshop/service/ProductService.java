package ru.samara.giftshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.dto.DTOMapper;
import ru.samara.giftshop.dto.ProductDetails;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.exceptions.*;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService extends BaseService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product saveNewItem(Product product, Long categoryId) {
        if(productRepository.existsByName(product.getName())){
            throw new ApiException(DataValidationResponse.PRODUCT_ALREADY_EXIST);
        }
        if(!categoryRepository.existsById(categoryId)){
            throw new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND);
        }
        return productRepository.saveAndFlush(product);
    }

    public List<Product> getByCategoryId(Long categoryId) {
        if(!categoryRepository.existsById(categoryId)){
            throw new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND);
        }
        return productRepository.getProductsByCategoryId(categoryId);
    }

    public Product delete(Long id){
        Optional<Product> op = productRepository.findById(id);
        if(op.isPresent()) {
            productRepository.delete(op.get());
            return op.get();
        }
        else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }

    public void update(Product p){
        Optional<Product> op = productRepository.findById(p.getId());
        if(op.isPresent()) {
            Product old = op.get();
            if(old.getName()!=null && p.getName()==null) p.setName(old.getName());
            if(old.getPrice()!=null && p.getPrice()==null) p.setPrice(old.getPrice());
            if(old.getImgSource()!=null && p.getImgSource()==null) p.setImgSource(old.getImgSource());
            if(old.getHeight()!=null && p.getHeight()==null) p.setHeight(old.getHeight());
            if(old.getCategory()!=null && p.getCategory()==null) p.setCategory(old.getCategory());
            productRepository.save(p);
        }
        else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }

    public ProductDetails getProductDetails(Long id) {
        return DTOMapper.toProductDetails(productRepository
                .findById(id)
                .orElseThrow(()-> new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND)));
    }
}