package com.techmojo.transaction_service;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAccounts {

	@Id
	private Long mobile;
	
	private Integer account;
	
	private Integer wallet;
	
	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	
	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Integer getWallet() {
		return wallet;
	}

	public void setWallet(Integer wallet) {
		this.wallet = wallet;
	}
	
	
	
}
