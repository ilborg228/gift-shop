package ru.samara.giftshop.exceptions;

@Deprecated
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String e) {
        super("Product: "+e+" not found");
    }
}
