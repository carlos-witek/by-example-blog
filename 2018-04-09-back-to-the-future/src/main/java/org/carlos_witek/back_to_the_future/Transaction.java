package org.carlos_witek.back_to_the_future;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.common.base.MoreObjects;

@Entity
public class Transaction {

	private long id;
	private String account;
	private BigDecimal amount;

	public Transaction() {
		super();
	}

	public Transaction( final long id, final String account, final BigDecimal amount ) {
		super();
		this.id = id;
		this.account = account;
		this.amount = amount;
	}

	@Id
	public long getId() {
		return id;
	}

	public void setId( final long id ) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount( final String account ) {
		this.account = account;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount( final BigDecimal amount ) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper( this )
				.add( "id", id )
				.add( "account", account )
				.add( "amount", amount )
				.toString();
	}

}
