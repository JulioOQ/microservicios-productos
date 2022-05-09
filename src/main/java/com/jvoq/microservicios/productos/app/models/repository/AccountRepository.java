package com.jvoq.microservicios.productos.app.models.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.jvoq.microservicios.productos.app.models.documents.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
	
	
	Flux<Account> findAccountByIdCliente(String idClient);
	
	Mono<Boolean> findAccountByIdClienteAndIdProducto(String idClient,String idProduct);
}
