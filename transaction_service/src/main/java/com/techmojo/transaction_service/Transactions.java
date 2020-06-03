package com.techmojo.transaction_service;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transactions {
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String mobileid;
	
	private Timestamp date_time;
	
	private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobileid() {
		return mobileid;
	}

	public void setMobileid(String mobile) {
		this.mobileid = mobile;
	}

	public Timestamp getDate_time() {
		return date_time;
	}

	public void setDate_time(Timestamp date_time) {
		this.date_time = date_time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
