package ru.samara.giftshop.exceptions;

@Deprecated
public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException() {
        super("Товар с таким именем уже существует");
    }
}
