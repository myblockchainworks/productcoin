/**
 * 
 */
package com.aequalis.dapps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aequalis.dapps.model.Transaction;

import java.util.List;
import java.lang.String;
import com.aequalis.dapps.model.Coin;

/**
 * @author anand
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findByFromaddress(String fromaddress);
	
	List<Transaction> findByToaddress(String toaddress);
	
	List<Transaction> findByCoin(Coin coin);
	
	List<Transaction> findByCoinAndFromaddress(Coin coin, String fromaddress);
	
	List<Transaction> findByCoinAndToaddress(Coin coin, String toaddress);
}
