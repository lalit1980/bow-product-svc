package com.boozeonwheel.product.controller.file;

import java.util.List;

import com.boozeonwheel.product.domain.file.FileMetaData;
import com.boozeonwheel.product.domain.file.ProductCategoryFileMetaData;
import com.boozeonwheel.product.dto.file.FileUploadResponse;
import com.boozeonwheel.product.dto.file.ProductCategoryFileUploadResponse;
import com.boozeonwheel.product.repository.file.FileRepository;
import com.boozeonwheel.product.repository.file.ProductCategoryFileRepository;
import com.boozeonwheel.product.service.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.CollectionUtils;

@RestController
@RequestMapping("/api")
public class FileController {

	private FileService fileService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	FileController(FileService fileService) {
		this.fileService = fileService;
	}
	

	public static final String SAMPLE_XLSX_FILE_PATH = "/Users/apple/Downloads/CLASSIC_WORK1.xls";
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	ProductCategoryFileRepository categoryfileRepository;

	@GetMapping("/fileservice/v1.0/{productCode}")
    public ResponseEntity<List<FileMetaData>> getCategoryByProductId(
    		@PathVariable("productCode") long productCode) {
		try {
			return new ResponseEntity<List<FileMetaData>>(fileRepository.findByProductId(productCode), HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<List<FileMetaData>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/fileservice/v1.0/supplier/supplierdata")
    public void loadSupplierData() {
		try {
			fileService.readSupplierData(SAMPLE_XLSX_FILE_PATH); 
		} catch (Exception e) {
			
		}
	}
	
	@GetMapping("/fileservice/v1.0/product/category/{productCategoryId}")
    public ResponseEntity<List<ProductCategoryFileMetaData>> getProductFileMetadataByCategoryId(
    		@PathVariable("productCategoryId") long productCategoryId) {
		try {
			return new ResponseEntity<List<ProductCategoryFileMetaData>>(categoryfileRepository.findByProductCategoryId(productCategoryId), HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<List<ProductCategoryFileMetaData>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/fileservice/v1.0/category/{id}")
    public ResponseEntity<FileMetaData> getFileMetaDataById(
    		@PathVariable("id") long id) {
		try {
			List<FileMetaData> fileDataList=fileRepository.findByFileId(id);
			if(!CollectionUtils.isNullOrEmpty(fileDataList) ) {
				return new ResponseEntity<FileMetaData>(fileDataList.get(0), HttpStatus.OK);
			}else {
				FileMetaData meta=new FileMetaData();
				
				return new ResponseEntity<FileMetaData>(meta, HttpStatus.OK);
			}
			 
		} catch (Exception e) {
			return new ResponseEntity<FileMetaData>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping({ "/fileservice/v1.0/{productCode}/{id}/{urlId}/{action}" })
	public ResponseEntity<FileUploadResponse> uploadFile(
			@RequestPart(value = "file") MultipartFile file,
			@PathVariable("productCode") long productCode,
			@PathVariable("id") long id,
			@PathVariable("urlId") Integer urlTypeId,
			@PathVariable("action") String action) {
		return new ResponseEntity<FileUploadResponse>(new FileUploadResponse(fileService.uploadFile(file,productCode,id,urlTypeId,action)), HttpStatus.OK); 
    }
	
	@PostMapping({ "/fileservice/v1.0/add/{productCategoryId}/{id}/{urlId}/{action}" })
	public ResponseEntity<ProductCategoryFileUploadResponse> uploadProductCategoryFile(
			@RequestPart(value = "file") MultipartFile file,
			@PathVariable("productCategoryId") long productCategoryId,
			@PathVariable("id") long id,
			@PathVariable("urlId") Integer urlTypeId,
			@PathVariable("action") String action) {
		return new ResponseEntity<ProductCategoryFileUploadResponse>(new ProductCategoryFileUploadResponse(fileService.uploadCategoryFileMetaData
				(file,productCategoryId,id,urlTypeId,action)), HttpStatus.OK); 
    }

	@DeleteMapping("/fileservice/v1.0")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.fileService.deleteFileFromS3Bucket(fileUrl);
	}
}
