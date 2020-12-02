package com.boozeonwheel.product.exception.category;

public class CategoryNotFoundException extends Exception{
	 public CategoryNotFoundException(String errorMessage, Throwable err) {
		 super(errorMessage, err);
	    }
}
