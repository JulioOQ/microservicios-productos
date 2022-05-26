package com.jvoq.microservicios.productos.app.models.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.jvoq.microservicios.productos.app.models.documents.Product;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
	public Flux<Product> findByFechaCreacionBetween(LocalDate i, LocalDate f);
}
