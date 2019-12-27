package com.springboot.enterprisePymeAccount.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.enterprisePymeAccount.document.PymeAccount;

import reactor.core.publisher.Mono;

public interface PymeAccountRepo extends ReactiveMongoRepository<PymeAccount, String> {

	public Mono<PymeAccount> findByNumberAccount(String numberAccount);
	
	
}
