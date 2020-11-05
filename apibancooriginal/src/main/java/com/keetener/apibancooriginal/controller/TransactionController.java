package com.keetener.apibancooriginal.controller;

import com.keetener.apibancooriginal.model.Transaction;
import com.keetener.apibancooriginal.service.TransactionService;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


/**
 * 
 * @author Keetener Rodrigo
 */
@RestController
@RequestMapping("/financial/v1/transactions")
public class TransactionController {
	
	private static final Logger logger = Logger.getLogger(TransactionController.class);
	
	@Autowired
	private TransactionService transactionService;
	@GetMapping
	public ResponseEntity<List<Transaction>> find() {
		if(transactionService.find().isEmpty()) {
			return ResponseEntity.notFound().build(); 
		}
		logger.info(transactionService.find());
		return ResponseEntity.ok(transactionService.find());
	}

	@DeleteMapping
	public ResponseEntity<Boolean> delete() {
		try {
			transactionService.delete();
			return ResponseEntity.noContent().build();
		}catch(Exception e) {
			logger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
        
	@PostMapping
	@ResponseBody
	public ResponseEntity<Transaction> create(@RequestBody JSONObject transaction) {
		try {
			if(transactionService.isJSONValid(transaction.toString())) {
				Transaction transactionCreated = transactionService.create(transaction);
				var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(transactionCreated.getNsu()).build().toUri();
				
				if(transactionService.isTransactionInFuture(transactionCreated)){
					logger.error("The transaction date is in the future.");
					return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
				}else {
					transactionService.add(transactionCreated);
					return ResponseEntity.created(uri).body(null);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			logger.error("JSON fields are not parsable. " + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@PutMapping(path = "/{id}", produces = { "application/json" })
	public ResponseEntity<Transaction> update(@PathVariable("id") long id, @RequestBody JSONObject transaction) {
		try {
			if(transactionService.isJSONValid(transaction.toString())) {
				Transaction transactionToUpdate = transactionService.findById(id);
				if(transactionToUpdate == null){
					logger.error("The transaction not found.");
					return ResponseEntity.notFound().build(); 
				}else {
					Transaction transactionUpdated = transactionService.update(transactionToUpdate, transaction);
					return ResponseEntity.ok(transactionUpdated);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			logger.error("JSON fields are not parsable." + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

}
