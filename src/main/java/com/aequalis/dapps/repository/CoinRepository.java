/**
 * 
 */
package com.aequalis.dapps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aequalis.dapps.model.Coin;
import java.lang.String;

/**
 * @author anand
 *
 */
public interface CoinRepository  extends JpaRepository<Coin, Long>{

	Coin findByName(String name);
	Coin findByProductaddress(String productaddress);
}
