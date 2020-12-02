package com.boozeonwheel.product.domain.liquor;

public class Liquor {

	private String liquorCode;
	
	public Liquor(String liquorCode, M_LIQUOR liquor) {
		super();
		this.liquorCode = liquorCode;
		this.liquor = liquor;
	}

	private M_LIQUOR liquor;

	public String getLiquorCode() {
		return liquorCode;
	}

	public void setLiquorCode(String liquorCode) {
		this.liquorCode = liquorCode;
	}

	public M_LIQUOR getLiquor() {
		return liquor;
	}

	public void setLiquor(M_LIQUOR liquor) {
		this.liquor = liquor;
	}

	@Override
	public String toString() {
		return "Liquor [liquorCode=" + liquorCode + ", liquor=" + liquor + "]";
	}
	
	
}
