package com.boozeonwheel.product.domain.master;

import java.util.List;

public class Supplier {
	
	private Integer supplierId;
	private String supplierName;
	private List<Integer> productId;
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + "]";
	}
	public List<Integer> getProductId() {
		return productId;
	}
	public void setProductId(List<Integer> productId) {
		this.productId = productId;
	}

}
