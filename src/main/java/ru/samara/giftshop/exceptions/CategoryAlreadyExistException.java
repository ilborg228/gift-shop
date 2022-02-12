package ru.samara.giftshop.exceptions;

@Deprecated
public class CategoryAlreadyExistException extends RuntimeException{
    public CategoryAlreadyExistException() {
        super("Такая категория уже есть");
    }
}
