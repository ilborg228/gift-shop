package ru.samara.giftshop.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="goods")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProductEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Double price;

    private String imgSource;

    private Integer height;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private CategoryEntity category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductEntity that = (ProductEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}