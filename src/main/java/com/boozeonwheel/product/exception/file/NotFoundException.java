package com.boozeonwheel.product.exception.file;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {
	public NotFoundException() {

	}

	public NotFoundException(String message) {
		super(message);
	}
	public NotFoundException(Supplier<String> supplier) {
		super(supplier.get());
	}
	public static void when(boolean expression, String message) {
		if(expression) throw new NotFoundException(message);
	}
	public static void whenNot(boolean expression, String message) {
		if(!expression) throw new NotFoundException(message);
	}

}
