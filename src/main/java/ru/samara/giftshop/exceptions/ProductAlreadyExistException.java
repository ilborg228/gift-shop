package ru.samara.giftshop.exceptions;

public class ProductAlreadyExistException extends Exception{
    public ProductAlreadyExistException() {
        super("Товар с таким именем уже существует");
    }
}
