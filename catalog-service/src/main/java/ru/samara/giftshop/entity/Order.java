package ru.samara.giftshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> product;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private String address;

    private Date orderCreation;
}
