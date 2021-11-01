package ru.samara.giftshop.exceptions;

public class NoSuchCategoryException extends RuntimeException{

    public NoSuchCategoryException(Long id) {
        super("Нет "+id+" категории");
    }
}
