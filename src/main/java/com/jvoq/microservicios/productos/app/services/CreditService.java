package com.jvoq.microservicios.productos.app.services;



import com.jvoq.microservicios.productos.app.models.documents.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {
	
	public Flux<Credit> findAll();

	public Mono<Credit> findById(String id);

	public Mono<Credit> save(Credit credit);

	public Mono<Void> delete(Credit credit);
}
