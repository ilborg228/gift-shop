package ru.samara.giftshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    private String imageUrl;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    private boolean hasChild;
}
