package com.boozeonwheel.product.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.boozeonwheel.product.dto.taxonomies.HomeResult;
import com.google.gson.Gson;

public class Dummy {
	public static void main(String[] args) {
		Gson gson = new Gson();
		HomeResult bannerResult=null;
        try (Reader reader = new FileReader("/Users/apple/Desktop/Product.json")) {

            // Convert JSON File to Java Object
        	bannerResult = gson.fromJson(reader,HomeResult.class);
			
			// print staff object
            System.out.println(bannerResult);

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
