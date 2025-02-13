package com.boozeonwheel.product.domain.liquor;

import java.util.List;

import com.boozeonwheel.product.domain.file.FileMetaData;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "M_LIQUOR")
public class M_LIQUOR {

	
	@Indexed(name = "liquor_code_index", direction = IndexDirection.DESCENDING)
	private long LIQUOR_CODE;
	
	@Indexed(name = "item_description_index", expireAfterSeconds = 10)
	private String LIQUOR_DESCRIPTION;
	
	@Indexed(name = "supplier_index", expireAfterSeconds = 10)
	private String LIQUOR_SUPPLIER;
	
	@Indexed(name = "liqor_type_index", expireAfterSeconds = 10)
	private String LIQUOR_TYPE;
	
	@Indexed(name = "liqor_quantity_index", expireAfterSeconds = 10)
	private String QUANTITY;
	
	private String MEASUREMENT;
	
	private Boolean IS_ACTIVE;
	
	private int productCategoryId;
	
	private List<FileMetaData> fileMetaData;

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

	public Boolean getIS_ACTIVE() {
		return IS_ACTIVE;
	}

	public void setIS_ACTIVE(Boolean iS_ACTIVE) {
		IS_ACTIVE = iS_ACTIVE;
	}

	public List<FileMetaData> getFileMetaData() {
		return fileMetaData;
	}

	public void setFileMetaData(List<FileMetaData> fileMetaData) {
		this.fileMetaData = fileMetaData;
	}

	@Override
	public String toString() {
		return "M_LIQUOR [LIQUOR_CODE=" + LIQUOR_CODE + ", LIQUOR_DESCRIPTION=" + LIQUOR_DESCRIPTION
				+ ", LIQUOR_SUPPLIER=" + LIQUOR_SUPPLIER + ", LIQUOR_TYPE=" + LIQUOR_TYPE + ", QUANTITY=" + QUANTITY
				+ ", MEASUREMENT=" + MEASUREMENT + ", IS_ACTIVE=" + IS_ACTIVE + ", productCategoryId="
				+ productCategoryId + ", fileMetaData=" + fileMetaData + "]";
	}

	public int getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}


	
}
