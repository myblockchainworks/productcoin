/**
 * 
 */
package com.aequalis.dapps.service;

import java.util.List;

import com.aequalis.dapps.model.Coin;

/**
 * @author anand
 *
 */
public interface CoinService {
	public void addCoin(Coin coin);
	
	public Coin findByName(String name);
	
	public Coin findByProductaddress(String productaddress);
	
	public List<Coin> findByAll();
}
