package ru.samara.giftshop.exceptions;

public class NoSuchProductException extends RuntimeException{
    public NoSuchProductException() {
        super("Такого товара нет");
    }
}
