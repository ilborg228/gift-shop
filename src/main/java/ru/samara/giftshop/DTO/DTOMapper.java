package ru.samara.giftshop.DTO;

import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {

    public static CategoryDTO toCategoryDTO(Category c){
        return new CategoryDTO(c.getCategoryName(),c.getImgSource(),c.getId());
    }

    public static ProductDTO toProductDTO(Product p){
        return new ProductDTO(p.getName(),p.getPrice(),p.getImgSource(),p.getHeight(), p.getCategory().getId());
    }
}
