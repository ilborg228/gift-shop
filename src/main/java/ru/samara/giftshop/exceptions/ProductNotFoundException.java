package ru.samara.giftshop.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String e) {
        super("Product: "+e+" not found");
    }
}
