package ru.samara.giftshop.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String text;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    private Date creation;

    @Enumerated(EnumType.STRING)
    private ScoreValue scoreValue;

    public enum ScoreValue {
        ONE, TWO, THREE, FOUR, FIVE
    }
}
