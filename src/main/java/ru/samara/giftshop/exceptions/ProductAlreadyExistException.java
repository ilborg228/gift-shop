package ru.samara.giftshop.exceptions;

public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException() {
        super("Товар с таким именем уже существует");
    }
}
