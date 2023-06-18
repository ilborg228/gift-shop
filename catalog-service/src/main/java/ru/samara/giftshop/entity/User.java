package ru.samara.giftshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Status role;

    public enum Status {
        GUEST, ADMIN;
    }

    public User(String id) {
        this.id = UUID.fromString(id);
    }
}
