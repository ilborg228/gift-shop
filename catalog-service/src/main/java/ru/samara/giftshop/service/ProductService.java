package ru.samara.giftshop.service;

import lombok.RequiredArgsConstructor;
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
import ru.samara.giftshop.repository.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService extends BaseService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    public void saveNewItem(ProductDetails product){
        notNull(product);
        notNull(product.getName());
        notNull(product.getCategoryId());

        if(productRepository.existsByName(product.getName()))
            throw new ApiException(DataValidationResponse.PRODUCT_ALREADY_EXIST);

        Category category = categoryRepository.findById(product.getCategoryId())
                .orElseThrow(()-> new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND));
        product.setCategoryId(category.getId());
        productRepository.save(DtoMapper.toProduct(product));
    }

    public Long getProductIdByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(()-> new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND))
                .getId();
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

    public void updateInStock(ProductDetails p){
        Product product = productRepository.findById(p.getId())
                .orElseThrow(()-> new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND));
        if(!Objects.equals(product.getInStock(), p.getInStock()))
            product.setInStock(p.getInStock());
        productRepository.save(product);
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