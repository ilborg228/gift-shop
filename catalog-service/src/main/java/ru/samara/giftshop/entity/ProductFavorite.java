package ru.samara.giftshop.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name="product_favorites")
@Data
public class ProductFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
