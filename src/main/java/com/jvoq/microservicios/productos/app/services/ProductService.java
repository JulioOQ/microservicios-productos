package com.jvoq.microservicios.productos.app.services;


import com.jvoq.microservicios.productos.app.models.documents.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	public Flux<Product> findAll();

	public Mono<Product> findById(String id);

	public Mono<Product> save(Product product);

	public Mono<Void> delete(Product product);
	
	

}
