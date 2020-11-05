package com.keetener.apibancooriginal.factory.impl;

import com.keetener.apibancooriginal.enumeration.TransactionTypeEnum;
import com.keetener.apibancooriginal.factory.TransactionFactory;
import com.keetener.apibancooriginal.model.Transaction;

/**
 * @author Keetener Rodrigo
 */
public class TransactionFactoryImpl implements TransactionFactory{

	@Override
	public Transaction createTransaction(String type) {
		if(TransactionTypeEnum.MONEY.getValue().equals(type)) {
			return new Transaction(TransactionTypeEnum.MONEY);
		}
		return new Transaction(TransactionTypeEnum.CARD);
	}

}
