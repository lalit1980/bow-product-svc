package com.boozeonwheel.product.controller.s3servcie;

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

import com.boozeonwheel.product.client.AmazonClient;
import com.boozeonwheel.product.domain.file.FileMetaData;
import com.boozeonwheel.product.repository.file.FileRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for upload, delete and get files from S3 bucket.")
public class S3UploadController {

	private AmazonClient amazonClient;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	S3UploadController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}

	
	@Autowired
	FileRepository fileRepository;

	@GetMapping("/fileservice/v1.0/{productCode}")
    public ResponseEntity<List<FileMetaData>> getCategory(
    		@PathVariable("productCode") long productCode) {
		try {
			return new ResponseEntity<List<FileMetaData>>(fileRepository.findByProductId(productCode), HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<List<FileMetaData>>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping({ "/fileservice/v1.0/{productCode}/{urlId}" })
	@ApiOperation("Uploads files to S3 bucket.")
	public ResponseEntity<String> uploadFile(
			@RequestPart(value = "file") MultipartFile file,
			@PathVariable("productCode") long productCode,
			@PathVariable("urlId") Integer urlTypeId) {
		return new ResponseEntity<String>(this.amazonClient.uploadFile(file,productCode,urlTypeId), HttpStatus.OK); 
    }

	@DeleteMapping("/fileservice/v1.0")
	@ApiOperation("Deletes files to S3 bucket.")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	}
}
