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
import com.aequalis.dapps.repository.CoinRepository;
import com.aequalis.dapps.service.CoinService;

/**
 * @author anand
 *
 */
@Service
@Qualifier("coinService")
@Transactional
public class CoinServiceImpl implements CoinService {

	@Autowired
	CoinRepository coinRepository;
	
	@Override
	public void addCoin(Coin coin) {
		coinRepository.saveAndFlush(coin);
	}

	@Override
	public Coin findByName(String name) {
		return coinRepository.findByName(name);
	}

	@Override
	public Coin findByProductaddress(String productaddress) {
		return coinRepository.findByProductaddress(productaddress);
	}

	@Override
	public List<Coin> findByAll() {
		return coinRepository.findAll();
	}

}
