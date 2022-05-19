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

import com.jvoq.microservicios.productos.app.dtos.BankDto;
import com.jvoq.microservicios.productos.app.models.documents.Bank;
import com.jvoq.microservicios.productos.app.services.BankService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RefreshScope
@RestController
@RequestMapping("/banks")
public class BankController {

	@Autowired
	BankService bankService;

	@Autowired
	private ModelMapper mapper;

	@Value("${mensaje.verificacion:default}")
	private String mensaje;

	@GetMapping("verificar")
	public String viewDiscounts() {
		return "Mensaje -> " + mensaje;
	}

	@GetMapping
	public Mono<ResponseEntity<Flux<BankDto>>> getAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(bankService.findAll()));
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<BankDto>> getById(@PathVariable String id) {
		return bankService.findById(id).map(b -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(b))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Mono<ResponseEntity<BankDto>> create(@RequestBody BankDto bankDto) {
		return bankService.save(bankDto).map(b -> ResponseEntity.created(URI.create("/banks".concat(b.getIdBanco())))
				.contentType(MediaType.APPLICATION_JSON).body(b));
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<BankDto>> update(@RequestBody BankDto bankDto, @PathVariable String id) {
		return bankService.update(bankDto, id)
				.map(b -> ResponseEntity.created(URI.create("/banks".concat(b.getIdBanco())))
						.contentType(MediaType.APPLICATION_JSON).body(b))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> drop(@PathVariable String id) {
		return bankService.findById(id).flatMap(b -> {
			Bank bank = mapper.map(b, Bank.class);

			return bankService.delete(bank).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
