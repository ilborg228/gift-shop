package ru.samara.giftshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
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

    public User(Long id) {
        this.id = id;
    }
}
