package com.boozeonwheel.product.service.file;

public interface StorageProvider {
    void store(String fileName, byte[] content);

    String getLocation();
}
