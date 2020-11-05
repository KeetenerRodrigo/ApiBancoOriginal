package com.keetener.apibancooriginal.model;

import com.keetener.apibancooriginal.enumeration.TransactionTypeEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Keetener Rodrigo
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
	
	private Long id;
	private String nsu;
	private String autorizationNumber;
	private BigDecimal amount;
	private LocalDateTime transactionDate;
	private TransactionTypeEnum type;
	
	public Transaction(TransactionTypeEnum type){
		this.type = type;
	}

}
