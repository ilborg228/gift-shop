package ru.samara.giftshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="product_images")
@Data
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private Boolean primaryImage;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}
