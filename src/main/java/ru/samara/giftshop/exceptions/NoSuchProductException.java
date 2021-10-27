package ru.samara.giftshop.exceptions;

public class NoSuchProductException extends Exception{
    public NoSuchProductException() {
        super("Такого товара нет");
    }
}
