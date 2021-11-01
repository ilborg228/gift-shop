package ru.samara.giftshop.exceptions;

public class NoSuchCategoryException extends Exception{

    public NoSuchCategoryException(Long id) {
        super("Нет "+id+" категории");
    }
}
