package ru.samara.giftshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.dto.CommentDto;
import ru.samara.giftshop.dto.DtoMapper;
import ru.samara.giftshop.dto.ProductDto;
import ru.samara.giftshop.dto.ProductDetails;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.exceptions.*;
import ru.samara.giftshop.helpers.OrderBy;
import ru.samara.giftshop.helpers.OrderByType;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.repository.CommentRepository;
import ru.samara.giftshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService extends BaseService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    public Product saveNewItem(ProductDetails product) {
        if(product.getCategoryId() == null)
            throw new ApiException(DataValidationResponse.INVALID_REQUEST);
        if(productRepository.existsByName(product.getName()))
            throw new ApiException(DataValidationResponse.PRODUCT_ALREADY_EXIST);
        if(!categoryRepository.existsById(product.getCategoryId()))
            throw new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND);
        return productRepository.save(DtoMapper.toProduct(product));
    }

    public List<ProductDto> getByCategoryId(
            Long categoryId, Integer page, Integer pageSize, OrderBy orderBy, OrderByType orderByType) {

        if(!categoryRepository.existsById(categoryId)){
            throw new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND);
        }

        Sort sort = Sort.by(Sort.Direction.fromString(orderByType.getDirection()),orderBy.getColumn());
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        return productRepository.findProductsByCategoryId(categoryId,pageable).stream()
                .map(DtoMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        Optional<Product> op = productRepository.findById(id);
        if(op.isPresent()) {
            productRepository.delete(op.get());
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
            if(old.getHeight()!=null && p.getHeight()==null) p.setHeight(old.getHeight());
            if(old.getCategory()!=null && p.getCategory()==null) p.setCategory(old.getCategory());
            productRepository.save(p);
        }
        else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }

    public ProductDetails getProductDetails(Long id) {
        return DtoMapper.toProductDetails(productRepository
                .findById(id)
                .orElseThrow(()-> new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND)));
    }

    public List<CommentDto> getCommentsByProductId(Long productId) {
        Sort sort = Sort.by(Sort.Direction.DESC,"creation");
        Pageable pageable = PageRequest.of(0,5,sort);
        Product product = new Product();
        product.setId(productId);

        return commentRepository
                .findAllByProduct(product, pageable)
                .stream()
                .map(DtoMapper::toCommentDto)
                .collect(Collectors.toList());
    }
}