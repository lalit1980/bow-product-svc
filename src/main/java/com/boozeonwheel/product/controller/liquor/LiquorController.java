package com.boozeonwheel.product.controller.liquor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.boozeonwheel.product.domain.liquor.M_LIQUOR;
import com.boozeonwheel.product.repository.liquor.LiquorRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Liquor.")
public class LiquorController {
	@Autowired
	LiquorRepository liquorRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/liquor/v1.0/{LIQUOR_CODE}")
	@ApiOperation("Returns a specific liquor by their identifier. 404 if does not exist.")
	public M_LIQUOR get(@PathVariable("LIQUOR_CODE") long LIQUOR_CODE) {
		List<M_LIQUOR> list=null;
		M_LIQUOR obj=null;
		list = liquorRepository.findByLiquorCode(LIQUOR_CODE);
		if(list!=null && list.size()==1) {
			obj= list.get(0);
		}
		return obj;
	}

	@GetMapping("/liquor/v1.0/liquorDescription/{liquorDescription}")
	@ApiOperation("Returns a specific Liquor Description. 404 if does not exist.")
	public List<M_LIQUOR> getLiquorByLiquorDescription(@PathVariable("liquorDescription") String liquorDescription) {
		return liquorRepository.findByLiquorDescription(liquorDescription.toUpperCase());
	}

	@GetMapping("/liquor/v1.0/liquorSupplier/{liquorSupplier}")
	@ApiOperation("Returns a specific Liquor Description. 404 if does not exist.")
	public List<M_LIQUOR> getLiquorByLiquorSupplier(@PathVariable("liquorSupplier") String liquorSupplier) {
		return liquorRepository.findBySupplier(liquorSupplier.toUpperCase());
	}

	@GetMapping("/liquor/v1.0/liquorType/{liquorType}")
	@ApiOperation("Returns a specific Liquor Type. 404 if does not exist.")
	public List<M_LIQUOR> getLiquorByLiquorType(@PathVariable("liquorType") String liquorType) {
		return liquorRepository.findByLiquorType(liquorType.toUpperCase());
	}

	@GetMapping("/liquor/v1.0/liquorQuantity/{liquorQuantity}")
	@ApiOperation("Returns a specific Liquor Quantity. 404 if does not exist.")
	public List<M_LIQUOR> getLiquorByLiquorQuantity(@PathVariable("liquorQuantity") String liquorQuantity) {
		return liquorRepository.findByLiquorQuantity(liquorQuantity.toUpperCase());
	}

	@GetMapping("/liquor/v1.0/liquorMeasurement/{liquorMeasurement}")
	@ApiOperation("Returns a specific Liquor Quantity. 404 if does not exist.")
	public List<M_LIQUOR> getLiquorByMeasurement(@PathVariable("liquorMeasurement") String liquorMeasurement) {
		return liquorRepository.findByLiquorMeasurement(liquorMeasurement.toUpperCase());
	}

	@PostMapping({ "/liquor/v1.0" })
	@ApiOperation("Creates a new liquor.")
	public M_LIQUOR add(@RequestBody M_LIQUOR liquor) {

		return liquorRepository.save(liquor);
	}

	@PutMapping("/liquor/v1.0/update/{LIQUOR_CODE}")
	@ApiOperation("Update a new liquor.")
	public UpdateResult update(@PathVariable("LIQUOR_CODE") long LIQUOR_CODE,
			@RequestBody M_LIQUOR liquor) {
		return liquorRepository.updateLiquor(liquor,LIQUOR_CODE);
	}

	@DeleteMapping({ "/liquor/v1.0/{liquorCode}" })
	@ApiOperation("Deletes a liquor from the system. 404 if the person's identifier is not found.")
	public DeleteResult delete(@PathVariable("LIQUOR_CODE") int LIQUOR_CODE) {
		return liquorRepository.deleteLiquor(LIQUOR_CODE);
	}

	@ApiOperation("Returns list of all liquor in the system.")
	@GetMapping("/liquor/v1.0")
	public List<M_LIQUOR> findAll() {
		return liquorRepository.findAll();
	}
	
	@RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
