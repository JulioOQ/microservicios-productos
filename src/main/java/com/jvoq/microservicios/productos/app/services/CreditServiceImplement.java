package com.jvoq.microservicios.productos.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvoq.microservicios.productos.app.models.documents.Credit;
import com.jvoq.microservicios.productos.app.models.repository.CreditRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImplement implements CreditService {

	@Autowired
	CreditRepository creditRepository;

	@Override
	public Flux<Credit> findAll() {
		return creditRepository.findAll();
	}

	@Override
	public Mono<Credit> findById(String id) {
		return creditRepository.findById(id);
	}

	@Override
	public Mono<Credit> save(Credit credit) {
		return creditRepository.save(credit);
	}

	@Override
	public Mono<Void> delete(Credit credit) {
		return creditRepository.delete(credit);
	}

}
