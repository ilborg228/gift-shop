package ru.samara.giftshop.DTO;

import org.springframework.util.StringUtils;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {

    public static CategoryDTO toCategoryDTO(Category c){
        return new CategoryDTO(c.getCategoryName(),c.getImgSource(),c.getId());
    }

    public static ProductDTO toProductDTO(Product p){
        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setImgSource(p.getImgSource());
        dto.setCategoryId(p.getCategory().getId());
        return dto;
    }

    public static ProductDetails toProductDetails(Product p){
        ProductDetails dto = new ProductDetails();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setImgSource(p.getImgSource());
        dto.setCategoryId(p.getCategory().getId());
        dto.setDescription(p.getDescription());
        dto.setHeight(p.getHeight());
        return dto;
    }
}
