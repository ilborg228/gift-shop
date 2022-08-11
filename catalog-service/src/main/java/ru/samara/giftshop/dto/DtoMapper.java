package ru.samara.giftshop.dto;

import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.entity.Comment;
import ru.samara.giftshop.entity.Product;

public class DtoMapper {

    public static CategoryDto toCategoryDTO(Category c){
        return new CategoryDto(c.getId(), c.getCategoryName(), c.getImgSource(),
                c.getChild() != null ? toCategoryDTO(c.getChild()) : null);
    }

    public static ProductDto toProductDTO(Product p){
        ProductDto dto = new ProductDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setCategoryId(p.getCategory().getId());
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
}
