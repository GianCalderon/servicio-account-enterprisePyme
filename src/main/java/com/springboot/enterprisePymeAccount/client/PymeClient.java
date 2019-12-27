package com.springboot.enterprisePymeAccount.client;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.enterprisePymeAccount.dto.AccountClient;
import com.springboot.enterprisePymeAccount.dto.PymeDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PymeClient {

private static final Logger LOGGER = LoggerFactory.getLogger(PymeClient.class);
	
     WebClient client = WebClient.create("http://localhost:8013/api/pyme");	

//	@Autowired
//	private WebClient clientEmp;
	
	public Flux<PymeDto> findAll() {
		
		return client.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(PymeDto.class));
	}


	public Mono<PymeDto> findById(String id) {
		
		LOGGER.info("Buscando con ID ---> "+id);
		
		return client.get()
				.uri("/{id}",Collections.singletonMap("id",id))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PymeDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

	
	public Mono<PymeDto> save(PymeDto PymeDto) {
		
		LOGGER.info("Listo para Guardar ---> "+PymeDto.toString());
		
		return client.post()
			   .accept(MediaType.APPLICATION_JSON)
			   .contentType(MediaType.APPLICATION_JSON)
		       .body(BodyInserters.fromValue(PymeDto))
			   .retrieve()
			   .bodyToMono(PymeDto.class);

	}

	public Mono<Void> delete(String id) {
		
		return client.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<PymeDto> update(PymeDto PymeDto, String numDoc) {
		
		LOGGER.info("Listo para Actualizar ---> "+PymeDto.toString());
		
		return client.put()
				   .uri("/{id}",Collections.singletonMap("id",numDoc))
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(PymeDto)
				   .retrieve()
				   .bodyToMono(PymeDto.class);
	}
	
	public Mono<PymeDto> findByNumDoc(String ruc) {

		return client.get()
				.uri("/doc/{ruc}",Collections.singletonMap("ruc",ruc))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PymeDto.class);
		        
	}
	
	
	public Flux<AccountClient> extractAccounts(String ruc) {

		return client.get()
				.uri("/valid/{ruc}",Collections.singletonMap("ruc",ruc))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(AccountClient.class);
			
	}
}
