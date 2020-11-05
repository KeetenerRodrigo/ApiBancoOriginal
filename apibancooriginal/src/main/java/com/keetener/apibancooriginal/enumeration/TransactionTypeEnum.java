package com.keetener.apibancooriginal.enumeration;


/**
 * @author Keetener Rodrigo
 */
public enum TransactionTypeEnum {
	
	CARD("CARD"), MONEY("MONEY");
	
	private String value;
	
	private TransactionTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
