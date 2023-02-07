package ru.samara.giftshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Status role;

    public enum Status {
        GUEST, ADMIN;
    }
}
