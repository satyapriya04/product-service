package com.example.product.exception;


public class ProductException extends RuntimeException {

    public ProductException(RuntimeException e) {

        super(e);
    }
    public ProductException(String message) {

        super(message);
    }

    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
