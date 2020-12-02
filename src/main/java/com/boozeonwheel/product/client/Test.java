package com.boozeonwheel.product.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
	public static void main(String[] args) {
		try {
			String filePath = "/Users/apple/Downloads/CLASSIC_work.xlsx";
			FileInputStream excelFile = new FileInputStream(new File(filePath));
			Workbook workbook = new XSSFWorkbook(excelFile);
			List<? extends PictureData> lst = workbook.getAllPictures();
			int i=0;
			for (Iterator it = lst.iterator(); it.hasNext();) {
				PictureData pict = (PictureData) it.next();
				String ext = pict.suggestFileExtension();
				byte[] data = pict.getData();
				if (ext.equals("jpeg")) {
					FileOutputStream out = new FileOutputStream("/Users/apple/Downloads/picconvert/SW-00"+i+".jpg");
					out.write(data);
					out.close();
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
