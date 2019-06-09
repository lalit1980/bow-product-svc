package com.boozeonwheel.product.domain.liquor;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document(collection = "M_LIQUOR")
@ApiModel(description = "Class representing a liquor master data and tracked by the application.")
public class M_LIQUOR {

	
	@Indexed(name = "liquor_code_index", direction = IndexDirection.DESCENDING)
	@ApiModelProperty(notes = "Unique identifier of the liquor. No two persons can have the same id.", example = "1", required = true, position = 0)
	private long LIQUOR_CODE;
	
	@Indexed(name = "item_description_index", expireAfterSeconds = 10)
	@ApiModelProperty(notes = "Liquor desscription", example = "Electronics", required = true, position = 0)
	private String LIQUOR_DESCRIPTION;
	
	@Indexed(name = "supplier_index", expireAfterSeconds = 10)
	@ApiModelProperty(notes = "Supplier", example = "Detail description of particular product type", required = true, position = 0)
	private String LIQUOR_SUPPLIER;
	
	@Indexed(name = "liqor_type_index", expireAfterSeconds = 10)
	@ApiModelProperty(notes = "Liquor Type", example = "Detail description of particular product type", required = true, position = 0)
	private String LIQUOR_TYPE;
	
	@Indexed(name = "liqor_quantity_index", expireAfterSeconds = 10)
	@ApiModelProperty(notes = "Quantity", example = "Detail description of particular product type", required = true, position = 0)
	private String QUANTITY;
	
	@ApiModelProperty(notes = "Measurement", example = "Detail description of particular product type", required = true, position = 0)
	private String MEASUREMENT;
	
	@ApiModelProperty(notes = "Liquor updated by can be a person who updated last", example = "System User", required = true, position = 0)
	private String UPDATED_BY;
	
	@ApiModelProperty(notes = "Liquor is Active?", example = "true/false", required = true, position = 0)
	private Boolean IS_ACTIVE;
	
	
	@ApiModelProperty(notes = "Liquor image path", example = "S3 bucket URL", required = true, position = 0)
	private String IMAGE_PATH;

	public long getLIQUOR_CODE() {
		return LIQUOR_CODE;
	}

	public void setLIQUOR_CODE(long lIQUOR_CODE) {
		LIQUOR_CODE = lIQUOR_CODE;
	}

	public String getLIQUOR_DESCRIPTION() {
		return LIQUOR_DESCRIPTION;
	}

	public void setLIQUOR_DESCRIPTION(String lIQUOR_DESCRIPTION) {
		LIQUOR_DESCRIPTION = lIQUOR_DESCRIPTION;
	}

	public String getLIQUOR_SUPPLIER() {
		return LIQUOR_SUPPLIER;
	}

	public void setLIQUOR_SUPPLIER(String lIQUOR_SUPPLIER) {
		LIQUOR_SUPPLIER = lIQUOR_SUPPLIER;
	}

	public String getLIQUOR_TYPE() {
		return LIQUOR_TYPE;
	}

	public void setLIQUOR_TYPE(String lIQUOR_TYPE) {
		LIQUOR_TYPE = lIQUOR_TYPE;
	}

	public String getQUANTITY() {
		return QUANTITY;
	}

	public void setQUANTITY(String qUANTITY) {
		QUANTITY = qUANTITY;
	}

	public String getMEASUREMENT() {
		return MEASUREMENT;
	}

	public void setMEASUREMENT(String mEASUREMENT) {
		MEASUREMENT = mEASUREMENT;
	}

	public String getUPDATED_BY() {
		return UPDATED_BY;
	}

	public void setUPDATED_BY(String uPDATED_BY) {
		UPDATED_BY = uPDATED_BY;
	}

	public Boolean getIS_ACTIVE() {
		return IS_ACTIVE;
	}

	public void setIS_ACTIVE(Boolean iS_ACTIVE) {
		IS_ACTIVE = iS_ACTIVE;
	}

	

	public String getIMAGE_PATH() {
		return IMAGE_PATH;
	}

	public void setIMAGE_PATH(String iMAGE_PATH) {
		IMAGE_PATH = iMAGE_PATH;
	}

	@Override
	public String toString() {
		return "M_LIQUOR [LIQUOR_CODE=" + LIQUOR_CODE + ", LIQUOR_DESCRIPTION=" + LIQUOR_DESCRIPTION
				+ ", LIQUOR_SUPPLIER=" + LIQUOR_SUPPLIER + ", LIQUOR_TYPE=" + LIQUOR_TYPE + ", QUANTITY=" + QUANTITY
				+ ", MEASUREMENT=" + MEASUREMENT + ", UPDATED_BY=" + UPDATED_BY + ", IS_ACTIVE=" + IS_ACTIVE
				+  ", IMAGE_PATH=" + IMAGE_PATH + "]";
	}

	
}
