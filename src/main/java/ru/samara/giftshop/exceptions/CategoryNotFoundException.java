package ru.samara.giftshop.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String e) {
        super("Category: "+e+" not found");
    }
}
