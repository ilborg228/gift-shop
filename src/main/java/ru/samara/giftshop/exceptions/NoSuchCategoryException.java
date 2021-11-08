package ru.samara.giftshop.exceptions;

public class NoSuchCategoryException extends RuntimeException{

    public NoSuchCategoryException(String e) {
        super("Нет "+e+" категории");
    }
}
