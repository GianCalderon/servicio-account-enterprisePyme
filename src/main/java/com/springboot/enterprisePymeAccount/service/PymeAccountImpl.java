package com.springboot.enterprisePymeAccount.service;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.enterprisePymeAccount.client.PymeClient;
import com.springboot.enterprisePymeAccount.document.PymeAccount;
import com.springboot.enterprisePymeAccount.dto.AccountDto;
import com.springboot.enterprisePymeAccount.dto.PymeDto;
import com.springboot.enterprisePymeAccount.repo.PymeAccountRepo;
import com.springboot.enterprisePymeAccount.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PymeAccountImpl implements PymeAccountInterface {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(PymeAccountImpl.class);
	
	
	
	@Autowired
	PymeAccountRepo repo;
	
	@Autowired
	UtilConvert convert;
	
	@Autowired
	PymeClient client;
	

	
	@Override
	public Flux<PymeAccount> findAll() {
	
		return repo.findAll();
	}

	@Override
	public Mono<PymeAccount> findById(String id) {
		
		return repo.findById(id);
	}

	@Override
	public Mono<PymeAccount> save(PymeAccount PymeAccount) {
		
		PymeAccount.setCreateDate(new Date());
		PymeAccount.setUpdateDate(new Date());
		PymeAccount.setNameAccount("Cuenta-Corriente");
		PymeAccount.setIdOperation(new ArrayList<String>());
		
		return repo.save(PymeAccount);
	}

	@Override
	public Mono<PymeAccount> update(PymeAccount PymeAccount, String id) {
		
		return repo.findById(id).flatMap(s -> {

			s.setNameAccount(PymeAccount.getNameAccount());
			s.setNumberAccount(PymeAccount.getNumberAccount());
			s.setBalance(PymeAccount.getBalance());
			s.setState(PymeAccount.getState());
			s.setTea(PymeAccount.getTea());
			s.setUpdateDate(PymeAccount.getUpdateDate());
			s.setCreateDate(PymeAccount.getCreateDate());
			s.setIdOperation(PymeAccount.getIdOperation());
			
			
			return repo.save(s);
			});
	}
	

	@Override
	public Mono<Void> delete(PymeAccount PymeAccount) {
		
		return repo.delete(PymeAccount);
	}

	
	@Override
	public Mono<PymeAccount> findByNumAccount(String numAccount) {
		
		return repo.findByNumberAccount(numAccount);
	}
	

//	@Override
//	public Mono<PersonalDto> saveHeadline(AccountDto accountDto) {
//	
//     return client.extractAccounts(accountDto.getNumDoc()).collectList().flatMap(cuentas -> {
//			
//			int cont = 0;
//
//		     for (int i = 0; i < cuentas.size(); i++) {
//
//					AccountClient obj = cuentas.get(i);
//
//					LOGGER.info("PRUEBA 3 --->" + accountDto.toString());
//
//				    if (obj.getNumberAccount().substring(0, 6).equals(CodAccount.COD_CURRENT_ACCOUNT)) cont++;
//
//				}
//		     
//				if (cont == 0) {
//
//					return repo.save(convert.convertAccountEnt(accountDto)).flatMap(cuenta -> {
//
//						return client.findByNumDoc(accountDto.getNumDoc()).flatMap(titular -> {
//
//							LOGGER.info("Flujo Inicial ---->: " + titular.toString());
//
//							titular.setIdAccount(cuenta.getId());
//							titular.setNameAccount(cuenta.getNameAccount());
//							titular.setNumberAccount(cuenta.getNumberAccount());
//
//							LOGGER.info("Flujo Final ----->: " + titular.toString());
//
//							return client.update(titular, accountDto.getNumDoc()).flatMap(p->{
//								
//								p.setIdAccount(cuenta.getId());
//								return Mono.just(p);
//							});
//
//						});
//
//					});
//
//				} else {
//
//					return Mono.empty();
//				}
//
//			});
//	}
//
//	@Override
//	public Mono<PymeAccountPerDto> saveHeadlines(PymeAccountPerDto PymeAccountPerDto) {
//		
//		return save(convert.convertPymeAccountPer(PymeAccountPerDto)).flatMap(cuenta -> {
//
//			PymeAccountPerDto.getHeadlines().forEach(titular -> {
//
//				titular.setIdAccount(cuenta.getId());
//				titular.setNameAccount(cuenta.getNameAccount());
//				titular.setNumberAccount(cuenta.getNumberAccount());
//
//				client.save(titular);
//
//			});
//
//			return Mono.just(PymeAccountPerDto);
//		});
//	}
//	
//	
//	
//	
	@Override
	public Mono<PymeAccount> savePyme(AccountDto accountDto) {


		LOGGER.info("Service 1---> :"+accountDto.toString());

					return client.findByNumDoc(accountDto.getNumDoc()).flatMap(enteprise -> {
						
						LOGGER.info("Service 2---> :"+enteprise.toString());
						
						return repo.save(convert.convertAccountPyme(accountDto)).flatMap(cuenta -> {

						LOGGER.info("Flujo Inicial ---->: " + cuenta.toString());

						enteprise.setIdAccount(cuenta.getId());
						enteprise.setNameAccount(cuenta.getNameAccount());
						enteprise.setNumberAccount(cuenta.getNumberAccount());

						LOGGER.info("Flujo Final ----->: " + enteprise.toString());

						 client.update(enteprise, accountDto.getNumDoc()).block();
					

						 return Mono.just(cuenta);
					});

				});

				} 
	


	
	
	

}
