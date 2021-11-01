package ru.samara.giftshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="goods")
@Data
public class ProductEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Double price;

    private String imgSource;

    private Integer height;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;
}