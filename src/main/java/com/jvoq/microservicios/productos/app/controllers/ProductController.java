package com.jvoq.microservicios.productos.app.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvoq.microservicios.productos.app.models.documents.Product;
import com.jvoq.microservicios.productos.app.services.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public Mono<ResponseEntity<Flux<Product>>> getAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(productService.findAll()));
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<Product>> getById(@PathVariable String id) {
		return productService.findById(id).map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	

	@PostMapping
	public Mono<ResponseEntity<Product>> create(@RequestBody Product product) {
		return productService.save(product)
				.map(p -> ResponseEntity.created(URI.create("/products".concat(p.getIdProducto())))
						.contentType(MediaType.APPLICATION_JSON).body(p));
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<Product>> update(@RequestBody Product product, @PathVariable String id) {
		return productService.findById(id).flatMap(p -> {			
			p.setTipoProducto(product.getTipoProducto());
			p.setNombre(product.getNombre());
			p.setDescripcion(product.getDescripcion());
			p.setJuridico(product.getJuridico());
			p.setNatural(product.getNatural());
			

			return productService.save(p);
		}).map(p -> ResponseEntity.created(URI.create("/products".concat(p.getIdProducto())))
				.contentType(MediaType.APPLICATION_JSON).body(p)).defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> drop(@PathVariable String id) {
		return productService.findById(id).flatMap(p -> {
			return productService.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
}
