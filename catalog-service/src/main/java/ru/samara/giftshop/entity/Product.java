package ru.samara.giftshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String imgSource;

    private Integer height;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;
}