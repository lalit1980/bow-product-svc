package com.boozeonwheel.product.controller.s3servcie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boozeonwheel.product.client.AmazonClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for upload, delete and get files from S3 bucket.")
public class S3UploadController {

	
	private AmazonClient amazonClient;

    @Autowired
    S3UploadController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
    

	@PostMapping({ "/fileservice/v1.0" })
	@ApiOperation("Uploads files to S3 bucket.")
	public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonClient.uploadFile(file);
    }
	
	@DeleteMapping("/fileservice/v1.0")
	@ApiOperation("Deletes files to S3 bucket.")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
}
