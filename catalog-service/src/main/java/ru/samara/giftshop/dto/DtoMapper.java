package ru.samara.giftshop.dto;

import ru.samara.giftshop.entity.*;

import java.util.stream.Collectors;

public class DtoMapper {

    public static CategoryDto toCategoryDTO(Category c){
        CategoryDto category = new CategoryDto();
        category.setId(c.getId());
        category.setCategoryName(c.getCategoryName());
        category.setImageUrl(c.getImageUrl());
        category.setParentId(c.getParent() != null ? c.getParent().getId() : null);
        category.setHasChild(c.isHasChild());
        return category;
    }


    public static Category toCategory(CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setCategoryName(dto.getCategoryName());
        category.setImageUrl(dto.getImageUrl());
        if (dto.getParentId() != null) {
            Category parent = new Category();
            parent.setId(dto.getParentId());
            category.setParent(parent);
        }
        return category;
    }

    public static ProductDto toProductDTO(Product p){
        ProductDto dto = new ProductDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setCategoryId(p.getCategory().getId());
        dto.setImageUrl(p.getProductImages().stream()
                .filter(ProductImage::getPrimaryImage)
                .findFirst()
                .map(ProductImage::getImageUrl)
                .orElse(null));
        return dto;
    }

    public static ProductDetails toProductDetails(Product p){
        ProductDetails dto = new ProductDetails();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setCategoryId(p.getCategory().getId());
        dto.setDescription(p.getDescription());
        dto.setHeight(p.getHeight());
        dto.setImageUrl(p.getProductImages().stream()
                .filter(ProductImage::getPrimaryImage)
                .findFirst().map(ProductImage::getImageUrl)
                .orElse(null));
        dto.setImages(p.getProductImages().stream()
                .filter(i->!i.getPrimaryImage())
                .map(ProductImage::getImageUrl)
                .collect(Collectors.toList()));
        return dto;
    }

    public static Product toProduct(ProductDetails productDetails){
        Product product = new Product();
        product.setId(productDetails.getId());
        product.setName(productDetails.getName());
        product.setHeight(productDetails.getHeight());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());
        Category category = new Category();
        category.setId(productDetails.getCategoryId());
        product.setCategory(category);
        return product;
    }

    public static Comment toComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setCreation(commentDto.getCreation());
        comment.setId(commentDto.getId());
        comment.setScoreValue(commentDto.getScoreValue());
        comment.setText(commentDto.getText());
        Product product = new Product();
        product.setId(commentDto.getProductId());
        comment.setProduct(product);
        return comment;
    }

    public static CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCreation(comment.getCreation());
        commentDto.setId(comment.getId());
        commentDto.setScoreValue(comment.getScoreValue());
        commentDto.setText(comment.getText());
        commentDto.setProductId(comment.getProduct().getId());
        return commentDto;
    }

    public static ProductImageDto toProductImageDto(ProductImage image) {
        ProductImageDto productImageDto = new ProductImageDto();
        productImageDto.setId(image.getId());
        productImageDto.setPrimaryImage(image.getPrimaryImage());
        productImageDto.setImageUrl(image.getImageUrl());
        return productImageDto;
    }

    public static OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
        orderDto.setAddress(order.getAddress());
        orderDto.setOrderCreation(order.getOrderCreation());
        orderDto.setProducts(
                order.getProducts()
                        .stream()
                        .map(DtoMapper::toProductDTO)
                        .collect(Collectors.toList())
        );
        return orderDto;
    }
}
