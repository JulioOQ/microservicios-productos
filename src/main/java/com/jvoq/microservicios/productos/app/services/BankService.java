package com.jvoq.microservicios.productos.app.services;



import com.jvoq.microservicios.productos.app.models.documents.Bank;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankService {

	public Flux<Bank> findAll();

	public Mono<Bank> findById(String id);

	public Mono<Bank> save(Bank bank);

	public Mono<Void> delete(Bank bank);
}
