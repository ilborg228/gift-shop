package ru.samara.giftshop.exceptions;

public class CategoryAlreadyExistException extends Exception{
    public CategoryAlreadyExistException() {
        super("Такая категория уже есть");
    }
}
