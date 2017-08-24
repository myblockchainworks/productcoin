/**
 * 
 */
package com.aequalis.dapps.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author anand
 *
 */
@Entity
@Table(name="coins")
public class Coin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long coinid;

	@Column(name = "name")
	private String name;
	
	@Column(name = "symbol")
	private String symbol;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "launchdate")
	private String launchdate;
	
	@Column(name = "expirydate")
	private String expirydate;
	
	@Column(name = "payoutsperyear")
	private int payoutsperyear;
	
	@Column(name = "productaddress")
	private String productaddress;

	/**
	 * @return the coinid
	 */
	public Long getCoinid() {
		return coinid;
	}

	/**
	 * @param coinid the coinid to set
	 */
	public void setCoinid(Long coinid) {
		this.coinid = coinid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the launchdate
	 */
	public String getLaunchdate() {
		return launchdate;
	}

	/**
	 * @param launchdate the launchdate to set
	 */
	public void setLaunchdate(String launchdate) {
		this.launchdate = launchdate;
	}

	/**
	 * @return the expirydate
	 */
	public String getExpirydate() {
		return expirydate;
	}

	/**
	 * @param expirydate the expirydate to set
	 */
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	/**
	 * @return the payoutsperyear
	 */
	public int getPayoutsperyear() {
		return payoutsperyear;
	}

	/**
	 * @param payoutsperyear the payoutsperyear to set
	 */
	public void setPayoutsperyear(int payoutsperyear) {
		this.payoutsperyear = payoutsperyear;
	}

	/**
	 * @return the productaddress
	 */
	public String getProductaddress() {
		return productaddress;
	}

	/**
	 * @param productaddress the productaddress to set
	 */
	public void setProductaddress(String productaddress) {
		this.productaddress = productaddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coin [coinid=" + coinid + ", name=" + name + ", symbol=" + symbol + ", amount=" + amount
				+ ", launchdate=" + launchdate + ", expirydate=" + expirydate + ", payoutsperyear=" + payoutsperyear
				+ ", productaddress=" + productaddress + "]";
	}
	
}
