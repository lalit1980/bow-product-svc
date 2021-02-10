package com.boozeonwheel.product.controller.liquor;

import java.util.ArrayList;
import java.util.List;

import com.boozeonwheel.product.domain.file.FileMetaData;
import com.boozeonwheel.product.domain.liquor.M_LIQUOR;
import com.boozeonwheel.product.repository.liquor.LiquorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LiquorController {
	
	@Autowired
    LiquorRepository liquorRepository;
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/liquor/v1.0/{LIQUOR_CODE}")
	public M_LIQUOR get(@PathVariable("LIQUOR_CODE") long LIQUOR_CODE) {
		
		return liquorRepository.findByLiquorCode(LIQUOR_CODE);
		
	}

	@PostMapping("/liquor/v1.0/liquorDescription/{liquorDescription}/{pageNumber}/{pageSize}")
	public Page<M_LIQUOR> getLiquorByLiquorDescription(@PathVariable("liquorDescription") String liquorDescription,@PathVariable("pageNumber") int pageNumber,@PathVariable("pageSize") int pageSize ) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return liquorRepository.findByLiquorDescription(liquorDescription.toUpperCase(),pageable);
	}

	@GetMapping("/liquor/v1.0/liquorSupplier/{liquorSupplier}")
	public List<M_LIQUOR> getLiquorByLiquorSupplier(@PathVariable("liquorSupplier") String liquorSupplier) {
		return liquorRepository.findBySupplier(liquorSupplier.toUpperCase());
	}

	@GetMapping("/liquor/v1.0/liquorType/{liquorType}")
	public List<M_LIQUOR> getLiquorByLiquorType(@PathVariable("liquorType") String liquorType) {
		return liquorRepository.findByLiquorType(liquorType.toUpperCase());
	}

	@GetMapping("/liquor/v1.0/liquorQuantity/{liquorQuantity}")
	public List<M_LIQUOR> getLiquorByLiquorQuantity(@PathVariable("liquorQuantity") String liquorQuantity) {
		return liquorRepository.findByLiquorQuantity(liquorQuantity.toUpperCase());
	}

	@GetMapping("/liquor/v1.0/liquorMeasurement/{liquorMeasurement}")
	public List<M_LIQUOR> getLiquorByMeasurement(@PathVariable("liquorMeasurement") String liquorMeasurement) {
		return liquorRepository.findByLiquorMeasurement(liquorMeasurement.toUpperCase());
	}

	@RequestMapping(value = "/liquor/v1.0/{LIQUOR_CODE}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public void addLiquor(
    		@RequestParam(value = "liquorCode", required = true) long liquorCode,
    		@RequestParam(value = "liquorDescription", required = true) String liquorDescription,
    		@RequestParam(value = "liquorSupplier", required = true) String liquorSupplier,
    		@RequestParam(value = "liquorType", required = true) String liquorType,
    		@RequestParam(value = "quantity", required = true) String quantity,
    		@RequestParam(value = "measurement", required = true) String measurement,
    		@RequestParam(value = "isActive", required = true) Boolean isActive,
    		
    		@RequestParam(value = "file", required = true) MultipartFile file) {
		
		List<FileMetaData> fileMetaDataList=new ArrayList<FileMetaData>();
		M_LIQUOR liquor=new M_LIQUOR();
		liquor.setLIQUOR_CODE(liquorCode);
		liquor.setLIQUOR_DESCRIPTION(liquorDescription);
		liquor.setLIQUOR_SUPPLIER(liquorSupplier);
		liquor.setLIQUOR_TYPE(liquorType);
		liquor.setMEASUREMENT(measurement);
		liquor.setQUANTITY(quantity);
		liquor.setIS_ACTIVE(isActive);
		FileMetaData fileMetaData=new FileMetaData();
		fileMetaData.setProductCode(liquorCode);
		fileMetaDataList.add(fileMetaData);
		liquor.setFileMetaData(fileMetaDataList);
		//liquorService.storeData(file, liquor);
    }
	
	
	
	

	@PutMapping("/liquor/v1.0/update/{LIQUOR_CODE}")
	public UpdateResult update(@PathVariable("LIQUOR_CODE") long LIQUOR_CODE,
			@RequestBody M_LIQUOR liquor) {
		return liquorRepository.updateLiquor(liquor);
	}

	@DeleteMapping({ "/liquor/v1.0/{LIQUOR_CODE}" })
	public DeleteResult delete(@PathVariable("LIQUOR_CODE") long LIQUOR_CODE) {
		logger.info("Liquor Code "+LIQUOR_CODE);
		return liquorRepository.deleteLiquor(LIQUOR_CODE);
	}

	@GetMapping("/liquor/v1.0")
	public Page<M_LIQUOR> findAll(Pageable pageable) {
		return liquorRepository.findAll(pageable);
	}
	
	@RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
