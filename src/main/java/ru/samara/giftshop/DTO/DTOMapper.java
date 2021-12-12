package ru.samara.giftshop.DTO;

import org.springframework.stereotype.Component;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.entity.UserEntity;

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

    public static UserDTO toUserDTO(UserEntity u){
        return new UserDTO(u.getName(),u.getRole());
    }
}
