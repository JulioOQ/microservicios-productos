package com.jvoq.microservicios.productos.app.services;

import com.jvoq.microservicios.productos.app.dtos.BankDto;
import com.jvoq.microservicios.productos.app.models.documents.Bank;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankService {

	public Flux<BankDto> findAll();

	public Mono<BankDto> findById(String id);

	public Mono<BankDto> save(BankDto bankDto);
	
	public Mono<BankDto> update(BankDto bankDto, String id);

	public Mono<Void> delete(Bank bank);
}
