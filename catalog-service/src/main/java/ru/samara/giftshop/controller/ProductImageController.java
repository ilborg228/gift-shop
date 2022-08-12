package ru.samara.giftshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.samara.giftshop.dto.DtoMapper;
import ru.samara.giftshop.dto.ProductImageDto;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.repository.ProductImageRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductImageController extends BaseController{
    private final ProductImageRepository productImageRepository;

    @GetMapping("/products/{productId}/images")
    public List<ProductImageDto> getImages(@PathVariable Long productId) {
        return productImageRepository
                .findAllByProduct(new Product(productId))
                .stream()
                .map(DtoMapper::toProductImageDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{productId}/image")
    public ProductImageDto getPrimaryImage(@PathVariable Long productId) {
        return DtoMapper.toProductImageDto(productImageRepository
                .findProductImageByProductAndPrimaryImage(
                        new Product(productId), true));
    }
}
