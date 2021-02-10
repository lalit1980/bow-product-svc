package com.boozeonwheel.product.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.boozeonwheel.product.domain.master.Master;
import com.boozeonwheel.product.repository.master.ProductMasterRepository;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boozeonwheel.product.service.file.FileSequenceGeneratorService;

/**
 * Created by Yadav, Lalit Singh on 21/01/2020.
 */

@Service
public class ExcelReader {

	@Autowired
    ProductMasterRepository masterRepository;

	@Autowired
	FileSequenceGeneratorService fileSeqGen;

	// public static final String SAMPLE_XLS_FILE_PATH = "./sample-xls-file.xls";
	public static final String SAMPLE_XLSX_FILE_PATH = "/Users/apple/Downloads/CLASSIC_WORK1.xls";

	public List<Master> readSupplierData(String filePath) throws EncryptedDocumentException, IOException {
		
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		HSSFWorkbook workbook = (HSSFWorkbook) WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		List<Master> masterList = new ArrayList<Master>();
		// Retrieving the number of sheets in the Workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

		// Getting the Sheet at index zero
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);

		// 3. Or you can use Java 8 forEach loop with lambda
		System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
		Map<String, String> map = new TreeMap<>();
		Set<DataDTO> listMaster = new HashSet<DataDTO>();
		if (sheet != null) {
			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					DataDTO dto = null;
					for (int j = 1; j < 5; j++) {
						if (row != null && row.getCell(1) != null && row.getCell(1).getStringCellValue() != null
								&& row.getCell(1).getStringCellValue().trim().length() > 0) {
							dto = new DataDTO();
							System.out.println(row.getCell(1).getStringCellValue());
							dto.setProductCode(row.getCell(1).getStringCellValue());
							if (row.getCell(1).getStringCellValue().equalsIgnoreCase("SW-700")) {
								System.out.println("Debug....");
							}
							if (row.getCell(2) != null && row.getCell(2).getCellTypeEnum() == CellType.STRING) {
								dto.setStyleDescription(row.getCell(2).getStringCellValue());
							}

							if (row.getCell(3) != null && row.getCell(3).getCellTypeEnum() == CellType.NUMERIC) {
								dto.setWidth(row.getCell(3).getNumericCellValue());
							}

							if (row.getCell(4) != null && row.getCell(4).getCellTypeEnum() == CellType.NUMERIC) {
								dto.setHeight(row.getCell(4).getNumericCellValue());
							}

							if (row.getCell(5) != null && row.getCell(5).getCellTypeEnum() == CellType.NUMERIC) {

								dto.setLength(row.getCell(5).getNumericCellValue());

							} else if (row.getCell(5) != null && row.getCell(5).getCellTypeEnum() == CellType.STRING) {

								dto.setLength(Double.parseDouble(row.getCell(5).getStringCellValue()));
							}

							if (row.getCell(6) != null && row.getCell(6).getCellTypeEnum() == CellType.STRING) {
								dto.setMaterial(row.getCell(6).getStringCellValue());
							}

							if (row.getCell(7) != null && row.getCell(7).getCellTypeEnum() == CellType.NUMERIC) {
								dto.setPrice(row.getCell(7).getNumericCellValue());
							} else if (row.getCell(7) != null && row.getCell(7).getCellTypeEnum() == CellType.STRING) {
								dto.setPrice(Double.parseDouble(row.getCell(7).getStringCellValue()));
							}
							listMaster.add(dto);
						}

					}

				}

			}
			
			listMaster.forEach(list -> {
				Master master = new Master();
				master.setId(fileSeqGen.generateSequence(Master.SEQUENCE_NAME));
				master.setCategoryId(2);
				master.setSlug(list.getProductCode());
				master.setName(list.getStyleDescription());
				master.setWidth(list.getWidth());
				master.setHeight(list.getHeight());
				master.setDepth(list.getLength());
				master.setDescription(list.getMaterial());
				master.setPrice("" + list.getPrice());
				System.out.println(master.toString());
				masterList.add(master);
			});

			System.out.println(map.size());
		}

		Map<String, PictureData> mapDat = getSheetPictrues03(sheet, workbook);
		System.out.println("Picture map Size: " + mapDat.size());
		System.out.println("List Size: " + listMaster.size());
		masterRepository.addAllProductMasters(masterList);
		workbook.close();
		return masterList;
	}

	public static void main(String[] args) throws IOException, InvalidFormatException {
		ExcelReader re=new ExcelReader();
		re.readSupplierData(SAMPLE_XLSX_FILE_PATH);
	}

	public static Map<String, PictureData> getSheetPictrues03(HSSFSheet sheet, HSSFWorkbook workbook) {
		Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();
		List<HSSFPictureData> pictures = workbook.getAllPictures();
		if (!pictures.isEmpty()) {
			for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
				HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
				if (shape instanceof HSSFPicture) {
					HSSFPicture pic = (HSSFPicture) shape;
					int pictureIndex = pic.getPictureIndex() - 1;
					HSSFPictureData picData = pictures.get(pictureIndex);
					String picIndex = String.valueOf(anchor.getRow1()) + "_" + String.valueOf(anchor.getCol1());
					sheetIndexPicMap.put(picIndex, picData);
				}
			}
			return sheetIndexPicMap;
		} else {
			return sheetIndexPicMap;
		}
	}
}
