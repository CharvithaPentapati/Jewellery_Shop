package com.jewelleryshopping.globalexception;

@SuppressWarnings("serial")
public class InvalidEntityException extends RuntimeException{
    public InvalidEntityException(String message) {
        super(message);
    }
 
    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
