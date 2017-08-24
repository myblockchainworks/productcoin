/**
 * 
 */
package com.aequalis.dapps.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author anand
 *
 */
@Entity
@Table(name="transactions")
public class Transaction  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionid;

	@Column(name = "fromaddress")
	private String fromaddress;
	
	@Column(name = "toaddress")
	private String toaddress;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "transactionaddress")
	private String transactionaddress;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "transactiondate")
	@Temporal(TemporalType.TIMESTAMP)
    private Date transactiondate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coinid")
	private Coin coin;

	/**
	 * @return the transactionid
	 */
	public Long getTransactionid() {
		return transactionid;
	}

	/**
	 * @param transactionid the transactionid to set
	 */
	public void setTransactionid(Long transactionid) {
		this.transactionid = transactionid;
	}

	/**
	 * @return the fromaddress
	 */
	public String getFromaddress() {
		return fromaddress;
	}

	/**
	 * @param fromaddress the fromaddress to set
	 */
	public void setFromaddress(String fromaddress) {
		this.fromaddress = fromaddress;
	}

	/**
	 * @return the toaddress
	 */
	public String getToaddress() {
		return toaddress;
	}

	/**
	 * @param toaddress the toaddress to set
	 */
	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}

	/**
	 * @return the transactionaddress
	 */
	public String getTransactionaddress() {
		return transactionaddress;
	}

	/**
	 * @param transactionaddress the transactionaddress to set
	 */
	public void setTransactionaddress(String transactionaddress) {
		this.transactionaddress = transactionaddress;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the transactiondate
	 */
	public Date getTransactiondate() {
		return transactiondate;
	}

	/**
	 * @param transactiondate the transactiondate to set
	 */
	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}

	/**
	 * @return the coin
	 */
	public Coin getCoin() {
		return coin;
	}

	/**
	 * @param coin the coin to set
	 */
	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [transactionid=" + transactionid + ", fromaddress=" + fromaddress + ", toaddress="
				+ toaddress + ", amount=" + amount + ", transactionaddress=" + transactionaddress + ", description="
				+ description + ", transactiondate=" + transactiondate + ", coin=" + coin + "]";
	}

}
