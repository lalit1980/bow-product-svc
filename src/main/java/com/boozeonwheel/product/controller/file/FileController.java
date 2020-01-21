package com.boozeonwheel.product.controller.file;

import java.util.List;

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
import com.boozeonwheel.product.domain.file.FileMetaData;
import com.boozeonwheel.product.dto.file.FileUploadResponse;
import com.boozeonwheel.product.repository.file.FileRepository;
import com.boozeonwheel.product.service.file.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for upload, delete and get files from S3 bucket.")
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
	@ApiOperation("Uploads files to S3 bucket.")
	public ResponseEntity<FileUploadResponse> uploadFile(
			@RequestPart(value = "file") MultipartFile file,
			@PathVariable("productCode") long productCode,
			@PathVariable("id") long id,
			@PathVariable("urlId") Integer urlTypeId,
			@PathVariable("action") String action) {
		this.fileService.uploadFile(file,productCode,id,urlTypeId,action);
		return new ResponseEntity<FileUploadResponse>(new FileUploadResponse(fileService.uploadFile(file,productCode,id,urlTypeId,action)), HttpStatus.OK); 
    }

	@DeleteMapping("/fileservice/v1.0")
	@ApiOperation("Deletes files to S3 bucket.")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.fileService.deleteFileFromS3Bucket(fileUrl);
	}
}
