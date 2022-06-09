package ru.samara.giftshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.dto.DTOMapper;
import ru.samara.giftshop.dto.ProductDTO;
import ru.samara.giftshop.dto.ProductDetails;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.exceptions.*;
import ru.samara.giftshop.helpers.OrderBy;
import ru.samara.giftshop.helpers.OrderByType;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return productRepository.save(product);
    }

    public List<ProductDTO> getByCategoryId(
            Long categoryId, Integer page, Integer pageSize, OrderBy orderBy, OrderByType orderByType) {

        if(!categoryRepository.existsById(categoryId)){
            throw new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND);
        }

        Sort sort = Sort.by(Sort.Direction.fromString(orderByType.getDirection()),orderBy.getColumn());
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        return productRepository.findProductsByCategoryId(categoryId,pageable).stream()
                .map(DTOMapper::toProductDTO)
                .collect(Collectors.toList());
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