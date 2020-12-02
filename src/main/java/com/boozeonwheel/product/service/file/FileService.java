package com.boozeonwheel.product.service.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.CollectionUtils;
import com.boozeonwheel.product.client.DataDTO;
import com.boozeonwheel.product.domain.file.FileMetaData;
import com.boozeonwheel.product.domain.file.ProductCategoryFileMetaData;
import com.boozeonwheel.product.domain.master.Master;
import com.boozeonwheel.product.domain.url.UrlType;
import com.boozeonwheel.product.exception.file.DataCorruptedException;
import com.boozeonwheel.product.repository.file.FileRepository;
import com.boozeonwheel.product.repository.file.ProductCategoryFileRepository;
import com.boozeonwheel.product.repository.master.ProductMasterRepository;
import com.boozeonwheel.product.repository.url.UrlTypeRepository;

@Service
public class FileService {

	private AmazonS3 s3client;

	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;
	@Value("${amazonProperties.bucketName}")
	private String bucketName;
	@Value("${amazonProperties.accessKey}")
	private String accessKey;
	@Value("${amazonProperties.secretKey}")
	private String secretKey;
	
	@Value("${dataflag.mockdata}")
	private Boolean mockFlag;

	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	ProductCategoryFileRepository productCategoryFileRepository;

	@Autowired
	UrlTypeRepository urlTypeRepo;

	@Autowired
	FileSequenceGeneratorService fileSeqGen;

	@Autowired
	ProductMasterRepository productMasterRepo;

