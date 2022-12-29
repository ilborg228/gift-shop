package ru.samara.giftshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="product_images")
@Data
@NamedQuery(name = "ProductImage.findAllByProducts"
        , query = "select imageUrl from ProductImage where product.id in (?1) and primaryImage = true")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private Boolean primaryImage;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}
