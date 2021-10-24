package ru.samara.giftshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="goods")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String imgSource;

    private Integer height;

    private Integer width;
}