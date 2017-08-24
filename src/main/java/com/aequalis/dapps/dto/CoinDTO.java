/**
 * 
 */
package com.aequalis.dapps.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anand
 *
 */
public class CoinDTO {

	private String name;
	private String symbol;
	private String amount;
	private String launchDate;
	private String expiryDate;
	private int payoutsPerYear;
	private int reservedForPayout;
	private String productAddress;
	private String firstPayoutDate;
	private String nextPayoutDate;
	private int nominalValue; 
	private int issueSize;
	private int payoutRate;
	private int status;
	
	List<BalanceDTO> users = new ArrayList<BalanceDTO>();
	
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
	 * @return the launchDate
	 */
	public String getLaunchDate() {
		return launchDate;
	}
	/**
	 * @param launchDate the launchDate to set
	 */
	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}
	/**
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return the payoutsPerYear
	 */
	public int getPayoutsPerYear() {
		return payoutsPerYear;
	}
	/**
	 * @param payoutsPerYear the payoutsPerYear to set
	 */
	public void setPayoutsPerYear(int payoutsPerYear) {
		this.payoutsPerYear = payoutsPerYear;
	}
	/**
	 * @return the productAddress
	 */
	public String getProductAddress() {
		return productAddress;
	}
	/**
	 * @param productAddress the productAddress to set
	 */
	public void setProductAddress(String productAddress) {
		this.productAddress = productAddress;
	}
	/**
	 * @return the firstPayoutDate
	 */
	public String getFirstPayoutDate() {
		return firstPayoutDate;
	}
	/**
	 * @param firstPayoutDate the firstPayoutDate to set
	 */
	public void setFirstPayoutDate(String firstPayoutDate) {
		this.firstPayoutDate = firstPayoutDate;
	}
	/**
	 * @return the nextPayoutDate
	 */
	public String getNextPayoutDate() {
		return nextPayoutDate;
	}
	/**
	 * @param nextPayoutDate the nextPayoutDate to set
	 */
	public void setNextPayoutDate(String nextPayoutDate) {
		this.nextPayoutDate = nextPayoutDate;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the nominalValue
	 */
	public int getNominalValue() {
		return nominalValue;
	}
	/**
	 * @param nominalValue the nominalValue to set
	 */
	public void setNominalValue(int nominalValue) {
		this.nominalValue = nominalValue;
	}
	/**
	 * @return the issueSize
	 */
	public int getIssueSize() {
		return issueSize;
	}
	/**
	 * @param issueSize the issueSize to set
	 */
	public void setIssueSize(int issueSize) {
		this.issueSize = issueSize;
	}
	/**
	 * @return the payoutRate
	 */
	public int getPayoutRate() {
		return payoutRate;
	}
	/**
	 * @param payoutRate the payoutRate to set
	 */
	public void setPayoutRate(int payoutRate) {
		this.payoutRate = payoutRate;
	}
	/**
	 * @return the users
	 */
	public List<BalanceDTO> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(List<BalanceDTO> users) {
		this.users = users;
	}
	/**
	 * @return the reservedForPayout
	 */
	public int getReservedForPayout() {
		return reservedForPayout;
	}
	/**
	 * @param reservedForPayout the reservedForPayout to set
	 */
	public void setReservedForPayout(int reservedForPayout) {
		this.reservedForPayout = reservedForPayout;
	}
	
}
