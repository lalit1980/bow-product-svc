package com.boozeonwheel.product.controller.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boozeonwheel.product.api.file.UploadResponse;
import com.boozeonwheel.product.client.AmazonClient;
import com.boozeonwheel.product.domain.file.FileMetaData;
import com.boozeonwheel.product.service.file.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("/api")
@Api(value = "Upload", description = "Allows uploading and listing metadata of uploaded files")
public class UploadController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final FileService fileService;
    private final AmazonClient amazonService;

    public UploadController(FileService fileService,AmazonClient amazonService) {
        this.fileService = fileService;
        this.amazonService=amazonService;
    }

    @RequestMapping(value = "/fileupload/v1.0", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Make a POST request to upload the file",
            produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The POST call is successful"),
            @ApiResponse(code = 500, message = "The POST call is failed"),
            @ApiResponse(code = 400, message = "The POST call has wrong request data, e.g. uploading file with the same name as before")
    })
    public ResponseEntity<UploadResponse> uploadFile(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true)
            @RequestParam("file") MultipartFile file,
            @ApiParam(name = "title", value = "Title of the file", required = true)
            @RequestParam("title") String title,
            @ApiParam(name = "details", value = "Details of the file", required = true)
            @RequestParam("details") String details,
            @ApiParam(name = "liquorCode", value = "Liquor Code", required = true)
            @RequestParam("LIQUOR_CODE") Long LIQUOR_CODE) {
    	logger.info("Content Type: "+file.getContentType());
    	UploadResponse response = new UploadResponse();
    	if(file.getContentType().equalsIgnoreCase("image/jpeg")) {
	        Assert.isTrue(!file.isEmpty(), "File cannot be empty");
	        
	        response.setMessage("Successfully uploaded");
	        response.setTitle(title);
	        response.setDetails(details);
	        response.setFileName(file.getOriginalFilename());
	        response.setLIQUOR_CODE(LIQUOR_CODE);
	       
	        String S3path=amazonService.uploadFile(file);
	        response.setS3URL(S3path);
	        fileService.storeData(file, title,details, S3path, LIQUOR_CODE);
	       
	        return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
        	response = new UploadResponse();
            response.setMessage("Error in File uploaded, only image allowed");
            return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
        }
        
    }

    @RequestMapping(value = "/fileupload/v1.0/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "List metadata of uploaded files",
            produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The GET is successful"),
    })
    public ResponseEntity<Page<FileMetaData>> uploadFile(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(fileService.getAllMetaData(pageable), HttpStatus.OK);

    }
}
