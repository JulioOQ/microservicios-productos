package com.jvoq.microservicios.productos.app.controllers;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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

import com.jvoq.microservicios.productos.app.dtos.ProductDto;
import com.jvoq.microservicios.productos.app.models.documents.Product;
import com.jvoq.microservicios.productos.app.services.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RefreshScope
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ModelMapper mapper;

	@Value("${mensaje.verificacion:default}")
	private String mensaje;

	@GetMapping("verificar")
	public String viewDiscounts() {
		return "Mensaje -> " + mensaje;
	}

	@GetMapping
	public Mono<ResponseEntity<Flux<ProductDto>>> getAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(productService.findAll()));
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<ProductDto>> getById(@PathVariable String id) {
		return productService.findById(id).map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Mono<ResponseEntity<ProductDto>> create(@RequestBody ProductDto productDto) {
		return productService.save(productDto)
				.map(p -> ResponseEntity.created(URI.create("/products".concat(p.getIdProducto())))
						.contentType(MediaType.APPLICATION_JSON).body(p));
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<ProductDto>> update(@RequestBody ProductDto productDto, @PathVariable String id) {
		return productService.update(productDto, id)
				.map(p -> ResponseEntity.created(URI.create("/products".concat(p.getIdProducto())))
						.contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> drop(@PathVariable String id) {
		return productService.findById(id).flatMap(p -> {
			Product product = mapper.map(p, Product.class);

			return productService.delete(product).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
