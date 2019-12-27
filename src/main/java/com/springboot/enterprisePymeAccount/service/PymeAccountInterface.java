package com.springboot.enterprisePymeAccount.service;

import com.springboot.enterprisePymeAccount.document.PymeAccount;
import com.springboot.enterprisePymeAccount.dto.AccountDto;
import com.springboot.enterprisePymeAccount.dto.PymeDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PymeAccountInterface {

	
	public Flux<PymeAccount> findAll();
	public Mono<PymeAccount> findById(String id);
	public Mono<PymeAccount> save(PymeAccount PymeAccount);
	public Mono<PymeAccount> update(PymeAccount PymeAccount ,String id);
	public Mono<Void> delete(PymeAccount PymeAccount);
	
	public Mono<PymeAccount> findByNumAccount(String numAccount);
//	public Mono<PymeAccount> saveOperation(OperationDto operationDto);	
//	
//	public Mono<PersonalDto> saveHeadline(AccountDto accountDto);     
//	public Mono<PymeAccountPerDto> saveHeadlines (PymeAccountPerDto PymeAccountPerDto);
//	
	public Mono<PymeAccount> savePyme(AccountDto accountDto);
	

	

	
	
	
	
}
