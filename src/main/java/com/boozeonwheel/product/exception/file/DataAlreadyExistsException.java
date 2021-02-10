package com.boozeonwheel.product.exception.file;

public class DataAlreadyExistsException extends RuntimeException {
    public DataAlreadyExistsException(String message) {
        super(message);
    }
}
