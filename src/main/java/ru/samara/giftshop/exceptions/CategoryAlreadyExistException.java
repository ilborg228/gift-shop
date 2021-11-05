package ru.samara.giftshop.exceptions;

public class CategoryAlreadyExistException extends RuntimeException{
    public CategoryAlreadyExistException() {
        super("Такая категория уже есть");
    }
}
