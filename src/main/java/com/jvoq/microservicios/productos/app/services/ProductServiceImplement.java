package com.jvoq.microservicios.productos.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvoq.microservicios.productos.app.models.documents.Product;
import com.jvoq.microservicios.productos.app.models.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImplement implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Flux<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Mono<Product> findById(String id) {
		return productRepository.findById(id);
	}

	@Override
	public Mono<Product> save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Mono<Void> delete(Product product) {
		return productRepository.delete(product);
	}

}
