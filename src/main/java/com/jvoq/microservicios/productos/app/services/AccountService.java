package com.jvoq.microservicios.productos.app.services;

import com.jvoq.microservicios.productos.app.models.documents.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

	public Flux<Account> findAll();

	public Mono<Account> findById(String id);

	public Mono<Account> save(Account account);

	public Mono<Void> delete(Account account);
	
	public Flux<Account> findAccoutsByIdClient(String idClient);
	
	public Mono<Boolean> findProductByIdClientAndIdProduct(String idClient,String idProduct);
	

	
	
}
