package ru.samara.giftshop.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MyMail {
    private String to;
    private String subject;
    private String text;
}
