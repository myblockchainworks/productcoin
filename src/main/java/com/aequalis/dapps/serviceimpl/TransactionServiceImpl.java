/**
 * 
 */
package com.aequalis.dapps.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aequalis.dapps.model.Coin;
import com.aequalis.dapps.model.Transaction;
import com.aequalis.dapps.service.TransactionService;
import com.aequalis.dapps.repository.TransactionRepository;

/**
 * @author anand
 *
 */
@Service
@Qualifier("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public void addTransaction(Transaction transaction) {
		transactionRepository.saveAndFlush(transaction);
	}

	@Override
	public List<Transaction> findByCoin(Coin coin) {
		return transactionRepository.findByCoin(coin);
	}

	@Override
	public List<Transaction> findByCoinAndFromaddress(Coin coin, String fromaddress) {
		return transactionRepository.findByCoinAndFromaddress(coin, fromaddress);
	}

	@Override
	public List<Transaction> findByCoinAndToaddress(Coin coin, String toaddress) {
		return transactionRepository.findByCoinAndToaddress(coin, toaddress);
	}

	@Override
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}

}
