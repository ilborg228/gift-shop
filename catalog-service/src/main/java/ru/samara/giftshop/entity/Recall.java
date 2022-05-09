package ru.samara.giftshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="recall")
@Data
public class Recall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String phone;
}
