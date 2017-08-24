/**
 * 
 */
package com.aequalis.dapps.service;

import java.util.List;

import com.aequalis.dapps.model.Coin;
import com.aequalis.dapps.model.Transaction;


/**
 * @author anand
 *
 */
public interface TransactionService {
	public void addTransaction(Transaction transaction);
	
	public List<Transaction> findAll();
	
	public List<Transaction> findByCoin(Coin coin);
	
	public List<Transaction> findByCoinAndFromaddress(Coin coin, String fromaddress);
	
	public List<Transaction> findByCoinAndToaddress(Coin coin, String toaddress);
}
