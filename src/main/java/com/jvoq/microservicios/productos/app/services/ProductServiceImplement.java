package com.jvoq.microservicios.productos.app.services;

import java.time.LocalDate;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvoq.microservicios.productos.app.dtos.ProductDto;
import com.jvoq.microservicios.productos.app.models.documents.Product;
import com.jvoq.microservicios.productos.app.models.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImplement implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Flux<ProductDto> findAll() {
		return productRepository.findAll().map(this::convertEntityToDto);
	}

	@Override
	public Mono<ProductDto> findById(String id) {
		return productRepository.findById(id).map(this::convertEntityToDto);
	}

	@Override
	public Mono<ProductDto> save(ProductDto productDto) {
		productDto.setFechaCreacion(new Date());
		Product product = this.convertDtoToEntity(productDto);
		return productRepository.save(product).map(this::convertEntityToDto);
	}

	@Override
	public Mono<ProductDto> update(ProductDto productDto, String id) {
		return this.findById(id).flatMap(p -> {
			p.setTipoProducto(productDto.getTipoProducto());
			p.setNombre(productDto.getNombre());
			p.setDescripcion(productDto.getDescripcion());
			p.setJuridico(productDto.getJuridico());
			p.setNatural(productDto.getNatural());
			p.setMaxDeposito(productDto.getMaxDeposito());
			p.setMaxRetiro(productDto.getMaxRetiro());
			p.setComDeposito(productDto.getComDeposito());
			p.setComRetiro(productDto.getComRetiro());
			p.setComMantenimiento(productDto.getComMantenimiento());
			p.setFechaCreacion(p.getFechaCreacion());
			return this.save(p);
		});
	}

	@Override
	public Mono<Void> delete(Product product) {
		return productRepository.delete(product);
	}

	@Override
	public Flux<Product> findProductsByFechaBetween(String i, String f) {
		LocalDate start = LocalDate.parse(i);
		LocalDate end = LocalDate.parse(f);
		return productRepository.findByFechaCreacionBetween(start, end);
	}

	private ProductDto convertEntityToDto(Product product) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return mapper.map(product, ProductDto.class);
	}

	private Product convertDtoToEntity(ProductDto productDto) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return mapper.map(productDto, Product.class);
	}
}
