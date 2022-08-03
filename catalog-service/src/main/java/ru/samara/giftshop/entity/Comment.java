package ru.samara.giftshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comments")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String text;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public Date creation;

    public Integer scoreValue;
}
