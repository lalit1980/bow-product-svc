package com.boozeonwheel.product.domain.type;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document(collection = "M_SellerCategory")
@ApiModel(description = "Class representing a product type and tracked by the application.")
public class ProductType {

	
	@Indexed(name = "product_type_id_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the product type. No two product can have the same id.", example = "1", required = true, position = 0)
	private int productTypeId;
	
	@Indexed(name = "product_type_index", expireAfterSeconds = 10)
	@ApiModelProperty(notes = "Product type will be unique", example = "Headphone", required = true, position = 0)
	private String productType;
	
	
}
