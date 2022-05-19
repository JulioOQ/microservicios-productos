package com.jvoq.microservicios.productos.app.services;

import com.jvoq.microservicios.productos.app.dtos.ProductDto;
import com.jvoq.microservicios.productos.app.models.documents.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	public Flux<ProductDto> findAll();

	public Mono<ProductDto> findById(String id);

	public Mono<ProductDto> save(ProductDto productDto);

	public Mono<ProductDto> update(ProductDto productDto, String id);

	public Mono<Void> delete(Product product);
}
