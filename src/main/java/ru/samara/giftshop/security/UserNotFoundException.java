package ru.samara.giftshop.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String e) {
        super("User: "+e+" not found");
    }
}