	@Autowired
	DiskStorageProvider storageProvider;

	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		this.s3client = new AmazonS3Client(credentials);
	}
	
	public String uploadCategoryFileMetaData(MultipartFile multipartFile, long categoryId, Long id, Integer urlTypeId, String action) {
		File file = null;
		String fileUrl = null;
		try {
			file = convertMultiPartToFile(multipartFile);
			if (action.equalsIgnoreCase("edit")) {
				List<ProductCategoryFileMetaData> fileDataList = productCategoryFileRepository.findByFileId(id);
				if(!CollectionUtils.isNullOrEmpty(fileDataList) ) {
					for (ProductCategoryFileMetaData fileData : fileDataList) {
						deleteFileFromS3Bucket(fileData.getS3Path());
						String fileName = generateProductCategoryFileName(multipartFile);
						fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
						uploadFileTos3bucket(fileName, file);
						ProductCategoryFileMetaData fileMetaData = new ProductCategoryFileMetaData();
						String location = getTargetFileLocation(fileName);
						fileMetaData.setId(fileData.getId());
						fileMetaData.setFileName(fileName);
						fileMetaData.setContentSize(multipartFile.getSize());
						fileMetaData.setContentType(multipartFile.getContentType());
						if (multipartFile.getContentType().equalsIgnoreCase("image/jpeg")
								|| multipartFile.getContentType().equalsIgnoreCase("image/png")) {
							BufferedImage bimg = ImageIO.read(file);
							fileMetaData.setAttachmentHeight(bimg.getHeight());
							fileMetaData.setAttachmentWidth(bimg.getWidth());
							fileMetaData.setType("Spree::"+multipartFile.getContentType());
							fileMetaData.setViewableType("Spree::Category");
						}
						fileMetaData.setViewableId(0);
						UrlType urlType = urlTypeRepo.findByUrlId(urlTypeId);
						fileMetaData.setUrlTypeId(urlTypeId);
						fileMetaData.setUrlType(urlType.getUrlType());
						fileMetaData.setAlt(fileName);
						fileMetaData.setPosition(1);
						fileMetaData.setLocation(location);
						fileMetaData.setS3Path(fileUrl);
						fileMetaData.setAttachmentUpdatedAt(new Date());
						fileMetaData.setProductCategoryId(categoryId);
						
						UUID uuid = UUID.randomUUID();
						String randomUUIDString = uuid.toString();
						fileMetaData.setFileId(randomUUIDString);
						productCategoryFileRepository.updateFileMetaData(fileMetaData, id);
						storageProvider.store(fileName, extractContent(multipartFile));
						
					}
					
				}
			}else if(action.equalsIgnoreCase("add")) {
				long generatedId = fileSeqGen.generateSequence(ProductCategoryFileMetaData.SEQUENCE_NAME);
				
				String fileName = generateProductCategoryFileName(multipartFile);
				fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
				uploadFileTos3bucket(fileName, file);
				ProductCategoryFileMetaData fileMetaData = new ProductCategoryFileMetaData();
				String location = getTargetFileLocation(fileName);
				fileMetaData.setId(generatedId);
				fileMetaData.setFileName(fileName);
				fileMetaData.setContentSize(multipartFile.getSize());
				fileMetaData.setContentType(multipartFile.getContentType());
				if (multipartFile.getContentType().equalsIgnoreCase("image/jpeg")
						|| multipartFile.getContentType().equalsIgnoreCase("image/png")) {
					BufferedImage bimg = ImageIO.read(file);
					fileMetaData.setAttachmentHeight(bimg.getHeight());
					fileMetaData.setAttachmentWidth(bimg.getWidth());
					fileMetaData.setType("Spree::"+multipartFile.getContentType());
					fileMetaData.setViewableType("Spree::Category");
				}
				fileMetaData.setViewableId(0);
				UrlType urlType = urlTypeRepo.findByUrlId(urlTypeId);
				fileMetaData.setUrlTypeId(urlTypeId);
				fileMetaData.setUrlType(urlType.getUrlType());
				fileMetaData.setAlt(fileName);
				fileMetaData.setPosition(1);
				fileMetaData.setLocation(location);
				fileMetaData.setS3Path(fileUrl);
				fileMetaData.setAttachmentUpdatedAt(new Date());
				
				UUID uuid = UUID.randomUUID();
				String randomUUIDString = uuid.toString();
				fileMetaData.setFileId(randomUUIDString);
				fileMetaData.setProductCategoryId(categoryId);
				productCategoryFileRepository.save(fileMetaData);
				storageProvider.store(fileName, extractContent(multipartFile));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fileUrl;
		
	}

	public String uploadFile(MultipartFile multipartFile, Long productCode, Long id, Integer urlTypeId, String action) {
		File file = null;
		String fileUrl = null;
		try {
			file = convertMultiPartToFile(multipartFile);
			if (action.equalsIgnoreCase("edit")) {
				List<FileMetaData> fileDataList = fileRepository.findByFileId(id);
				if(!CollectionUtils.isNullOrEmpty(fileDataList) ) {
					for (FileMetaData fileData : fileDataList) {
						Master master = productMasterRepo
								.findByProductMasterId(productCode != null ? productCode.intValue() : null);
						deleteFileFromS3Bucket(fileData.getS3Path());
						String fileName = generateFileName(multipartFile, productCode, id, urlTypeId,master.getSku());
						fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
						uploadFileTos3bucket(fileName, file);
						FileMetaData fileMetaData = new FileMetaData();
						String location = getTargetFileLocation(fileName);
						fileMetaData.setId(fileData.getId());
						fileMetaData.setFileName(fileName);
						fileMetaData.setContentSize(multipartFile.getSize());
						fileMetaData.setContentType(multipartFile.getContentType());
						if (multipartFile.getContentType().equalsIgnoreCase("image/jpeg")
								|| multipartFile.getContentType().equalsIgnoreCase("image/jpeg")) {
							BufferedImage bimg = ImageIO.read(file);
							fileMetaData.setAttachmentHeight(bimg.getHeight());
							fileMetaData.setAttachmentWidth(bimg.getWidth());
							fileMetaData.setType("Spree::Image");
						}
						fileMetaData.setViewableId(productCode != null ? productCode.intValue() : null);
						
						if (master != null) {
							if (!master.getIsMaster()) {
								fileMetaData.setViewableType("Spree::Variant");
							} else {
								fileMetaData.setViewableType("Spree::Master");
							}
						}
						UrlType urlType = urlTypeRepo.findByUrlId(urlTypeId);
						fileMetaData.setUrlTypeId(urlTypeId);
						fileMetaData.setUrlType(urlType.getUrlType());
						fileMetaData.setAlt(master.getName());
						fileMetaData.setPosition(1);
						fileMetaData.setLocation(location);
						fileMetaData.setS3Path(fileUrl);
						fileMetaData.setAttachmentUpdatedAt(new Date());
						fileMetaData.setProductCode(productCode);
						UUID uuid = UUID.randomUUID();
						String randomUUIDString = uuid.toString();
						fileMetaData.setFileId(randomUUIDString);
						fileRepository.updateFileMetaData(fileMetaData, id);
						storageProvider.store(fileName, extractContent(multipartFile));
						
					}
					
				}
				
			} else if (action.equalsIgnoreCase("add")) {
				if(mockFlag) {
					for(int i=1;i<=4;i++) {
						long generatedId = fileSeqGen.generateSequence(FileMetaData.SEQUENCE_NAME);
						Master master = productMasterRepo
								.findByProductMasterId(productCode != null ? productCode.intValue() : null);
						String fileName = generateFileName(multipartFile, productCode, generatedId, urlTypeId, master.getSku());
						fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
						uploadFileTos3bucket(fileName, file);
						FileMetaData fileMetaData = new FileMetaData();
						String location = getTargetFileLocation(fileName);
						fileMetaData.setId(generatedId);
						fileMetaData.setFileName(fileName);
						fileMetaData.setContentSize(multipartFile.getSize());
						fileMetaData.setContentType(multipartFile.getContentType());
						if (multipartFile.getContentType().equalsIgnoreCase("image/jpeg")
								|| multipartFile.getContentType().equalsIgnoreCase("image/png")) {
							BufferedImage bimg = ImageIO.read(file);
							fileMetaData.setAttachmentHeight(bimg.getHeight());
							fileMetaData.setAttachmentWidth(bimg.getWidth());
							fileMetaData.setType("Spree::"+multipartFile.getContentType());
						}
						fileMetaData.setViewableId(productCode != null ? productCode.intValue() : null);
						
						if (master != null) {
							if (!master.getIsMaster()) {
								fileMetaData.setViewableType("Spree::Variant");
							} else {
								fileMetaData.setViewableType("Spree::Master");
							}
						}
						UrlType urlType = urlTypeRepo.findByUrlId(i);
						fileMetaData.setUrlTypeId(i);
						fileMetaData.setUrlType(urlType.getUrlType());
						fileMetaData.setAlt(master.getName());
						fileMetaData.setPosition(1);
						fileMetaData.setLocation(location);
						fileMetaData.setS3Path(fileUrl);
						fileMetaData.setAttachmentUpdatedAt(new Date());
						fileMetaData.setProductCode(productCode);
						UUID uuid = UUID.randomUUID();
						String randomUUIDString = uuid.toString();
						fileMetaData.setFileId(randomUUIDString);
						fileRepository.save(fileMetaData);
						if(i==1) {
							storageProvider.store(fileName, extractContent(multipartFile));
						}
					}
				}else {
					long generatedId = fileSeqGen.generateSequence(FileMetaData.SEQUENCE_NAME);
					Master master = productMasterRepo
							.findByProductMasterId(productCode != null ? productCode.intValue() : null);
					String fileName = generateFileName(multipartFile, productCode, generatedId, urlTypeId, master.getSku());
					fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
					uploadFileTos3bucket(fileName, file);
					FileMetaData fileMetaData = new FileMetaData();
					String location = getTargetFileLocation(fileName);
					fileMetaData.setId(generatedId);
					fileMetaData.setFileName(fileName);
					fileMetaData.setContentSize(multipartFile.getSize());
					fileMetaData.setContentType(multipartFile.getContentType());
					if (multipartFile.getContentType().equalsIgnoreCase("image/jpeg")
							|| multipartFile.getContentType().equalsIgnoreCase("image/png")) {
						BufferedImage bimg = ImageIO.read(file);
						fileMetaData.setAttachmentHeight(bimg.getHeight());
						fileMetaData.setAttachmentWidth(bimg.getWidth());
						fileMetaData.setType("Spree::"+multipartFile.getContentType());
					}
					fileMetaData.setViewableId(productCode != null ? productCode.intValue() : null);
					
					if (master != null) {
						if (!master.getIsMaster()) {
							fileMetaData.setViewableType("Spree::Variant");
						} else {
							fileMetaData.setViewableType("Spree::Master");
						}
					}
					UrlType urlType = urlTypeRepo.findByUrlId(urlTypeId);
					fileMetaData.setUrlTypeId(urlTypeId);
					fileMetaData.setUrlType(urlType.getUrlType());
					fileMetaData.setAlt(master.getName());
					fileMetaData.setPosition(1);
					fileMetaData.setLocation(location);
					fileMetaData.setS3Path(fileUrl);
					fileMetaData.setAttachmentUpdatedAt(new Date());
					fileMetaData.setProductCode(productCode);
					UUID uuid = UUID.randomUUID();
					String randomUUIDString = uuid.toString();
					fileMetaData.setFileId(randomUUIDString);
					fileRepository.save(fileMetaData);
					storageProvider.store(fileName, extractContent(multipartFile));
					
					
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private String generateFileName(MultipartFile multiPart, long productCode, Long id, Integer urlType,String sku) {
		String fileName=null;
		if(sku!=null && sku.length()>0) {
			fileName=sku+"-"+productCode + "-" + id + "-" + urlType + "-" +new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
		}else {
			fileName=productCode + "-" + id + "-" + urlType + "-" + new Date().getTime() + "-" +multiPart.getOriginalFilename().replace(" ", "_");
		}
		return fileName;
	}
	
	private String generateProductCategoryFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	private void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(
				new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	public String deleteFileFromS3Bucket(String fileUrl) {
		// String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		// System.out.println("fileName: "+fileName);
		try {
			s3client.deleteObject(new DeleteObjectRequest(bucketName, fileUrl));
			return "Successfully deleted";
		} catch (Exception e) {
			String msg=e.getMessage();
			return msg;
		}
		
		
	}

	private String getTargetFileLocation(String originalFilename) {
		return storageProvider.getLocation() + File.separator + originalFilename;
	}

	private byte[] extractContent(MultipartFile file) {
		try {
			return file.getBytes();
		} catch (IOException e) {
			throw new DataCorruptedException(e);
		}
	}
	
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
							
							if (row.getCell(8) != null && row.getCell(8).getCellTypeEnum() == CellType.NUMERIC) {
								dto.setCategoryId(row.getCell(8).getNumericCellValue());
							}
							
							
							listMaster.add(dto);
						}

					}

				}

			}
			
			listMaster.forEach(list -> {
				Master master = new Master();
				master.setId(fileSeqGen.generateSequence(Master.SEQUENCE_NAME));
				master.setCategoryId((int)list.getCategoryId());
				master.setSku(list.getProductCode());
				master.setName(list.getStyleDescription());
				master.setWidth(list.getWidth());
				master.setHeight(list.getHeight());
				master.setDepth(list.getLength());
				master.setDescription(list.getMaterial());
				master.setPrice("" + list.getPrice());
				master.setDisplayPrice("" + list.getPrice());
				master.setInStock(true);
				master.setIsBackorderable(true);
				master.setIsMaster(true);
				master.setIsOrderable(true);
				master.setOptionsText("");
				master.setSlug("");
				master.setTotalOnHand(100);
				master.setTrackInventory(true);
				master.setWeight("0.0");
				master.setIsDestroyed(false);
				System.out.println(master.toString());
				masterList.add(master);
			});

			System.out.println(map.size());
		}

		Map<String, PictureData> mapDat = getSheetPictrues03(sheet, workbook);
		System.out.println("Picture map Size: " + mapDat.size());
		System.out.println("List Size: " + listMaster.size());
		productMasterRepo.addAllProductMasters(masterList);
		workbook.close();
		return masterList;
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
