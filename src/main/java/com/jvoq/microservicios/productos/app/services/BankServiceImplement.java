package com.jvoq.microservicios.productos.app.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvoq.microservicios.productos.app.dtos.BankDto;
import com.jvoq.microservicios.productos.app.models.documents.Bank;
import com.jvoq.microservicios.productos.app.models.repository.BankRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankServiceImplement implements BankService {

	@Autowired
	BankRepository bankRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Flux<BankDto> findAll() {
		return bankRepository.findAll().map(this::convertEntityToDto);
	}

	@Override
	public Mono<BankDto> findById(String id) {
		return bankRepository.findById(id).map(this::convertEntityToDto);
	}

	@Override
	public Mono<BankDto> save(BankDto bankDto) {
		Bank bank = this.convertDtoToEntity(bankDto);
		return bankRepository.save(bank).map(this::convertEntityToDto);
	}

	@Override
	public Mono<BankDto> update(BankDto bankDto, String id) {
		return this.findById(id).flatMap(b -> {
			b.setNombre(bankDto.getNombre());
			b.setCorreo(bankDto.getCorreo());
			b.setDireccion(bankDto.getDireccion());
			b.setTelefono(bankDto.getTelefono());
			b.setRuc(bankDto.getRuc());
			return this.save(b);
		});
	}

	@Override
	public Mono<Void> delete(Bank bank) {
		return bankRepository.delete(bank);
	}

	private BankDto convertEntityToDto(Bank bank) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return mapper.map(bank, BankDto.class);
	}

	private Bank convertDtoToEntity(BankDto bankDto) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return mapper.map(bankDto, Bank.class);
	}
}
