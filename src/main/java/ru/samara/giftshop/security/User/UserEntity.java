package ru.samara.giftshop.security.User;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME", length = 50, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 100)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private RoleEntity role;
}
