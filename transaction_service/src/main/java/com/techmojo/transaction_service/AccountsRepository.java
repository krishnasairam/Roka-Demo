package com.techmojo.transaction_service;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepository extends CrudRepository<UserAccounts, Long>{

}


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

