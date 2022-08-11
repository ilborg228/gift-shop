package ru.samara.giftshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="product_images")
@Data
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgSource;

    private Boolean primaryImage;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}
