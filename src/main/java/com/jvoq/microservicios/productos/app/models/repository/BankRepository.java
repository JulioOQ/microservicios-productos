package com.jvoq.microservicios.productos.app.models.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.jvoq.microservicios.productos.app.models.documents.Bank;

@Repository
public interface BankRepository extends ReactiveMongoRepository<Bank, String> {

}
