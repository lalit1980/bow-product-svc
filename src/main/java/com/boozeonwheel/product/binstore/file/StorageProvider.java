package com.boozeonwheel.product.binstore.file;

public interface StorageProvider {
    void store(String fileName, byte[] content);

    String getLocation();
}
