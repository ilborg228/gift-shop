package ru.samara.giftshop.DTO;

import ru.samara.giftshop.DTO.CategoryDTO;
import ru.samara.giftshop.DTO.ProductDTO;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.security.UserDTO;
import ru.samara.giftshop.security.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {

    public static CategoryDTO toCategoryDTO(CategoryEntity c){
        List<ProductEntity> products = c.getProducts().stream()
                .peek(p->p.setCategory(null))
                .collect(Collectors.toList());
        return new CategoryDTO(c.getCategoryName(),c.getImgSource(),products);
    }

    public static ProductDTO toProductDTO(ProductEntity p){
        CategoryEntity category = p.getCategory();
        category.setProducts(null);
        return new ProductDTO(p.getName(),p.getPrice(),p.getImgSource(),p.getHeight(),category);
    }
}
