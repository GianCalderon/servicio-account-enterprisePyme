package com.springboot.enterprisePymeAccount.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.enterprisePymeAccount.document.PymeAccount;
import com.springboot.enterprisePymeAccount.dto.AccountDto;
import com.springboot.enterprisePymeAccount.dto.PymeDto;
import com.springboot.enterprisePymeAccount.service.PymeAccountImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/pymeAccount")
public class PymeAccountController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(PymeAccountController.class);

	@Autowired
	PymeAccountImpl service;
	


	@GetMapping
	public Mono<ResponseEntity<Flux<PymeAccount>>> toList() {

		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<PymeAccount>> search(@PathVariable String id) {

		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@PostMapping
	public Mono<ResponseEntity<PymeAccount>> save(@RequestBody PymeAccount PymeAccount) {

		return service.save(PymeAccount)
				.map(s -> ResponseEntity.created(URI.create("/api/PymeAccount".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<PymeAccount>> update(@RequestBody PymeAccount PymeAccount,
			@PathVariable String id) {
		
		LOGGER.info("Controller ---> :"+PymeAccount.toString());

		return service.update(PymeAccount, id)
				.map(s -> ResponseEntity.created(URI.create("/api/PymeAccount".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

		return service.findById(id).flatMap(s -> {
			return service.delete(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}
	
	
	
	// OPERACIONES QUE EXPONEN SERVICIOS

	
	@PostMapping("/savePyme")
	public Mono<ResponseEntity<PymeAccount>> saveEnterprise(@RequestBody AccountDto accountDto) {

		LOGGER.info("Controller ---> :"+accountDto.toString());

		return service.savePyme(accountDto).map(s -> ResponseEntity.created(URI.create("/api/PymeAccount"))
				.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(new ResponseEntity<PymeAccount>(HttpStatus.CONFLICT));


	}
	

	
	@GetMapping("/cuenta/{numberAccount}")
	public Mono<ResponseEntity<PymeAccount>> searchByNumAccount(@PathVariable String numberAccount) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numberAccount);

		return service.findByNumAccount(numberAccount).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	
	

	
}
