package com.keetener.apibancooriginal.factory;

import com.keetener.apibancooriginal.model.Transaction;

/**
 * @author Keetener Rodrigo
 */
public interface TransactionFactory {

	Transaction createTransaction(String type);
}
