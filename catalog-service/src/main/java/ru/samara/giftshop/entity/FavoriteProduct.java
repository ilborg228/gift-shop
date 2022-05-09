package ru.samara.giftshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="favorites")
@Data
public class FavoriteProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
