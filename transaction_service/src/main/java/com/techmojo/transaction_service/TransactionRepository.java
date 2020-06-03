package com.techmojo.transaction_service;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transactions, Long> {

}
