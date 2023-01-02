package ru.samara.giftshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
@Data
@NamedQuery(name = "Product.incrementView", query = "update Product set views = views + 1 where id = :productId")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer height;

    private String description;

    private Long views;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductImage> productImages;

    public Product (Long id) {
        this.id = id;
    }

    public Product(){}
}