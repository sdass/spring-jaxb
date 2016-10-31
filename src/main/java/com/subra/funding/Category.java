package com.subra.funding;

public enum Category {

    Transaction(1),	CustomerPull(2),	AddressPull(3),  FundingMethod(4), CurrentBalance(5), GeneralOp(6);
	
	
	private final int code;

	
	private Category(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}


	

}
