package ru.samara.giftshop.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public class MyMail {
    private String to;
    private String subject;
    private String text;
}
