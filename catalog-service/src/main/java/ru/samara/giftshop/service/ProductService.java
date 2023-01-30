package ru.samara.giftshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.dto.*;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.exceptions.*;
import ru.samara.giftshop.helpers.OrderBy;
import ru.samara.giftshop.helpers.OrderByType;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.repository.CommentRepository;
import ru.samara.giftshop.repository.ProductImageRepository;
import ru.samara.giftshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService extends BaseService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final ProductImageRepository productImageRepository;

    public Product saveNewItem(ProductDetails product){
        notNull(product);
        notNull(product.getCategoryId());

        if(productRepository.existsByName(product.getName()))
            throw new ApiException(DataValidationResponse.PRODUCT_ALREADY_EXIST);
        if(!categoryRepository.existsById(product.getCategoryId()))
            throw new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND);

        MyMail mail = MyMail.builder()
                .to("shirokih_i@mail.ru")
                .subject(product.getName())
                .text("New product was created!!")
                .build();
        try {
            rabbitTemplate.convertAndSend("notificationQueue", jsonMapper.writeValueAsString(mail));
        } catch (JsonProcessingException ex) {
            logger.error(ex.getMessage());
        }
        return productRepository.save(DtoMapper.toProduct(product));
    }

    public ProductListDto getByCategoryId(
            Long categoryId, Integer page, Integer pageSize, OrderBy orderBy, OrderByType orderByType) {

        if(!categoryRepository.existsById(categoryId)){
            throw new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND);
        }

        Long count = productRepository.countAllByCategoryId(categoryId);

        Sort sort = Sort.by(Sort.Direction.fromString(orderByType.getDirection()), orderBy.getColumn());
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        List<ProductDto> products = productRepository.findProductsByCategoryId(categoryId, pageable)
                .stream()
                .map(DtoMapper::toProductDTO)
                .collect(Collectors.toList());

        Optional<Category> category = categoryRepository.findById(categoryId);
        return new ProductListDto(products, count, category.map(Category::getCategoryName).orElse(null));
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
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND));

        productRepository.incrementView(product.getId());

        return DtoMapper.toProductDetails(product);
    }

    public List<CommentDto> getCommentsByProductId(Long productId) {
        Sort sort = Sort.by(Sort.Direction.DESC, OrderBy.CREATION.getColumn());
        Pageable pageable = PageRequest.of(0,5,sort);

        return commentRepository
                .findAllByProductId(productId, pageable)
                .stream()
                .map(DtoMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getMostViewed() {
        Sort sort = Sort.by(Sort.Direction.DESC, OrderBy.VIEWS.getColumn());
        Pageable pageable = PageRequest.of(0,5,sort);

        return productRepository
                .findAll(pageable)
                .stream()
                .map(DtoMapper::toProductDTO)
                .collect(Collectors.toList());
    }
}