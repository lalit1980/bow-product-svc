package com.boozeonwheel.product.exception.file;

public class DataCorruptedException extends RuntimeException {
    public DataCorruptedException(Throwable thr) {
        super(thr);
    }
}
